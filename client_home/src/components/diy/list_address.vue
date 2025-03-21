<template>
	<view class="list_address">
		<view class="address_card" v-for="(o,i) in list" :key="i">
			<view class="address_info">
				<view class="address_top">
					<view class="address_content">
						<view class="name">
							<text>{{o[vm.name]}}</text>
						</view>
						<view class="tag">
							<text class="tag_default"
								:class="{ 'show': o[vm.default],'hide': !o[vm.default] }">默认</text>
						</view>
						<view class="phone">
							<text>{{o[vm.phone]}}</text>
						</view>
					</view>
					<view class="btn_show">
						<button @click="more_show(o,i)">
							<uni-icons type="more-filled"></uni-icons>
						</button>
					</view>
				</view>
				<view class="address_bottom">
					<text>
						{{o[vm.address]}}
					</text>
				</view>
			</view>
			<view class="bar_btn" v-if="o[vm.display_btn]">
				<button @click="choose_default(o[vm.address_id],o[vm.user_id],i)"
					v-if="$check_action('/address/list','set')">
					<text>设为默认</text>
				</button>
				<button @click="del_address(o[vm.address_id],i)" v-if="$check_action('/address/list','del')">
					<text>删除</text>
				</button>
				<button @click="$nav('/pages/user/address_edit?address_id='+o[vm.address_id])"
					v-if="$check_action('/address/list','set')">
					<text>编辑</text>
				</button>
			</view>
		</view>
		<view class="btn_address" @click="$nav('/pages/user/address_edit')" v-if="$check_action('/address/list','add')">+ 新建收货地址
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			list: {
				type: Array,
				default: function() {
					return [{
						name: "",
						phone: "",
						address: "",
						user_id: 0,
						address_id: 0,
						default: 0,
						display_btn: "display_btn"
					}];
				}
			},
			vm: {
				type: Object,
				default: function() {
					return {
						name: "name",
						phone: "phone",
						address: "address",
						user_id: "user_id",
						address_id: "address_id",
						default: "default",
						display_btn: "display_btn"
					}
				}
			}
		},
		data() {
			return {}
		},
		methods: {
			// 选择默认地址
			async choose_default(address_id, user_id, i) {
				var _this = this;
				console.log(address_id, user_id);
				var res = await this.$post(`~/api/address/set?user_id=${user_id}`, {
					default: 0
				})
				if (res.result) {
					this.list.map(o => o.default = 0);
					this.$post(`~/api/address/set?user_id=${user_id}&address_id=${address_id}`, {
						default: 1
					}, (json) => {
						if (json.result) {
							_this.$toast("修改默认地址成功!");
							this.list[i].default = 1;
						} else if (json.error) {
							_this.$toast(json.error.message);
							console.log(json.error);
						}
					})
				} else if (res.error) {
					_this.$toast(res.error.message);
					console.log(res.error);
				}
			},

			// 删除
			del_address(address_id, index) {
				this.$get("~/api/address/del", {
					address_id
				}, (res) => {
					this.list.splice(index, 1);
				})
			},

			// 更多展示
			more_show(o,i) {
				o.display_btn = !o.display_btn
				this.$set(this.list,i,o);
			}
		}
	}
</script>

<style>
	.list_address {}

	.list_address .address_card {
		display: flex;
		flex-direction: column;
		padding: 0.5rem 1rem;
	}

	.list_address .address_info {
		display: flex;
		flex-direction: column;
	}

	.list_address .address_top {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
	}

	.list_address .address_bottom {
		height: 2.5rem;
	}

	.list_address .address_content {
		flex: 1;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		flex-wrap: wrap;
	}

	.list_address .hide {
		display: none;
	}

	.list_address .show {
		display: block;
	}

	.list_address .tag_default {
		font-weight: 600;
		font-size: 0.75rem;
		margin: 1rem;
		padding: 0.25rem;
		color: #4CD964;
		border: #4CD964 1px solid;
		border-radius: 5px;
	}

	.list_address .btn_show {
		padding-left: 1rem;
		width: 20%;
		min-width: 5rem;
	}

	.list_address .btn_show {
		color: #15BAF8;
		border-radius: 5px;
	}

	.list_address .bar_btn {
		margin-top: .5rem;
		display: flex;
		justify-content: flex-start;
		flex-wrap: wrap;
	}

	.list_address .bar_btn>button {
		flex: 1;
		min-width: 7rem;
		border: #dcdcdc 1px solid;
	}

	.btn_address:hover {
		cursor: pointer;
	}

	.btn_address {
		color: #fff;
		font-size: 0.9rem;
		background-color: #FA4E28;
		height: 2.2rem;
		line-height: 2.2rem;
		border-radius: 1rem;
		margin: 0 auto;
		text-align: center;
	}
</style>

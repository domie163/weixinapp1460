<template>
	<view class="form_address">
		<view class="header">
			{{ form.address_id ? "修改" : "添加" }}收货地址
		</view>
		<view class="name">
			<text>收货人</text>
			<uni-easyinput type="text" v-model="form[vm.name]" />
		</view>
		<view class="phone">
			<text>手机号</text>
			<uni-easyinput type="text" v-model="form[vm.phone]" />
		</view>
		<view class="address">
			<text>详细地址</text>
			<textarea type="text" style="height: 3rem;" v-model="form[vm.address]" />
			</view>
		<button type="primary" class="btn_save" @click="save_address()">保存</button>
	</view>
</template>

<script>
	import mixin from "@/libs/mixins/component.js";
	export default {
		mixins: [mixin],
		props: {
			form: {
				type: Object,
				default: {}
			},
			vm: {
				type: Object,
				default: function() {
					return {
						address: "address",
						address_id: "address_id",
						name: "name",
						phone: "phone"
					}
				}
			}
		},
		methods: {
			// 保存地址
			save_address() {
				var {address,name,phone,address_id,user_id} = this.form
				user_id = this.user.user_id
				if(address_id){
					this.$post(`~/api/address/set?address_id=${address_id}`,{address,name,phone,user_id},(res)=>{
						console.log(res);
						uni.navigateBack({
							delta: 1
						});
					})
				}else{
					this.$post(`~/api/address/add?`,{address,name,phone,user_id},(res)=>{
						console.log(res);
						uni.navigateBack({
							delta: 1
						});
					})
				}
			}
		}
	}
</script>

<style>
	.form_address {
		padding: 0 0.8rem;
	}

	.header {
		justify-content: center;
		text-align: center;
	}
	.form_address input {
		display: inline-block;
	}
	.form_address>view {
		display: flex;
		align-items: center;
		padding: 0.5rem 0;
		border-bottom: 1px solid #f4f4f4;
		font-size: 0.9rem;
	}

	.form_address>view>text {
		width: 4.5rem;
	}

	.form_address>view>input {
		font-size: 0.9rem;
	}
	.form_address>view>textarea {
		font-size: 0.9rem;
	}
	.address{
		align-items: baseline;
	}
	.form_address .address>text{
		width: 5.5rem;
		margin-right: 0.5rem;
	}
	
	.btn_save {
		margin-top: 0.5rem;
	}
</style>

<template>
	<view class="cart_block">
		<view class="list_cart">
			<view class="item_cart" v-for="(o,i) in list" :key="i">
				<!-- 多选按钮 -->
				<view class="choose_btn" :class="{active:o[vm.selected]}" @click="select_func(i)">
					<view class="btn_circle">
						<uni-icons style="color: #fff;font-size: 0.3rem;" v-if="o[vm.selected]" type="checkmarkempty">
						</uni-icons>
					</view>
				</view>
				<view class="cart_body">
					<image style="width: 4.5rem;height: 4.5rem;" :src="$fullImgUrl(o[vm.img])" mode="scaleToFill">
					</image>

					<view class="info">
						<view class="top_info">
							<view class="title">{{o[vm.title]}} </view>
							<view class="btn_del" @click="del_cart(o,i)" v-if="$check_action('/cart/list','del')">
								<uni-icons type="trash" color="var(--color_grey)"></uni-icons>
							</view>
						</view>
						<view class="bottom_price">
							<view class="price">
								<text>￥{{ o[vm.price] }}</text>
								<text class="price_ago">￥{{ o[vm.price_ago] }}</text>

							</view>
							<template v-if="$check_action('/cart/details','set')">
							<uni-number-box :value="Number(o['num'])" :min="1"  @change="change_num(arguments,i,o)" />
							</template>
						</view>
					</view>
				</view>
			</view>
		</view>

		<!-- 结算栏 -->
		<view class="bar_settle" v-if="$check_action('/order/list','add')">
			<view class="choose_btn" :class="{active:select_all_flag}">
				<view class="btn_circle" @click="select_all()">
					<uni-icons style="color: #fff;font-size: 0.3rem;" v-if="select_all_flag" type="checkmarkempty">
					</uni-icons>
				</view>
				<text>全选</text>
			</view>
			<view class="right_settle">
				<view class="sum_block">
					<text>共计：</text>
					<text class="sum">￥{{sum_price()}}</text>
				</view>
				<view class="settle">
					<button class="btn_settle" @click="settle_all()" size="mini">购买</button>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			list: {
				type: Array,
				default: function() {
					return []
				}
			},
			vm: {
				type: Object,
				default: function() {
					return {
						cart_id: "cart_id",
						title: "title",
						img: "img",
						user_id: "user_id",
						state: "state",
						price: "price",
						price_ago: "price_ago",
						price_count: "price_count",
						num: "num",
						goods_id: "goods_id",
						description: "description",
						selected: "selected"
					}
				}
			}
		},
		data() {
			return {
				select_all_flag:false
			}
		},
		computed: {

		},
		mounted(){
			this.$nextTick(()=>{
				console.log(this.list,5566)
			})

		},
		methods: {
			sum_price() {
				var sum_price = 0
				this.list.map(o => {
					if (o.selected) {
						sum_price += o.price_count
					}
				})
				return sum_price.toFixed(2)
			},
			selectedAll() {
				return this.list.every(o => o.selected)
			},
			change_num(arr, idx, obj) {
				var num = Number(arr[0])
        obj.num = num
				this.$post(`~/api/cart/set?cart_id=${obj.cart_id}`, {
					num,
					"price":obj.price,
					"price_count":obj.price * num
				}, (res) => {
					this.list.map(o => {
						if (o.cart_id === obj.cart_id) {
							o.price_count = num * obj.price
						}
					})
				})
			},
			del_cart(o, i) {
				this.$get(`~/api/cart/del?cart_id=${o.cart_id}`, {}, (res) => {
					this.list.splice(i, 1)
				})
			},
			// 选择
			select_func(i) {
				this.list[i].selected = !this.list[i].selected
				this.$set(this.list,i,this.list[i]);
				this.select_all_flag =this.selectedAll();
			},
			// 全选
			select_all() {
				if (this.selectedAll()) {
					this.list.map(o => {
						o.selected = false
					})
					this.select_all_flag = false;
				} else {
					this.list.map(o => {
						o.selected = true
					})
					this.select_all_flag = true;

				}
			},
			// 根据当前时间和随机数生成流水号
			order_code() {
				var orderCode = '';
				for (var i = 0; i < 6; i++) //6位随机数，用以加在时间戳后面。
				{
					orderCode += Math.floor(Math.random() * 10);
				}
				orderCode = new Date().getTime() + orderCode; //时间戳，用来生成订单号。
				return orderCode;
			},
			// 单个结算
			async settle_one(obj, index, order_number) {
				// 获取被选中的商品
				var {
					title,
					img,
					price,
					price_ago,
					price_count,
					num,
					type,
					goods_id,
					description,
					user_id,
					cart_id
				} = obj


				// 获取联系方式
				var address_promise = new Promise((resolve, reject) => {
					this.$get('~/api/address/get_obj?', {
						user_id,
						default: 1
					}, res => {
						if (res.result && res.result.obj) {
							resolve(res.result.obj);
						} else {
							this.$toast("地址未添加")
							return
						}
					})
				})
				var {
					name: contact_name,
					phone: contact_phone,
					address: contact_address,
					postcode: postal_code
				} = await address_promise

				// 请求参数
				var body = {
					order_number,
					title,
					img,
					price,
					price_ago,
					price_count,
					num,
					type,
					goods_id,
					description,
					contact_name,
					contact_phone,
					contact_address,
					postal_code,
					user_id
				}
				// 发送添加订单请求
				await new Promise((resolve, reject) => {
					this.$post('~/api/order/add?', body, (res) => {
						if (res.result) {
							this.$toast("加入订单成功");
							resolve()
						}
					})
				})
				// 删除购物车
				await new Promise((resolve, reject) => {
					this.$get('~/api/cart/del?', {
						cart_id
					}, (res) => {
						if (res.result) {
							this.list.splice(index, 1)
							resolve()
						}
					})
				})
			},
			// 选中的结算
			async settle_all() {
				// 查询库存
				let flag = false;
				for (let i=0;i<this.list.length;i++){
					if (this.list[i].selected) {
						let goods_json = await this.$get("~/api/goods/get_obj?", {"goods_id": this.list[i].goods_id});
						if (goods_json.result && goods_json.result.obj) {
							if (this.list[i].num>goods_json.result.obj.inventory){
								flag = true
								this.$toast("库存不足");
								break;
							}
						}
					}
				}
				if (flag){
					return;
				}
				// 生成订单号
				var order_number = this.order_code()
				// 选出选中的物品
				var list_settle = this.list.map(async (o, i) => {
					if (o.selected) {
						await this.settle_one(o, i, order_number)
						this.$nav("/pages/order/details?order_number=" + order_number)
					}
				})
			},
			// 判断是否在微信小程序
			is_wx_env() {
				// 判断是否在微信小程序中打开
				var ua = window.navigator.userAgent.toLowerCase();
				let that = this;
				var bool = false
				if (ua.match(/MicroMessenger/i) == 'micromessenger') { //判断是否是微信环境
					//微信环境下
				}
				return bool
			}
		}
	}
</script>

<style lang="scss" scoped>
	.list_cart {
		background-color: #fff;
		border-radius: 1rem;
		padding-bottom: 3rem;
	}

	.list_cart .item_cart {
		display: flex;
		justify-content: flex-start;
		align-items: center;
		padding: 0.6rem;
	}

	.list_cart .item_cart+.item_cart {
		border-top: 1px solid #eee;
	}

	.list_cart .choose_btn {
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.list_cart .choose_btn .circle {
		width: 1rem;
		height: 1rem;
		border-radius: 50%;
	}

	.list_cart .choose_btn .btn_circle {
		display: inline-block;
		width: 0.9rem;
		height: 0.9rem;
		margin-right: 0.5rem;
		border-radius: 50%;
		border: 1px solid var(--color_grey);
	}

	.list_cart .choose_btn.active {
		color: var(--color_primary);
	}

	.list_cart .choose_btn.active .btn_circle {
		background-color: var(--color_primary);
		border: 0;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.list_cart .cart_body {
		display: flex;
		justify-content: space-between;
		align-items: stretch;
		flex: 1;
	}

	.list_cart .info {
		flex: 1;
		padding-left: 0.6rem;
		position: relative;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}
	.list_cart .top_info{
		display: flex;
	}
	.list_cart .title {
		flex: 1;
		font-size: var(font_mini);
		text-overflow: -o-ellipsis-lastline;
		overflow: hidden;
		text-overflow: ellipsis;
		display: -webkit-box;
		-webkit-line-clamp: 2;
		line-clamp: 2;
		-webkit-box-orient: vertical;
	}


	.list_cart .btn_del {
		width: 2rem;
		height: 2rem;
		line-height: 2rem;
		text-align: center;
	}

	.list_cart .bottom_price {
		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.list_cart .price {
		color: var(--color_red);
		font-weight: bold;
		font-size: 1rem;
		flex: 1;
	}

	.list_cart .price_ago {
		margin-left: .2em;
		text-decoration: line-through;
		font-size: 0.7rem;
		color: #999;
	}


	.list_cart .subtract,
	.add {
		border: 1px solid #eee;
		border-radius: 50%;
		color: #919191;
		background-color: #eee;
	}

	.list_cart .subtract text {
		position: relative;
		top: -1px;
	}

	.list_cart .handle_num .num {
		width: 1.4rem;
	}


	.bar_settle {
		//position: fixed;
		//left: 0;
		//right: 0;
		//bottom: 0;
		height: 3rem;
		z-index: 9999;
		background-color: #fff;
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 0 0.8rem;
		border-top: 1px solid #eee;
	}

	.bar_settle .choose_btn {
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.bar_settle .choose_btn .circle {
		width: 1rem;
		height: 1rem;
		border-radius: 50%;
	}

	.bar_settle .choose_btn .btn_circle {
		display: inline-block;
		width: 0.9rem;
		height: 0.9rem;
		margin-right: 0.5rem;
		border-radius: 50%;
		border: 1px solid var(--color_grey);
	}

	.bar_settle .choose_btn.active {
		color: var(--color_primary);
	}

	.bar_settle .choose_btn.active .btn_circle {
		background-color: var(--color_primary);
		border: 0;
		display: flex;
		justify-content: center;
		align-items: center;
	}


	.bar_settle .right_settle {
		display: flex;
		justify-content: space-between;
	}

	.bar_settle .sum_block {
		display: flex;
		justify-content: space-between;
		align-items: center;
		margin-right: 0.5rem;
	}

	.bar_settle .sum {
		color: var(--color_red);
	}

	.bar_settle .settle {
		display: flex;
		align-items: center;
	}

	.bar_settle .btn_settle {
		color: #fff;
		border-radius: 5rem;
		background-color: var(--color_primary);
	}

	.bar_settle .button-hover {
		opacity: 0.8;
	}
</style>

<template>
	<view class="page_goods" id="goods_details">

		<!-- 商品详情模块(开始) -->
		<view>
			<view class="container">
				<view>
					<view>
						<view class="" style="background-color: #fff;margin-bottom: 0.5rem;">
							<!-- 商品详情 -->
							<div_goods :obj="obj"></div_goods>
						</view>
					</view>
				</view>
			</view>
		</view>
		<!-- 商品详情模块(结束) -->

		<!-- 商品属性模块(开始) -->
		<view>
			<view class="container">
				<view>
					<view>
						<view class="">
							<!-- 商品属性标题 -->
							<bar_title title="商品属性" />
							<rich-text class="rich_text" :nodes="$setRichTextImage(obj.content)"></rich-text>
						</view>
					</view>
				</view>
				<view>
					<view>
						<view>
									<!--{loop tables obj idx}-->
							<!--{if obj.config.details_page == "前详情" && obj.config.cart_page !== "无" }-->
								<div_food_information v-if="obj.type == '食物信息'" :obj_goods="obj"></div_food_information>
							<!--{/if}-->
							<!--{/loop}-->
											</view>
					</view>
				</view>
			</view>
		</view>
		<!-- 商品属性模块(结束) -->

		<!-- 评论区模块(开始) -->
		<view>
			<view class="container">
				<view>
					<view>
						<view class="">
							<!-- 评论区标题 -->
							<bar_title :title="bar_title" v-if="$check_action('/comment/list','get')" :url="bar_url">
							</bar_title>
						</view>
					</view>
				</view>
				<view>
					<view>
						<view class="">
							<!-- 评论区列表 -->
							<list_comment style="background-color: #fff;" v-if="$check_action('/comment/list','get')"
								:list="list_comment" :obj="form_comment"></list_comment>
						</view>
					</view>
				</view>
				<view>
					<view>
						<view class="">
							<!-- 发表评论 -->
							<view class="pa" v-if="$check_action('/comment/list','add') && comment_flag">
								<navigator class="link"
									:url="'/pages/comment/edit?source_table=goods&source_field=goods_id&source_id=' + obj.goods_id">
									发表评论</navigator>
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>
		<!-- 评论区模块(结束) -->
		<!-- 购买栏模块(开始) -->
		<view class="buy_wrap">
			<view class="container">
				<view>
					<view>
						<view style="max-width: 50rem;margin: 0 auto;">
							<bar_goods_nav :isEllipse="true" :options="right_options" :buttonGroup="buttonGroup" :cart_action="!obj.source_table||$check_cart_page('/'+obj.source_table+'/details')"
								@click="onClick" @buttonClick="buttonClick" />
						</view>
					</view>
				</view>
			</view>
		</view>
		<!-- 购买栏模块(结束) -->
		<!-- 聊天对话框(开始) -->
		<view class="support_module" v-if="showChat">
			<view
					class="container"
					:class="{ small_css: isSmall, big_css: !isSmall }"
			>
				<view class="support_module_title">
          <span class="title_btn" @click="openSupportModal">{{
              !isSmall ? "x" : "□"
            }}</span>
				</view>
				<scroll-view
						:scroll-top="scrollTop"
						scroll-y="true"
						class="content support_content"
						@scroll="scroll"
				>
					<view v-for="(item, index) in chatList" :key="index">
						<view class="item item-center" v-if="item.sendTime">
							<span v-text="item.sendTime"></span>
						</view>
						<view
								class="item"
								:class="{ item_left: item.isAdmin, item_right: !item.isAdmin }"
						>
							<view class="avatar" v-if="item.isAdmin">
								<image src="../../static/img/system_icon.jpg" />
							</view>
							<view
									class="bubble"
									:class="{ 'bubble-right': !item.isAdmin }"
									v-text="item.content"
							></view>
							<view class="avatar" v-if="!item.isAdmin">
								<image src="../../static/img/mine.jpg" />
							</view>
						</view>
					</view>
				</scroll-view>
				<view class="input-area">
          <textarea
				  name="text"
				  style="height: 50px"
				  v-model="sendValue"
				  id="textarea"
				  placeholder="输入内容"
		  ></textarea>
				</view>
				<view class="button-area">
					<!-- <view style="width: 100px;height:50px;cursor: pointer;" @tap="toToggle"></view> -->
					<span class="send-btn" @tap.stop="sendChat">发 送</span>
				</view>
			</view>
		</view>
		<!-- 聊天对话框(结束) -->
	</view>
</template>

<script>
			import div_food_information from "@/components/diy/div_food_information.vue";;
						import div_goods from "@/components/diy/div_goods.vue";
	import list_comment from "@/components/diy/list_comment.vue";
	import bar_title from "@/components/diy/bar_title.vue";
	import bar_goods_nav from "@/components/diy/bar_goods_nav.vue";

	import mixin from "@/libs/mixins/page.js"
	export default {
		mixins: [mixin],
		components: {
					div_food_information,
								div_goods,
			list_comment,
			bar_title,
			bar_goods_nav
		},
		data() {
			return {
				url_get_obj: "~/api/goods/get_obj?",
				field: "goods_id",
				query: {
					goods_id: 0
				},
				right_options: [{
					icon: 'headphones',
					text: '客服'
				}, {
					icon: 'cart',
					text: '购物车',
				}, {
					icon: 'heart',
					text: '收藏',
				}],
				buttonGroup: [{
						text: '加入购物车',
						backgroundColor: '#ff0000',
						color: '#fff'
					},
					{
						text: '立即购买',
						backgroundColor: '#ffa200',
						color: '#fff'
					}
				],
				// 商品详情初始化
				obj: {
					goods_id: 0,
					title: "",
					img: "",
					description: "",
					price_ago: 0,
					price: 0,
					sales: 0,
					inventory: 0,
					type: "",
					hits: 0,
					content: "",
					create_time: "",
					update_time: "",
					img_1: "",
					img_2: "",
					img_3: "",
					img_4: "",
					img_5: "",
					source_table: "",
					source_field: "",
					source_id: 0,
					customize_field:"",
					user_id:0
				},
				// 评论列表初始化
				list_comment: [],
				// 评论bar标题
				bar_title: "评论区",
				// 评论bar的链接
				bar_url: "",
				form_comment: {
					source_table: "goods",
					source_field: "goods_id",
					source_id: 0,
					reply_to_id: 0,
				},
				// 是否已经收藏
				check_collected: false,
				comment_flag:true,
				isAdmin: false,
				chatList: [],
				showChat: false,
				isSmall: false,
				scrollTop: 0,
				oldScrollTop: 0,
				token: "",
				sendValue: "",
			}
		},
		methods: {
			// toggle
			toToggle() {
				this.isAdmin = !this.isAdmin;
			},
			// open
			openSupportModal() {
				let record = uni.getStorageSync("supportChat");
				this.isAdmin =
						uni.getStorageSync("auth")[0].user_group == "管理员" ? true : false;
				this.token = this.token || uni.getStorageSync("token") || "";
				if (record) {
					this.chatList = JSON.parse(record);
				}

				if (!this.token) {
					return uni.showToast({ title: "请先进行登录", icon: "none" });
				}
				console.log("打开客服");
				this.showChat = !this.showChat;
				if (this.chatList.length && this.showChat) {
					setTimeout(() => {
						this.scrollBottom();
					}, 0);
				}
			},
			// 缩小聊天窗口
			scaleSmall() {
				this.isSmall = !this.isSmall;
			},
			// 获取聊天记录
			getChat() {},
			scroll(e) {
				this.oldScrollTop = e.detail.scrollTop;
			},
			// 滚动到最底部
			scrollBottom() {
				uni
						.createSelectorQuery()
						.select(".support_content")
						.boundingClientRect((res) => {
							let newbottom = res.bottom + res.height;
							this.scrollTop = this.oldScrollTop;
							this.$nextTick(() => {
								this.scrollTop = newbottom + 5000;
							});
						})
						.exec();
			},
			// 发送聊天
			sendChat() {
				let nowTime = new Date(+new Date() + 8 * 3600 * 1000)
						.toJSON()
						.substr(0, 16)
						.replace("T", " ");
				let nowTimeChuo = new Date().getTime();
				let reallyTime = "";
				let spaceTime = 12000; //	20分钟
				if (!this.sendValue) {
					return uni.showToast({ title: "不能发送空内容", icon: "none" });
				}
				this.chatList.length &&
				(reallyTime =
						nowTimeChuo - this.chatList[this.chatList.length - 1]["nowTime"] >
						spaceTime
								? nowTime
								: "");
				!this.chatList.length && (reallyTime = nowTime);
				let obj = {
					isAdmin: this.isAdmin,
					content: this.sendValue,
					sendTime: reallyTime,
					nowTime: nowTimeChuo,
				};
				this.chatList.push(obj);

				setTimeout(() => {
					this.scrollBottom();
				}, 0);

				uni.setStorageSync("supportChat", JSON.stringify(this.chatList));

				this.sendValue = "";
			},
			// 购买栏点击事件
			onClick(e) {
				if (e.index === 0) {
					// uni.showToast({
					// 	title: `尽情期待客服功能`,
					// 	icon: 'none'
					// })
					this.openSupportModal();
				} else if (e.index === 1) {
					this.$nav("/pages/cart/list")
				} else if (e.index === 2) {
					this.add_collect(e.index)
				}
			},
			// 购买栏点击事件
			buttonClick(e) {
				console.log(e)
				if (e.index === 0) {
					this.add_cart()
				}else if(e.index === 1){
					this.buy_now()
				}
			},
			/**收藏
			 * @param {Object} index :index of right_options
			 */
			add_collect(index) {
				var {
					title,
					img,
					goods_id
				} = this.obj
				var body = {
					title,
					img,
					source_table: "goods",
					source_field: "goods_id",
					source_id: goods_id,
					user_id: this.$store.state.user.user_id
				}
				if (!this.check_collected) {
					this.check_collected = true
					this.right_options[index].icon = "heart-filled"
					this.$post('~/api/collect/add?', body, (res) => {
						this.$toast("收藏成功");
						console.log(res);
					})
				} else {
					this.check_collected = false
					this.right_options[index].icon = "heart"
					this.$get('~/api/collect/del', body, (res) => {
						this.$toast("取消收藏");
					})
				}
			},
			// 添加购物车
			add_cart() {
				var {
					title,
					img,
					price,
					price_ago,
					description,
					goods_id,
					type
				} = this.obj
				var body = {
					title,
					img,
					price,
					price_ago,
					price_count: price,
					description,
					goods_id,
					type,
					user_id: this.$store.state.user.user_id
				}
				this.$get(
						"~/api/cart/get_list?",
						{
							goods_id: this.obj.goods_id,
							user_id: body.user_id
						},
						(res) => {
							if (res.result.count) {
								var { cart_id, num, price, price_count } = res.result.list[0];
								num += 1;
								price_count += price;
								this.$post(
										`~/api/cart/set?cart_id=${cart_id}`,
										{
											num,
											price,
											price_count,
										},
										(res) => {
											this.$toast("已加入购物车", "成功");
											console.log("更改值");
										}
								);
							} else {
								this.$post("~/api/cart/add?", body, (res) => {
									this.$toast("已加入购物车", "成功");
								});
							}
						}
				);
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
			// 立即购买,加入订单
			async buy_now() {
				//库存不足直接返回
				if (this.obj.inventory<1){
					this.$toast("库存不足");
					return;
				}
				var {
					title,
					img,
					price,
					price_ago,
					description,
					type,
					goods_id
				} = this.obj
				var user_id = this.$store.state.user.user_id
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

				// 生成订单号
				var order_number = this.order_code()
				var body = {
					order_number,
					goods_id,
					title,
					type,
					img,
					price,
					price_ago,
					num: 1,
					price_count: price,
					contact_name,
					contact_phone,
					contact_address,
					postal_code,
					user_id,
					description
				}
				console.log(body);
				this.$post('~/api/order/add?', body, (res) => {
					this.$toast("加入订单成功");
					this.$nav("/pages/order/details?order_number=" + order_number)
				})
			},

			get_obj_after(json) {
				var obj = this.obj;
				this.get_comment(obj);
				this.form_comment.source_id = obj.goods_id;
				this.$get("~/api/order/get_obj?", {"goods_id":this.obj.goods_id,"user_id":this.$store.state.user.user_id,"state":"已付款"}, (json) => {
                    if (json.result && json.result.obj) {
						console.log(json.result)
					}else {
						this.comment_flag = false
					}
				})
			},
			// 获取评论
			get_comment() {
				var options = getCurrentPages()[getCurrentPages().length - 1].options
				var query = {
					"source_table": "goods",
					"source_field": "goods_id",
					"source_id": options.goods_id,
					orderby: "create_time desc",
					reply_to_id: "0",
				}
				this.$get("~/api/comment/get_list?", query, (json) => {
					if (json.result) {
						var list_comment = json.result.list
						list_comment.map((o) => {
							o.list_reply = [];
						});
						this.add_reply(list_comment).then((list) => {
							this.list_comment = list;
						});
					}
				})
			},
			/**
			 * @param { Array } list 评论列表
			 * 添加回复到评论列表
			 */
			add_reply(list) {
				return new Promise((resolve) => {
					for (let idx = 0; idx < list.length; idx++) {
						const obj = list[idx];
						this.$get(
							"~/api/comment/get_list?", {
								source_table: "goods",
								source_field: "goods_id",
								source_id: obj.goods_id,
								orderby: "create_time desc",
								reply_to_id: obj.comment_id,
							},
							(res) => {
								if (res.result) {
									obj.list_reply = res.result.list;
								}
							}
						);
					}
					resolve(list);
				});
			},
		},
		onLoad() {
			this.get_comment()
		}
	}
</script>

<style>
	.page_goods {
		padding-bottom: 3rem;
	}

	.link {
		text-align: center;
		padding: 0.5rem 0;
		border: 1px solid #DBDBDB;
		border-radius: 0.5rem;
	}

	.buy_wrap {
		position: fixed;
		bottom: 0;
		left: 0;
		width: 100%;
		background-color: #FFFFFF;
	}
	.support_module .container{
		height: 64%;
		width: 19.4rem;
		border-radius: 4px;
		border: 0.5px solid #e0e0e0;
		background-color: #f5f5f5;
		overflow: hidden;
		position: fixed;
		padding: 0;
		margin-left: -9.7rem;
		z-index: 99999999;
	}
	.support_module	.small_css{
		height: 40px;
		width: 200px;
		right: -15px;
		bottom: 0px;
	}
	.support_module	.big_css{
		top: 12%;
		left: 50%;
	}
	.support_module .content{
		width: calc(100% - 0px);
		overflow-y: scroll;
		height: 64%;
		padding: 0 10px 10px 0;
	}
	.support_module .content::-webkit-scrollbar{
		display: none
	}
	.support_module_title{
		text-align: right;
		height: 40px;
		line-height: 40px;
		border-bottom: 1px solid #ccc;

	}
	.support_module_title .title_btn{
		font-size: 20px;
		cursor: pointer;
		margin-right: 20px;
		color: #888;

	}
	.support_module .content:hover::-webkit-scrollbar-thumb{
		background:rgba(0,0,0,0.1);
	}
	.support_module .bubble{
		max-width: 182px;
		padding: 10px;
		border-radius: 5px;
		position: relative;
		color: #000;
		word-wrap:break-word;
		word-break:normal;
		font-size: 12px;
	}
	.support_module .item_left .bubble{
		margin-left: 10px;
		background-color: #fff;
	}
	.support_module .item_left .bubble:before{
		content: "";
		position: absolute;
		width: 0;
		height: 0;
		border-left: 10px solid transparent;
		border-top: 10px solid transparent;
		border-right: 10px solid #fff;
		border-bottom: 10px solid transparent;
		left: -20px;
	}
	.support_module .item_right .bubble{
		margin-right: 10px;
		background-color: #9eea6a;
	}
	.support_module .item_right .bubble:before{
		content: "";
		position: absolute;
		width: 0;
		height: 0;
		border-left: 10px solid #9eea6a;
		border-top: 10px solid transparent;
		border-right: 10px solid transparent;
		border-bottom: 10px solid transparent;
		left: none;

		right: -20px;
	}
	.support_module .item{
		margin-top: 15px;
		display: flex;
		width: 100%;
	}
	.support_module .item.item_right{
		justify-content: flex-end;
	}
	.support_module .item.item-center{
		justify-content: center;
	}
	.support_module .item.item-center span{
		font-size: 12px;
		padding: 2px 4px;
		color: #fff;
		background-color: #dadada;
		border-radius: 3px;
		-moz-user-select:none; /*火狐*/
		-webkit-user-select:none; /*webkit浏览器*/
		-ms-user-select:none; /*IE10*/
		-khtml-user-select:none; /*早期浏览器*/
		user-select:none;
	}

	.support_module .avatar image{
		width: 42px;
		height: 42px;
		border-radius: 50%;
	}
	.support_module .input-area{
		border-top:0.5px solid #e0e0e0;
		height: 15%;
		background-color: #fff;
	}
	.support_module textarea{
		flex: 1;
		padding: 10px;
		font-size: 12px;
		border: none;
		overflow-y: auto;
		overflow-x: hidden;
		outline:none;
		resize:none;
		width: 102%;
		height: 100%;
		min-height: 100%;
		max-height: 100%;
		box-sizing: border-box;
	}
	.support_module .button-area{
		display: flex;
		height: 50px;
		line-height: 50px;
		padding: 5px;
		justify-content: flex-end;
		text-align: right;
		width: 100%;
		background: #fff;
	}
	.support_module .button-area .send-btn{
		width: 80px;
		height: 28px;
		line-height: 28px;
		border: none;
		outline: none;
		border-radius: 4px;
		float: right;
		cursor: pointer;
		background: #9eea6a;
		font-size: 12px;
		color: #333;
		margin: 0px;
		text-align: center;
	}
</style>

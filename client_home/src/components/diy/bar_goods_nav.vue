<template>
	<!-- 底部占位 -->
	<!-- <view class="uni-tab__seat" /> -->
	<view class="bar_goods_nav">
		<!-- 右侧按钮 -->
		<view class="cart-sub-left">
			<view v-for="(obj,idx) in options" :key="idx" class="cart-button-left" @click="onClick(idx,obj)">
				<view class="icon">
					<uni-icons :type="obj.icon" size="20" color="#646566"></uni-icons>
				</view>
				<text class="text">{{ obj.text }}</text>
				<!-- 角标 -->
				<view class="dot-box">
					<text v-if="obj.info" :class="{ 'dots': obj.info > 9 }" class="dot " :style="{
							'backgroundColor':obj.infoBackgroundColor?obj.infoBackgroundColor:'#ff0000',
							color:obj.infoColor?obj.infoColor:'#fff'
						}">{{ obj.info }}</text>
				</view>
			</view>
		</view>

		<view :class="{'right':isEllipse}" v-if="cart_action" class="cart-sub-right ">
			<view v-for="(obj,idx) in buttonGroup" :key="idx"
				:style="{backgroundColor:obj.backgroundColor,color:obj.color}" class="cart-button-right"
				@click="buttonClick(idx,obj)"><text :style="{color:obj.color}"
					class="cart-button-right-text">{{ obj.text }}</text>
			</view>
		</view>
	</view>
</template>

<script>
	/**
	 * GoodsNav 商品导航
	 * @description 商品加入购物车、立即购买等
	 * @tutorial https://ext.dcloud.net.cn/plugin?id=865
	 * @property {Array} options 组件参数
	 * @property {Array} buttonGroup 组件按钮组参数
	 * @property {Boolean} isEllipse = [true | false] 是否椭圆
	 * @event {Function} click 左侧点击事件
	 * @event {Function} buttonClick 右侧按钮组点击事件
	 * @example <goods-nav :isEllipse="true"  options="" buttonGroup="buttonGroup"  @click="" @buttonClick="" />
	 */
	export default {
		name: 'UniGoodsNav',
		props: {
			options: {
				type: Array,
				default () {
					return [{
						icon: 'shop',
						text: '店铺',
					}, {
						icon: 'cart',
						text: '购物车'
					}]
				}
			},
			buttonGroup: {
				type: Array,
				default () {
					return [{
							text: '加入购物车',
							backgroundColor: '#ffa200',
							color: '#fff'
						},
						{
							text: '立即购买',
							backgroundColor: '#ff0000',
							color: '#fff'
						}
					]
				}
			},
			isEllipse: {
				type: Boolean,
				default: false
			},
			cart_action: {
				type: Boolean,
				default: true
			}
		},
		methods: {
			onClick(index, obj) {
				this.$emit('click', {
					index,
					content: obj,

				})
			},
			buttonClick(index, obj) {
				this.$emit('buttonClick', {
					index,
					content: obj
				})
			}
		}
	}
</script>

<style>
	.bar_goods_nav {
		display: flex;
		flex: 1;
		height: 50px;
		background-color: #fff;
		z-index: 999999;
	}

	.bar_goods_nav .cart-sub-left {
		display: flex;
		padding: 0 5px;
	}

	.bar_goods_nav .cart-sub-right {
		display: flex;
		flex: 1;
	}

	.bar_goods_nav .right {
		margin: 5px 0;
		margin-right: 10px;
		border-radius: 100px;
		overflow: hidden;
	}

	.bar_goods_nav .cart-button-left {
		display: flex;
		position: relative;
		justify-content: center;
		align-items: center;
		flex-direction: column;
		margin: 0 10px;
		/* #ifdef H5 */
		cursor: pointer;
		/* #endif */
	}

	.bar_goods_nav .icon {
		width: 18px;
		height: 18px;
	}

	.bar_goods_nav .image {
		width: 18px;
		height: 18px;
	}

	.bar_goods_nav .text {
		margin-top: 3px;
		font-size: 12px;
		color: #646566;
	}

	.bar_goods_nav .cart-button-right {
		/* #ifndef APP-NVUE */
		display: flex;
		flex-direction: column;
		/* #endif */
		display: flex;
		flex: 1;
		justify-content: center;
		align-items: center;
		/* #ifdef H5 */
		cursor: pointer;
		/* #endif */
	}

	.bar_goods_nav .cart-button-right-text {
		font-size: 14px;
		color: #fff;
	}

	.bar_goods_nav .cart-button-right:active {
		opacity: 0.7;
	}

	.bar_goods_nav .dot-box {
		/* #ifndef APP-NVUE */
		flex-direction: column;
		/* #endif */
		display: flex;
		position: absolute;
		right: -2px;
		top: 2px;
		justify-content: center;
		align-items: center;
	}

	.bar_goods_nav .dot {
		padding: 0 4px;
		line-height: 15px;
		color: #ffffff;
		text-align: center;
		font-size: 12px;
		background-color: #ff0000;
		border-radius: 15px;
	}

	.bar_goods_nav .dots {
		padding: 0 4px;
		border-radius: 15px;
	}
</style>

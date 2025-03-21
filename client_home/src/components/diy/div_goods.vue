<template>
	<view class="div_goods">
		<template v-if="swiper_img.length">
				<!-- 改了这里 -->
			<swiper @change="changeswiper" class="swiper" indicator-active-color="#fff" :indicator-dots="indicatorDots" :autoplay="autoplay"
				:interval="interval" :duration="duration"  :current="currentNum" :style="{height:bigImgHeight}">

				<swiper-item v-for="(o_img,i) in swiper_img" :key="i" :style="{height:bigImgHeight}">
					<!-- 要看效果把swiper_img变量名换成swiper_img2 -->
					<image :style="{height:bigImgHeight}" class='swipeShowImg'  @load="fitHight(1)" :src="$fullImgUrl(o_img)"></image>
				</swiper-item>
				<!-- 改了这里 -->
			</swiper> 
		</template>
		<template v-else>
			<image :src="$fullImgUrl(obj[vm.img])" style="width: 100%;" mode=""></image>
		</template>

		<view class="wrap">
			<!-- 改了这里 -->
			<view class='includelittle_goodspic'>
				<!-- 要看效果把swiper_img变量名换成swiper_img2 -->
				<image @load="fitHight(2)" :style="{height:littleImgHeight}" v-for="(o_img,i) in swiper_img" :key="i" @click='changeswiperpic(i)' :class="['little_goodspic',currentNum==i?'little_goodspic_act':'']" :src="$fullImgUrl(o_img)"></image>
			</view>
			<!-- 改了这里 -->
			<view class="title">
				<text>{{obj[vm.title]}}</text>
			</view>

			<view class="price_wrap">
				<text class="price">￥{{obj[vm.price]}}</text>
				<text class="price_ago">￥{{obj[vm.price_ago]}}</text>
			</view>

			<view class="description">
				<view class="sales">
					<text>已卖{{obj[vm.sales]}}</text>
				</view>
				<view>
					<div v-for="o in customize_field_list">
						<label >{{o.field_name+" : "}}</label>
						<span v-if="o.type==='image'">
							<image v-if="o.field_value" style="width: 10rem;height: 10rem;" :src="$fullImgUrl(o.field_value) || '@/static/img/default.png'" mode="scaleToFill"></image>
						</span>
						<span v-else-if="o.type==='video'">
							<a v-if="o.field_value" :href="$fullUrl(o.field_value)" target="_blank" style="color: rgb(64, 158, 255);">查看视频</a>
						</span>
						<span v-else-if="o.type==='audio'">
							<a v-if="o.field_value" :href="$fullUrl(o.field_value)" target="_blank" style="color: rgb(64, 158, 255);">聆听音频</a>
						</span>
						<span v-else-if="o.type==='file'">
							<a v-if="o.field_value" :href="$fullUrl(o.field_value)" target="_blank" style="color: rgb(64, 158, 255);">点击下载</a>
						</span>
						<span v-else-if="o.type==='date'">
							{{$toTime(o.field_value,"yyyy-MM-dd")}}
						</span>
						<span v-else-if="o.type==='time'">
							{{$toTime(o.field_value,"hh:mm:ss")}}
						</span>
						<span v-else-if="o.type==='datetime'">
							{{$toTime(o.field_value,"yyyy-MM-dd hh:mm:ss")}}
						</span>
						<span v-else>{{o.field_value}}</span>
					</div>
				</view>
				<view>
					<div>
						<text>{{obj[vm.description]}}</text>
					</div>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			obj: {
				type: Object,
				default: function() {
					return {}
				}
			},
			vm: {
				type: Object,
				default: function() {
					return {
						goods_id: "goods_id",
						title: "title",
						img: "img",
						description: "description",
						price_ago: "price_ago",
						price: "price",
						sales: "sales",
						inventory: "inventory",
						type: "type",
						hits: "hits",
						content: "content",
						create_time: "create_time",
						update_time: "update_time",
						img_1: "img_1",
						img_2: "img_2",
						img_3: "img_3",
						img_4: "img_4",
						img_5: "img_5",
						customize_field:"customize_field"
					}
				}
			},
		},
		data() {
			return {
				currentNum:0,
				/* 改了这里 */
				bigImgHeight:'300px',
				littleImgHeight:'50px',
				swiper_img2:[
					"https://dcdn.yuyuebus.com/front/img/buychoose.png",
					"https://dcdn.yuyuebus.com/front/img/buychoosenone.png",
					"https://dcdn.yuyuebus.com/front/img/buylocation.png",
					"https://dzgj.yuyuebus.com/static/front/dist/static/img/icon-question.png",
					"https://dcdn.yuyuebus.com/front/img/buychoose.png",
				],
				// 轮播图属性
				indicatorDots: false,
				autoplay: true,
				interval: 2000,
				duration: 500,
				justicNum:0
				/* 改了这里 */
			}
		},
		methods: {
			/* 改了这里 */
			changeswiper(e){
				this.currentNum= e.detail.current;
			},
			changeswiperpic(num){
				let that=this;
				this.currentNum=num;
				this.autoplay=false;
				setTimeout(function(){
					that.autoplay=true;
				},2000)
			},
			fitHight(type){
				let that=this;
				if(type==1){
					let objbig = uni.createSelectorQuery().select('.swipeShowImg');
					objbig.boundingClientRect(function (data) { // data - 各种参数
					that.bigImgHeight=data.width*0.92+'px';
					}).exec()
				}else{
					uni.getSystemInfo({
					 success: function (res) { // res - 各种参数
						let objlittle = uni.createSelectorQuery().select('.little_goodspic');
						objlittle.boundingClientRect(function (data) { // data - 各种参数
							that.justicNum=that.justicNum+1;
							that.littleImgHeight=data.width*0.92+'px';
						}).exec()
					 },
					})
				}
			},
			/* 改了这里 */
		},
		computed: {
			// 获得商品轮播图
			swiper_img: function() {
				var obj = this.obj
				var list_img = []
				for (let k in obj) {
					let sub_k = k
					if (sub_k.substr(0, 3) === "img" && obj[k]) {
						list_img.push(obj[k])
					}
				}
				return list_img
			},
			// 获得商品轮播图
			customize_field_list: function() {
				if (this.obj.customize_field) {
					let list_fields = JSON.parse(this.obj.customize_field)
					return list_fields;
				}
				return []
			}
		}
	}
</script>

<style>
	 /* 改了这里 */
	 .swipeShowImg{
	 	width: 100%;
	 }
	.includelittle_goodspic{
		margin-bottom:10px;
		width: 100%;
		display: flex;flex-direction: row;align-items: center;justify-content: flex-start;
	}
	.little_goodspic{
		width: 18%;border:1px solid #fff;margin-right:2.5%;height: auto;
	}
	.little_goodspic_act{
		border:1px solid #5676fc;
	}
	.little_goodspic:last-child{
		margin-right:0;
	}
	/* 改了这里 */
	.div_goods>.wrap {
		position: relative;
		padding: 0.8rem;
	}

	.title {
		font-weight: 600;
	}

	.price_wrap {
		position: absolute;
		top: -2.2rem;
		left: 0;
		padding: 0.2rem 0.4rem;
		margin: 0.5rem 0;
		background-color: var(--color_orange)
	}

	.price {
		color: #fff;
		font-weight: 600;
	}

	.price_ago {
		color: #fff;
		margin-left: .5rem;
		text-decoration: line-through;
		font-size: 0.7rem;
	}

	.sales {
		font-size: 0.7rem;
		margin: 0.3rem 0;
		color: #949494;
	}

	.description {
		margin-top: 1rem;
		font-size: 0.8rem;
		color: var(--color_grey);
	}
</style>

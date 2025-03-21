<template>
	<div>
			<view v-if="$check_register_field('add','user_name','/customer_users/view')" class="col-12 col-md-6">
			<view class="diy_title">
				<text>
					用户姓名:
				</text>
			</view>
									<!-- 文本 -->
				<view class="diy_field diy_text">
					<input type="text" id="form_user_name" v-model="form['user_name']" @blur="handleBlur($event.target.value,'user_name')"  placeholder="请输入用户姓名" />
				</view>
							</view>
				<view v-if="$check_register_field('add','user_gender','/customer_users/view')" class="col-12 col-md-6">
			<view class="diy_title">
				<text>
					用户性别:
				</text>
			</view>
					<!-- 选项 -->
			<view class="diy_field diy_down">
				<uni-data-select
						id="form_user_gender"
						v-model="form['user_gender']"
						:localdata="list_user_gender"
						@change="change_user_gender"
				></uni-data-select>
			</view>
				</view>
				<view v-if="$check_register_field('add','user_age','/customer_users/view')" class="col-12 col-md-6">
			<view class="diy_title">
				<text>
					用户年龄:
				</text>
			</view>
									<!-- 文本 -->
				<view class="diy_field diy_text">
					<input type="text" id="form_user_age" v-model="form['user_age']" @blur="handleBlur($event.target.value,'user_age')"  placeholder="请输入用户年龄" />
				</view>
							</view>
		</div>
</template>

<script>
	export default {
		model: {
			prop: "form",
			event: "change"
		},
		props: {
			form: {
				type: Object,
				default: ()=>{
					return {
						url_upload: "~/api/customer_users/upload?",
					}
				}
			}
		},
		data(){
			return {
								"list_user_gender": [],
							}
		},
		methods: {
					/**
			 * 获取用户性别列表
			 */
			async get_list_user_gender() {
									['男','女'].map((o) => this.list_user_gender.push({value:o,text:o}));
											},
			change_user_gender(val) {
				if (val === "全部" || val === "") {
					this.$emit('change',{"value":"","type":"user_gender"})
				}else{
					this.$emit('change',{"value":val,"type":"user_gender"})
				}
			},
					/**
			 * 上传图片
			 * @param {Object} param文件参数
			 */
			change_img(key_name){
				var _self = this;
				_self.upload_img_flag = false
				// 选择图像方法
				uni.chooseImage({
					count: 1,
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: function(res) {
						const tempFilePaths = res.tempFilePaths;
						const uploadTask = uni.uploadFile({
							url: _self.$fullUrl('/api/customer_users/upload?'),
							filePath: tempFilePaths[0],
							name: 'file',
							formData: {
								'i_want_to_customize': 'test'
							},
							header: {
								'x-auth-token': "null"
							},
							success: function(uploadFileRes) {
								var filename = JSON.parse(uploadFileRes.data).result.url
								_self.$delete(_self.form,key_name);
								_self.$set(_self.form,key_name,filename);
								_self.handleBlur(filename,key_name);
							}
						});

						uploadTask.onProgressUpdate(function(res) {
							_self.percent = res.progress;
							console.log('上传进度' + res.progress);
							console.log('已经上传的数据长度' + res.totalBytesSent);
							console.log('预期需要上传的数据总长度' + res.totalBytesExpectedToSend);
						});
					},
					error: function(e) {
						console.log(e);
					}
				});
			},
			handleBlur(value,type){
				this.$emit('change',{value,type})
			}
		},
    mounted(){
						this.get_list_user_gender();
						}
	}
</script>

<style>
.col-md-6{
	display: flex;
	margin-bottom: 1.25rem;
	height: 36px;
}
.diy_title{
	/* flex: 1.2; */
	display: inline-block;
	width: 5.0625rem;
	margin-right: 0.3125rem;
	font-size: 0.875rem;
    color: #333;
		padding-top: 0.3125rem;
}
.diy_field{
	/* flex: 5; */
	display: inline-block;
	width: 100%;
	background: #ffffff;
	height: 2.25rem;
	line-height:  2.25rem;
	border-radius: 0.25rem;
	padding-left: 0.625rem;


	}
	.diy_field input{
		display: inline;
		font-size: 0.875rem;
	}
</style>

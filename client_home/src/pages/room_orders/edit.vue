<template>
	<view class="diy_edit page_room_orders" id="room_orders_edit">
		<view class='warp'>
			<view class='container'>
				<view class='row'>
						<view v-if="$check_field('set','order_number') || $check_field('add','order_number') || $check_field('get','order_number')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								订单编号:
							</text>
						</view>
								<!-- 文本 -->
									<view class="diy_field diy_text diy_text_row">
							<input type="text" id="form_order_number" v-model="form['order_number']" placeholder="请输入订单编号" v-if="(form['order_number'] && $check_field('set','order_number')) || (!form['order_number'] && $check_field('add','order_number'))" :disabled="true" />
							<text v-else-if="$check_field('get','order_number')">{{ form['order_number'] }}</text>
						</view>
										</view>
							<view v-if="$check_field('set','room_name') || $check_field('add','room_name') || $check_field('get','room_name')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								房间名称:
							</text>
						</view>
								<!-- 文本 -->
									<view class="diy_field diy_text diy_text_row">
							<input type="text" id="form_room_name" v-model="form['room_name']" placeholder="请输入房间名称" v-if="(form['room_name'] && $check_field('set','room_name')) || (!form['room_name'] && $check_field('add','room_name'))" :disabled="disabledObj['room_name_isDisabled']" />
							<text v-else-if="$check_field('get','room_name')">{{ form['room_name'] }}</text>
						</view>
										</view>
							<view v-if="$check_field('set','room_type') || $check_field('add','room_type') || $check_field('get','room_type')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								房间房型:
							</text>
						</view>
								<!-- 文本 -->
									<view class="diy_field diy_text diy_text_row">
							<input type="text" id="form_room_type" v-model="form['room_type']" placeholder="请输入房间房型" v-if="(form['room_type'] && $check_field('set','room_type')) || (!form['room_type'] && $check_field('add','room_type'))" :disabled="disabledObj['room_type_isDisabled']" />
							<text v-else-if="$check_field('get','room_type')">{{ form['room_type'] }}</text>
						</view>
										</view>
							<view v-if="$check_field('set','room_price') || $check_field('add','room_price') || $check_field('get','room_price')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								房间价格:
							</text>
						</view>
								<!-- 文本 -->
									<view class="diy_field diy_text diy_text_row">
							<input type="text" id="form_room_price" v-model="form['room_price']" placeholder="请输入房间价格" v-if="(form['room_price'] && $check_field('set','room_price')) || (!form['room_price'] && $check_field('add','room_price'))" :disabled="disabledObj['room_price_isDisabled']" />
							<text v-else-if="$check_field('get','room_price')">{{ form['room_price'] }}</text>
						</view>
										</view>
							<view v-if="$check_field('set','customer_users') || $check_field('add','customer_users') || $check_field('get','customer_users')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								顾客用户:
							</text>
						</view>
						<view class="diy_field diy_down diy_text_row diy_select_flex">
							<uni-data-select
									id="form_customer_users"
									v-model="form['customer_users']"
									:localdata="list_user_customer_users"
									:clear="!disabledObj['customer_users_isDisabled']"
									:disabled="disabledObj['customer_users_isDisabled']"
									v-if="(form['customer_users'] && $check_field('set','customer_users')) || (!form['customer_users'] && $check_field('add','customer_users'))"
							></uni-data-select>
							<text v-else-if="$check_field('get','customer_users')">{{ form['customer_users'] }}</text>
						</view>
					</view>
							<view v-if="$check_field('set','user_name') || $check_field('add','user_name') || $check_field('get','user_name')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								用户姓名:
							</text>
						</view>
								<!-- 文本 -->
									<view class="diy_field diy_text diy_text_row">
							<input type="text" id="form_user_name" v-model="form['user_name']" placeholder="请输入用户姓名" v-if="(form['user_name'] && $check_field('set','user_name')) || (!form['user_name'] && $check_field('add','user_name'))" :disabled="disabledObj['user_name_isDisabled']" />
							<text v-else-if="$check_field('get','user_name')">{{ form['user_name'] }}</text>
						</view>
										</view>
							<view v-if="$check_field('set','contact_number') || $check_field('add','contact_number') || $check_field('get','contact_number')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								联系电话:
							</text>
						</view>
								<!-- 文本 -->
									<view class="diy_field diy_text diy_text_row">
							<input type="text" id="form_contact_number" v-model="form['contact_number']" placeholder="请输入联系电话" v-if="(form['contact_number'] && $check_field('set','contact_number')) || (!form['contact_number'] && $check_field('add','contact_number'))" :disabled="disabledObj['contact_number_isDisabled']" />
							<text v-else-if="$check_field('get','contact_number')">{{ form['contact_number'] }}</text>
						</view>
										</view>
							<view v-if="$check_field('set','check_in_time') || $check_field('add','check_in_time') || $check_field('get','check_in_time')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								入住时间:
							</text>
						</view>
								<!-- 日长 -->
						<view class="diy_field diy_datetime diy_text_row">
							<uni-datetime-picker :disabled="disabledObj['check_in_time_isDisabled']" type="datetime" id="form_check_in_time" v-model="form['check_in_time']" placeholder="请输入入住时间" v-if="(form['check_in_time'] && $check_field('set','check_in_time')) || (!form['check_in_time'] && $check_field('add','check_in_time'))" />
							<text v-else-if="$check_field('get','check_in_time')">{{ form['check_in_time'] }}</text>
						</view>
							</view>
							<view v-if="$check_field('set','booking_days') || $check_field('add','booking_days') || $check_field('get','booking_days')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								预订天数:
							</text>
						</view>
								<!-- 文本 -->
									<view class="diy_field diy_text diy_text_row">
							<input type="text" id="form_booking_days" v-model="form['booking_days']" placeholder="请输入预订天数" v-if="(form['booking_days'] && $check_field('set','booking_days')) || (!form['booking_days'] && $check_field('add','booking_days'))" :disabled="disabledObj['booking_days_isDisabled']" />
							<text v-else-if="$check_field('get','booking_days')">{{ form['booking_days'] }}</text>
						</view>
										</view>
							<view v-if="$check_field('set','total_order_price') || $check_field('add','total_order_price') || $check_field('get','total_order_price')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								订单总价:
							</text>
						</view>
								<!-- 文本 -->
									<view class="diy_field diy_text diy_text_row">
							<input type="text" id="form_total_order_price" v-model="form['total_order_price']" placeholder="请输入订单总价" v-if="(form['total_order_price'] && $check_field('set','total_order_price')) || (!form['total_order_price'] && $check_field('add','total_order_price'))" @focus="set_total_order_price()" :disabled="disabledObj['total_order_price_isDisabled']" />
							<text v-else-if="$check_field('get','total_order_price')">{{ form['total_order_price'] }}</text>
						</view>
										</view>
							<view v-if="$check_field('set','booking_remarks') || $check_field('add','booking_remarks') || $check_field('get','booking_remarks')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								预订备注:
							</text>
						</view>
								<!-- 多文本 -->
						<view class="diy_field diy_desc diy_text_row">
							<textarea id="form_booking_remarks" v-model="form['booking_remarks']" v-if="(form['booking_remarks'] && $check_field('set','booking_remarks')) || (!form['booking_remarks'] && $check_field('add','booking_remarks'))" :disabled="disabledObj['booking_remarks_isDisabled']"/>
							<text v-else-if="$check_field('get','booking_remarks')">{{ form['booking_remarks'] }}</text>
						</view>
							</view>
							<view v-if="$check_field('set','reservation_status') || $check_field('add','reservation_status') || $check_field('get','reservation_status')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								预订状态:
							</text>
						</view>
								<!-- 选项 -->
						<view class="diy_field diy_down diy_text_row diy_select_flex">
							<uni-data-select
									id="form_reservation_status"
									v-model="form['reservation_status']"
									:localdata="list_reservation_status"
									v-if="(form['reservation_status'] && $check_field('set','reservation_status')) || (!form['reservation_status'] && $check_field('add','reservation_status'))"
							></uni-data-select>
							<text v-else-if="$check_field('get','reservation_status')">{{ form['reservation_status'] }}</text>
						</view>
							</view>
							<view v-if="$check_field('set','reservation_response') || $check_field('add','reservation_response') || $check_field('get','reservation_response')" class="col-12 col-md-6 row-item">
						<view class="diy_title diy_text_row">
							<text>
								预订回复:
							</text>
						</view>
								<!-- 多文本 -->
						<view class="diy_field diy_desc diy_text_row">
							<textarea id="form_reservation_response" v-model="form['reservation_response']" v-if="(form['reservation_response'] && $check_field('set','reservation_response')) || (!form['reservation_response'] && $check_field('add','reservation_response'))" :disabled="disabledObj['reservation_response_isDisabled']"/>
							<text v-else-if="$check_field('get','reservation_response')">{{ form['reservation_response'] }}</text>
						</view>
							</view>
	
				</view>
				<view class="row">
					<view class="col-12">
						<view class="btn_box">
							<button class="btn_submit primary_btn" @click="submit()">提交</button>
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
import mixin from "@/libs/mixins/page.js";
export default {
	mixins:[mixin],
	components:{},
	data(){
		return{
			url_get_obj:"~/api/room_orders/get_obj?",
			url_add:"~/api/room_orders/add?",
			url_set:"~/api/room_orders/set?",

			// 登录权限
			oauth: {
				"signIn": true,
				"user_group": []
			},

			// 查询条件
			query: {
					"order_number": "",
						"room_name": "",
						"room_type": "",
						"room_price": "",
						"customer_users": 0,
						"user_name": "",
						"contact_number": "",
						"check_in_time": "",
						"booking_days": 0,
						"total_order_price": "",
						"booking_remarks": "",
						"reservation_status": "",
						"reservation_response": "",
					"room_orders_id": 0
			},

			obj: {
					"order_number": this.$get_stamp(), // 订单编号
						"room_name":  '', // 房间名称
						"room_type":  '', // 房间房型
						"room_price":  '', // 房间价格
						"customer_users": 0, // 顾客用户
						"user_name":  '', // 用户姓名
						"contact_number":  '', // 联系电话
						"check_in_time": this.$toTime(new Date().getTime(), "yyyy-MM-dd hh:mm:ss"),
						"booking_days":  0 , // 预订天数
						"total_order_price":  '', // 订单总价
						"booking_remarks":  '', // 预订备注
						"reservation_status":  '', // 预订状态
						"reservation_response":  '', // 预订回复
					"room_orders_id": 0,

			},

			// 表单字段
			form: {
					"order_number": this.$get_stamp(), // 订单编号
						"room_name":  '', // 房间名称
						"room_type":  '', // 房间房型
						"room_price":  '', // 房间价格
						"customer_users": 0, // 顾客用户
						"user_name":  '', // 用户姓名
						"contact_number":  '', // 联系电话
						"check_in_time": this.$toTime(new Date().getTime(), "yyyy-MM-dd hh:mm:ss"),
						"booking_days":  0 , // 预订天数
						"total_order_price":  '', // 订单总价
						"booking_remarks":  '', // 预订备注
						"reservation_status":  '', // 预订状态
						"reservation_response":  '', // 预订回复
					"room_orders_id": 0,
			},
			disabledObj:{
					"order_number_isDisabled": false,
						"room_name_isDisabled": false,
						"room_type_isDisabled": false,
						"room_price_isDisabled": false,
						"customer_users_isDisabled": false,
						"user_name_isDisabled": false,
						"contact_number_isDisabled": false,
						"check_in_time_isDisabled": false,
								"total_order_price_isDisabled": false,
						"booking_remarks_isDisabled": false,
						"reservation_status_isDisabled": false,
						"reservation_response_isDisabled": false,
				},

													// 用户列表
			list_user_customer_users: [],
																	list_reservation_status: [],
				
			field:"room_orders_id",
			table_key:"room_orders",

		}
	},
	methods: {
			
	
				
	
				
	
				
	
					/**
		 * 获取顾客用户用户列表
		 */
		async get_list_user_customer_users() {
			// if(this.user_group !== "管理员" && this.form["customer_users"] === 0) {
			//     this.form["customer_users"] = this.user.user_id;
			// }
			var json = await this.$get("~/api/user/get_list?user_group=顾客用户");
			if(json.result && json.result.list){
				json.result.list.map((o) => this.list_user_customer_users.push({value:o.user_id,text:o.nickname + '-' + o.username}));
			}
			else if(json.error){
				console.error(json.error);
			}
		},
				async get_user_session_customer_users(){
			var _this = this;
			var json = await this.$get("~/api/user_group/get_obj?name=顾客用户");
			if(json.result && json.result.obj){
				var source_table = json.result.obj.source_table;
				var user_id = _this.$store.state.user.user_id;
				if (user_id){
					var url = "~/api/"+source_table+"/get_obj?"
					this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
						if (res.result && res.result.obj) {
							var arr = []
							for (let key in res.result.obj) {
								arr.push(key)
							}
							var arrForm = []
							for (let key in _this.form) {
								arrForm.push(key)
							}
							_this.form["customer_users"] = user_id
							_this.disabledObj['customer_users' + '_isDisabled'] = true
							for (var i=0;i<arr.length;i++){
                if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                  for (var j = 0; j < arrForm.length; j++) {
                    if (arr[i] === arrForm[j]) {
                      if (arr[i] !== "customer_users") {
                        _this.form[arrForm[j]] = res.result.obj[arr[i]]
                        _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                        break;
                      }
                    }
                  }
                }
							}
						}
					});
				}
			}
			else if(json.error){
				console.error(json.error);
			}
		},
	
	
				
	
				
	
				
	
				
	
				
	
								set_total_order_price(){
			this.form.total_order_price = parseFloat(this.form.room_price) * parseFloat(this.form.booking_days)
			  let r = /^\+?[1-9][0-9]*$/; // 正整数
			  if(!r.test(this.form.total_order_price) ){
				this.form.total_order_price = this.form.total_order_price.toFixed(2);
			  }
		},
									
	
				/**
		 * 获取预订状态列表
		 */
		async get_list_reservation_status() {
					['预订失败','预订成功'].map((o) => this.list_reservation_status.push({value:o,text:o}));
						},
			
	
				
	
			change_file(key_name){
			var _self = this;
				this.$chooseFile().then(res=>{
					console.log(res)

						const uploadTask = uni.uploadFile({
							url: _self.$fullUrl("/api/feedback/upload?"),
							filePath: res[0].path,
							name: "file",
							formData: {
								i_want_to_customize: "test",
							},
							header: {
								"x-auth-token": _self.$store.state.user.token,
							},
							success: function(uploadFileRes) {
								console.log(uploadFileRes)
								var filename = JSON.parse(uploadFileRes.data).result.url;
								_self.form[key_name] = filename;
							},
						});

						uploadTask.onProgressUpdate(function(res) {
							_self.percent = res.progress;
							console.log("上传进度" + res.progress);
							console.log("已经上传的数据长度" + res.totalBytesSent);
							console.log(
								"预期需要上传的数据总长度" + res.totalBytesExpectedToSend
							);
						});

				})
		},
		change_img(key_name) {
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
						url: _self.$fullUrl('/api/room_orders/upload?'),
						filePath: tempFilePaths[0],
						name: 'file',
						formData: {
							'room_orders': 'test'
						},
						header: {
							'x-auth-token': _self.$store.state.user.token
						},
						success: function(uploadFileRes) {
							var filename = JSON.parse(uploadFileRes.data).result.url
							var img_url = filename
							_self.form[key_name] = img_url
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

		/**
		 * 获取对象后获取缓存表单
		 * @param {Object} json
		 * @param {Object} func
		 */
		get_obj_before(param){
			var form = uni.db.get("form");
			if (form) {
        delete(form.examine_state)
        delete(form.examine_reply)
				this.obj = uni.push(this.obj ,form);
				this.form = uni.push(this.form ,form);
			}
			var arr = []
			for (let key in form) {
				arr.push(key)
			}
			for (var i=0;i<arr.length;i++){
				this.disabledObj[arr[i] + '_isDisabled'] = true
			}
																		if (this.form["check_in_time"] && JSON.stringify(this.form["check_in_time"]).indexOf("-")===-1) {
				this.form["check_in_time"] = this.$toTime(parseInt(this.form["check_in_time"]), "yyyy-MM-dd hh:mm:ss")
			}
															uni.db.del("form");
			return param;
		},

		/**
		 * 获取对象后获取缓存表单
		 * @param {Object} json
		 * @param {Object} func
		 */
		get_obj_after(json ,func){
			var form = uni.db.get("form");
			var obj = Object.assign({} ,form ,this.obj);
			if (form) {
				this.obj = uni.push(this.obj ,obj);
			}
			if (form) {
				this.form = uni.push(this.form ,form);
			}
			if(func){
				func(json);
			}
		},

	},
	onLoad(){
																this.get_user_session_customer_users();
				this.get_list_user_customer_users();
																						this.get_list_reservation_status();
							},
}
</script>

<style scoped>
	input{
		font-size: 10px;
	}

	.form_edit {
		background-color: #fff;
		margin-bottom: 0.5rem;
		padding: 1rem;
		font-size: 10px;
	}

	.item {
		display: flex;
		padding: 0.2rem 0;
	}

	.left_text {
		flex: 0 0 25%;
		display: flex;
		align-items: center;
	}

	.right_text {
		flex: 0 0 75%;
		border-bottom: 1px solid #eee;
	}
	.right_text.btn_warp{
		border-bottom: 0;
	}

	.btn_submit {
		text-align: center;
		background-color: #fff;
		padding: 0.3rem;
		margin: 0.1rem 1rem;
		border: 1px solid #eee;
		border-radius: 0.5rem;
	}

	.btn_submit:hover {
		opacity: 0.5;
	}
	.btn_add_img{
		color: #D3D3D3;
		text-align: center;
		border: 1px solid #eee;
		height: 5rem;
		width: 5rem;
		position: relative;
	}
	.btn_add_img text{
		font-size: 35px;
		position: absolute;
		left: 50%;
		top: 50%;
		transform: translate(-50%,-50%);
	}




</style>
<style scoped>
/*新样式*/
.diy_text_row {
  display: inline-block;
}
.container {
	margin-top:1rem ;
	padding: 1rem;
    -webkit-box-shadow: 0px 0px 0px #888888;
    box-shadow: 0px 0px 0px #888888;
}
.primary_btn{
		background-color: #22B8B8;
		color: #FFFFFF;
	}
	.btn_submit{
		padding: 0;
		margin-top:1rem ;
	}
	.row-item {
		padding: 10px 10px;
	    background: #f8f6fc;
		margin-bottom: 1rem;
	}
	.diy_field{
		padding-left: 1rem;
	}
	.diy_title{
		align-items: center;
        font-size: 14px;
        color: #333;
	}

	.row-item{
		display: flex !important;
		align-items: baseline;
	}
	.diy_select_flex{
		flex: 1;
	}
	.query_select{
		flex: 1;
		border-color: rgb(229, 229, 229);
		background-color: rgb(255, 255, 255);
		border-radius: 4px;
		box-sizing: border-box;
		flex: 1;
		width: 100%;
		line-height: 2;
		font-size: 14px;
		height: 2.4em;
		min-height: 2.4em;
		display: block;
		outline:none;
	}
</style>


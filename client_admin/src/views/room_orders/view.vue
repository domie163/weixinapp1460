<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','order_number') || $check_field('add','order_number') || $check_field('set','order_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="订单编号" prop="order_number">
												<el-input id="order_number" v-model="form['order_number']" placeholder="请输入订单编号"
							  v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','order_number')) || (!form['room_orders_id'] && $check_field('add','order_number'))" :disabled="true"></el-input>
					<div v-else-if="$check_field('get','order_number')">{{form['order_number']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','room_name') || $check_field('add','room_name') || $check_field('set','room_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="房间名称" prop="room_name">
												<el-input id="room_name" v-model="form['room_name']" placeholder="请输入房间名称"
							  v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','room_name')) || (!form['room_orders_id'] && $check_field('add','room_name'))" :disabled="disabledObj['room_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','room_name')">{{form['room_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','room_type') || $check_field('add','room_type') || $check_field('set','room_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="房间房型" prop="room_type">
												<el-input id="room_type" v-model="form['room_type']" placeholder="请输入房间房型"
							  v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','room_type')) || (!form['room_orders_id'] && $check_field('add','room_type'))" :disabled="disabledObj['room_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','room_type')">{{form['room_type']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','room_price') || $check_field('add','room_price') || $check_field('set','room_price')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="房间价格" prop="room_price">
												<el-input id="room_price" v-model="form['room_price']" placeholder="请输入房间价格"
							  v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','room_price')) || (!form['room_orders_id'] && $check_field('add','room_price'))" :disabled="disabledObj['room_price_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','room_price')">{{form['room_price']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','customer_users') || $check_field('add','customer_users') || $check_field('set','customer_users')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="顾客用户" prop="customer_users">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_customer_users(form['customer_users']) }}
							<!--<el-input id="business_name" v-model="form['customer_users']" placeholder="请输入顾客用户"-->
							<!--v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','customer_users')) || (!form['room_orders_id'] && $check_field('add','customer_users'))" :disabled="disabledObj['customer_users_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','customer_users')">{{form['customer_users']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','customer_users')) || (!form['room_orders_id'] && $check_field('add','customer_users'))" id="customer_users" v-model="form['customer_users']" :disabled="disabledObj['customer_users_isDisabled']">
								<el-option v-for="o in list_user_customer_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','customer_users')" id="customer_users" v-model="form['customer_users']" :disabled="true">
								<el-option v-for="o in list_user_customer_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="customer_users" v-model="form['customer_users']" :disabled="disabledObj['customer_users_isDisabled']">
							<el-option v-for="o in list_user_customer_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','user_name') || $check_field('add','user_name') || $check_field('set','user_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="用户姓名" prop="user_name">
												<el-input id="user_name" v-model="form['user_name']" placeholder="请输入用户姓名"
							  v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','user_name')) || (!form['room_orders_id'] && $check_field('add','user_name'))" :disabled="disabledObj['user_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','user_name')">{{form['user_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','contact_number') || $check_field('add','contact_number') || $check_field('set','contact_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="联系电话" prop="contact_number">
												<el-input id="contact_number" v-model="form['contact_number']" placeholder="请输入联系电话"
							  v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','contact_number')) || (!form['room_orders_id'] && $check_field('add','contact_number'))" :disabled="disabledObj['contact_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contact_number')">{{form['contact_number']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','check_in_time') || $check_field('add','check_in_time') || $check_field('set','check_in_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="入住时间" prop="check_in_time">
								<el-date-picker :disabled="disabledObj['check_in_time_isDisabled']" v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','check_in_time')) || (!form['room_orders_id'] && $check_field('add','check_in_time'))" id="check_in_time"
						v-model="form['check_in_time']" type="datetime" placeholder="选择日期时间">
					</el-date-picker>
					<div v-else-if="$check_field('get','check_in_time')">{{form['check_in_time']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','booking_days') || $check_field('add','booking_days') || $check_field('set','booking_days')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="预订天数" prop="booking_days">
								<el-input-number id="booking_days" v-model.number="form['booking_days']"
						v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','booking_days')) || (!form['room_orders_id'] && $check_field('add','booking_days'))" :disabled="disabledObj['booking_days_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','booking_days')">{{form['booking_days']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','total_order_price') || $check_field('add','total_order_price') || $check_field('set','total_order_price')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="订单总价" prop="total_order_price">
												<el-input id="total_order_price" v-model="form['total_order_price']" placeholder="请输入订单总价"
							  v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','total_order_price')) || (!form['room_orders_id'] && $check_field('add','total_order_price'))"  @focus="set_total_order_price()" :disabled="disabledObj['total_order_price_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','total_order_price')">{{form['total_order_price']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','booking_remarks') || $check_field('add','booking_remarks') || $check_field('set','booking_remarks')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="预订备注" prop="booking_remarks">
								<el-input type="textarea" id="booking_remarks" v-model="form['booking_remarks']" placeholder="请输入预订备注"
						v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','booking_remarks')) || (!form['room_orders_id'] && $check_field('add','booking_remarks'))" :disabled="disabledObj['booking_remarks_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','booking_remarks')">{{form['booking_remarks']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','reservation_status') || $check_field('add','reservation_status') || $check_field('set','reservation_status')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="预订状态" prop="reservation_status">
								<el-select id="reservation_status" v-model="form['reservation_status']"
						v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','reservation_status')) || (!form['room_orders_id'] && $check_field('add','reservation_status'))">
						<el-option v-for="o in list_reservation_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','reservation_status')">{{form['reservation_status']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','reservation_response') || $check_field('add','reservation_response') || $check_field('set','reservation_response')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="预订回复" prop="reservation_response">
								<el-input type="textarea" id="reservation_response" v-model="form['reservation_response']" placeholder="请输入预订回复"
						v-if="user_group === '管理员' || (form['room_orders_id'] && $check_field('set','reservation_response')) || (!form['room_orders_id'] && $check_field('add','reservation_response'))" :disabled="disabledObj['reservation_response_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','reservation_response')">{{form['reservation_response']}}</div>
							</el-form-item>
			</el-col>
					
	
	
	
	
	
	
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "room_orders_id",
				url_add: "~/api/room_orders/add?",
				url_set: "~/api/room_orders/set?",
				url_get_obj: "~/api/room_orders/get_obj?",
				url_upload: "~/api/room_orders/upload?",

				query: {
					"room_orders_id": 0,
				},

				form: {
								"order_number": this.$get_stamp(), // 订单编号
										"room_name":  '', // 房间名称
										"room_type":  '', // 房间房型
										"room_price":  '', // 房间价格
										"customer_users": 0, // 顾客用户
										"user_name":  '', // 用户姓名
										"contact_number":  '', // 联系电话
										"check_in_time":  '', // 入住时间
										"booking_days":  0, // 预订天数
										"total_order_price":  '', // 订单总价
										"booking_remarks":  '', // 预订备注
										"reservation_status":  '', // 预订状态
										"reservation_response":  '', // 预订回复
											"room_orders_id": 0, // ID
						
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
					          			"booking_days_isDisabled": false,
										"total_order_price_isDisabled": false,
										"booking_remarks_isDisabled": false,
										"reservation_status_isDisabled": false,
										"reservation_response_isDisabled": false,
										},

	
		
		
		
		
					// 用户列表
				list_user_customer_users: [],
						// 用户组
				group_user_customer_users: "",
				
		
		
		
		
		
						// 预订状态选项列表
				list_reservation_status: ['预订失败','预订成功'],
	
		
	
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
                    this.list_user_customer_users = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					/**
			 * 获取顾客用户用户组
			 */
			async get_group_user_customer_users() {
							this.form["customer_users"] = this.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=顾客用户");
				if(json.result && json.result.obj){
					this.group_user_customer_users = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_customer_users(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_customer_users.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
									for (let key in _this.form) {
							arrForm.push(key)
						}
												_this.form["customer_users"] = id
									_this.disabledObj['customer_users' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "customer_users") {
			                      _this.form[arrForm[j]] = res.result.obj[arr[i]]
			                      _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								  break;
								} else {
								  _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								}
							  }
							}
						  }
						}
					}
				});
			},
					get_user_customer_users(id){
				var obj = this.list_user_customer_users.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			
	
			
	
			
	
			
	
			
	
										set_total_order_price(){
				this.form.total_order_price = parseFloat(this.form.room_price) * parseFloat(this.form.booking_days)
			},
							
	
			
	
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
										// 获取缓存数据附加
				form = $.db.get("form");
							$.push(this.form ,form);
								
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
															        if (this.form["check_in_time"].indexOf("-")===-1){
            this.form["check_in_time"] = this.$toTime(parseInt(this.form["check_in_time"]),"yyyy-MM-dd hh:mm:ss")
        }
																$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){


																						        if(this.form["check_in_time"]=="0000-00-00 00:00:00"){
          this.form["check_in_time"] = null;
        }
				if(parseInt(this.form["check_in_time"]) > 9999){
					this.form["check_in_time"] = this.$toTime(parseInt(this.form["check_in_time"]),"yyyy-MM-dd hh:mm:ss")
				}
																		

			},

			/**
			 * 提交前验证事件
			 * @param {Object} 请求参数
			 * @return {String} 验证成功返回null, 失败返回错误提示
			 */
			submit_check(param) {
				let msg = null
																																													return msg;
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/room_orders/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/room_orders/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/room_orders/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/room_orders/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/room_orders/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
													this.get_list_user_customer_users();
					this.get_group_user_customer_users();
																					},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}




</style>

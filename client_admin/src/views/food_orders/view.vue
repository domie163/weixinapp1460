<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','order_number') || $check_field('add','order_number') || $check_field('set','order_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="订单编号" prop="order_number">
												<el-input id="order_number" v-model="form['order_number']" placeholder="请输入订单编号"
							  v-if="user_group === '管理员' || (form['food_orders_id'] && $check_field('set','order_number')) || (!form['food_orders_id'] && $check_field('add','order_number'))" :disabled="disabledObj['order_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','order_number')">{{form['order_number']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','customer_users') || $check_field('add','customer_users') || $check_field('set','customer_users')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="顾客用户" prop="customer_users">
													<el-select v-if="user_group === '管理员' || (form['food_orders_id'] && $check_field('set','customer_users')) || (!form['food_orders_id'] && $check_field('add','customer_users'))" id="customer_users" v-model="form['customer_users']" :disabled="disabledObj['customer_users_isDisabled']">
							<el-option v-for="o in list_user_customer_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','customer_users')" id="customer_users" v-model="form['customer_users']" :disabled="true">
							<el-option v-for="o in list_user_customer_users" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','food_name') || $check_field('add','food_name') || $check_field('set','food_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="食物名称" prop="food_name">
												<el-input id="food_name" v-model="form['food_name']" placeholder="请输入食物名称"
							  v-if="user_group === '管理员' || (form['food_orders_id'] && $check_field('set','food_name')) || (!form['food_orders_id'] && $check_field('add','food_name'))" :disabled="disabledObj['food_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','food_name')">{{form['food_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','purchase_quantity') || $check_field('add','purchase_quantity') || $check_field('set','purchase_quantity')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="购买数量" prop="purchase_quantity">
								<el-input-number id="purchase_quantity" v-model.number="form['purchase_quantity']"
						v-if="user_group === '管理员' || (form['food_orders_id'] && $check_field('set','purchase_quantity')) || (!form['food_orders_id'] && $check_field('add','purchase_quantity'))" :disabled="disabledObj['purchase_quantity_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','purchase_quantity')">{{form['purchase_quantity']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','total_order_price') || $check_field('add','total_order_price') || $check_field('set','total_order_price')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="订单总价" prop="total_order_price">
												<el-input id="total_order_price" v-model="form['total_order_price']" placeholder="请输入订单总价"
							  v-if="user_group === '管理员' || (form['food_orders_id'] && $check_field('set','total_order_price')) || (!form['food_orders_id'] && $check_field('add','total_order_price'))" :disabled="disabledObj['total_order_price_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','total_order_price')">{{form['total_order_price']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','production_status') || $check_field('add','production_status') || $check_field('set','production_status')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="制作状态" prop="production_status">
								<el-select id="production_status" v-model="form['production_status']"
						v-if="user_group === '管理员' || (form['food_orders_id'] && $check_field('set','production_status')) || (!form['food_orders_id'] && $check_field('add','production_status'))">
						<el-option v-for="o in list_production_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','production_status')">{{form['production_status']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','order_remarks') || $check_field('add','order_remarks') || $check_field('set','order_remarks')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="订单备注" prop="order_remarks">
								<el-input type="textarea" id="order_remarks" v-model="form['order_remarks']" placeholder="请输入订单备注"
						v-if="user_group === '管理员' || (form['food_orders_id'] && $check_field('set','order_remarks')) || (!form['food_orders_id'] && $check_field('add','order_remarks'))" :disabled="disabledObj['order_remarks_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','order_remarks')">{{form['order_remarks']}}</div>
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
				field: "food_orders_id",
				url_add: "~/api/food_orders/add?",
				url_set: "~/api/food_orders/set?",
				url_get_obj: "~/api/food_orders/get_obj?",
				url_upload: "~/api/food_orders/upload?",

				query: {
					"food_orders_id": 0,
				},

				form: {
								"order_number":  '', // 订单编号
										"customer_users": 0, // 顾客用户
										"food_name":  '', // 食物名称
										"purchase_quantity":  0, // 购买数量
										"total_order_price":  '', // 订单总价
										"production_status":  '', // 制作状态
										"order_remarks":  '', // 订单备注
											"food_orders_id": 0, // ID
						
				},
				disabledObj:{
								"order_number_isDisabled": false,
										"customer_users_isDisabled": false,
										"food_name_isDisabled": false,
					          			"purchase_quantity_isDisabled": false,
										"total_order_price_isDisabled": false,
										"production_status_isDisabled": false,
										"order_remarks_isDisabled": false,
										},

	
		
					// 用户列表
				list_user_customer_users: [],
				
		
		
						// 制作状态选项列表
				list_production_status: ['制作中','送餐中','已完成'],
	
		
	
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
			
	
			
	
			
	
			
	
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
									
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
																		$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){


																					

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
					bl = this.$check_action('/food_orders/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/food_orders/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/food_orders/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/food_orders/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/food_orders/view','get');
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

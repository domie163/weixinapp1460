<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','room_name') || $check_field('add','room_name') || $check_field('set','room_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="房间名称" prop="room_name">
												<el-input id="room_name" v-model="form['room_name']" placeholder="请输入房间名称"
							  v-if="user_group === '管理员' || (form['room_information_id'] && $check_field('set','room_name')) || (!form['room_information_id'] && $check_field('add','room_name'))" :disabled="disabledObj['room_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','room_name')">{{form['room_name']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','room_type') || $check_field('add','room_type') || $check_field('set','room_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="房间房型" prop="room_type">
								<el-select id="room_type" v-model="form['room_type']"
						v-if="user_group === '管理员' || (form['room_information_id'] && $check_field('set','room_type')) || (!form['room_information_id'] && $check_field('add','room_type'))">
						<el-option v-for="o in list_room_type" :key="o['room_type']" :label="o['room_type']"
							:value="o['room_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','room_type')">{{form['room_type']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','room_price') || $check_field('add','room_price') || $check_field('set','room_price')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="房间价格" prop="room_price">
								<el-input-number id="room_price" v-model.number="form['room_price']"
						v-if="user_group === '管理员' || (form['room_information_id'] && $check_field('set','room_price')) || (!form['room_information_id'] && $check_field('add','room_price'))" :disabled="disabledObj['room_price_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','room_price')">{{form['room_price']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','room_location') || $check_field('add','room_location') || $check_field('set','room_location')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="房间位置" prop="room_location">
												<el-input id="room_location" v-model="form['room_location']" placeholder="请输入房间位置"
							  v-if="user_group === '管理员' || (form['room_information_id'] && $check_field('set','room_location')) || (!form['room_information_id'] && $check_field('add','room_location'))" :disabled="disabledObj['room_location_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','room_location')">{{form['room_location']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','room_image') || $check_field('add','room_image') || $check_field('set','room_image')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="房间图片" prop="room_image">
								<el-upload :disabled="disabledObj['room_image_isDisabled']" id="room_image" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_room_image"
						:show-file-list="false" v-if="user_group === '管理员' || (form['room_information_id'] && $check_field('set','room_image')) || (!form['room_information_id'] && $check_field('add','room_image'))">
						<img v-if="form['room_image']" :src="$fullUrl(form['room_image'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','room_image')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['room_image'])" :preview-src-list="[$fullUrl(form['room_image'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','room_introduction') || $check_field('add','room_introduction') || $check_field('set','room_introduction')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="房间介绍" prop="room_introduction">
								<el-input type="textarea" id="room_introduction" v-model="form['room_introduction']" placeholder="请输入房间介绍"
						v-if="user_group === '管理员' || (form['room_information_id'] && $check_field('set','room_introduction')) || (!form['room_information_id'] && $check_field('add','room_introduction'))" :disabled="disabledObj['room_introduction_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','room_introduction')">{{form['room_introduction']}}</div>
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
				field: "room_information_id",
				url_add: "~/api/room_information/add?",
				url_set: "~/api/room_information/set?",
				url_get_obj: "~/api/room_information/get_obj?",
				url_upload: "~/api/room_information/upload?",

				query: {
					"room_information_id": 0,
				},

				form: {
								"room_name":  '', // 房间名称
										"room_type":  '', // 房间房型
										"room_price":  0, // 房间价格
										"room_location":  '', // 房间位置
										"room_image":  '', // 房间图片
										"room_introduction":  '', // 房间介绍
											"room_information_id": 0, // ID
						
				},
				disabledObj:{
								"room_name_isDisabled": false,
										"room_type_isDisabled": false,
					          			"room_price_isDisabled": false,
										"room_location_isDisabled": false,
										"room_image_isDisabled": false,
										"room_introduction_isDisabled": false,
										},

	
						// 房间房型选项列表
				list_room_type: [""],
	
		
		
		
		
	
			}
		},
		methods: {


	
	
			
				/**
			 * 获取房间房型列表
			 */
			async get_list_room_type() {
				var json = await this.$get("~/api/room_type_management/get_list?");
				if(json.result && json.result.list){
					this.list_room_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
			
	
			
	
						/**
			 * 上传房间图片
			 * @param {Object} param 图片参数
			 */
			upload_room_image(param){
						this.uploadFile(param.file, "room_image");
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
					bl = this.$check_action('/room_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/room_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/room_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/room_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/room_information/view','get');
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
						this.get_list_room_type();
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

<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px">

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="联系人姓名" prop="contact_name">
					<el-input v-model="form.contact_name" placeholder="请输入联系人姓名"></el-input>
				</el-form-item>
			</el-col>

			<!--<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">-->
				<!--<el-form-item label="联系人邮箱" prop="contact_email">-->
					<!--<el-input type="email" v-model="form.contact_email" placeholder="请输入联系人邮箱"></el-input>-->
				<!--</el-form-item>-->
			<!--</el-col>-->

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="联系人手机" prop="contact_phone">
					<el-input type="phone" v-model="form.contact_phone" placeholder="请输入联系人手机"></el-input>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="收件地址" prop="contact_address">
					<el-input v-model="form.contact_address" placeholder="请输入收件地址"></el-input>
				</el-form-item>
			</el-col>

			<!--<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">-->
				<!--<el-form-item label="邮政编码" prop="postal_code">-->
					<!--<el-input v-model="form.postal_code" placeholder="请输入邮政编码"></el-input>-->
				<!--</el-form-item>-->
			<!--</el-col>-->

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="描述" prop="description">
					<el-input v-model="form.description" placeholder="请输入描述"></el-input>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="订单状态" prop="state">
					<el-select v-model="form.state" placeholder="请选择">
						<el-option v-for="o in list_state" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8" v-if="user_group == '管理员' || ($check_field('set','user_id') && query.order_id) || $check_field('add','user_id')" class="el_form_item_warp">
				<el-form-item label="买家" prop="user_id">
					<el-select v-model="form.user_id" placeholder="请选择">
						<el-option :key="-1" label="未选择" :value="0">
						</el-option>
						<el-option v-for="(o,i) in list_user" :key="i" :label="o.nickname" :value="o.user_id">
						</el-option>
					</el-select>
				</el-form-item>
			</el-col>

			<!--<el-col :xs="24" :sm="12" :lg="8" v-if="user_group == '管理员' || ($check_field('set','merchant_id') && query.order_id) || $check_field('add','merchant_id')" class="el_form_item_warp">-->
				<!--<el-form-item label="卖家" prop="merchant_id">-->
					<!--<el-select v-model="form.merchant_id" placeholder="请选择">-->
						<!--<el-option :key="-1" label="未选择" :value="0">-->
						<!--</el-option>-->
						<!--<el-option v-for="(o,i) in list_user" :key="i" :label="o.nickname" :value="o.user_id">-->
						<!--</el-option>-->
					<!--</el-select>-->
				<!--</el-form-item>-->
			<!--</el-col>-->

			<el-col :xs="24" :sm="24" :lg="24" class="el_form_btn_warp">
				<el-form-item>
					<el-col :xs="24" :sm="24" :lg="12" class="el_form_btn el_form_btn_1">
						<el-button style="width: 100%; float: left;" type="primary" @click="submit()">提交</el-button>
					</el-col>
					<el-col :xs="24" :sm="24" :lg="12" class="el_form_btn el_form_btn_2">
						<el-button style="width: 100%; float: right;" @click="cancel()">取消</el-button>
					</el-col>
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
				field: "order_id",
				url_add: "~/api/order/add?",
				url_set: "~/api/order/set?",
				url_get_obj: "~/api/order/get_obj?",
				url_upload: "~/api/order/upload?",

				query: {
					order_id: 0
				},

				form: {
					order_id: 0,
					order_number: "",
					goods_id: 0,
					title: "",
					img: "",
					price: 0,
					price_ago: 0,
					num: 0,
					price_count: 0,
					norms: "",
					contact_name: "",
					contact_email: "",
					contact_phone: "",
					contact_address: "",
					postal_code: "",
					user_id: 0,
					merchant_id: 0,
					description: "",
					state: ""
				},

				list_user: [],

				list_state: ["待付款", "已付款"]
			}
		},
		methods: {
			/**
			 * 获取用户
			 */
			async get_list_user() {
				var json = await this.$get("~/api/user/get_list?");
				if(json.result){
					this.list_user = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
		},
		created() {
			this.get_list_user();
		}
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

<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="70px">
			
			<el-col :xs="24" :sm="12" :lg="6" class="el_form_item_warp">
				<el-form-item label="姓名" prop="name">
					<el-input v-model="form.name" >
					</el-input>
				</el-form-item>
			</el-col>
			
			<el-col :xs="24" :sm="12" :lg="6" class="el_form_item_warp">
				<el-form-item label="手机号码" prop="phone">
					<el-input type="phone" v-model="form.phone" >
					</el-input>
				</el-form-item>
			</el-col>
			
			<el-col :xs="24" :sm="12" :lg="6" class="el_form_item_warp">
				<el-form-item label="邮编" prop="postcode">
					<el-input v-model="form.postcode"></el-input>
				</el-form-item>
			</el-col>
			
			<el-col :xs="24" :sm="12" :lg="6" class="el_form_item_warp">
				<el-form-item label="地址" prop="address">
					<el-input v-model="form.address"></el-input>
				</el-form-item>
			</el-col>
			
			<el-col :xs="24" :sm="12" :lg="6" class="el_form_btn_warp">
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
				field: "address_id",
				url_add: "~/api/address/add?",
				url_set: "~/api/address/set?",
				url_get_obj: "~/api/address/get_obj?",
				url_upload: "~/api/address/upload?",
				
				query: {
					address_id: 0
				},
				
				form: {
					address_id: 0,
					name: "",
					phone: "",
					postcode: "",
					address: ""
				},
				
			}
		},
		methods: {

			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadAvatar(param) {
				this.uploadFile(param.file, "avatar");
			},
			
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadImg(param) {
				this.uploadFile(param.file, "img");
			}
			
		},
		created() {
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

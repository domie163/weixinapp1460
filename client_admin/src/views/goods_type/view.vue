<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="80px">

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="分类名称">
					<el-input v-model="form.name"></el-input>
				</el-form-item>
			</el-col>

			<!--<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">-->
				<!--<el-form-item label="图标" prop="icon">-->
					<!--<el-upload class="avatar-uploader" drag accept="image/gif, image/jpeg, image/png, image/jpg"-->
						<!--action="" :http-request="uploadImg" :show-file-list="false">-->
						<!--<img v-if="form.icon" :src="$fullUrl(form.icon)" class="avatar">-->
						<!--<i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
					<!--</el-upload>-->
				<!--</el-form-item>-->
			<!--</el-col>-->
			<!---->
			<!--<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">-->
				<!--<el-form-item label="描述">-->
					<!--<el-input type="textarea" :rows="10" v-model="form.desc" placeholder="请输入内容"></el-input>-->
				<!--</el-form-item>-->
			<!--</el-col>-->

			<el-col :xs="24" :sm="24" :lg="24" class="el_form_btn_warp">
				<el-form-item>
					<el-button style="width: 100%; float: left;" type="primary" @click="submit()">提交</el-button>
					<el-button style="width: 100%; float: right;" @click="cancel()">取消</el-button>
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
				field: "type_id",
				url_add: "~/api/goods_type/add?",
				url_set: "~/api/goods_type/set?",
				url_get_obj: "~/api/goods_type/get_obj?",
				url_upload: "~/api/goods_type/upload?",

				query: {
					type_id: 0
				},

				form: {
					type_id: 0,
					name: "",
					desc: "",
					icon: ""
				},

			}
		},
		methods: {
			/**
			 * 上传图标
			 * @param {Object} param
			 */
			uploadImg(param) {
				this.uploadFile(param.file, "icon");
			},
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

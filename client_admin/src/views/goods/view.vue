<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="70px">

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="封面图" prop="avatar">
					<el-upload class="avatar-uploader" drag accept="image/gif, image/jpeg, image/png, image/jpg"
						action="" :http-request="uploadImg" :show-file-list="false">
						<img v-if="form.img" :src="form.img" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="主图_1" prop="avatar">
					<el-upload class="avatar-uploader" drag accept="image/gif, image/jpeg, image/png, image/jpg"
						action="" :http-request="uploadImg_1" :show-file-list="false">
						<img v-if="form.img_1" :src="form.img_1" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="主图_2" prop="avatar">
					<el-upload class="avatar-uploader" drag accept="image/gif, image/jpeg, image/png, image/jpg"
						action="" :http-request="uploadImg_2" :show-file-list="false">
						<img v-if="form.img_2" :src="form.img_2" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="主图_3" prop="avatar">
					<el-upload class="avatar-uploader" drag accept="image/gif, image/jpeg, image/png, image/jpg"
						action="" :http-request="uploadImg_3" :show-file-list="false">
						<img v-if="form.img_3" :src="form.img_3" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="主图_4" prop="avatar">
					<el-upload class="avatar-uploader" drag accept="image/gif, image/jpeg, image/png, image/jpg"
						action="" :http-request="uploadImg_4" :show-file-list="false">
						<img v-if="form.img_4" :src="form.img_4" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="主图_5" prop="avatar">
					<el-upload class="avatar-uploader" drag accept="image/gif, image/jpeg, image/png, image/jpg"
						action="" :http-request="uploadImg_5" :show-file-list="false">
						<img v-if="form.img_5" :src="form.img_5" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="12" class="el_form_item_warp">
				<el-form-item label="标题" prop="title">
					<el-input v-model="form.title" placeholder="请输入标题"></el-input>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="12" class="el_form_item_warp">
				<el-form-item label="描述" prop="description">
					<el-input v-model="form.description" placeholder="请输入描述"></el-input>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="原价" prop="price_ago">
					<el-input-number v-model="form.price_ago"></el-input-number>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="卖价" prop="price">
					<el-input-number v-model="form.price"></el-input-number>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="点击量" prop="hits">
					<el-input-number v-model="form.hits" placeholder="请输入点击量"></el-input-number>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="销量" prop="sales">
					<el-input-number v-model="form.sales" placeholder="请输入销量"></el-input-number>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="分类" prop="type">
					<el-select v-model="form.type" @change="get_source">
						<el-option v-for="(o ,i) in list_goods_type" :value="o.name" :key="o.name" :label="o.name"
							></el-option>
					</el-select>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="库存" prop="inventory">
					<el-input-number v-model="form.inventory" placeholder="请输入库存"></el-input-number>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="正文" prop="content">
					<quill-editor v-model="form.content">
					</quill-editor>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="24" :lg="24" v-show="!form.source_table" style="text-align: center;" class="el_form_btn_warp">
				<el-button type="primary" @click="submit()">提交</el-button>
				<el-button @click="cancel()">取消</el-button>
			</el-col>

					<!-- 食物信息 -->
			<diy_food_information v-if="form.source_table === 'food_information'" :query="{food_information_id:form.source_id}" :form_goods="form" :func_check="checkForm" :func_submit="submit" ></diy_food_information>
					
		</el-form>

	</el-main>
</template>

<script>
	import mixin from "../../mixins/page.js";
			import diy_food_information from "../../components/diy/diy_food_information.vue";
					
	export default {
		mixins: [mixin],
		components:{
					diy_food_information,
							},
		data() {
			return {
				field: "goods_id",
				url_add: "~/api/goods/add?",
				url_set: "~/api/goods/set?",
				url_get_obj: "~/api/goods/get_obj?",
				url_upload: "~/api/goods/upload?",

				query: {
					goods_id: 0
				},

				form: {
					goods_id: 0,
					title: "",
					img: "",
					description: "",
					price_ago: 0,
					price: 0,
					inventory: 0,
					type: "",
					hits: 0,
					content: "",
					img_1: "",
					img_2: "",
					img_3: "",
					img_4: "",
					img_5: "",
					source_table: "",
					source_field: "",
					source_id: 0
				},

						"form_food_information": {},
					
				list_goods_type: []

			}
		},
		methods: {

			/**
			 * 获取来源
			 * @param {Object} form 表单
			 * @param {Object} o 类
			 */
			get_source(name) {
				var form = this.form;
				var list = this.list_goods_type;
				for(var i=0;i<list.length;i++){
					var o = list[i];
					if(o.name == name){
						form.source_table = o.source_table;
						form.source_field = o.source_field;
						form.source_id = 0;
						break;
					}
				}
			},

			/**
			 * 上传封面图
			 * @param {Object} param
			 */
			uploadImg(param) {
				this.uploadFile(param.file, "img");
			},

			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadImg_1(param) {
				this.uploadFile(param.file, "img_1");
			},

			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadImg_2(param) {
				this.uploadFile(param.file, "img_2");
			},

			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadImg_3(param) {
				this.uploadFile(param.file, "img_3");
			},

			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadImg_4(param) {
				this.uploadFile(param.file, "img_4");
			},

			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadImg_5(param) {
				this.uploadFile(param.file, "img_5");
			},

			// 获取商品类型
			async get_goods_type() {
				var list = await this.$get("~/api/goods_type/get_list?");
				this.list_goods_type = list.result.list;
			},
		},
		created() {
			this.get_goods_type();
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

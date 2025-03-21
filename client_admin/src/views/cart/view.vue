<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="80px">
			
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="购买数量">
					<el-input-number @change="set_price_count" v-model="form.num"></el-input-number>
				</el-form-item>
			</el-col>
			
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="修改总价">
					<el-input-number v-model="form.price_count"></el-input-number>
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
	import mixin from "../../mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {

				"title": "cart",
				field: "cart_id",
				
				url_add: "~/api/cart/add?",
				url_set: "~/api/cart/set?",
				url_get_obj: "~/api/cart/get_obj?",
				
				query: {
					cart_id: 0
				},

				form: {
					cart_id: 0,
					title: "",
					content: ""
				},
				
			}
		},
		methods: {
			set_price_count(){
				var obj = this.form;
				obj.price_count = obj.price * obj.num;
			}
		},
		created() {
		}
	}
</script>

<style>
</style>

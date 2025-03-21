<template>
	<el-main class="bg table_wrap">
		<el-form label-position="right" :model="query" class="form p_4" label-width="120">
			<el-row>
				<el-col :xs="24" :sm="12" :lg="6" class="el_form_search_wrap">
					<el-form-item label="订单号">
						<el-input v-model="query.order_number"></el-input>
					</el-form-item>
				</el-col>
				<el-col :xs="24" :sm="12" :lg="6" class="el_form_search_wrap">
					<el-form-item label="商品名称">
						<el-input v-model="query.title"></el-input>
					</el-form-item>
				</el-col>
				<el-col :xs="24" :sm="12" :lg="6" class="el_form_search_wrap">
					<el-form-item label="联系人姓名">
						<el-input v-model="query.contact_name"></el-input>
					</el-form-item>
				</el-col>

				<el-col :xs="24" :sm="12" :lg="6" class="el_form_search_wrap">
					<el-form-item label="状态">
						<el-select v-model="query.state">
							<el-option :key="-1" :value="0" :label="'无'"></el-option>
							<el-option v-for="(o,i) in list_state" :key="i" :value="o" :label="o">
							</el-option>
						</el-select>
					</el-form-item>
				</el-col>

				<el-col :xs="24" :sm="24" :lg="24" class="search_btn_wrap">
					<el-col :xs="24" :sm="12" :lg="12" class="search_btn_1">
						<el-form-item>
							<el-button type="primary" @click="search()" class="search_btn_find">查询</el-button>
							<el-button @click="reset()" class="search_btn_reset">重置</el-button>
						</el-form-item>
					</el-col>
					<el-col :xs="24" :sm="12" :lg="12" class="search_btn_2">
						<el-form-item>
							<el-button v-if="user_group == '管理员' || $check_action('/order/table','del')"
								class="float-right search_btn_del" type="danger" @click="delInfo()">删除</el-button>
						</el-form-item>
					</el-col>
				</el-col>

			</el-row>
		</el-form>
		<el-table border :data="list" @selection-change="selectionChange" @sort-change="$sortChange" style="width: 100%"
			stripe>

			<el-table-column fixed type="selection" tooltip-effect="dark" width="55">
			</el-table-column>

			<el-table-column fixed prop="title" label="商品名称" width="120">
			</el-table-column>

			<el-table-column prop="order_number" label="订单号" min-width="200">
			</el-table-column>

			<el-table-column prop="img" label="商品图片" min-width="110">
				<template slot-scope="scope">
					<div class="demo-image__preview">
						<el-image style="width: 100px; height: 100px" :src="$fullUrl(scope.row.img)"
							:preview-src-list="[$fullUrl(scope.row.img)]">
							<div slot="error" class="image-slot">
								<img src="/img/error.png" style="width: 90px; height: 90px" />
							</div>
						</el-image>
					</div>
				</template>
			</el-table-column>

			<el-table-column prop="price" label="价格" sortable min-width="100">
			</el-table-column>

			<el-table-column prop="price_ago" label="原价" sortable min-width="100">
			</el-table-column>

			<el-table-column prop="num" label="购买数量" sortable min-width="120">
			</el-table-column>

			<el-table-column prop="price_count" label="总价" sortable min-width="100">
			</el-table-column>

			<el-table-column prop="contact_name" label="联系人名称" sortable min-width="120">
			</el-table-column>

			<el-table-column prop="contact_address" label="联系人地址" min-width="180">
			</el-table-column>

			<el-table-column prop="state" label="订单状态" min-width="120">
				<template slot-scope="scope">
					<div v-if="scope.row.state === '待退款'">
						<el-button type="danger" @click="set_state(scope.row)" round>待退款</el-button>
					</div>
					<div v-else>
						{{scope.row.state}}
					</div>
				</template>
			</el-table-column>

			<el-table-column prop="user_id" label="买家" sortable min-width="100">
				<template slot-scope="scope">
					<div>
						{{list_user.getVal("nickname",{"user_id":scope.row.user_id})}}
					</div>
				</template>
			</el-table-column>

			<el-table-column sortable prop="create_time" label="创建时间" min-width="200">
			    <template slot-scope="scope">
			        {{ $toTime(scope.row["create_time"],"yyyy-MM-dd hh:mm:ss") }}
			    </template>
			</el-table-column>

			<el-table-column sortable prop="update_time" label="更新时间" min-width="200">
			    <template slot-scope="scope">
			        {{ $toTime(scope.row["update_time"],"yyyy-MM-dd hh:mm:ss") }}
			    </template>
			</el-table-column>

			<!-- 操作 -->
			<el-table-column fixed="right" label="操作" width="80">
				<template slot-scope="scope">
					<router-link class="el-button el-button--small is-plain el-button--primary"
						:to="'./view?' + field + '=' + scope.row[field]" size="small">
            详情
					</router-link>
				</template>
			</el-table-column>
			<!-- /操作 -->

		</el-table>

		<!-- 分页器 -->
		<div class="mt text_center">
			<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
				:current-page="query.page" :page-sizes="[7, 10, 30, 100]" :page-size="query.size"
				layout="total, sizes, prev, pager, next, jumper" :total="count">
			</el-pagination>
		</div>
		<!-- /分页器 -->

		<el-dialog title="退款审批" :visible.sync="refund_view" width="30%" :before-close="handleClose">

			<span>
				<el-form ref="form" :model="form" label-width="80px">
					<el-form-item label="退款状态">
						<el-select v-model="form.state">
							<el-option label="待退款" value="待退款" :key="1"></el-option>
							<el-option label="已退款" value="已退款" :key="2"></el-option>
							<el-option label="已拒绝" value="已拒绝" :key="3"></el-option>
						</el-select>
					</el-form-item>
				</el-form>
			</span>

			<span slot="footer" class="dialog-footer">
				<el-button @click="refund_view = false; form = {state:''}">取消</el-button>
				<el-button type="primary" @click="toSet()">提交</el-button>
			</span>

		</el-dialog>

	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				url_get_list: "~/api/order/get_list?like=0",
				url_set: "~/api/order/set?",
				url_del: "~/api/order/del?",

				// 字段ID
				field: "order_id",

				refund_view: false,

				form: {
					state: ""
				},

				query: {
					size: 10,
					page: 1,
					state: "",
					order_number: "",
					name: "",
					contact_name: "",
				},

				// 数据
				list: [],

				// 获取用户信息
				list_user: [],

				// 状态组
				list_state: ["待付款", "已付款"]
			}
		},
		methods: {
			get_list_before(){
				if(this.user_group==='卖家'){
					this.url_get_list = "~/api/order/get_business_order_list?user_id="+this.user.user_id;
				}else {
          if (this.user_group!=='管理员'){
            this.url_get_list = "~/api/order/get_list?user_id="+this.user.user_id;
          }
        }
			},
			/**
			 * 确认关闭
			 * @param {Object} done
			 */
			handleClose(done) {
				var _this = this;
				this.$confirm('确认关闭？')
					.then(_ => {
						_this.form = {
							state: ""
						};
						done();
					})
					.catch(_ => {});
			},

			/**
			 * 修改状态
			 */
			toSet() {
				this.refund_view = false;
				this.set({
					state: this.form.state
				}, {
					order_id: this.form.order_id
				});
				this.form = {
					state: ""
				};
				this.get_list();
			},

			/**
			 * @description 显示退款窗口
			 * @param {Object} o 对象集
			 */
			set_state(o) {
				this.form = Object.assign({}, o);
				this.refund_view = 1;
			},

			/**
			 * @description 删除
			 * @param {Object} index
			 * @param {Object} rows
			 */
			deleteRow(index, rows) {
				rows.splice(index, 1);
			},

			/**
			 * @description 表格样式
			 */
			table_class({
				row,
				column,
				rowIndex,
				columnIndex
			}) {
				return "table_class";
			},

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

<style type="text/css">
	.refund_view {
		top: 0;
	}

	.bg {
		background: white;
	}

	.form.p_4 {
		padding: 1rem;
	}

	.form .el-input {
		width: initial;
	}

	.mt {
		margin-top: 1rem;
	}

	.float-right {
		float: right;
	}

	.mr-1 {
		margin-right: 1rem;
	}

	.el-table .table_class {
		background: rgba(150, 150, 150, 0.1);
		text-align: center;
	}

	.text_center {
		text-align: center;
	}

	.float-right {
		float: right;
	}
</style>

<template>
	<el-main class="bg table_wrap">
		<el-form label-position="right" :model="query" class="form p_4" label-width="120">
			<el-row>
				<el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
					<el-form-item label="标题">
						<el-input v-model="query.title"></el-input>
					</el-form-item>
				</el-col>

				<el-col :xs="24" :sm="14" :lg="8" class="el_form_search_wrap">
					<el-form-item label="标签">
						<el-input v-model="query.tag"></el-input>
					</el-form-item>
				</el-col>

				<el-col :xs="24" :sm="14" :lg="8" class="el_form_search_wrap">
					<el-form-item label="分类">
						<el-select v-model="query.type">
							<el-option :key="-1" :value="''" :label="'无'"></el-option>
							<el-option v-for="(o,i) in list_goods_type" :key="i" :value="o.name" :label="o.name"></el-option>
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
							<el-button v-if="user_group == '管理员' || $check_action('/goods/table','del')" class="float-right search_btn_del" type="danger" @click="delInfo()">删除</el-button>
							<router-link v-if="user_group == '管理员' || $check_action('/goods/view')" class="el-button float-right el-button--default el-button--primary search_btn_add"
								to="./view?">添加
							</router-link>
						</el-form-item>
					</el-col>
				</el-col>
			</el-row>
		</el-form>
		<el-table border :data="list" @selection-change="selectionChange" @sort-change="$sortChange" style="width: 100%" stripe>

			<el-table-column fixed type="selection" tooltip-effect="dark" width="50">
			</el-table-column>

			<el-table-column fixed prop="title" label="标题" sortable width="100">
			</el-table-column>

			<el-table-column prop="img" label="封面图" min-width="110">
				<template slot-scope="scope">
					<el-image style="width: 100px; height: 100px" :src="$fullUrl(scope.row.img)" :preview-src-list="[$fullUrl(scope.row.img)]">
						<div slot="error" class="image-slot">
							<img src="/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</template>
			</el-table-column>

			<el-table-column prop="price" label="卖价" sortable min-width="120">
			</el-table-column>

			<el-table-column prop="price_ago" label="原价" sortable min-width="120">
			</el-table-column>

			<el-table-column prop="inventory" label="商品库存" sortable min-width="120">
			</el-table-column>

			<el-table-column prop="sales" label="销量" sortable min-width="120">
			</el-table-column>

			<el-table-column prop="hits" label="点击量" sortable min-width="120">
			</el-table-column>

			<el-table-column prop="type" label="产品分类" min-width="180">
			</el-table-column>

			<el-table-column prop="description" label="描述" min-width="200">
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
			<el-table-column fixed="right" label="操作" width="180">
				<template slot-scope="scope">
					<router-link class="el-button el-button--small is-plain el-button--primary" :to="'./view?' + field + '=' + scope.row[field]"
					 size="small">详情</router-link>
					 <router-link class="el-button el-button--small is-plain el-button--primary"
					 	:to="'../comment/table?size=10&page=1&source_table=goods&source_field=' + field + '&source_id=' + scope.row[field]" size="small">
					 	查看评论
					 </router-link>
				</template>
			</el-table-column>
			<!-- /操作 -->

		</el-table>

		<!-- 分页器 -->
		<div class="mt text_center">
			<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="query.page"
			 :page-sizes="[7, 10, 30, 100]" :page-size="query.size" layout="total, sizes, prev, pager, next, jumper" :total="count">
			</el-pagination>
		</div>
		<!-- /分页器 -->

	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				// 获取数据地址
				url_get_list: "~/api/goods/get_list?like=0",
				url_del: "~/api/goods/del?",

				// 字段ID
				field:"goods_id",

				// 查询
				query: {
					size: 10,
					page: 1,
					title: "",
					keywords: "",
					type: "",
				},

				// 数据
				list: [],

				// 商品分类
				list_goods_type:[]
			}
		},
		methods: {
			deleteRow(index, rows) {
				rows.splice(index, 1);
			},
			table_class({row, column, rowIndex, columnIndex}){
				return "table_class";
			},
			/**
			 * 获取商品分类
			 */
			async get_goods_type() {
				var json = await this.$get("~/api/goods_type/get_list?");
				if(json.result){
					this.list_goods_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
		},
		created(){
			this.get_goods_type();
		}
	}
</script>

<style type="text/css">
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

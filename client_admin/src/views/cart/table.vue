<template>
	<el-main class="bg table_wrap">
		<el-form label-position="right" :model="query" class="form p_4" label-width="120">
			<el-row>
				<el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
					<el-form-item label="标题">
						<el-input v-model="query.title"></el-input>
					</el-form-item>
				</el-col>
				<el-col :xs="24" :sm="24" :lg="8" class="el_form_search_wrap">
					<el-form-item label="关键词">
						<el-input v-model="query.keywords"></el-input>
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
							<el-button v-if="user_group == '管理员' || $check_action('/cart/table','del')" class="float-right search_btn_del" type="danger" @click="delInfo()">删除</el-button>loat-right" type="danger" @click="delInfo()">删除</el-button>
							<router-link v-if="user_group == '管理员' || $check_action('/cart/view')" class="el-button float-right el-button--default el-button--primary search_btn_add"
								to="./view?">添加
							</router-link>
						</el-form-item>
					</el-col>
				</el-col>
			</el-row>
		</el-form>

		<el-table border :data="list" @selection-change="selectionChange" style="width: 100%">

			<!-- 多选按钮 -->
			<el-table-column fixed type="selection" tooltip-effect="dark" width="55">
			</el-table-column>
			<!-- /多选按钮 -->

			<el-table-column fixed label="标题" prop="title" width="120">
			</el-table-column>

			<el-table-column label="封面图" prop="img" min-width="100">
				<template slot-scope="scope">
					<el-image style="width: 100px; height: 100px" :src="scope.row.img" :preview-src-list="[scope.row.img]">
						<div slot="error" class="image-slot">
							<img src="/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</template>
			</el-table-column>

			<el-table-column label="单价" prop="price" min-width="80">
			</el-table-column>

			<el-table-column label="原价" prop="price_old" min-width="80">
			</el-table-column>

			<el-table-column label="数量" prop="num" min-width="80">
			</el-table-column>

			<el-table-column label="总价" prop="price_count" min-width="150">
			</el-table-column>

			<el-table-column label="状态" prop="state" min-width="100">
				<template slot-scope="scope">
					{{getObj(list_state,scope.row.state).name}}
				</template>
			</el-table-column>

			<el-table-column prop="create_time" label="创建时间" min-width="200">
			    <template slot-scope="scope">
			    	{{ $toTime(scope.row["create_time"],"yyyy-MM-dd hh:mm:ss") }}
			    </template>
			</el-table-column>

			<el-table-column prop="update_time" label="更新时间" min-width="200">
			    <template slot-scope="scope">
			    	{{ $toTime(scope.row["update_time"],"yyyy-MM-dd hh:mm:ss") }}
			    </template>
			</el-table-column>

			<!-- 操作 -->
			<el-table-column fixed="right" label="操作" width="60">
				<template slot-scope="scope">
					<router-link class="el-button el-button--small is-circle is-plain el-button--primary" :to="'./view?' + field + '=' + scope.row[field]"
					 size="small"><i class="el-icon-edit"></i></router-link>
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
	import mixin from "../../mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {

				// 表
				table:"cart",

				// 获取数据地址
				url_get_list: "~/api/cart/get_list?like=0",
				url_del: "~/api/cart/del?",

				// 字段ID
				field:"cart_id",

				// 查询
				query: {
					size: 10,
					page: 1,
					title: "",
					keywords: ""
				},

				// 数据
				list: [],

				// 状态
				list_state: [
					{"name":"使用中","value":1},
					{"name":"已失效","value":0}
				]
			}
		},
		methods: {
			async get_user_group(){
				var list = await this.$get("~/api/collect/get_list?");
				this.user_group = list.result.list;
			},
			table_class({row, column, rowIndex, columnIndex}){
				return "table_class";
			}
			// http://localhost:3000/api/collect/count_group?
		},
		created(){
		}
	}
</script>

<style type="text/css">

</style>

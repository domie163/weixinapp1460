<template>
	<el-menu class="side el-menu-vertical-demo" default-active="/" @open="handleOpen" @close="handleClose"
		background-color="#545c64" text-color="#fff" active-text-color="#38f" :collapse="isCollapse" :router="true">
		<el-menu-item index="/">
			<i class="el-icon-s-home"></i>
			<span slot="title">后台首页</span>
		</el-menu-item>

		<el-submenu index="web"
			v-show="user_group == '管理员' || $check_group(['/nav/table','/link/table','/slides/table','/ad/table'])">
			<template slot="title"><i class="el-icon-picture-outline"></i><span>轮播图</span></template>
			<!-- <el-menu-item v-show="user_group == '管理员' || $check_action('/auth/table')" index="/auth/table">
				<span>权限管理</span>
			</el-menu-item> -->
			<!-- <el-menu-item v-show="user_group == '管理员' || $check_action('/link/table')" index="/link/table">
				<span>友情链接</span>
			</el-menu-item> -->
			<el-menu-item v-show="user_group == '管理员' || $check_action('/slides/table')" index="/slides/table">
				<span>轮播图管理</span>
			</el-menu-item>
			<!-- <el-menu-item v-show="user_group == '管理员' || $check_action('/ad/table')" index="/ad/table">
				<span>广告</span>
			</el-menu-item> -->
		</el-submenu>

		<el-submenu index="notic"
			v-show="user_group == '管理员' || $check_group(['/notice/table'])">
			<template slot="title"><i class="el-icon-warning-outline"></i><span>公告信息管理</span></template>
			<el-menu-item v-show="user_group == '管理员' || $check_action('/notice/table')" index="/notice/table">
				<span>公告信息</span>
			</el-menu-item>
		</el-submenu>


	


		<el-submenu index="mall" v-show="user_group == '管理员' || $check_group(['/goods/table','/order/table'])">
			<template slot="title"><i class="el-icon-shopping-cart-2"></i><span>商城管理</span></template>
									<el-menu-item v-show="user_group == '管理员' || $check_action('/food_information/table')" index="/food_information/table"
						  class="bg-hover"><span>食物信息</span></el-menu-item>
																		<!--<el-menu-item v-show="user_group == '管理员' || $check_action('/goods/table')" index="/goods/table"
				class="bg-hover"><span>{{$page_title("/goods/table") || "商品"}}</span></el-menu-item>-->
			<el-menu-item v-show="user_group == '管理员' || $check_action('/goods_type/table')" index="/goods_type/table"
				class="bg-hover"><span>{{$page_title("/goods_type/table") || "分类列表"}}</span></el-menu-item>

			<el-menu-item v-show="user_group == '管理员' || $check_action('/order/table')" index="/order/table"
				class="bg-hover"><span>{{$page_title("/order/table") || "订单"}}</span></el-menu-item>
		</el-submenu>

		<el-submenu index="user"
					v-show="user_group == '管理员' || $check_group(['/user/table','/user_group/table'
						,'/customer_users/table'
													])">
			<template slot="title"><i class="el-icon-s-custom"></i><span>系统用户</span></template>
			<el-menu-item index="/user/table" v-show="user_group == '管理员'"><span>管理员</span></el-menu-item>
						<el-menu-item index="/customer_users/table" v-show="user_group == '管理员' || $check_action('/customer_users/table')"><span>顾客用户</span></el-menu-item>
									<!-- <el-menu-item index="/user_group/table"><span>用户组</span></el-menu-item> -->
		</el-submenu>


		<el-submenu index="more" v-show="auth.length">
			<template slot="title"><i class="el-icon-menu"></i><span>模块管理</span></template>
			<el-menu-item v-for="(o, idx) in auth" v-show="$check_action(o.path)" :index="o.path">
				<span>{{o.page_title}}</span>
			</el-menu-item>
		</el-submenu>
	</el-menu>
</template>

<script>
	export default {
		props: {
			isCollapse: {
				trye: Boolean,
				default: false
			}
		},
		data: function() {
			return {
				user_group: this.$store.state.user.user_group
			}
		},
		methods: {
			handleOpen(key, keyPath) {
				// console.log(key, keyPath);
			},

			handleClose(key, keyPath) {
				// console.log(key, keyPath);
			},

		},
		computed: {
			auth() {
				var tables = this.$store.state.web.auth;
				var lt = [];
				for (var i = 0; i < tables.length; i++) {
					var o = tables[i];
					if (o.path.indexOf("/table") !== -1) {
						let list = ["exam","article", "article_type", ,"comment","goods","order","notice"];
												list.push("food_information");
																						list.push("customer_users");
												if (list.indexOf(o.table_name) === -1) {
							lt.push(o);
						}
					}
				}
				return lt;
			}
		}
	}
</script>


<style scoped="scoped">
	.el-menu-vertical-demo:not(.el-menu--collapse) {
		width: 16rem;
	}

	.side {
		float: left;
		background: #545c64;
		color: #fff;
		overflow-y: scroll;
		height: 100vh;
	}
	.side::-webkit-scrollbar{
		display: none;
	}
</style>

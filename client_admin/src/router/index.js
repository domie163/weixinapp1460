import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue';
import login from '../views/login.vue';
import forgot from '../views/forgot.vue';
Vue.use(VueRouter)

const routes = [
	// 主页
	{
		path: '/',
		name: 'index',
		component: index,
		meta: {
			index: 0,
			title: '首页'
		}
	},

	// 登录
	{
		path: '/login',
		name: 'login',
		component: login,
		meta: {
			index: 0,
			title: '登录'
		}
	},


	// 忘记密码
	{
		path: '/forgot',
		name: "forgot",
		component: forgot,
		meta: {
			index: 0,
			title: '忘记密码'
		}
	},

	// 修改密码
	{
		path: '/user/password',
		name: "password",
		component: () => import("../views/user/password.vue"),
		meta: {
			index: 0,
			title: '修改密码'
		}
	},

	// 视频播放页
	{
		path: "/media/video",
		name: "video",
		component: () => import('../views/media/video.vue'),
		meta: {
			index: 0,
			title: "视频"
		}
	},

	// 音频播放页
	{
		path: "/media/audio",
		name: "audio",
		component: () => import('../views/media/audio.vue'),
		meta: {
			index: 0,
			title: "音频"
		}
	},



	// 友情链接路由
	// {
	// 	path: '/link/table',
	// 	name: 'link_table',
	// 	component: () => import('../views/link/table.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '链接列表'
	// 	}
	// },
	// {
	// 	path: '/link/view',
	// 	name: 'link_view',
	// 	component: () => import('../views/link/view.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '链接详情'
	// 	}
	// },

	// 轮播图路由
	{
		path: '/slides/table',
		name: 'slides_table',
		component: () => import('../views/slides/table.vue'),
		meta: {
			index: 0,
			title: '轮播图列表'
		}
	},
	{
		path: '/slides/view',
		name: 'slides_view',
		component: () => import('../views/slides/view.vue'),
		meta: {
			index: 0,
			title: '轮播图详情'
		}
	},
	
	// 广告路由
	// {
	// 	path: '/ad/table',
	// 	name: 'ad_table',
	// 	component: () => import('../views/ad/table.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '广告列表'
	// 	}
	// },
	// {
	// 	path: '/ad/view',
	// 	name: 'ad_view',
	// 	component: () => import('../views/ad/view.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '广告详情'
	// 	}
	// },



	// 公告路由
	{
		path: '/notice/table',
		name: 'notice_table',
		component: () => import('../views/notice/table.vue'),
		meta: {
			index: 0,
			title: '公告信息列表'
		}
	},
	{
		path: '/notice/view',
		name: 'notice_view',
		component: () => import('../views/notice/view.vue'),
		meta: {
			index: 0,
			title: '公告信息详情'
		}
	},

	// 商品路由
	// {
	// 	path: '/goods/table',
	// 	name: 'goods_table',
	// 	component: () => import('../views/goods/table.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '商品列表'
	// 	}
	// },
	// {
	// 	path: '/goods/view',
	// 	name: 'goods_view',
	// 	component: () => import('../views/goods/view.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '商品详情'
	// 	}
	// },

	// 商品分类路由
	{
		path: '/goods_type/table',
		name: 'goods_type_table',
		component: () => import('../views/goods_type/table.vue'),
		meta: {
			index: 0,
			title: '商品分类列表'
		}
	},
	{
		path: '/goods_type/view',
		name: 'goods_type_view',
		component: () => import('../views/goods_type/view.vue'),
		meta: {
			index: 0,
			title: '商品分类详情'
		}
	},

	// 订单路由
	{
		path: '/order/table',
		name: 'order_table',
		component: () => import('../views/order/table.vue'),
		meta: {
			index: 0,
			title: '订单列表'
		}
	},
	{
		path: '/order/view',
		name: 'order_view',
		component: () => import('../views/order/view.vue'),
		meta: {
			index: 0,
			title: '订单详情'
		}
	},

	// 评论路由
	{
		path: '/comment/table',
		name: 'comment_table',
		component: () => import('../views/comment/table.vue'),
		meta: {
			index: 0,
			title: '评论列表'
		}
	},
	{
		path: '/comment/view',
		name: 'comment_view',
		component: () => import('../views/comment/view.vue'),
		meta: {
			index: 0,
			title: '评论详情'
		}
	},

	// 顾客用户路由
	{
		path: '/customer_users/table',
		name: 'customer_users_table',
		component: () => import('../views/customer_users/table.vue'),
		meta: {
			index: 0,
			title: '顾客用户列表'
		}
	},
	{
		path: '/customer_users/view',
		name: 'customer_users_view',
		component: () => import('../views/customer_users/view.vue'),
		meta: {
			index: 0,
			title: '顾客用户详情'
		}
	},
	// 食物信息路由
	{
		path: '/food_information/table',
		name: 'food_information_table',
		component: () => import('../views/food_information/table.vue'),
		meta: {
			index: 0,
			title: '食物信息列表'
		}
	},
	{
		path: '/food_information/view',
		name: 'food_information_view',
		component: () => import('../views/food_information/view.vue'),
		meta: {
			index: 0,
			title: '食物信息详情'
		}
	},
	// 食物订单路由
	{
		path: '/food_orders/table',
		name: 'food_orders_table',
		component: () => import('../views/food_orders/table.vue'),
		meta: {
			index: 0,
			title: '食物订单列表'
		}
	},
	{
		path: '/food_orders/view',
		name: 'food_orders_view',
		component: () => import('../views/food_orders/view.vue'),
		meta: {
			index: 0,
			title: '食物订单详情'
		}
	},
	// 房间信息路由
	{
		path: '/room_information/table',
		name: 'room_information_table',
		component: () => import('../views/room_information/table.vue'),
		meta: {
			index: 0,
			title: '房间信息列表'
		}
	},
	{
		path: '/room_information/view',
		name: 'room_information_view',
		component: () => import('../views/room_information/view.vue'),
		meta: {
			index: 0,
			title: '房间信息详情'
		}
	},
	// 房间订单路由
	{
		path: '/room_orders/table',
		name: 'room_orders_table',
		component: () => import('../views/room_orders/table.vue'),
		meta: {
			index: 0,
			title: '房间订单列表'
		}
	},
	{
		path: '/room_orders/view',
		name: 'room_orders_view',
		component: () => import('../views/room_orders/view.vue'),
		meta: {
			index: 0,
			title: '房间订单详情'
		}
	},
	// 房型管理路由
	{
		path: '/room_type_management/table',
		name: 'room_type_management_table',
		component: () => import('../views/room_type_management/table.vue'),
		meta: {
			index: 0,
			title: '房型管理列表'
		}
	},
	{
		path: '/room_type_management/view',
		name: 'room_type_management_view',
		component: () => import('../views/room_type_management/view.vue'),
		meta: {
			index: 0,
			title: '房型管理详情'
		}
	},

	// 用户路由
	{
		path: '/user/table',
		name: 'user_table',
		component: () => import('../views/user/table.vue'),
		meta: {
			index: 0,
			title: '用户列表'
		}
	},
	{
		path: '/user/view',
		name: 'user_view',
		component: () => import('../views/user/view.vue'),
		meta: {
			index: 0,
			title: '用户详情'
		}
	},
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue'),
		meta: {
			index: 0,
			title: '个人信息'
		}
	},
	// 用户组路由
	{
		path: '/user_group/table',
		name: 'user_group_table',
		component: () => import('../views/user_group/table.vue'),
		meta: {
			index: 0,
			title: '用户组列表'
		}
	},
	{
		path: '/user_group/view',
		name: 'user_group_view',
		component: () => import('../views/user_group/view.vue'),
		meta: {
			index: 0,
			title: '用户组详情'
		}
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.beforeEach((to, from, next) => {
	let token = to.query.token;
	if (token){
		$.db.set("token",token,120);
	}
	next();
})

router.afterEach((to, from, next) => {
	let title = "酒店管理系统小程序-admin";
	document.title = title;
})

export default router

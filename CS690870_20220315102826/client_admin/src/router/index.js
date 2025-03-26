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
	// 教师路由
	{
		path: '/teacher/table',
		name: 'teacher_table',
		component: () => import('../views/teacher/table.vue')
	},
	{
		path: '/teacher/view',
		name: 'teacher_view',
		component: () => import('../views/teacher/view.vue')
	},
	// 班级管理路由
	{
		path: '/class_management/table',
		name: 'class_management_table',
		component: () => import('../views/class_management/table.vue')
	},
	{
		path: '/class_management/view',
		name: 'class_management_view',
		component: () => import('../views/class_management/view.vue')
	},
	// 学生路由
	{
		path: '/student/table',
		name: 'student_table',
		component: () => import('../views/student/table.vue')
	},
	{
		path: '/student/view',
		name: 'student_view',
		component: () => import('../views/student/view.vue')
	},
	// 通知公告路由
	{
		path: '/notice_announcement/table',
		name: 'notice_announcement_table',
		component: () => import('../views/notice_announcement/table.vue')
	},
	{
		path: '/notice_announcement/view',
		name: 'notice_announcement_view',
		component: () => import('../views/notice_announcement/view.vue')
	},
	// 每日安排路由
	{
		path: '/daily_schedule/table',
		name: 'daily_schedule_table',
		component: () => import('../views/daily_schedule/table.vue')
	},
	{
		path: '/daily_schedule/view',
		name: 'daily_schedule_view',
		component: () => import('../views/daily_schedule/view.vue')
	},
	// 每日打卡路由
	{
		path: '/punch_in_every_day/table',
		name: 'punch_in_every_day_table',
		component: () => import('../views/punch_in_every_day/table.vue')
	},
	{
		path: '/punch_in_every_day/view',
		name: 'punch_in_every_day_view',
		component: () => import('../views/punch_in_every_day/view.vue')
	},
	// 学生请假路由
	{
		path: '/students_ask_for_leave/table',
		name: 'students_ask_for_leave_table',
		component: () => import('../views/students_ask_for_leave/table.vue')
	},
	{
		path: '/students_ask_for_leave/view',
		name: 'students_ask_for_leave_view',
		component: () => import('../views/students_ask_for_leave/view.vue')
	},
	// 学生日记路由
	{
		path: '/student_diary/table',
		name: 'student_diary_table',
		component: () => import('../views/student_diary/table.vue')
	},
	{
		path: '/student_diary/view',
		name: 'student_diary_view',
		component: () => import('../views/student_diary/view.vue')
	},
	// 疫情上报路由
	{
		path: '/epidemic_reporting/table',
		name: 'epidemic_reporting_table',
		component: () => import('../views/epidemic_reporting/table.vue')
	},
	{
		path: '/epidemic_reporting/view',
		name: 'epidemic_reporting_view',
		component: () => import('../views/epidemic_reporting/view.vue')
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
	let title = "高校学生健康打卡系统-admin";
	document.title = title;
})

export default router

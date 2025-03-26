<template>
	<div class="page_root" id="root_index">
		<div class="warp">
			<div class="container-fluid">
				<el-row>
					<el-col :span="4">
						<mm_label bg_color="bg_purple" icon="el-icon-user-solid" :url="url_user_count" unit="人"
								  title="用户数量"></mm_label>
					</el-col>
				</el-row>

				<el-row>
					<el-col v-if="user_group == '管理员' || $check_figure('/students_ask_for_leave/table')" :span="8">
						<div class="card chart">
							<pieChart v-if="list_students_ask_for_leave.length" id="list_students_ask_for_leave" :list="list_students_ask_for_leave" :title="'学生请假统计'"></pieChart>
							<div v-if="!list_students_ask_for_leave.length">学生请假没有符合条件的数据</div>
						</div>
					</el-col>
				</el-row>
			</div>
		</div>
	</div>
</template>
<script>
	import mixin from "@/mixins/page.js";
	import pieChart from "@/components/charts/pie_chart";
	import barChart from "@/components/charts/bar_chart";
	import newBarChart from "@/components/charts/new_bar_chart";
	import lineChart from "@/components/charts/line_chart";
	import newLineChart from "@/components/charts/new_line_chart";
	import mm_label from "@/components/mm_label.vue";
	export default {
		mixins: [mixin],
		name: "Home",
		components: {
			pieChart,
			barChart,
			newBarChart,
			lineChart,
			newLineChart,
			mm_label
		},
		data() {
			return {
				isAdmin: false,
				recognitionType: "",
				activeName: "third",
				list_students_ask_for_leave: [],
				url_user_count: "~/api/user/count?",
			};
		},
		created() {
			this.getUserInfo();
			// 执行学生请假数据获取
			this.get_list_students_ask_for_leave();
			
			
		},
		mounted() {},
		methods: {
			async get_nickname(list,flag){
				if (flag) {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i],
								null,
								(json) => {
									if (json.result) {
										list[i] = json.result.obj.nickname;
									}
								});
					}
				}else {
					for (let i=0;i<list.length;i++){
						await this.$get(
								"~/api/user/get_obj?user_id="+list[i].name,
								null,
								(json) => {
									if (json.result) {
										list[i].name = json.result.obj.nickname;
									}
								});
					}
				}
			},
			// 获取学生请假统计图数据
			get_list_students_ask_for_leave() {
				let data = {};
				let flag = false;
				let user_group = this.$store.state.user.user_group;
				let user_id = this.$store.state.user.user_id;
				if (user_group!='管理员'){
					if (user_group=="学生"){
						data.student_number = user_id;
					}
					if (user_group=="教师"){
						data.teacher_job_number = user_id;
					}
				}
				this.$get("~/api/students_ask_for_leave/list_group?groupby=leave_type", data, (json) => {
					if (json.result) {
						var list = json.result.list;
						this.list_students_ask_for_leave = list.map((o) => {
							return {
								name: o[1],
								value: o[0]
							};
						});
						if (flag){
							this.get_nickname(this.list_students_ask_for_leave,false);
						}
					}
				});
			},
				getUserInfo(){
					let userGroup = window.localStorage.getItem('user_group');
						if(userGroup){
						let _userGroup =JSON.parse(userGroup);
						let _info = JSON.parse(_userGroup.value);
						this.isAdmin = _info["user_group"] == "管理员" ? true : false;
					}
			}

		},
		computed:{
			recognitionHeight(){
				if(this.recognitionType === "face"){
					return "1070px"
				}else{
					return "1180px"
				}
			},
			recognitionUrl(){
					if(this.recognitionType === "face"){
					return "https://www.sk-ai.com/Experience/face-compare"
				}else{
					return "https://www.sk-ai.com/Experience/recognition?type="
				}
			}
		}
	};
</script>

<style scoped="scoped">
	.chart {
		display: block;
		width: 100%;
		height: 400px;
		padding: 1rem;
		position: relative;
	}

	.el-col {
		padding: 0.5rem;
	}

	.card {
		overflow: hidden;
	}
	
	.iframe_box ,.iframe_box_change{
		width: 100%;
		height: 1180px;
		position: relative;
		margin-top: 25px;
	}
	.iframe_box_change{
		height: 580px;
		padding-top: 50px;
	}
.iframe_box	.iframe_box_content, .iframe_box_change .iframe_box_content{
	width: 100%;
	height: 100%;
}
.iframe_box_top{
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100px;
	font-size: 25px;
	line-height: 100px;
	background: #fff;
	z-index: 99999999;
	padding-left: 50px;
}
</style>

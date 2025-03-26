<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','student_number') || $check_field('add','student_number') || $check_field('set','student_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学号" prop="student_number">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_student_number(form['student_number']) }}
							<!--<el-input id="business_name" v-model="form['student_number']" placeholder="请输入学号"-->
							<!--v-if="user_group === '管理员' || (form['epidemic_reporting_id'] && $check_field('set','student_number')) || (!form['epidemic_reporting_id'] && $check_field('add','student_number'))" :disabled="disabledObj['student_number_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','student_number')">{{form['student_number']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['epidemic_reporting_id'] && $check_field('set','student_number')) || (!form['epidemic_reporting_id'] && $check_field('add','student_number'))" id="student_number" v-model="form['student_number']" :disabled="disabledObj['student_number_isDisabled']">
								<el-option v-for="o in list_user_student_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','student_number')" id="student_number" v-model="form['student_number']" :disabled="true">
								<el-option v-for="o in list_user_student_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="student_number" v-model="form['student_number']" :disabled="disabledObj['student_number_isDisabled']">
							<el-option v-for="o in list_user_student_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_name') || $check_field('add','student_name') || $check_field('set','student_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学生姓名" prop="student_name">
					<el-input id="student_name" v-model="form['student_name']" placeholder="请输入学生姓名"
							  v-if="user_group === '管理员' || (form['epidemic_reporting_id'] && $check_field('set','student_name')) || (!form['epidemic_reporting_id'] && $check_field('add','student_name'))" :disabled="disabledObj['student_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_name')">{{form['student_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','teacher_job_number') || $check_field('add','teacher_job_number') || $check_field('set','teacher_job_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="教师工号" prop="teacher_job_number">
						<el-select v-if="user_group === '管理员' || (form['epidemic_reporting_id'] && $check_field('set','teacher_job_number')) || (!form['epidemic_reporting_id'] && $check_field('add','teacher_job_number'))" id="teacher_job_number" v-model="form['teacher_job_number']" :disabled="disabledObj['teacher_job_number_isDisabled']">
							<el-option v-for="o in list_user_teacher_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','teacher_job_number')" id="teacher_job_number" v-model="form['teacher_job_number']" :disabled="true">
							<el-option v-for="o in list_user_teacher_job_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','teacher_name') || $check_field('add','teacher_name') || $check_field('set','teacher_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="教师姓名" prop="teacher_name">
					<el-input id="teacher_name" v-model="form['teacher_name']" placeholder="请输入教师姓名"
							  v-if="user_group === '管理员' || (form['epidemic_reporting_id'] && $check_field('set','teacher_name')) || (!form['epidemic_reporting_id'] && $check_field('add','teacher_name'))" :disabled="disabledObj['teacher_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','teacher_name')">{{form['teacher_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','class_name') || $check_field('add','class_name') || $check_field('set','class_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="班级名称" prop="class_name">
					<el-input id="class_name" v-model="form['class_name']" placeholder="请输入班级名称"
							  v-if="user_group === '管理员' || (form['epidemic_reporting_id'] && $check_field('set','class_name')) || (!form['epidemic_reporting_id'] && $check_field('add','class_name'))" :disabled="disabledObj['class_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','class_name')">{{form['class_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','report_type') || $check_field('add','report_type') || $check_field('set','report_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="上报类型" prop="report_type">
					<el-select id="report_type" v-model="form['report_type']"
						v-if="user_group === '管理员' || (form['epidemic_reporting_id'] && $check_field('set','report_type')) || (!form['epidemic_reporting_id'] && $check_field('add','report_type'))">
						<el-option v-for="o in list_report_type" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','report_type')">{{form['report_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','nucleic_acid_results') || $check_field('add','nucleic_acid_results') || $check_field('set','nucleic_acid_results')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="核酸结果" prop="nucleic_acid_results">
					<el-select id="nucleic_acid_results" v-model="form['nucleic_acid_results']"
						v-if="user_group === '管理员' || (form['epidemic_reporting_id'] && $check_field('set','nucleic_acid_results')) || (!form['epidemic_reporting_id'] && $check_field('add','nucleic_acid_results'))">
						<el-option v-for="o in list_nucleic_acid_results" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','nucleic_acid_results')">{{form['nucleic_acid_results']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','detection_time') || $check_field('add','detection_time') || $check_field('set','detection_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="检测时间" prop="detection_time">
					<el-date-picker :disabled="disabledObj['detection_time_isDisabled']" v-if="user_group === '管理员' || (form['epidemic_reporting_id'] && $check_field('set','detection_time')) || (!form['epidemic_reporting_id'] && $check_field('add','detection_time'))" id="detection_time"
						v-model="form['detection_time']" type="datetime" placeholder="选择日期时间">
					</el-date-picker>
					<div v-else-if="$check_field('get','detection_time')">{{form['detection_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','health_code') || $check_field('add','health_code') || $check_field('set','health_code')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="健康码" prop="health_code">
					<el-upload :disabled="disabledObj['health_code_isDisabled']" id="health_code" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_health_code"
						:show-file-list="false" v-if="user_group === '管理员' || (form['epidemic_reporting_id'] && $check_field('set','health_code')) || (!form['epidemic_reporting_id'] && $check_field('add','health_code'))">
						<img v-if="form['health_code']" :src="$fullUrl(form['health_code'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','health_code')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['health_code'])" :preview-src-list="[$fullUrl(form['health_code'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','remarks') || $check_field('add','remarks') || $check_field('set','remarks')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="备注" prop="remarks">
					<el-input type="textarea" id="remarks" v-model="form['remarks']" placeholder="请输入备注"
						v-if="user_group === '管理员' || (form['epidemic_reporting_id'] && $check_field('set','remarks')) || (!form['epidemic_reporting_id'] && $check_field('add','remarks'))" :disabled="disabledObj['remarks_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','remarks')">{{form['remarks']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
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
				field: "epidemic_reporting_id",
				url_add: "~/api/epidemic_reporting/add?",
				url_set: "~/api/epidemic_reporting/set?",
				url_get_obj: "~/api/epidemic_reporting/get_obj?",
				url_upload: "~/api/epidemic_reporting/upload?",

				query: {
					"epidemic_reporting_id": 0,
				},

				form: {
					"student_number": 0, // 学号
					"student_name":'', // 学生姓名
					"teacher_job_number": 0, // 教师工号
					"teacher_name":'', // 教师姓名
					"class_name":'', // 班级名称
					"report_type":'', // 上报类型
					"nucleic_acid_results":'', // 核酸结果
					"detection_time":'', // 检测时间
					"health_code":'', // 健康码
					"remarks":'', // 备注
					"epidemic_reporting_id": 0, // ID

				},
				disabledObj:{
					"student_number_isDisabled": false,
					"student_name_isDisabled": false,
					"teacher_job_number_isDisabled": false,
					"teacher_name_isDisabled": false,
					"class_name_isDisabled": false,
					"report_type_isDisabled": false,
					"nucleic_acid_results_isDisabled": false,
					"detection_time_isDisabled": false,
					"health_code_isDisabled": false,
					"remarks_isDisabled": false,
				},
				// 用户列表
				list_user_student_number: [],
				// 用户组
				group_user_student_number: "",
				// 用户列表
				list_user_teacher_job_number: [],
				//上报类型选项列表
				list_report_type: ['确诊病例','密切接触者','无症状患者','其他'],
				//核酸结果选项列表
				list_nucleic_acid_results: ['阴性','阳性'],

			}
		},
		methods: {
			/**
			 * 获取学生用户列表
			 */
			async get_list_user_student_number() {
                // if(this.user_group !== "管理员" && this.form["student_number"] === 0) {
                //     this.form["student_number"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=学生");
                if(json.result && json.result.list){
                    this.list_user_student_number = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取学生用户组
			 */
			async get_group_user_student_number() {
				this.form["student_number"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=学生");
				if(json.result && json.result.obj){
					this.group_user_student_number = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_student_number(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_student_number.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="student_number") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
			},
			get_user_student_number(id){
				var obj = this.list_user_student_number.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			/**
			 * 获取教师用户列表
			 */
			async get_list_user_teacher_job_number() {
                // if(this.user_group !== "管理员" && this.form["teacher_job_number"] === 0) {
                //     this.form["teacher_job_number"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=教师");
                if(json.result && json.result.list){
                    this.list_user_teacher_job_number = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_teacher_job_number(id){
				var obj = this.list_user_teacher_job_number.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			/**
			 * 上传健康码
			 * @param {Object} param图片参数
			 */
			upload_health_code(param){
				this.uploadFile(param.file, "health_code");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "学号":
							if(param["student_number"] > 0){
								param["student_number"] = this.user.user_id;
							}
							break;
						case "教师工号":
							if(param["teacher_job_number"] > 0){
								param["teacher_job_number"] = this.user.user_id;
							}
							break;
					}
				}
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							if(dbKey === "charging_standard"){
								this.form['charging_rules'] = form[dbKey];
								this.disabledObj['charging_rules_isDisabled'] = true;
							};
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				this.form["detection_time"] = this.$toTime(parseInt(this.form["detection_time"]),"yyyy-MM-dd hh:mm:ss")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["detection_time"]) > 9999){
					this.form["detection_time"] = this.$toTime(parseInt(this.form["detection_time"]),"yyyy-MM-dd hh:mm:ss")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/epidemic_reporting/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/epidemic_reporting/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/epidemic_reporting/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/epidemic_reporting/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/epidemic_reporting/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_user_student_number();
			this.get_group_user_student_number();
			this.get_list_user_teacher_job_number();
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

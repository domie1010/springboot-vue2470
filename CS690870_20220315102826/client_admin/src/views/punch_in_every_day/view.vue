<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','student_number') || $check_field('add','student_number') || $check_field('set','student_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学号" prop="student_number">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_student_number(form['student_number']) }}
							<!--<el-input id="business_name" v-model="form['student_number']" placeholder="请输入学号"-->
							<!--v-if="user_group === '管理员' || (form['punch_in_every_day_id'] && $check_field('set','student_number')) || (!form['punch_in_every_day_id'] && $check_field('add','student_number'))" :disabled="disabledObj['student_number_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','student_number')">{{form['student_number']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['punch_in_every_day_id'] && $check_field('set','student_number')) || (!form['punch_in_every_day_id'] && $check_field('add','student_number'))" id="student_number" v-model="form['student_number']" :disabled="disabledObj['student_number_isDisabled']">
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
							  v-if="user_group === '管理员' || (form['punch_in_every_day_id'] && $check_field('set','student_name')) || (!form['punch_in_every_day_id'] && $check_field('add','student_name'))" :disabled="disabledObj['student_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_name')">{{form['student_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','teacher_job_number') || $check_field('add','teacher_job_number') || $check_field('set','teacher_job_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="教师工号" prop="teacher_job_number">
						<el-select v-if="user_group === '管理员' || (form['punch_in_every_day_id'] && $check_field('set','teacher_job_number')) || (!form['punch_in_every_day_id'] && $check_field('add','teacher_job_number'))" id="teacher_job_number" v-model="form['teacher_job_number']" :disabled="disabledObj['teacher_job_number_isDisabled']">
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
							  v-if="user_group === '管理员' || (form['punch_in_every_day_id'] && $check_field('set','teacher_name')) || (!form['punch_in_every_day_id'] && $check_field('add','teacher_name'))" :disabled="disabledObj['teacher_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','teacher_name')">{{form['teacher_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','class_name') || $check_field('add','class_name') || $check_field('set','class_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="班级名称" prop="class_name">
					<el-input id="class_name" v-model="form['class_name']" placeholder="请输入班级名称"
							  v-if="user_group === '管理员' || (form['punch_in_every_day_id'] && $check_field('set','class_name')) || (!form['punch_in_every_day_id'] && $check_field('add','class_name'))" :disabled="disabledObj['class_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','class_name')">{{form['class_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','record_date') || $check_field('add','record_date') || $check_field('set','record_date')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="记录日期" prop="record_date">
					<el-date-picker :disabled="disabledObj['record_date_isDisabled']" v-if="user_group === '管理员' || (form['punch_in_every_day_id'] && $check_field('set','record_date')) || (!form['punch_in_every_day_id'] && $check_field('add','record_date'))" id="record_date"
						v-model="form['record_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','record_date')">{{form['record_date']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','today_temperature') || $check_field('add','today_temperature') || $check_field('set','today_temperature')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="今日体温" prop="today_temperature">
					<el-input id="today_temperature" v-model="form['today_temperature']" placeholder="请输入今日体温"
							  v-if="user_group === '管理员' || (form['punch_in_every_day_id'] && $check_field('set','today_temperature')) || (!form['punch_in_every_day_id'] && $check_field('add','today_temperature'))" :disabled="disabledObj['today_temperature_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','today_temperature')">{{form['today_temperature']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','physical_state') || $check_field('add','physical_state') || $check_field('set','physical_state')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="身体状态" prop="physical_state">
					<el-select id="physical_state" v-model="form['physical_state']"
						v-if="user_group === '管理员' || (form['punch_in_every_day_id'] && $check_field('set','physical_state')) || (!form['punch_in_every_day_id'] && $check_field('add','physical_state'))">
						<el-option v-for="o in list_physical_state" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','physical_state')">{{form['physical_state']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','punch_in_address') || $check_field('add','punch_in_address') || $check_field('set','punch_in_address')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="打卡地址" prop="punch_in_address">
					<el-input id="punch_in_address" v-model="form['punch_in_address']" placeholder="请输入打卡地址"
							  v-if="user_group === '管理员' || (form['punch_in_every_day_id'] && $check_field('set','punch_in_address')) || (!form['punch_in_every_day_id'] && $check_field('add','punch_in_address'))" :disabled="disabledObj['punch_in_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','punch_in_address')">{{form['punch_in_address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','health_code') || $check_field('add','health_code') || $check_field('set','health_code')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="健康码" prop="health_code">
					<el-upload :disabled="disabledObj['health_code_isDisabled']" id="health_code" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_health_code"
						:show-file-list="false" v-if="user_group === '管理员' || (form['punch_in_every_day_id'] && $check_field('set','health_code')) || (!form['punch_in_every_day_id'] && $check_field('add','health_code'))">
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
				field: "punch_in_every_day_id",
				url_add: "~/api/punch_in_every_day/add?",
				url_set: "~/api/punch_in_every_day/set?",
				url_get_obj: "~/api/punch_in_every_day/get_obj?",
				url_upload: "~/api/punch_in_every_day/upload?",

				query: {
					"punch_in_every_day_id": 0,
				},

				form: {
					"student_number": 0, // 学号
					"student_name":'', // 学生姓名
					"teacher_job_number": 0, // 教师工号
					"teacher_name":'', // 教师姓名
					"class_name":'', // 班级名称
					"record_date":'', // 记录日期
					"today_temperature":'', // 今日体温
					"physical_state":'', // 身体状态
					"punch_in_address":'', // 打卡地址
					"health_code":'', // 健康码
					"punch_in_every_day_id": 0, // ID

				},
				disabledObj:{
					"student_number_isDisabled": false,
					"student_name_isDisabled": false,
					"teacher_job_number_isDisabled": false,
					"teacher_name_isDisabled": false,
					"class_name_isDisabled": false,
					"record_date_isDisabled": false,
					"today_temperature_isDisabled": false,
					"physical_state_isDisabled": false,
					"punch_in_address_isDisabled": false,
					"health_code_isDisabled": false,
				},
				// 用户列表
				list_user_student_number: [],
				// 用户组
				group_user_student_number: "",
				// 用户列表
				list_user_teacher_job_number: [],
				//身体状态选项列表
				list_physical_state: ['良好','发热','其他'],

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
				this.form["record_date"] = this.$toTime(parseInt(this.form["record_date"]),"yyyy-MM-dd")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["record_date"]) > 9999){
					this.form["record_date"] = this.$toTime(parseInt(this.form["record_date"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/punch_in_every_day/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/punch_in_every_day/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/punch_in_every_day/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/punch_in_every_day/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/punch_in_every_day/view','get');
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

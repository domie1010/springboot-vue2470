<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','title') || $check_field('add','title') || $check_field('set','title')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="标题" prop="title">
					<el-input id="title" v-model="form['title']" placeholder="请输入标题"
							  v-if="user_group === '管理员' || (form['notice_announcement_id'] && $check_field('set','title')) || (!form['notice_announcement_id'] && $check_field('add','title'))" :disabled="disabledObj['title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','title')">{{form['title']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','relevant_attachments') || $check_field('add','relevant_attachments') || $check_field('set','relevant_attachments')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="相关附件" prop="relevant_attachments">
					<div v-if="disabledObj['relevant_attachments_isDisabled']">
						<div v-if="$check_field('get','relevant_attachments')">
							<el-button type="primary" @click="$download($fullUrl(form['relevant_attachments']),form['relevant_attachments'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
					<div v-else>
						<el-upload v-if="user_group === '管理员' || (form['notice_announcement_id'] && $check_field('set','relevant_attachments')) || (!form['notice_announcement_id'] && $check_field('add','relevant_attachments'))" class="upload-demo" drag
								   action="" style="max-width: 300px;width: 100%;" :http-request="upload_relevant_attachments" :limit="1" accept="">
							<i class="el-icon-upload"></i>
							<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
						</el-upload>
						<div v-else-if="$check_field('get','relevant_attachments')">
							<el-button type="primary" @click="$download($fullUrl(form['relevant_attachments']),form['relevant_attachments'])">下载<i
									class="el-icon-download el-icon--right"></i></el-button>
						</div>
					</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','publisher') || $check_field('add','publisher') || $check_field('set','publisher')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="发布人" prop="publisher">
					<el-input id="publisher" v-model="form['publisher']" placeholder="请输入发布人"
							  v-if="user_group === '管理员' || (form['notice_announcement_id'] && $check_field('set','publisher')) || (!form['notice_announcement_id'] && $check_field('add','publisher'))" :disabled="disabledObj['publisher_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','publisher')">{{form['publisher']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','release_date') || $check_field('add','release_date') || $check_field('set','release_date')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="发布日期" prop="release_date">
					<el-date-picker :disabled="disabledObj['release_date_isDisabled']" v-if="user_group === '管理员' || (form['notice_announcement_id'] && $check_field('set','release_date')) || (!form['notice_announcement_id'] && $check_field('add','release_date'))" id="release_date"
						v-model="form['release_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','release_date')">{{form['release_date']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','content') || $check_field('add','content') || $check_field('set','content')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="内容" prop="content">
					<el-input type="textarea" id="content" v-model="form['content']" placeholder="请输入内容"
						v-if="user_group === '管理员' || (form['notice_announcement_id'] && $check_field('set','content')) || (!form['notice_announcement_id'] && $check_field('add','content'))" :disabled="disabledObj['content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','content')">{{form['content']}}</div>
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
				field: "notice_announcement_id",
				url_add: "~/api/notice_announcement/add?",
				url_set: "~/api/notice_announcement/set?",
				url_get_obj: "~/api/notice_announcement/get_obj?",
				url_upload: "~/api/notice_announcement/upload?",

				query: {
					"notice_announcement_id": 0,
				},

				form: {
					"title":'', // 标题
					"relevant_attachments":'', // 相关附件
					"publisher":'', // 发布人
					"release_date":'', // 发布日期
					"content":'', // 内容
					"notice_announcement_id": 0, // ID

				},
				disabledObj:{
					"title_isDisabled": false,
					"relevant_attachments_isDisabled": false,
					"publisher_isDisabled": false,
					"release_date_isDisabled": false,
					"content_isDisabled": false,
				},

			}
		},
		methods: {
			/**
			 * 上传相关附件
			 * @param {Object} param文件参数
			 */
			upload_relevant_attachments(param){
				this.uploadFile(param.file, "relevant_attachments");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
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
				this.form["release_date"] = this.$toTime(parseInt(this.form["release_date"]),"yyyy-MM-dd")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["release_date"]) > 9999){
					this.form["release_date"] = this.$toTime(parseInt(this.form["release_date"]),"yyyy-MM-dd")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/notice_announcement/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/notice_announcement/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/notice_announcement/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/notice_announcement/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/notice_announcement/view','get');
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

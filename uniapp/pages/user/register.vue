<template>
	<view class="login-class">
		<u-toast ref="uToast" />
		<u-form :model="form" ref="registerForm">
			<u-form-item prop="username" label-width="0px">
				<u-input v-model="form.username" placeholder="请输入用户名" :border="true" maxlength="100" />
			</u-form-item>
			<u-form-item prop="password" label-width="0px">
				<u-input v-model="form.password" placeholder="请输入密码" :border="true" maxlength="100" />
			</u-form-item>
			<u-form-item prop="checkPassword" label-width="0px">
				<u-input v-model="form.checkPassword" placeholder="确认密码" :border="true" maxlength="100" />
			</u-form-item>
			<u-form-item prop="email" label-width="0px">
				<u-input v-model="form.email" placeholder="请输入邮箱" :border="true" maxlength="100" />
			</u-form-item>
			<u-form-item prop="code" label-width="0px">
				<u-input v-model="form.code" placeholder="请输入验证码" :border="true" maxlength="100" />
				<u-verification-code @change="codeChange" seconds="60" ref="uCode" />
				<u-button @click="getCode">{{tips}}</u-button>
			</u-form-item>
		</u-form>
		<u-button type="primary" @click="register">注册</u-button>
		<view @click="toLogin" style="color: #007AFF;float: right;margin: 10px 5px 0px 0px;font-size: 16px;">已有账号？立即登陆</view>
	</view>
</template>

<script>
	export default {
		onReady() {
			this.$refs.registerForm.setRules(this.rules);
		},
		data() {
			return {
				form: {
					username: '',
					password: '',
					code: ''
				},
				tips: '',
				rules: {
					username: [{
						required: true,
						min: 5,
						max: 12,
						message: '请输长度为5~12的用户名',
						trigger: 'blur,change'
					}],
					password: [{
						required: true,
						min: 5,
						max: 12,
						message: '请输长度为5~12的密码',
						trigger: 'blur,change'
					}],
					code: [{
						required: true,
						message: '请输验证码',
						trigger: 'blur,change'
					}],
					checkPassword: [{
							required: true,
							message: '两次输入的密码不一致',
							trigger: 'blur,change'
						},
						{
							// 自定义验证函数，见上说明
							validator: (rule, value, callback) => {
								if (value == undefined || value == '' || value != this.form.password) {
									return false;
								}
								return true;
							},
							message: '两次输入的密码不一致',
							// 触发器可以同时用blur和change
							trigger: ['change', 'blur'],
						}
					]
				}
			}
		},
		methods: {
			codeChange(text) {
				this.tips = text;
			},
			register() {
				this.$refs.registerForm.validate(valid => {
					if (!valid) {
						return false;
					}
					this.$request.post('register', this.form).then(res => {
						//登陆成功，将用户信息放到storage中
						uni.setStorageSync('userinfo', res.data.data);
						//跳转到上一页
						uni.navigateBack({})
					})
				});
			},
			toLogin() {
				uni.navigateTo({
					url: 'login'
				})
			},
			getCode() {
				if (this.$refs.uCode.canGetCode) {
					if(this.form.email==undefined||!this.$validate.isEmai(this.form.email)){
						this.$refs.uToast.show({
							title: "邮箱有误！",
							type: 'error',
							duration:1000
						})
						return false;
					}
					uni.showLoading({
						title: '正在发送验证码'
					})
					
					this.$request.post('getCode', this.form).then(res => {
						uni.hideLoading();
						this.$refs.uToast.show({
							title: "验证码发送成功",
							type: 'success',
							duration:1500
						})
					})
					
					// 通知验证码组件内部开始倒计时
					this.$refs.uCode.start();
				} 
			}
		}
	}
</script>

<style lang="scss">
</style>

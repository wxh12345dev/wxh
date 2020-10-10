<template>
	<view class="login-class">
		<u-toast ref="uToast" />
		 <u-form :model="form" ref="uForm">
		 	<u-form-item prop="username" label-width="0px">
		 		<u-input v-model="form.username" placeholder="请输入用户名" :border="true"  maxlength="100" />
		 	</u-form-item>
		 	<u-form-item prop="password" label-width="0px">
		 		<u-input v-model="form.password" placeholder="请输入密码" :border="true"   maxlength="100" />
		 	</u-form-item>
		 </u-form>
		 <u-button type="primary" @click="login">登陆</u-button>
		 <view @click="toRegister" style="color: #007AFF;float: right;margin: 10px 5px 0px 0px;font-size: 16px;">还没有账号？立即注册</view>
	</view>
</template>

<script>
	export default {
		onReady() {
			this.$refs.uForm.setRules(this.rules);
		},
		onLoad(option) {
			if(option.type){
				uni.showToast({
					title: '请先登陆！',
					icon: 'none',
					duration: 1500
				})
			}
		},
		data(){
			return {
				form: {
					username: '',
					password:''
				},
				rules: {
					username: [{
						required: true,
						message: '请输入用户名',
						trigger: 'blur,change'
					}],
					password: [{
						required: true,
						message: '请输入密码',
						trigger: 'blur,change'
					}]
				}
			}
		},
		methods:{
			login(){
				this.$refs.uForm.validate(valid => {
					if (!valid) {
						return false;
					}
					this.$request.post('login', this.form).then(res => {
						//登陆成功，将用户信息放到storage中
						uni.setStorageSync('userinfo',res.data.data);
						//跳转到上一页
						uni.navigateBack({})
					})
				});
			},
			toRegister(){
				uni.navigateTo({
					url:'register'
				})
			}
		}
	}
</script>

<style lang="scss">
</style>

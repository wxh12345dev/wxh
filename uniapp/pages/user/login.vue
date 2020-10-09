<template>
	<view>
		 <u-form :model="form" ref="uForm">
		 	<u-form-item prop="username" label-width="0px">
		 		<u-input v-model="form.username" placeholder="请输入用户名" :border="true"  maxlength="300" />
		 	</u-form-item>
		 	<u-form-item prop="password" label-width="0px">
		 		<u-input v-model="form.password" placeholder="请输入密码" :border="true" type="textarea" height="400" maxlength="1000" />
		 	</u-form-item>
		 </u-form>
		 <u-button type="primary" @click="login">登陆</u-button>
	</view>
</template>

<script>
	export default {
		onReady() {
			this.$refs.uForm.setRules(this.rules);
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
		}
	}
</script>

<style lang="scss">
</style>

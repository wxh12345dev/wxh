<template>
	<view>
		<u-toast ref="uToast" />
		<uni-nav-bar left-icon="back" left-text="返回" :title="title" @clickLeft="goBack" backgroundColor="#57b542" color="#ffffff"></uni-nav-bar>
		<view class="beiwang-detail">
			<u-form :model="form" ref="uForm">
				<u-form-item prop="content" label-width="0px">
					<u-input v-model="form.content" placeholder="请输入待办事项" :border="true" type="textarea" height="400" maxlength="1000" />
				</u-form-item>
			</u-form>
			<u-button type="primary" @click="save">保存</u-button>
		</view>
	</view>
</template>

<script>
	import uniNavBar from "@/components/uni-nav-bar/uni-nav-bar.vue"
	export default {
		components: {
			uniNavBar
		},
		data() {
			return {
				title: '',
				form: {
					content: '',
					id:''
				},
				rules: {
					content: [{
						required: true,
						message: '请输入待办事项',
						trigger: 'blur,change'
					}]
				}
			}
		},
		onReady() {
			this.$refs.uForm.setRules(this.rules);
		},
		onLoad(option) {
			console.log(option)
			this.title = option.title
			let id = option.id
			if(id!=undefined&&id.trim()!=''){
				this.$request.get('todo/get?id='+id).then(res => {
					this.form=res.data.data
				})
			}
		},
		methods: {
			goBack() {
				uni.navigateBack({
					url: 'index'
				})
			},
			save() {
				this.$refs.uForm.validate(valid => {
					if (!valid) {
						return false;
					}
					this.$request.postJson('todo/edit', JSON.stringify(this.form)).then(res => {
						if (res.data.info != undefined && res.data.info.trim() != '') {
							this.$refs.uToast.show({
								title: res.data.info,
								type: 'success',
								back: true,
								duration:500
							})
						}
					})
				});

			}
		}
	}
</script>

<style lang="scss">
</style>

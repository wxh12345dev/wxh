<template>
	<view>
		<u-toast ref="uToast" />
		<u-navbar  :title="title" back-text="返回"/>
		<view class="beiwang-detail">
			<u-form :model="form" ref="uForm">
				<u-form-item prop="title" label-width="0px">
					<u-input v-model="form.title" placeholder="请输入日记标题" :border="true"  maxlength="300" />
				</u-form-item>
				<u-form-item prop="content" label-width="0px">
					<u-input v-model="form.content" placeholder="请输入日记内容" :border="true" type="textarea" height="400" maxlength="1000" />
				</u-form-item>
				<u-form-item prop="content" label-width="0px" >
					<u-upload ref="uUpload" @on-success="uploadSuccess" :max-size="2 * 1024 * 1024" max-count="6" action="http://localhost:8083/upload"></u-upload>
				</u-form-item>
			</u-form>
			<u-button type="primary" @click="save">保存</u-button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: '',
				form: {
					content: '',
					id:'',
					title:''
				},
				hasLoad:false,
				rules: {
					title: [{
						required: true,
						message: '请输入日记标题',
						trigger: 'blur,change'
					}],
					content: [{
						required: true,
						message: '请输入日记内容',
						trigger: 'blur,change'
					}]
				}
			}
		},
		onReady() {
			this.$refs.uForm.setRules(this.rules);
		},
		onLoad(option) {
			this.title = option.title
			this.search(option.id)
		},
		onShow() {
			this.search(this.form.id)
		},
		methods: {
			goBack() {
				uni.navigateBack({
					url:'show'
				})
			},
			uploadSuccess(data, index, lists, name){
				lists[index].url = data.data
			},
			search(id){
				if(id!=undefined&&id.trim()!=''){
					this.$request.get('dairy/get?id='+id).then(res => {
						this.form=res.data.data
						let files = []
						let url = res.data.data.url;
						if(url){
							let urls = url.split(";");
							for(let value of urls){
								files.push({"url":value})
							}
							this.$refs.uUpload.lists = files
						}
					})
				}
			},
			save() {
				let files = this.$refs.uUpload.lists;
				let url='';
				for(let i=0;i<files.length;i++){
					let data = files[i].url
					if(data){
						url += data
					}
					if(i<files.length-1){
						url+=";"
					}
				}
				this.$refs.uForm.validate(valid => {
					if (!valid) {
						return false;
					}
					this.form.url = url
					this.$request.postJson('dairy/edit', JSON.stringify(this.form)).then(res => {
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

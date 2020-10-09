<template>
	<view>
		<u-navbar   back-text="返回">
			<view slot="right" @click="edit" style="color: rgb(96, 98, 102);font-size: 15px;width: 40px;line-height: 44px;">
				编辑
			</view>
		</u-navbar>
		<view class="riji-banner">
			<u-swiper :list="images" height="350" img-mode="scaleToFill"></u-swiper>
		</view>
		<view class="riji-header u-line-4">
			{{form.title}}
		</view>
		<view class="riji-body">
			{{form.content}}
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				form:{
					
				},
				images:[]
			}
		},
		onLoad(option) {
			this.search(option.id)
		},
		onShow() {
			this.search(this.form.id)
		},
		methods:{
			edit(){
				uni.navigateTo({
					url:'detail?id='+this.form.id+'&title=编辑日记'
				})
			},
			goBack() {
				uni.navigateBack({
					url:'index'
				})
			},
			search(id){
				if (id != undefined && id.trim() != '') {
					this.$request.get('dairy/get?id=' + id).then(res => {
						this.form = res.data.data
						let files = []
						let url = res.data.data.url;
						if(url){
							this.images = url.split(";");
						}
					})
				}
			}
		}
	}
</script>

<style lang="scss">
</style>

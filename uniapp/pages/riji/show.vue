<template>
	<view>
		<uni-nav-bar left-icon="back" left-text="返回" title="我的日记" @clickLeft="goBack" rightText="编辑" @clickRight="edit"></uni-nav-bar>
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
	import uniNavBar from "@/components/uni-nav-bar/uni-nav-bar.vue"
	export default {
		components: {
			uniNavBar
		},
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
					url:'detail?id='+this.form.id
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

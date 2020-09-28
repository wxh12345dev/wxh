<template>
	<view>
		<uni-nav-bar left-icon="back" left-text="返回"
		 :title="title"   @clickLeft="goBack"></uni-nav-bar>
	</view>
</template>

<script>
	import uniNavBar from "@/components/uni-nav-bar/uni-nav-bar.vue"
	export default {
		components: {
			uniNavBar
		},
		data(){
			return{
				title:''
			}
		},
		onLoad(option) {
			this.title=option.id
		},
		methods:{
			goBack(){
				uni.navigateBack({})
			}
		}
	}
</script>

<style lang="scss">
</style>

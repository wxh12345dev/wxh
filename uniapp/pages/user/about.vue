<template>
	<view>
		<uni-nav-bar left-icon="back" left-text="返回"
		 title="关于小程序"   @clickLeft="goBack"></uni-nav-bar>
		<view class="about-class">
			这是一款简易方便的备忘录和日记管理工具，为您记录生活点滴！
		</view>
	</view>
</template>

<script>
	import uniNavBar from "@/components/uni-nav-bar/uni-nav-bar.vue"
	export default {
		components: {
			uniNavBar
		},
		data(){
			return {
			}
		},
		methods:{
			goBack(){
				uni.navigateBack({
				})
			}
		}
	}
</script>

<style lang="scss">
</style>

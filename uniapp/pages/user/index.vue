<template>
	<view>
		<view style="width: 100%;height: 1px;">
		</view>
		<view class="user-class">
			<u-avatar :src="src"></u-avatar>
			<view class="name-text">{{name}}</view>
			<view class="name-text">{{18054818092}}</view>
		</view>
		<view class="user-btn">
			<u-toast ref="uToast" />
			<u-modal v-model="show" @confirm="confirm" content="清除后将退出登录,确定要清除吗？" :show-cancel-button="true" :show-title="false"></u-modal>
			<u-cell-group>
				<u-cell-item icon="info-circle" title="关于小程序" @click="toAbout"></u-cell-item>
				<u-cell-item icon="trash" title="清除缓存" @click="clearCache"></u-cell-item>
			</u-cell-group>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				src: 'http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg',
				name: '张三丰',
				phone: '18054818092',
				show: false
			}
		},
		methods: {
			toAbout() {
				uni.navigateTo({
					url: 'about'
				});
			},
			clearCache() {
				this.show = true;
			},
			confirm() {
				this.$refs.uToast.show({
					title: '缓存清除成功',
					type: 'success',
					url: '/pages/index/index',
					isTab:true,
					duration:600
				})
			}
		}
	}
</script>

<style lang="scss">
</style>

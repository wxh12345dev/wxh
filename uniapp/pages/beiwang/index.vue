<template>
	<view>
		<view class="beiwang-class">
			<ren-dropdown-filter :filterData='filterData' @onSelected='onSelected' @dateChange='dateChange'></ren-dropdown-filter>
			<view class="beiwang-icon">
				<u-icon class="add-icon-class" name="tianjia" custom-prefix="custom-icon" color="#ab6e31" size="80"></u-icon>
			</view>
		</view>
		<view class="list-class">
			<view class="list-item" v-for="(item,key) in datas" :key="item.id">
				<view class="head-class">
					<view class="head-title">
						{{item.createdStr}}
					</view>
					<view class="head-date" @click="deleteTodoDetail(item.id)">
						<u-icon name="trash" size="28"></u-icon>
					</view>
				</view>
				<view class="body-class u-line-3" @click="$request.navigateTo('/pages/beiwang/detail?id='+item.id)">
					{{item.content}}
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import RenDropdownFilter from '@/components/ren-dropdown-filter/ren-dropdown-filter.vue'
	export default {
		components: {
			RenDropdownFilter
		},
		data() {
			return {
				filterData: [
					[{
						text: '全部',
						value: ''
					}, {
						text: '待办',
						value: 1
					}, {
						text: '已完成',
						value: 2
					}]
				],
				datas: []
			}
		},
		onShow() {
			this.$request.postJson('todo/page',{
				currentPage:1,pageSize:5
			}).then(res=>{
				this.datas=res.data.data.records
			})
		},
		methods: {
			goBack() {
				uni.navigateBack({})
			},
			onSelected(res) {
				console.log(res)
			},
			dateChange(d) {
				uni.showToast({
					icon: 'none',
					title: d
				})
			}
		}
	}
</script>

<style lang="scss">
</style>

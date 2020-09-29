<template>
	<view>
		<view class="beiwang-class">
			<ren-dropdown-filter :filterData='filterData' :defaultIndex="[1]" @onSelected='onSelected'></ren-dropdown-filter>
			<view class="beiwang-icon" @click="addTodo">
				<u-icon class="add-icon-class" name="tianjia" custom-prefix="custom-icon" color="#ab6e31" size="60"></u-icon>
			</view>
		</view>
		<view class="list-class">
			<view class="toDo-class" v-for="(item,key) in datas" :key="item.id">
				<view class="head-class">
					<view class="head-title">
						{{item.created}}
					</view>
					<view class="head-date" @click="switchType(item)">
						<view v-if="item.finish==0">
							<u-icon name="weiwancheng" custom-prefix="custom-icon" color="#686868" size="40"></u-icon>
						</view>
						<view style="display: flex;justify-content: flex-end;" v-else>
							<view style="color: red;margin-right: 3px;">已完成</view>
							<u-icon name="wancheng" custom-prefix="custom-icon" color="#00aa00" size="40"></u-icon>
						</view>
					</view>
				</view>
				<view class="body-class u-line-3" @click="$request.navigateTo('/pages/beiwang/detail?title=编辑备忘录&id='+item.id)">
					{{item.content}}
				</view>
				<view class="tool-class">
					<u-icon name="trash" size="35" @click="deleteTodoDetail(item.id)"></u-icon>
				</view>
			</view>
		</view>
		<u-toast ref="uToast" />
		<u-modal v-model="show" content="确定要删除该数据吗？" :show-title="false" :show-cancel-button="true" @confirm="confirm"
		 :deleteId="deleteId"></u-modal>

		<view class="loadMore">
			<u-line class="u-line" length="60" color="#000000"></u-line>
			<u-loadmore :status="status" :icon-type="iconType" :load-text="loadText" font-size="34" />
			<u-line class="u-line" length="60" color="#000000"></u-line>
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
						value: 0
					}, {
						text: '已完成',
						value: 1
					}]
				],
				datas: [],
				deleteId: '',
				show: false,
				form: {
					currentPage: 1,
					pageSize: 5,
					finish: 0
				},
				status: 'loadmore',
				iconType: 'flower',
				loadText: {
					loadmore: '上拉加载更多',
					loading: '努力加载中',
					nomore: '正在加载中'
				}
			}
		},
		onShow() {
			this.search();
		},
		onPullDownRefresh() {
			this.search();
			uni.stopPullDownRefresh();
		},
		onReachBottom() {

		},
		methods: {
			search() {
				this.$request.postJson('todo/page', JSON.stringify(this.form)).then(res => {
					this.datas = res.data.data.records
				})
			},
			goBack() {
				uni.navigateBack({})
			},
			deleteTodoDetail(id) {
				this.show = true
				this.deleteId = id
			},
			onSelected(res) {
				this.form.finish = res[0][0].value
				this.search();
			},
			confirm() {
				this.$request.get('todo/delete?id=' + this.deleteId).then(res => {
					if (res.data.info != undefined && res.data.info.trim() != '') {
						this.$refs.uToast.show({
							title: res.data.info,
							type: 'success'
						})
					}
					this.$request.postJson('todo/page', {
						currentPage: 1,
						pageSize: 5
					}).then(res => {
						this.datas = res.data.data.records
					})
				})
			},
			dateChange(d) {
				uni.showToast({
					icon: 'none',
					title: d
				})
			},
			addTodo() {
				uni.navigateTo({
					url: 'detail?title=添加备忘录'
				})
			},
			switchType(item) {
				let finish = 1 - item.finish;
				this.$request.postJson('todo/edit', {
					"id": item.id,
					"content": item.content,
					"finish": finish
				}).then(res => {
					this.search()
				})
			}
		}
	}
</script>

<style lang="scss">
</style>

<template>
	<view>
		<view class="beiwang-class">
			<ren-dropdown-filter :filterData='filterData' :defaultIndex="[0]" @onSelected='onSelected'></ren-dropdown-filter>
			<view class="beiwang-icon" @click="addTodo">
				<u-icon class="add-icon-class" name="tianjia" custom-prefix="custom-icon" color="#ab6e31" size="60"></u-icon>
			</view>
		</view>
		<view class="list-class">
			<view  class="search-class">
				<u-search :show-action="true" v-model="form.content" placeholder="搜索备忘录" action-text="搜索" :animation="true"
				 @search="search" @clear="search" @custom="search"></u-search>
			</view>
			<todo :datas="datas" @pSearch="search" @pDeleteTodoDetail="deleteTodoDetail" @pSwitchType="switchType"/>
		</view>
		<u-toast ref="uToast" />
		<u-modal v-model="show" content="确定要删除该数据吗？" :show-title="false" :show-cancel-button="true" @confirm="confirm"
		 ></u-modal>
		<u-modal v-model="switchShow" :content="switchContent" :show-title="false" :show-cancel-button="true" @confirm="switchConfirm"></u-modal>
		<view class="loadMore">
			<u-loadmore :status="status" :icon-type="iconType" :load-text="loadText" font-size="34" />
		</view>
	</view>
</template>

<script>
	import todo from '@/components/todo/todo.vue'
	import RenDropdownFilter from '@/components/ren-dropdown-filter/ren-dropdown-filter.vue'
	export default {
		components: {
			RenDropdownFilter,
			todo
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
				show: false,
				form: {
					currentPage: 1,
					pageSize: 5,
					finish: 0,
					content:''
				},
				status: 'loadmore',
				iconType: 'flower',
				loadText: {
					loadmore: '上拉加载更多',
					loading: '努力加载中',
					nomore: '没有更多了'
				},
				pages:0,
				switchShow:false,
				switchContent:'',
				switchItem:{}
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
			if(this.pages>this.form.currentPage){
				this.form.currentPage += 1;
				//还有下一页
				this.search(true);
			}else{
				this.status = 'nomore'
				this.loadText.nomore="没有更多了"
			}
		},
		methods: {
			deleteTodoDetail(item) {
				this.show = true
				this.switchItem = item 
			},
			search(type) {
				if(type!=true){
					this.form.currentPage = 1
				}
				this.status = 'loading'
				this.$request.postJson('todo/page', JSON.stringify(this.form)).then(res => {
					if(type==true){
						this.datas = this.datas.concat(res.data.data.records)
					}else{
						this.datas = res.data.data.records
					}
					this.pages = res.data.data.pages
					if(this.pages<=this.form.currentPage){
						this.status = 'nomore'
					}else{
						this.status = 'loadmore'
					}
				}).catch(err=>{
					this.status = 'nomore'
					this.loadText.nomore="系统繁忙，请稍后再试"
				})
				
			},
			goBack() {
				uni.navigateBack({})
			},
			onSelected(res) {
				this.form.finish = res[0][0].value
				this.search();
			},
			confirm() {
				this.$request.get('todo/delete?id=' + this.switchItem.id).then(res => {
					if (res.data.info != undefined && res.data.info.trim() != '') {
						this.$refs.uToast.show({
							title: res.data.info,
							type: 'success'
						})
					}
					this.$request.postJson('todo/page', JSON.stringify(this.form)).then(res => {
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
			switchConfirm(){
				let finish = 1 - this.switchItem.finish;
				this.$request.postJson('todo/edit', {
					"id": this.switchItem.id,
					"content": this.switchItem.content,
					"finish": finish
				}).then(res => {
					this.search()
				})
			},
			switchType(item) {
				if(item.finish==0){
					this.switchContent="确定要标记为完成吗？"
				}else{
					this.switchContent="确定要标记为未完成吗？"
				}
				this.switchShow = true
				this.switchItem  = item
			}
		}
	}
</script>

<style lang="scss">
</style>

<template>
	<view>
		<view  class="search-class">
			<u-search :show-action="true" v-model="form.title" placeholder="搜索日记" action-text="搜索" :animation="true"
			 @search="search" @clear="search" @custom="search"></u-search>
		</view>
		<riji :datas="datas" @pSearch="search" @pDeleteTodoDetail="deleteTodoDetail"/>
		<u-toast ref="uToast" />
		<u-modal v-model="show" content="确定要删除该数据吗？" :show-title="false" :show-cancel-button="true" @confirm="confirm"
		 :deleteId="deleteId"></u-modal>

		<view class="loadMore">
			<u-loadmore :status="status" :icon-type="iconType" :load-text="loadText" font-size="34" />
		</view>
		<view class="riji-icon" @click="addTodo">
			<u-icon class="add-icon-class" name="tianjia" custom-prefix="custom-icon" color="#ab6e31" size="80"></u-icon>
		</view>
	</view>
</template>

<script>
	import riji from '@/components/todo/riji.vue'
	import RenDropdownFilter from '@/components/ren-dropdown-filter/ren-dropdown-filter.vue'
	export default {
		components: {
			RenDropdownFilter,
			riji
		},
		data() {
			return {
				datas: [],
				deleteId: '',
				show: false,
				form: {
					currentPage: 1,
					pageSize: 5,
					finish: 0,
					title:''
				},
				status: 'loadmore',
				iconType: 'flower',
				loadText: {
					loadmore: '上拉加载更多',
					loading: '努力加载中',
					nomore: '没有更多了'
				},
				pages:0
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
				this.deleteId = item.id 
			},
			search(type) {
				if(type!=true){
					this.form.currentPage = 1
				}
				this.status = 'loading'
				this.$request.postJson('dairy/page', JSON.stringify(this.form)).then(res => {
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
			confirm() {
				this.$request.get('dairy/delete?id=' + this.deleteId).then(res => {
					if (res.data.info != undefined && res.data.info.trim() != '') {
						this.$refs.uToast.show({
							title: res.data.info,
							type: 'success'
						})
					}
					this.$request.postJson('dairy/page', JSON.stringify(this.form)).then(res => {
						this.datas = res.data.data.records
					})
				})
			},
			addTodo() {
				uni.navigateTo({
					url: 'detail?title=添加日记'
				})
			}
		}
	}
</script>

<style lang="scss">
</style>

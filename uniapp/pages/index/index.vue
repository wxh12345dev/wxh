<template>
	<view class="index-class">
		<view class="index-header">
			<view class="time">
				{{currentTime}}
			</view>
			<view class="date">
				{{currentDay}}
			</view>
			<view class="date">
				今天也是充满希望的一天！
			</view>
		</view>
		<u-tabs :list="list" :is-scroll="false" :current="current" @change="change" font-size="40"></u-tabs>
		<view class="index-content">
			<todo v-show="current==0" :datas="datas" @pSearch="search" @pDeleteTodoDetail="deleteTodoDetail" @pSwitchType="switchType"/>
			<riji v-show="current==1" :datas="dairys" @pSearch="search" @pDeleteTodoDetail="deleteTodoDetail"/>
			<u-toast ref="uToast" />
			<u-modal v-model="show" content="确定要删除该数据吗？" :show-title="false" :show-cancel-button="true" @confirm="confirm"></u-modal>
			
			<view class="loadMore">
				<u-loadmore :status="status" :icon-type="iconType" :load-text="loadText" font-size="34" />
			</view>
		</view>
		<u-modal v-model="switchShow" :content="switchContent" :show-title="false" :show-cancel-button="true" @confirm="switchConfirm"></u-modal>
	</view>
</template>

<script>
	import riji from '@/components/todo/riji.vue'
	import todo from '@/components/todo/todo.vue'
	import moment from "moment";
	export default {
		components: {
			riji,
			todo
		},
		data() {
			return {
				currentTime: '',
				currentDay: '',
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
					nomore: '没有更多了'
				},
				dairys:[],
				pages:0,
				list: [{
					name: '我的待办'
				}, {
					name: '我的日记'
				}],
				current: 0,
				switchShow:false,
				switchContent:'',
				switchItem:{}
			}
		},
		mounted() {
			setInterval(() => {
				let date = moment(new Date());
				this.currentDay = date.format('MM月DD日') + " " + this.getWeek(date.weekday())
				this.currentTime = date.format('HH:mm:ss')
			}, 1000)
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
		onShow() {
			if(this.current==0){
				this.search()
			}else if(this.current==1){
				this.dairySearch()
			}
		},
		methods: {
			getWeek(index) {
				switch (index) {
					case 0:
						return '星期日';
					case 1:
						return '星期一';
					case 2:
						return '星期二';
					case 3:
						return '星期三';
					case 4:
						return '星期四';
					case 5:
						return '星期五';
					case 6:
						return '星期六';
				}
			},
			change(index) {
				this.current = index;
				if(this.current==0){
					this.search()
				}else if(this.current==1){
					this.dairySearch()
				}
			},
			switchType(item) {
				if(item.finish==0){
					this.switchContent="确定要标记为完成吗？"
				}else{
					this.switchContent="确定要标记为未完成吗？"
				}
				this.switchShow = true
				this.switchItem  = item
			},
			deleteTodoDetail(item) {
				this.show = true
				this.deleteId = item.id 
			},
			search(type) {
				if(type==undefined){
					this.form.currentPage = 1
				}
				this.status = 'loading'
				this.$request.postJson('todo/page', JSON.stringify(this.form)).then(res => {
					if(type){
						console.log(this.datas)
						this.datas = this.datas.concat(res.data.data.records)
						console.log(this.datas)
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
			dairySearch(type) {
				if(type!=true){
					this.form.currentPage = 1
				}
				this.status = 'loading'
				this.$request.postJson('dairy/page', JSON.stringify(this.form)).then(res => {
					if(type==true){
						this.dairys = this.dairys.concat(res.data.data.records)
					}else{
						this.dairys = res.data.data.records
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
			confirm() {
				let path = 'todo';
				if(this.current==1){
					path = 'dairy'
				}
				this.$request.get(path+'/delete?id=' + this.deleteId).then(res => {
					if (res.data.info != undefined && res.data.info.trim() != '') {
						this.$refs.uToast.show({
							title: res.data.info,
							type: 'success'
						})
					}
					this.$request.postJson(path+'/page', JSON.stringify(this.form)).then(res => {
						if(this.current==1){
							this.dairys = res.data.data.records
						}else{
							this.datas = res.data.data.records
						}
					})
				})
			}
		}
	}
</script>

<style lang="scss">
</style>

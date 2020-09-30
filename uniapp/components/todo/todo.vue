<template>
	<view>
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
				<u-icon name="trash" size="35" @click="deleteTodoDetail(item)"></u-icon>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name:'todo',
		props: {
			datas: Array
		},
		data() {
			return {
			}
		},
		methods: {
			deleteTodoDetail(item) {
				this.$emit('pDeleteTodoDetail', item)
			},
			confirm(){
				let finish = 1 - this.item.finish;
				this.$request.postJson('todo/edit', {
					"id": this.item.id,
					"content": this.item.content,
					"finish": finish
				}).then(res => {
					this.$emit('pSearch')
				})
			},
			switchType(item) {
				this.$emit('pSwitchType',item)
			}
		}
	}
</script>

<style>

</style>

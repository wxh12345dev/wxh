<template>
	<view>
		<view class="toDo-class" v-for="(item,key) in datas" :key="item.id">
			<view class="head-class">
				<view class="head-title u-line-1">
					{{item.title}}
				</view>
				<view class="head-date">
					{{item.created}}
				</view>
			</view>
			<view class="dairy-class" @click="$request.navigateTo('/pages/riji/show?id='+item.id)">
				<view class="left-content u-line-3">
					{{item.content}}
				</view>
				<view class="right-content">
					<u-image :src="item.urlFirst" :lazy-load="true" height="100%" width="80px"></u-image>
				</view>
			</view>
			<view class="tool-class">
				<u-icon name="trash" size="35" @click="deleteTodoDetail(item.id)"></u-icon>
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
			deleteTodoDetail(id) {
				this.$emit('pDeleteTodoDetail', id)
			}
		}
	}
</script>

<style>

</style>

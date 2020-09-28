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
			<view v-show="current==0" class="toDo-class" v-for="(item,key) in datas" :key="item.id">
				<view class="head-class">
					<view class="head-title">
						{{item.createdStr}}
					</view>
					<view class="head-date" @click="deleteTodoDetail(item.id)">
						<u-icon name="trash"  size="28"></u-icon>
					</view>
				</view>
				<view class="body-class u-line-3" @click="getTodoDetail(item.id)">
					{{item.content}}
				</view>
			</view>
			<view v-show="current==1" class="toDo-class" v-for="(item,key) in diarys" :key="item.id" @click="getRijiDetail(item.id)">
				<view class="head-class">
					<view class="head-title u-line-1">
						{{item.title}}
					</view>
					<view class="head-date">
						{{item.createdStr}}
					</view>
				</view>
				<view class="dairy-class">
					<view class="left-content u-line-3">
						{{item.content}}
					</view>
					<view class="right-content">
						<u-image :src="item.url" :lazy-load="true" height="100%" width="80px"></u-image>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import moment from "moment";
	export default {
		data() {
			return {
				currentTime: '',
				currentDay: '',
				datas: [{
					id:'111',
					content: '危楼高百尺，手可摘星辰。不敢高声语，恐惊天上人。',
					createdStr: '2020-09-28 11:16'
				}, {
					id:'222',
					content: '日照香炉生紫烟，遥看瀑布挂前川。飞流直下三千尺，疑是银河落九天。',
					createdStr: '2020-09-28 11:16'
				}],
				diarys:[{
					id:'333',
					createdStr:'2020-09-28 11:16',
					title:'国庆去哪玩',
					content:'今年国庆中秋一起放假啊，今年国庆中秋一起放假啊，今年国庆中秋一起放假啊，今年国庆中秋一起放假啊，今年国庆中秋一起放假啊，今年国庆中秋一起放假啊，今年国庆中秋一起放假啊，今年国庆中秋一起放假啊，今年国庆中秋一起放假啊，今年国庆中秋一起放假啊，今年国庆中秋一起放假啊，',
					url:'http://qiniu.wucunhua.com/52cbd32485d343b7bdefcc2264b89ccb_4.jpg'
				}],
				list: [{
					name: '我的待办'
				}, {
					name: '我的日记'
				}],
				current: 0
			}
		},
		mounted() {
			setInterval(() => {
				let date = moment(new Date());
				this.currentDay = date.format('MM月DD日') + " " + this.getWeek(date.weekday())
				this.currentTime = date.format('HH:mm:ss')
			}, 1000)
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
			}
		}
	}
</script>

<style lang="scss">
</style>

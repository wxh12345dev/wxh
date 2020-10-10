import Vue from 'vue'
let baseUrl = ""
if (process.env.NODE_ENV === 'development') {
	baseUrl = "http://localhost:8083/"
} else {
	baseUrl = "https://www.wucunhua.com/"
}
Vue.prototype.$baseUrl = baseUrl
const request = {
	postJson(url, data) {
		return this.getPromise(url, "POST", {
			'content-type': 'application/json'
		}, data);
	},
	get(url) {
		return this.getPromise(url, "GET", {
			'content-type': 'application/x-www-form-urlencoded'
		});
	},
	post(url, data) {
		return this.getPromise(url, "POST", {
			'content-type': 'application/x-www-form-urlencoded'
		}, data);
	},
	navigateTo(url) {
		uni.navigateTo({
			url: url
		})
	},
	login(){
		//#ifdef MP-WEIXIN
			uni.login({
				provider: 'weixin',
				success: function(loginRes) {
					console.log(loginRes.authResult);
					// 获取用户信息
					uni.getUserInfo({
						provider: 'weixin',
						success: function(infoRes) {
							return true;
							console.log('用户昵称为：' + infoRes.userInfo.nickName);
						}
					});
				}
			});
		//#endif
		//#ifdef APP-PLUS || APP-PLUS-NVUE || H5
			//跳转登录页
			uni.navigateTo({
				url:'/pages/user/login?type=unlogin'
			})
		//#endif
	},
	getPromise(url, method, header, data) {
		if(url!='login'&&url!='register'&&url!='getCode'){
			let user = uni.getStorageSync('userinfo');
			if (user == undefined || user == '') {
				return new Promise((resolve, reject) => {
					this.login()
					reject(res)
				})
			}
		}
		return new Promise((resolve, reject) => {
			return uni.request({
				url: baseUrl + url,
				method: method,
				header: header,
				data: data,
				success: (res) => {
					//返回数据及处理
					if (res.data.code == 200) {
						console.log(res)
						resolve(res)
					} else if (res.data.code == 412) {
						uni.showToast({
							title: res.data.info,
							icon: 'none'
						})
						reject(res)
					} else if (res.data.code == 101) {
						this.login()
						reject(res)
					} else {
						uni.showToast({
							title: '系统繁忙，请稍后再试！',
							icon: 'none',
							duration: 2000
						})
						reject(res)
					}
				},
				fail: (err) => {
					uni.showToast({
						title: '系统繁忙，请稍后再试！',
						icon: 'none',
						duration: 2000
					})
					reject(err)
				}
			})
		})
	}
}

export default request;

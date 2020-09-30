import Vue from 'vue'
let baseUrl = ""
if (process.env.NODE_ENV === 'development') {
	baseUrl = "http://localhost:8083/"
} else {
	baseUrl = "生产基地址"
}
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
	getPromise(url, method, header, data) {
		return new Promise((resolve, reject) => {
			return uni.request({
				url: baseUrl + url,
				method: method,
				header: header,
				data: data,
				success: (res) => {
					//返回数据及处理
					if (res.data.code == 200) {
						resolve(res)
					} else if (res.data.code == 412) {
						uni.showToast({
							title: res.data.info,
							icon: 'none'
						})
						reject(res)
					} else if (res.data.code == 101) {
						uni.showToast({
							title: '尚未登陆，请登陆后重试！',
							icon: 'none'
						})
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

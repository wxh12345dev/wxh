<script>
	export default {
		onShow(){
			//判断是否登陆
			 let user= uni.getStorageSync('user');
			 if(user==undefined){
				 // uni.getProvider({
					//  service:'oauth',
					//  success:function(res){
						 
					//  }
				 // });
				 //未登陆
				 uni.login({
				 	success:function(res){
						 
					}
				 })
			 }
		}
	}
</script>

<style lang="scss">
	/*每个页面公共css */
	@import "uview-ui/index.scss";
	@import "common/my.scss";
	@import "common/iconfont.css";
</style>

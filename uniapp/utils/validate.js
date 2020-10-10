let validate = {
	isPhone(path) {
	     if(!(/^1[3456789]\d{9}$/.test(path))){
	         return false;
	     }
	     return true;
	},
	isEmai(path) {
	   var filter = /^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/; //正则表达式
	  if (!filter.test(path)) {
	    return false;
	  } else {
	    return true;
	  }
	}
}
export default  validate


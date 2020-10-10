import Vue from 'vue'
import App from './App'
import uView from "uview-ui";
import request from 'utils/request.js'
import validate from 'utils/validate.js'
Vue.prototype.$request = request
Vue.prototype.$validate = validate
Vue.use(uView);
Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()

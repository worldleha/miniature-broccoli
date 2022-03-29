import Vue from 'vue'
import App from './App.vue'
import VueRouter from "vue-router"

import { LayoutPlugin } from 'bootstrap-vue'
import { CardPlugin } from 'bootstrap-vue'

import router from "./router/index.js"

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(VueRouter)
Vue.use(LayoutPlugin)
Vue.use(CardPlugin)

Vue.config.productionTip = false

new Vue({
	beforeCreate(){
		Vue.prototype.$bus = this;
	},
	render: h => h(App),
	router,
}).$mount('#app')


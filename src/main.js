import Vue from 'vue'
import App from './App.vue'
import VueRouter from "vue-router"


import {CardPlugin,PaginationPlugin,LayoutPlugin} from 'bootstrap-vue'

import router from "./router"
import store from "./store"

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'


Vue.use(VueRouter)
Vue.use(LayoutPlugin)
Vue.use(CardPlugin)
Vue.use(PaginationPlugin)


Vue.config.productionTip = false

new Vue({
	beforeCreate(){
		Vue.prototype.$bus = this;
	},
	render: h => h(App),
	router,
	store,
}).$mount('#app')


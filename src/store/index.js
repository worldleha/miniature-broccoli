import Vue from 'vue'
import Vuex from "vuex"

import pageControlOption from "./pageControlOption.js"
import articleHeaderOption from "./articleHeaderOption.js"

Vue.use(Vuex)



export default new Vuex.Store({
	modules:{
		pageControlOption,
		articleHeaderOption
	}
})
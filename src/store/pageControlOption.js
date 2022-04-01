export default {
	namespaced: true,
	actions:{},
	mutations:{
		
		setPageFromPage(state,page){
			state.page = page
		}
	},
	state:{
		index:0,
		page:{
			page1:1,
			page2:0,
			page3:0,
		}
		
	},
	getters:{},
}

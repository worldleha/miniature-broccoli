
export default {
	
	namespaced: true,
	actions:{},
	mutations:{
		
		setArticleHeader(state,articleHeader){
			state.articleHeader = articleHeader
		}
		
	},
	
	state:{
		articleHeader:{
			id:0,
			link:"www.baidu.com",
			title:"Python装饰器", 
			tags:["Python"], 
			introduce:"python装饰器的作用十分强大,建议深入学习", 
			date:{year:2020,month:2,day:10},
			imgLink:""
			},
			
		
	},
	getters:{},
}
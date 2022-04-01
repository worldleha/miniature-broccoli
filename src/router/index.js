import VueRouter from "vue-router"

// import SelectedTypeList from "../pages/SelectedTypeList"
// import VLinks from "../pages/VLinks"
// import SecondShow from "../pages/SecondShow"

const router = new VueRouter({
	
	routes: [{
			name: "window1",
			path: "/",
			meta:{
				index : 0,
				page:{
					page1:1,
					page2:0,
					page3:0
				}
			}
			},
			{
			name: "window2",
			path: "/selectWindow",
			meta:{
				index : 1,
				page:{
					page1:1,
					page2:1,
					page3:0
				}
			},
			children: [{
				name: "articleLinks",
				path: "articleLinks",
				meta:{
					page:{
						page1:1,
						page2:1,
						page3:0
					}
				}
				
				
			},
			{
				name: "links",
				path: "links",
				meta:{

					page:{
						page1:1,
						page2:2,
						page3:0
					}
				}

			},
			{
				name: "guestBook",
				path: "guestBook",
				meta:{
					page:{
						page1:1,
						page2:3,
						page3:0
					}
				}
				
				
			}]
		},
		{
			name: "window3",
			path: "/showArticle",
			meta:{
				index : 2,
				page:{
					page1:1,
					page2:1,
					page3:1
				},
				
			},
			beforeEnter: (to, from, next) => {
				console.log(to.query)
				next()
			}
		}
	],

	scrollBehavior(to , from , savedPosition) {
		//暂时未用于滑动窗口
		// this.app.$bus.$emit("toPosition", to.matched[0].meta.index*window.innerHeight)
		// return false
		if (savedPosition) {
			return {
				behavior: 'smooth',
				...savedPosition
			}
		} else {
			return {
				behavior: 'smooth',
				y: to.matched[0].meta.index*window.innerHeight
			}
		}

	},
})

router.beforeEach((to, from, next) => {
	router.app.$store.state.pageControlOption.index = to.matched[0].meta.index
	router.app.$store.commit("pageControlOption/setPageFromPage", to.meta.page)
	next()
})
export default router
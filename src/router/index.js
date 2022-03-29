import VueRouter from "vue-router"

export default new VueRouter({

	routes: [{
			name: "window1",
			path: "/",
			meta:{
				index : 0
			}
		},
		{
			name: "window2",
			path: "/selectWindow",
			meta:{
				index : 1
			},
			children: [{
				name: "articleLinks",
				path: "articleLinks"
			}]
		},
		{
			name: "window3",
			path: "/showWindow",
			meta:{
				index : 2
			}
		}
	],

	scrollBehavior(to , from , savedPosition) {
		//暂时未用于滑动窗口
		// this.app.$bus.$emit("toPosition", to.matched[0].meta.index*window.innerHeight)
		// return false
		if (savedPosition) {
			return savedPosition
		} else {
			
			return {
				behavior: 'smooth',
				y: to.matched[0].meta.index*window.innerHeight
			}
		}

	},
})

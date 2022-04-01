<template>

	<div id="show_window">
		<TopNavigation class="top-header"></TopNavigation>
		<!-- //暂时未用于滑动窗口 -->
		<SlidingWindow id="move_page" :style="{transform: 'translateY('+position+'px)'}" backgroundColor="red">
			<template v-slot:default>
				<TopWindow></TopWindow>
			</template>

		</SlidingWindow>
		<SlidingWindow backgroundColor="green">
			<template>
				<MiddleWindow></MiddleWindow>
			</template>
		</SlidingWindow>
		<SlidingWindow backgroundColor="blue">
			<template>
				<BottomWindow></BottomWindow>
			</template>
		</SlidingWindow>

	</div>

</template>

<script>
	
	
	import SlidingWindow from "./components/SlidingWindow"
	import TopNavigation from "./components/TopNavigation"
	import TopWindow from "./components/TopWindow"
	import MiddleWindow from "./components/MiddleWindow"
	import BottomWindow from "./components/BottomWindow"
	
	
	export default {
		name: 'App',
		components: {
			SlidingWindow,
			TopNavigation,
			TopWindow,
			MiddleWindow,
			BottomWindow
		},
		data() {
			return {
				//暂时未用于滑动窗口
				position: 0,
				objtest:{on:"ssd"}
			}
		},
		
		methods: {
			//暂时未用于滑动窗口
			toPosition(pos) {
				this.position = pos
			}
		},
		mounted() {
			//暂时未用于滑动窗口
			this.$bus.$on("toPosition", this.toPosition)
		},
		beforeDestroy() {
			//暂时未用于滑动窗口
			this.$bus.$off("toPosition")
		}
	}
</script>

<style scoped>
	/* @import url("../public/css/reset.css"); */
	#show_window {

		position: absolute;

		width: 100%;
		height: 100vh;


		/* overflow: hidden; */
	}

	#move_page {
		transform: translateY(0px);
	}

	.top-header {

		position: fixed;

		width: 100vw;

		background-color: aqua;

		z-index: 12;
	}
</style>

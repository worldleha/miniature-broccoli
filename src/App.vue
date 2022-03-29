<template>
	
	<div id="show_window">
		<VTop class = "top-header"></VTop>
		<!-- //暂时未用于滑动窗口 -->
		<SlidingWindow 
		id= "move_page" 
		:style="{transform: 'translateY('+position+'px)'}" 
		backgroundColor = "red">
			<template v-slot:default>	
				<FirstShow/>
			</template>
			
		</SlidingWindow>
		<SlidingWindow backgroundColor = "green"></SlidingWindow>
		<SlidingWindow backgroundColor = "blue"></SlidingWindow>
		
	</div>
	
</template>

<script>
import SlidingWindow from "./components/SlidingWindow"
import VTop from "./components/VTop"
import FirstShow from "./components/FirstShow"

export default {
		name: 'App',
		components: {
			SlidingWindow,
			VTop,
			FirstShow,
		},
		data(){
			return{
				//暂时未用于滑动窗口
				position: 0
			}
		},
		methods:{
			//暂时未用于滑动窗口
			toPosition(pos){
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
	#show_window{
		
		position: absolute;
		
		width: 100%;
		height: 100vh;
		
		
		/* overflow: hidden; */
	}
	
	#move_page{
		transform: translateY(0px);
	}
	
	.top-header{
		
		position: fixed;
		
		width: 100vw;
	
		background-color: aqua;
		
		z-index: 12;
	}

</style>

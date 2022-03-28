<template>
	
	<div id="show_window">
		<VTop class = "top-header"></VTop>
		<SlidingWindow backgroundColor = "red">
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
	
	.top-header{
		
		position: fixed;
		
		width: 100vw;
	
		background-color: aqua;
		
		z-index: 12;
	}
	
</style>

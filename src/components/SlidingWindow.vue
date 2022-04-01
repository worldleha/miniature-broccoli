<template>
	<div class = "set-sliding-window" :style = "{backgroundColor}">
		<slot>"未插入"</slot>
	</div>
</template>

<script>
	
	export default {
		name: "SlidingWindow",
		data(){
			return {
				
			}
		},
		props:["backgroundColor"]
		
	}
</script>

<style scoped>
	.set-sliding-window{
		
		position: relative;
		
		width: 100%;
		height: 100vh;
		
		overflow: hidden;
	}
</style>

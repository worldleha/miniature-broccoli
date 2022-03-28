<template>
	<div class = "set-SlidingWindow" :style = "{backgroundColor}">
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
	.set-SlidingWindow{
		
		position: relative;
		
		width: 100%;
		height: 100vh;
		
		overflow: hidden;
	}
</style>

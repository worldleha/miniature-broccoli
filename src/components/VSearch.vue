<template>
	<div 
	@mouseenter="isHover = true"
	@mouseleave="isHover = false"
	class="search-box">
		<img class = "search-logo bg-success" src="../assets/imgs/search.png" alt="">
		<input 
		@focusin="isFocus = true"
		@focusout="isFocus = false"
		:class = "{'search-input':true, 'search-input-expand':isExpand}" 
		type="text">
	
	</div>
</template>

<script>
	export default{
		name:"VSearch",
		data(){
			return {
				isHover:false,
				isFocus:false,
			}
		},
		computed:{
			isExpand(){
				if(this.isHover||this.isFocus){
					return true
				}
				return false
			}
		},
		methods:{
			
		}
	}
</script>

<style>
	
	

	.search-input{
		
		width: 2rem;
		height: 2rem;
		
		padding-left: 0rem;
		border:none;
		border-radius: 2rem;
		outline: none;
		
		transition: width ease-in .3s, padding-left .3s;  
	}
	.search-input-expand{
		
		width: 16rem;
		padding-left: 2.5rem;
	}
	
	.search-logo{
		
		position: absolute;
		
		width: 2rem;
		height: 2rem;
		
		border-radius: 2rem;
		
		z-index: 10;
			
	}
	
	.search-box:hover .search-logo{
		
		background-color: #1e7e34 !important;
	}
	
</style>

<template>
	<header>
		<h2 class="header-title1">磷叶石的个人博客</h2>
		<h2 class="header-title2">磷叶石</h2>
		<SearchBar class="search-box"></SearchBar>
		<BButtonGroup class = "b-button-box">
			<BButton @click = "linkToWindow('window1')" variant="success">首页</BButton>
			<BButton @click = "linkToWindow('links')" variant="success">相关链接</BButton>
			
			<BButton variant="success">关于我</BButton>
			<BButton @click = "linkToWindow('guestBook')" variant="success">留言</BButton>
		</BButtonGroup>
	</header>
</template>

<script>

	import {BButtonGroup,BButton} from "bootstrap-vue"
	import SearchBar from "./SearchBar.vue"
	
	export default{
		name: 'TopNavigation',
		components:{
			BButtonGroup,
			BButton,
			SearchBar
		},
		methods:{
			linkToWindow(name){
				this.$router.push({
					name
				})
			}
			
		}

	}
</script>

<style scoped>
	@media screen and (max-width:1080px){
		.header-title1{
			display: none;
		}
		.header-title2{
			display: block !important;
			width: 6rem;
			height: 5rem;
			
			margin-bottom: 0;
			margin-left: 13%;
			
			color: white;
			line-height: 5rem;
			vertical-align: middle;
			
			overflow: hidden;
		}
		.b-button-box{
			margin-bottom: 1.25rem;
		}
		
	}
	
	.header-title1{
		
		float:left;
		
		width: 16rem;
		height: 5rem;
		
		margin-bottom: 0;
		margin-left: 13%;
		
		color: white;
		line-height: 5rem;
		vertical-align: middle;
		
		overflow: hidden;
	}
	.header-title2{
		
		display: none;
		float:left;
		
	}

	
	.b-button-box{
		
		float:right;
		
		margin-top: 1.25rem;
		margin-right: 2%;
		height: 2.5rem;
	}
	.search-box{
		margin-top: 1.6rem;
		float: right;
		margin-right: 14%;
	}

	
</style>

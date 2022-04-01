<template>
	<transition name="fade">
		<div v-show="index === 2" class="middle-window">
			<ShowArticle v-if="page.page3 === 1"></ShowArticle>
		</div>
	</transition>

</template>

<script>
	import {mapState} from "vuex"
	import ShowArticle from "../pages/ShowArticle"

	
	
	export default {
		name: "BottomWindow",
		components:{
			ShowArticle,
		},
		computed:{
			...mapState("pageControlOption",["index", "page"])
		}
	}
</script>
<style>
</style>

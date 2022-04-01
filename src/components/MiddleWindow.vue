<template>
	<transition name="fade">
		<div v-show="index === 1" class="middle-window overflow-hidden">
			<transition name="goleft">
					
					<IndexArticles class="middle-window-child" v-if="page.page2===1" key="index"></IndexArticles>
					<RelatedLinks class="middle-window-child" v-if="page.page2===2" key="link"></RelatedLinks>
					<GuestBook class="middle-window-child" v-if="page.page2===3" key="guest"></GuestBook>

			</transition>
		</div>
	</transition>

</template>

<script>
	import {mapState} from "vuex"
	
	import IndexArticles from "../pages/IndexArticles"
	import RelatedLinks from "../pages/RelatedLinks"
	import GuestBook from "../pages/GuestBook"
	
	export default {
		name: "MiddleWindow",
		components:{
			IndexArticles,
			RelatedLinks,
			GuestBook
		},
		computed:{
			...mapState("pageControlOption",["index", "page"])
		}
	}
</script>

<style>

</style>

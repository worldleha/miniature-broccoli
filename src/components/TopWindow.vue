<template>
	<transition name="fade">
		<div v-if="index === 0" class="middle-window">
			<div class="first-show-left">
				<PhotoCarousel class="first-show-top"></PhotoCarousel>
				<LatestArticle class="first-show-bottom"></LatestArticle>
			</div>
			<div class="first-show-right">
				<BulletinBoard class="first-show-top"></BulletinBoard>
				<RelatedTags class="first-show-bottom"></RelatedTags>
			</div>

		</div>

	</transition>

</template>

<script>
	import BulletinBoard from "./BulletinBoard"
	import RelatedTags from "./RelatedTags"
	import PhotoCarousel from "./PhotoCarousel"
	import LatestArticle from "./LatestArticle.vue"

	import {
		mapState
	} from "vuex"

	export default {
		name: "TopWindow",
		components: {
			BulletinBoard,
			RelatedTags,
			PhotoCarousel,
			LatestArticle

		},
		data() {
			return {
				in: 2
			}
		},
		computed: {

			...mapState("pageControlOption", ['index'])
		}


	}
</script>

<style>
	.first-show-left {
		float: left;

		width: 65%;
		height: 94%;

		margin: 1%;

		background-color: #155724;

		overflow: auto;
	}

	.first-show-right {
		float: right;

		width: 30%;
		height: 94%;

		margin: 1%;

		background-color: #b7c727;
	}

	.first-show-top {

		width: 100%;
		height: 46%;

		margin: 1% auto;

	}
	
	.first-show-bottom {
		width: 100%;
		height: 50%;

		margin: 1% auto;
	}

</style>

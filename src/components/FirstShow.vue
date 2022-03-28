<template>
	<div class="middle-window">
		<div class="first-show-left">
			<VSlider class = "first-show-top"></VSlider>
			<NewArticlesList class = "first-show-bottom"></NewArticlesList>
		</div>
		<div class="first-show-right">
			<BulletinBoard class="first-show-top"></BulletinBoard>
			<TypeList class="first-show-bottom"></TypeList>
		</div>

	</div>
</template>

<script>

	import BulletinBoard from "./BulletinBoard"
	import TypeList from "./TypeList"
	import VSlider from "./VSlider"
	import NewArticlesList from "./NewArticlesList.vue"

	export default {
		name: "FirstShow",
		components: {
			BulletinBoard,
			TypeList,
			VSlider,
			NewArticlesList

		},

		
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
		height: 48%;

		margin: 2% auto;

	}

	.first-show-bottom {
		width: 100%;
		height: 45%;

		margin: 2% auto;
	}
</style>

<template>
	<b-card tag="article" style="overflow: auto;">
		<b-card-header class="parent-container" header-tag="nav">
			<h5>标签</h5>
			<router-link class="child-tag"  to="/selectWindow/articleLinks">更多标签</router-link>
		</b-card-header>
		<br/>
		<b-container>
			<b-row >
				<b-col>
					<BButton size="sm" variant="outline-success">Python</BButton>
				</b-col>
				<b-col>
					<BButton size="sm" variant="outline-success">Java</BButton>
				</b-col>
			</b-row>
			<br/>
			<b-row >
				<b-col>
					<BButton size="sm" variant="outline-success">Vue</BButton>
				</b-col>
				<b-col>
					<BButton size="sm" variant="outline-success">Js</BButton>
				</b-col>
			</b-row>
			<br/>
			<b-row >
				<b-col>
					<BButton size="sm" variant="outline-success">Spring Boot</BButton>
				</b-col>
				<b-col>
					<BButton size="sm" variant="outline-success">MySQL</BButton>
				</b-col>
			</b-row>

		</b-container>

	</b-card>

</template>

<script>
	import {
		BButton
	} from "bootstrap-vue"

	export default {
		name: "RelatedTags",
		components: {
			BButton,

		}
	}
</script>

<style scoped>
	.parent-container{
		position: relative;
	}
	.child-tag{
		position: absolute;
		right: 2rem; 
		bottom: 0.75rem;
	}
</style>

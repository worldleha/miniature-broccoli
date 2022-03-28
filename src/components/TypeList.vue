<template>
	<b-card header="分类" tag="article" style="max-width: 20rem;overflow: auto;">

		<b-container >
			<b-row class="space">
				<b-col>
					<BButton variant="outline-success">Python</BButton>
				</b-col>
				<b-col>
					<BButton variant="outline-success">Java</BButton>
				</b-col>
			</b-row>

			<b-row class="space">
				<b-col>
					<BButton variant="outline-success">Vue</BButton>
				</b-col>
				<b-col>
					<BButton variant="outline-success">Js</BButton>
				</b-col>
			</b-row>

			<b-row class="space">
				<b-col>
					<BButton variant="outline-success">Spring Boot</BButton>
				</b-col>
				<b-col>
					<BButton variant="outline-success">MySQL</BButton>
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
		name: "TypeList",
		components: {
			BButton,

		}
	}
</script>

<style scoped>
	.space{
		margin: 5px 0;
	}
</style>

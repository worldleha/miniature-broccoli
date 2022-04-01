<template>
	<div class="overflow-hidden">
		<b-card title="💡" tag="h6">

			<BListGroup>
				<BListGroupItem href="#">Vue + Spring Boot</BListGroupItem>
				<BListGroupItem href="#">快速开发</BListGroupItem>
				<BListGroupItem href="#">5天建站</BListGroupItem>
				<BListGroupItem href="#">更新时间: 2022/3/31</BListGroupItem>
			</BListGroup>



		</b-card>


	</div>
</template>

<script>
	import {
		BListGroupItem,
		BListGroup
	} from 'bootstrap-vue'
	export default {
		name: "BulletinBoard",
		components: {
			BListGroupItem,
			BListGroup,

		},

	}
</script>

<style>
</style>

<template>
	<div>
		<b-card 
		no-body 
		class="overflow-hidden" 
		style="width: 100%;margin: 0 auto;"
		v-for="articleHeader in articleHeaders"
		:key = 'articleHeader.id'
		@click="linkToArticle('window3', articleHeader)"
		>
			<b-row no-gutters>
				<b-col md="3">
					<b-card-img
					height = "100%"
					:src="articleHeader.imgLink" class="rounded-0"></b-card-img>
				</b-col>
				<b-col md="9">
					<b-card-body :title="articleHeader.title">
						<b-card-text>
							{{articleHeader.introduce}}
						</b-card-text>
					</b-card-body>
				</b-col>
			</b-row>
		</b-card>
	</div>
</template>

<script>
	export default {
		name: "LatestArticle",
		
		data(){
			return {
				articleHeaders:[{
					id: 0,
					title: "Python装饰器",
					tags: ["Python"],
					introduce: "python装饰器的作用十分强大,建议深入学习",
					date: {
						year: 2020,
						month: 2,
						day: 10
					},
					imgLink: "",
				}]
			}
		},
		
		methods:{
			linkToArticle(name, articleHeader){
				this.$router.push({
					name,
					query:{
						articleHeader
						
					}
				})
			}
		}
	}
</script>

<style>
</style>

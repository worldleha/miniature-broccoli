<template>
	<div class="fill-height overflow-auto">
		<b-card title-tag="h5" :title="articleHeader.title">
				<!-- 简介 -->
			<b-card-text style="min-height:3rem ; font-size: 12px;">
				{{articleHeader.introduce}} {{articleHeader.id}}
			</b-card-text>
			<b-row>
				<b-col md="9">
					<!-- 标签 -->
					<b-container fluid>
						<BBadge variant="primary" v-for="tag in articleHeader.tags" :key="tag">{{tag}}</BBadge>
					</b-container>
				</b-col>
				<b-col md="3">
					<!-- 日期 -->
					<BBadge variant="success">{{articleHeader.date.year}}-{{articleHeader.date.month}}-{{articleHeader.date.day}}</BBadge>
				</b-col>
			</b-row>

		</b-card>
		<br />
		<b-card id="showHtml" class="card-size-margin" v-html="vInnerHtml">
			加载中, 请稍等!
		</b-card>
		<br />
		<CommentBoard title="评论" :comments = "comments"></CommentBoard>
		

	</div>
</template>

<script>
	import {mapState} from "vuex"
	
	import {BBadge} from "bootstrap-vue"
	
	import CommentBoard from "../components/CommentBoard"
	
	export default {

		name: "ShowArticle",
		components: {
			BBadge,
			CommentBoard
		},
		computed:{
			
			...mapState("articleHeaderOption", ["articleHeader"])
		},
		data() {
			return {
				comments:[{
					id:1,
					name: "nihao",
					content:"这个网站真有趣!",
					ip:"192.168.1.1",
					date:{
						year:2022,
						month:2,
						day:12
					}
					
				}],
				vInnerScript: "",
				vInnerHtml: ""
			}
		},
		methods: {

			getInnerArticleHtml() {

				return `
				<canvas id = "pen" width= "300px"
					height= "300px" style = "position: absolute; z-index: 100;"></canvas>
				<div id = "jin" style="width: 100%;height :500px; background-color: blue;">
				
					<div id = "tian" style="width: 80px;height : 60px; margin: 50px auto; background-color: red;">
				
					</div>
				</div>
				<vscript>
					
					var p = document.getElementById("pen").getContext("2d");
					p.rect(100,50,50,50)
					p.fillStyle = "burlywood";
					p.fill()
					p.stroke()
					document.getElementById("jin").onclick=function(event){
						console.log(event)
					}
				</vscript>
				`

			},
			loadSplitScriptHtml() {

				var articleHtml = this.getInnerArticleHtml()
				var pattern = /<vscript>(.*?)<\/vscript>/gs
				var vscriptArray = articleHtml.match(pattern)
				vscriptArray.forEach(function(item) {
					articleHtml = articleHtml.replace(item, "")
				})
				vscriptArray = vscriptArray.map(vscript => vscript.slice(9, -10))

				this.vInnerHtml = articleHtml
				this.vInnerScript = vscriptArray.join("\n")

			},
			execScript() {

				var scriptElement = document.createElement("script")
				scriptElement.type = "text/javascript"
				scriptElement.text = this.vInnerScript

				document.getElementById("showHtml").appendChild(scriptElement)


			}


		},
		watch: {



		},
		mounted() {
			this.loadSplitScriptHtml()
			this.$nextTick(this.execScript)
		},


	}
</script>

<style>
</style>

<template>
	<b-card class="card-size-margin" :title="title">
		<div :style="{minHeight}" >
			<br/>
			<b-row 
			style="margin-bottom: 10px;"
			v-for="(comment, index) in comments" 
			:key="comment.ip" >
			
			
				<b-col  :md="6+(index%2===0?2:-2)">
					<b-card
					v-if="index%2 === 0"
					style="background-color: deeppink;" 
					:title="comment.name">
						
						<b-card-text style="min-height:3rem ;">
							{{comment.content}}
						</b-card-text>
						<b-row>
							<b-col md="9">
								<!-- ip -->
								<BBadge variant="primary"> IP: {{comment.ip}}</BBadge>
							
							</b-col>
							<b-col md="3">
								<!-- 日期 -->
								<BBadge variant="success">{{comment.date.year}}-{{comment.date.month}}-{{comment.date.day}}</BBadge>
							</b-col>
						</b-row>
					</b-card>
				</b-col>
				<b-col :md="6+(index%2===0?-2:2)">
					<b-card
					v-if="index%2 != 0"
					style="background-color: deeppink;" 
					:title="comment.name">
						
						<b-card-text style="min-height:3rem ;">
							{{comment.content}}
						</b-card-text>
						<b-row>
							<b-col md="9">
								<!-- ip -->
								<BBadge variant="primary"> IP: {{comment.ip}}</BBadge>
							
							</b-col>
							<b-col md="3">
								<!-- 日期 -->
								<BBadge variant="success">{{comment.date.year}}-{{comment.date.month}}-{{comment.date.day}}</BBadge>
							</b-col>
						</b-row>
					</b-card>
				</b-col>
			</b-row >
		</div>
		
	
		<b-pagination 
		v-model="currentPage" 
		:total-rows="rows" 
		:per-page="perPage" 
		align = "center"
		style="padding-top: 16px;"
		first-text="首"
		prev-text="上"
		next-text="下"
		last-text="末"
		>
		</b-pagination>
	</b-card>
</template>

<script>
	import {BBadge} from "bootstrap-vue"
	export default{
		
		name:"CommentBoard",
		components:{
			BBadge,
			
		},
		computed: {
			rows() {
				return this.comments.length
			}
		},
		data(){
			return{
				currentPage: 1,
				perPage: 4,
			}
		},
		props:["comments", "title","minHeight"]
	}
</script>

<style>
</style>

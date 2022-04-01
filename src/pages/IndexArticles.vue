<template>
	<div>
		<b-card>
			<b-card-header header-tag="nav">
				<b-container fluid>
					<BButton class="float-tag" variant="outline-success" v-for="item in tags" :key="item">{{item}}
					</BButton>
		
				</b-container>
			</b-card-header>
		</b-card>
		
		<b-card
		no-body 
		class="overflow-hidden" 
		v-for="articleHeader in articleHeaders"
		:key="articleHeader.id">
			<b-row no-gutters>
				<b-col md="4">
					<b-card-img
					:src="articleHeader.imgLink" class="fill-height rounded-0"></b-card-img>
				</b-col>
				<b-col md="8">
					<b-card-body class="fill-height" :title="articleHeader.title">	
					
						<!-- 简介 -->
						<b-card-text style="min-height:6rem ;">
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
						
		
		
					</b-card-body>
				</b-col>
			</b-row>
		</b-card>
		
		
	</div>
	
</template>

<script>
	import {
		BButton,
		BBadge
	} from "bootstrap-vue"
	export default {
		name: "IndexArticles",
		components: {
			BButton,
			BBadge,
		},
		data() {
			return {
				tags: ["全部"],
				articleHeaders:[{
					id:0,
					link:"www.baidu.com",
					title:"Python装饰器", 
					tags:["Python"], 
					introduce:"python装饰器的作用十分强大,建议深入学习", 
					date:{year:2020,month:2,day:10},
					imgLink:""
					}],
				page:1,
			}
		},
		computed:{
			
		},
		methods:{
			getTags(){

				this.tags.splice(this.tags.length,0,...["Python", "Java", "C", "C++", "Vue", "Bootstrap"])
			},
			fromTagGetData(){
				this.page++
				return [{
					id:1,
					link:"www.baidu.com",
					title:"Python装饰器", 
					tags:["Python"], 
					introduce:"python装饰器的作用十分强大,建议深入学习", 
					date:{year:2020,month:2,day:10},
					imgLink:""
					}]
			},
			showData(){
				this.articleHeaders.splice(this.articleHeaders.length,0,...this.fromTagGetData())
			}
		},
		mounted(){
			this.getTags();
			this.showData();
		}
	}
</script>

<style scoped>
	.float-tag {
		margin: 4px;
	}
</style>

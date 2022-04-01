<template>
	<div class="overflow-auto">
		<CommentBoard :comments = "comments" title = "留言板" minHeight = "84%"></CommentBoard>
	</div>
</template>

<script>
	
	
	import CommentBoard from "../components/CommentBoard"
	
	export default {

		name: "GuestBook",
		components: {
			
			CommentBoard
		},
		
		data() {
			return {
				comments: [{
					id: 1,
					name: "nihao",
					content: "这个网站真有趣!",
					ip: "192.168.1.1",
					date: {
						year: 2022,
						month: 2,
						day: 12
					}
				},
				{
					id: 2,
					name: "tahao",
					content: "That is fun!",
					ip: "192.164.1.1",
					date: {
						year: 2022,
						month: 3,
						day: 12
					}
				
				},
				{
					id: 3,
					name: "wohao",
					content: "gg!",
					ip: "192.168.1.12",
					date: {
						year: 2022,
						month: 2,
						day: 15,
					}
				
				}],
				
			}
		},
		


	}
</script>

<style>
</style>

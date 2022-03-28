<template>
	<div class = "bulletin-board-box">
		<b-card>
			<BTabs>
				<BTab title="First" active>
					<BListGroup >
						<BListGroupItem>你好啊兄弟</BListGroupItem>
						<BListGroupItem>前面有治疗术</BListGroupItem>
						<BListGroupItem>你说的是搜索</BListGroupItem>
						<BListGroupItem>测试需要不需要</BListGroupItem>
					</BListGroup>
				</BTab>
				<BTab title="Second">
					
				</BTab>
			</BTabs>
			
		</b-card>
		
		
	</div>
</template>

<script>
	import {BListGroupItem,BListGroup , BTabs, BTab} from 'bootstrap-vue'
	export default{
		name:"BulletinBoard",
		components:{
			BListGroupItem,
			BListGroup,
			BTab,
			BTabs,

		}
	}
</script>

<style>
	.bulletin-board-box{
		overflow: hidden;
	}
</style>

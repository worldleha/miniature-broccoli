<template>
	<div>
		<b-card no-body class="fill-height p-3">
			<b-tabs class="fill-height" pills card no-key-nav vertical>
				<b-tab title="我的链接" active>
					<b-card-text>
						<p>CSDN: <a href="https://blog.csdn.net/LaeHa?type=blog">https://blog.csdn.net/LaeHa?type=blog</a></p>
			
						<p>哔哩哔哩: <a href="https://space.bilibili.com/10016271">https://space.bilibili.com/10016271</a></p>
	
						<p>微信公众号: <a href="https://mp.weixin.qq.com/s/9jjXRdMhj51FjOAM_qikEQ">xinyouyiji142434</a></p>
						
						<p>GitHub: <a href="https://github.com/worldleha">https://github.com/worldleha</a></p>
					</b-card-text>
				</b-tab>
				<b-tab title="我的收藏">
					<b-card-text>
						<p>IP查看: <a href="http://www.ipqi.co/">http://www.ipqi.co/</a></p>
									
						<p>正则练习: <a href="https://alf.nu/RegexGolf">https://alf.nu/RegexGolf</a></p>
							
						<p>计算器: <a href="https://www.geogebra.org/3d">https://www.geogebra.org/3d</a></p>
						
						<p>在线C编译器: <a href="https://www.onlinegdb.com/online_c_compiler">https://www.onlinegdb.com/online_c_compiler</a></p>
						
						<p>谷歌实验室: <a href="http://1000.chromeexperiments.com/">http://1000.chromeexperiments.com/</a></p>
						
						<p>电路仿真(外网): <a href="https://www.falstad.com/circuit/circuitjs.html">https://www.falstad.com/circuit/circuitjs.html/ </a></p>
					</b-card-text>
				</b-tab>
				<b-tab title="设计">
					<b-card-text>
						<p>配色: <a href="https://colorhunt.co/">https://colorhunt.co/</a></p>
									
						<p>SVG编辑器: <a href="http://svg.wxeditor.com/">http://svg.wxeditor.com/</a></p>
							
						<p>原型图设计: <a href="https://design.penpot.app/">https://design.penpot.app/</a></p>
					</b-card-text>
				</b-tab>
				<b-tab title="框架">
					<b-card-text>
						<p>Vue: <a href="https://cn.vuejs.org/">https://cn.vuejs.org/</a></p>
									
						<p>Bootstrap: <a href="https://getbootstrap.com/">https://getbootstrap.com/</a></p>
							
						<p>Spring Boot: <a href="https://spring.io/projects/spring-boot#overview">https://spring.io/projects/spring-boot#overview</a></p>
					</b-card-text>
				</b-tab>
			</b-tabs>
		</b-card>
	</div>
</template>

<script>
	import {
		BTab,
		BTabs
	} from 'bootstrap-vue'
	export default {
		name: "RelatedLinks",
		components: {
			BTab,
			BTabs
		}
	}
</script>

<style>

</style>

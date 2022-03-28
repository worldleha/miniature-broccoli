<template>
	<div>
		<transition :duration="1000">
			<div class="slider-box">
				<div class="slider-ctrol slider-ctrol-left" @click="nextSlider(false)"></div>
				<div class="slider-ctrol slider-ctrol-right" @click="nextSlider(true)"></div>
				<div class="slider-img" :style="{backgroundImage: item}" v-for="(item, index) in urls"
					v-show="num===index" :key="index"></div>
				
			</div>
		</transition>
	</div>
</template>

<script>
	export default {
		name: "VSlider",
		data() {
			return {
				timer: null,
				color: "red",
				urls: [
					'url(' + require("../assets/imgs/0.jpg") + ')',
					'url(' + require("../assets/imgs/1.jpg") + ')',
					'url(' + require("../assets/imgs/2.jpg") + ')'
				],
				num: 0,
				animateTime: 10000
			}
		},
		mounted() {
			this.play() // 初始的时候加载
		},

		methods: {
			autoPlay() { // num自增，通过判断 num 和 index 相不相等，来显示对应 index 的banner

				this.num++
				if (this.num == this.urls.length) {
					this.num = 0
				}

			},
			play() { // 设置定时器，让banner显示隐藏
				if(this.timer){
					clearInterval(this.timer)
				}
				this.timer =setInterval(this.autoPlay, this.animateTime)
			},
			nextSlider(isNext){
				if(isNext){
					this.num++;

				}else{
					this.num--;
					
				}
				this.num =(this.num+this.urls.length)%this.urls.length;
	
				this.play();
			}
		}
	}
</script>

<style>
	.slider-box {
		position: relative;
		width: 100%;
		height: 100%;


	}

	.slider-img {
		width: 80%;
		height: 100%;
		margin: 0 auto;

		background-repeat: no-repeat;
		background-size: 100%;
		background-position: center;

	}
	.slider-ctrol{
		
		position: absolute;
		
		width: 10%;
		height: 100%;
		
		background-color: #6610F2;
	}
	
		
	.slider-ctrol-left{
		left: 10%
	}
	.slider-ctrol-right{
		right: 10%
	}
</style>

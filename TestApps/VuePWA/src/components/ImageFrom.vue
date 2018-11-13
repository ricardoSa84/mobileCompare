<template>
  <div>
    <p class="centerText">{{status}}</p>
    <div class="centerText">
        <input v-model="numberOfTimes" placeholder="Number of images to load">
        <button @click="process">Load images</button>
    </div>    
    <div class="centerText" v-for="image in images" :key="image.id" style="display:inline-block">
      <img @load="imgLoaded" :src="image.path"/>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      images: [{}],
      numberOfTimes: 0,
      status: 'Processing, please wait',
      start: '',
      imagesLoaded: 0
    }
  },
  methods: {
    process () {
      this._data.start = Date.now()
      this._data.images = []
      this._data.imagesLoaded = 0
      this._data.status = 'Processing, please wait'
      for (var x = 0; x < this._data.numberOfTimes; x++) {
        var obj = {
          id: x,
          path: this.$route.params.mode === '1' ? '/static/img/highres.jpg' : 'https://wallpapertag.com/wallpaper/full/7/9/1/134025-cool-medical-wallpaper-2000x1153-ipad-retina.jpg'
        }
        this._data.images.push(obj)
      }
    },
    imgLoaded () {
      this._data.imagesLoaded++
      if (this._data.imagesLoaded === parseInt(this._data.numberOfTimes)) {
        this._data.status = 'Task finished in: ' + Math.ceil(Date.now() - this._data.start) + 'ms'
      }
    }
  }
}
</script>
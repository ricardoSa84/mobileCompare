<template>
  <div>
    <p class="centerText">{{status}}</p>
    <p><input v-model="numberOfTimes" placeholder="Screens to load"></p>
    <p><button class="centerText" @click="startProcess">Load screens</button></p>
  </div>
</template>

<script>
export default {
  data () {
    return {
      numberOfTimes: null,
      status: ''
    }
  },
  beforeRouteUpdate (to, from, next) {
    console.log('beforeRouteUpdate')
  },
  watch: {
    '$route' (newRoute, oldRoute) {
      if (this.$route.params.counter === '0') {
        this._data.status = Date.now() - parseInt(this.$route.params.start)
      } else {
        this.$router.push('/memoryManagement/' + (parseInt(this.$route.params.counter) - 1) + '/' + this.$route.params.start)
      }
    }
  },
  methods: {
    startProcess () {
      const count = this._data.numberOfTimes
      const srt = Date.now()
      // this.$router.push('/memoryManagement/1/2')
      this.$router.push('/memoryManagement/' + count + '/' + srt)
    }
  }
}
</script>
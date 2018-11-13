<template>
  <div>
    <p class="centerText">Task is running please whait</p>
    <template v-for="result in results">
      <p :key="result">{{result}}</p>
    </template>
  </div>
</template>

<script>
export default {
  data () {
    return {
      results: ['']
    }
  },
  mounted: function () {
    var count = 0
    var startTime = Date.now()
    if (this.$route.params.mode === '0') {
      this.cpuIntensiveTask()
    } else if (this.$route.params.mode === '1') {
      for (var i = 1; i <= 32; i++) {
        this.$worker.run((args) => {
          for (var x = 0; x <= 100000; x++) {
            for (var y = 2; y < x; y++) {
              if (x % y === 0) {
                // console.log(x + ' Não é primo.')
                break
              }
            }
            // console.log(x + ' é primo.')
          }
          return args
        }, [i])
          .then(result => {
            // console.log('Task finished: ' + result)
            this._data.results.push('Task finished: ' + result)
            count++
            if (count === 32) {
              // console.log('Process finished in: ' + Math.ceil(Date.now() - startTime) + 'ms')
              this._data.results.push('Process finished in: ' + Math.ceil(Date.now() - startTime) + 'ms')
            }
          })
          .catch(e => {
            console.error(e)
          })
      }
    }
  },
  methods: {
    cpuIntensiveTask () {
      var start = Date.now()
      for (var x = 0; x < 100000; x++) {
        for (var y = 2; y < x; y++) {
          if (x % y === 0) {
            // console.log(x + 'Não é primo.')
            break
          }
        }
        // console.log(x + ' é primo.')
      }
      // console.log('Task finished in: ' + Math.ceil(Date.now() - start) + 'ms')
      this._data.results.push('Task finished in: ' + Math.ceil(Date.now() - start) + 'ms')
    } 
  }
}
</script>
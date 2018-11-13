<template>
  <v-container fluid>
    <v-layout row wrap>
    <v-flex lg6 md6 sm6 xs12 v-if="$route.params.chartType !== '' || $route.params.chartType !== undefined">
        <historydatatable></historydatatable>
    </v-flex>
    <v-flex lg6 md6 sm6 xs12 v-if="$route.params.chartType !== '' || $route.params.chartType !== undefined">
      <line-chart :chartData = "chartOptions"></line-chart>
        <!--<line-chart v-if="$route.params.chartType === 'chartData'" :chartData = "chartData" ></line-chart>
        <line-chart v-if="$route.params.chartType === 'heartBeats'" :chartData = "heartBeats" ></line-chart>
        <line-chart v-if="$route.params.chartType === 'temps'" :chartData = "temps" ></line-chart>
        <line-chart v-if="$route.params.chartType === 'humidity'" :chartData = "humidity" ></line-chart>
        <line-chart v-if="$route.params.chartType === 'CO2'" :chartData = "CO2" ></line-chart> -->
    </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
import LineChart from '../Charts/LineChart'
export default {
  mounted () {
    console.log('ChartType: ', this.$route.params.chartType)
    if (this.$route.params.chartType === '' || this.$route.params.chartType === undefined) {
      this.$route.push('/dashboard')
    }
    var splits = this.$route.params.chartType.split(',')
    console.log(splits)
    this.$store.dispatch('setChartData', { id: splits[0], label: splits[1] })
    /*
    switch (this.$route.params.chartType) {
      case 'heartBeats':
        this.$store.dispatch('getHeartBeats')
        break
      case 'temps':
        this.$store.dispatch('getTemps')
        break
      case 'humidity':
        this.$store.dispatch('getHumidity')
        break
      case 'CO2':
        this.$store.dispatch('getCO2')
        break
      default:
        break
      // }
    }
    */
  },
  components: {
    'line-chart': LineChart
  },
  computed: {
    chartData () {
      return this.$store.state.chartData
    },
    heartBeats () {
      return this.$store.state.heartBeats
    },
    temps () {
      return this.$store.state.temps
    },
    humidity () {
      return this.$store.state.humidity
    },
    CO2 () {
      return this.$store.state.CO2
    },
    chartOptions () {
      return this.$store.getters.chartOptions
    }
  }
}
</script>
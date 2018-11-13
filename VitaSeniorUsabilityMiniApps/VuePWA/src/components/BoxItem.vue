<template>
  <v-container grid-list-md>
    <p>{{board.location}}</p>
    <v-layout row wrap>
        <v-flex lg6 md6 sm6 xs12 v-for="item in board.Boardmodel.Sensors" :key="item.id">
          <v-card :class="getColor(item.measure)" router :to="'/DataVisualisation/' + item.id + ',' + item.measure">
              <v-container fluid grid-list-sm>
                   <v-layout row class="mt-2 mb-0" >
                      <v-flex lg12 md12 sm12 xs12 align-center justify-center>
                          <div>
                              <div class="subheading">{{item.measure}}</div>
                          </div>
                      </v-flex>
                  </v-layout>
                  <v-layout row class="mt-2 mb-0" >
                      <v-flex lg6 md6 sm6 xs6 align-center justify-center>
                          <v-icon class="mx-4" x-large>{{getIcon(item.measure)}}</v-icon>
                      </v-flex>
                      <v-flex lg6 md6 sm6 xs6 align-center justify-center>
                          <div>
                              <div class="subheading">{{item.last_values[item.last_values.length-1] }}</div>
                          </div>
                      </v-flex>
                  </v-layout>
              </v-container>
          </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>


<script>
export default {
  props: ['board'],
  computed: {},
  methods: {
    getColor (sensor) {
      switch (sensor) {
        case 'humidity':
          return 'blue'
        case 'temperature':
          return 'green'
        default:
          return 'Orange'
      }
    },
    getIcon (sensor) {
      switch (sensor) {
        case 'temperature':
          return 'ac_unit'
        case 'humidity':
          return 'opacity'
        default:
          return 'settings'
      }
    }
  }
}
</script>

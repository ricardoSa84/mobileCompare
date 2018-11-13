<template>
  <v-content>
        <v-container >
          <v-flex xs12 sm12 md12 lg12>
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>User status</v-toolbar-title>
              </v-toolbar>
                <v-card-text>
                    <template v-for="patientStat in patientStatus">
                        <v-layout row wrap fill-height :key="patientStat.id">
                            <v-flex xs1 sm2 md2 lg2 align-center justify-center>
                                <v-avatar 
                                :tile="false"
                                size="50px"
                                class="grey lighten-4"
                                >
                                    <img :src="patientStat.image"/>
                                </v-avatar>
                            </v-flex>
                            <v-flex xs10 sm8 md8 lg8 align-center justify-center>
                                <span>{{patientStat.statusText}}</span>
                            </v-flex>
                            <v-flex xs1 sm2 md2 lg2 align-center justify-center>
                                <!-- controlar a visibilidade com uma propriedade do objecto -->
                                <img v-if="!patientStat.danger" v-bind:src="require('../assets/tickCircle.svg')" height="25px" width="25px"/>
                                <!-- uma para ok outro para perigo -->
                                <img v-else v-bind:src="require('../assets/warning.svg')" height="25px" width="25px"/>
                            </v-flex>
                        </v-layout>
                    </template>
                </v-card-text>
            </v-card>
        </v-flex>
        <template  v-for="box in boxes">
            <v-flex xs12 sm12 md12 lg12 :key="box.id">
                <v-card class="elevation-12">
                    <v-toolbar dark color="primary">
                        <v-toolbar-title>{{box.address}}</v-toolbar-title>
                    </v-toolbar>
                    <v-card-text>
                        <template v-for="board in box.boards">
                            <boxItemsList :key="board.id" :board="board"></boxItemsList>
                        </template>
                        <v-flex xs12 sm12 md12 lg12>
                            <v-card class="elevation-12">
                                <v-toolbar dark color="primary">
                                    <v-toolbar-title>Patients</v-toolbar-title>
                                </v-toolbar>
                                <v-card-text>
                                    <v-container grid-list-md >
                                        <v-layout row wrap>
                                            <template v-for="patientNfo in patientsInfo" >
                                                <v-flex lg6 md6 sm6 xs12 :key="patientNfo.info.id">
                                                    <patientInfo :patientInfo = patientNfo ></patientInfo>
                                                </v-flex>
                                            </template>      
                                        </v-layout>
                                    </v-container>              
                                </v-card-text>
                            </v-card>
                        </v-flex>
                    </v-card-text>
                </v-card>
            </v-flex>
        </template>
      </v-container>
    </v-content>
  
</template>


<script>
export default {
  created () {
    this.$store.dispatch('prepareDashData')
  },
  data: () => ({}),
  computed: {
    boxes () {
      return this.$store.getters.dashData
    },
    patientStatus () {
      return this.$store.getters.patientStatus
    },
    patientsInfo () {
      return this.$store.getters.patientsInfo
    }
  }
}
</script>

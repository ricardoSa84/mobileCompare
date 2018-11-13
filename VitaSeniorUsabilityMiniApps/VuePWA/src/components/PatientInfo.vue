<template>
  <v-content>
        <v-container >
          <v-flex xs12 sm12 md12 lg12>
            <v-card class="elevation-12">
                <v-card-text>
                    <v-layout row wrap fill-height>
                        <v-flex xs4 sm4 md4 lg4 align-center justify-center>
                            <v-avatar 
                            :tile="false"
                            size="50px"
                            class="grey lighten-4"
                            >
                                <img src="https://vuetifyjs.com/static/doc-images/cards/desert.jpg"/>
                            </v-avatar>
                        </v-flex>
                            
                        <v-flex xs8 sm8 md8 lg8 align-center justify-center>
                            <v-layout row>{{patientInfo.info.name}}</v-layout>
                            <v-layout row>{{patientInfo.info.birthdate}}</v-layout>
                        </v-flex>
                    </v-layout>
                    <v-layout row fill-height>
                        <v-flex xs4 sm4 md4 lg4 align-center justify-center>
                            Heart beats:
                        </v-flex>
                        <v-flex xs8 sm8 md8 lg8 align-center justify-center>
                            {{patientInfo.data.heartBeats}}
                        </v-flex>
                    </v-layout>
                    <v-layout row fill-height>
                        <v-flex xs4 sm4 md4 lg4 align-center justify-center>
                            Oxygen levels:
                        </v-flex>
                        <v-flex xs8 sm8 md8 lg8 align-center justify-center>
                            {{patientInfo.data.O2}}
                        </v-flex>
                    </v-layout>
                    <v-layout row fill-height>
                        <v-flex xs4 sm4 md4 lg4 align-center justify-center>
                            Blood pressure:
                        </v-flex>
                        <v-flex xs8 sm8 md8 lg8 align-center justify-center>
                            <v-layout row>Hight: {{patientInfo.data.bpHight}}</v-layout>
                            <v-layout row>Low: {{patientInfo.data.bpLow}}</v-layout>
                        </v-flex>
                    </v-layout>
                </v-card-text>
            </v-card>
        </v-flex>
      </v-container>
    </v-content>
  
</template>


<script>
export default {
  props: ['patientInfo'],
  computed: {}
}
</script>

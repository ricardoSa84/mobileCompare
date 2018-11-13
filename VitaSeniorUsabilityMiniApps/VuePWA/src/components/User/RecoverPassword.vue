<template>
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>Reset password</v-toolbar-title>
                <v-spacer></v-spacer>
                <v-tooltip bottom>
                  <v-btn
                    icon
                    large
                    router
                    to="/signup"
                    slot="activator"
                  >
                    <v-icon large>create</v-icon>
                  </v-btn>
                  <span>Sign Up</span>
                </v-tooltip>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field v-model="email" prepend-icon="email" name="email" label="E-mail" type="email"></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn @click="recover" color="primary">Reset</v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
</template>

<script>
  export default {
    data: () => ({
      email: '',
      drawer: null
    }),
    props: {
      source: String
    },
    methods: {
      recover () {
        this.axios.post(this.$store.getters.baseUrl + '/forgot',
          {
            email: this.email
          }, this.$store.getters.requestConfig)
          .then((response) => {
            console.log(response)
            if (response.status === 200) {
              this.$router.push('/resetpassword')
            }
          })
          .catch((error) => {
            console.log(error)
          })
      }
    }
  }
</script>
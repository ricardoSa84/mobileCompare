<template>
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>SignUp form</v-toolbar-title>
                <v-spacer></v-spacer>
                <v-tooltip bottom>
                  <v-btn
                    icon
                    large
                    router
                    to="/signin"
                    slot="activator"
                  >
                    <v-icon large>lock</v-icon>
                  </v-btn>
                  <span>Sign In</span>
                </v-tooltip>
              </v-toolbar>
              <v-card-text>
                <v-form v-model="valid" ref="form" lazy-validation>
                  <v-text-field prepend-icon="name" 
                                name="name" 
                                label="Name"
                                v-model="name"
                                required
                                :rules="[v => !!v || 'Item is required']">
                  </v-text-field>
                  <v-text-field prepend-icon="email" 
                                name="email" 
                                label="email"
                                v-model="email" 
                                type="email"
                                required
                                :rules="emailRules">
                  </v-text-field>
                  <v-text-field prepend-icon="lock" 
                                name="password" 
                                label="Password" 
                                id="password"
                                v-model="password" 
                                type="password"
                                :rules="[v => !!v || 'Item is required']"
                                required>
                  </v-text-field>
                  <v-text-field prepend-icon="lock" 
                                name="passwordConf" 
                                label="Password Confirmation" 
                                id="passwordConf"
                                v-model="passwordConf" 
                                type="password"
                                :rules="[(comparePasswords)]">
                  </v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary"
                      @click="onSignup"
                      :disabled="!valid">
                      Sign Up
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
</template>

<script>
  export default {
    data () {
      return {
        valid: true,
        name: '',
        email: '',
        password: '',
        passwordConf: '',
        emailRules: [
          v => !!v || 'E-mail is required',
          v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail must be valid']
      }
    },
    computed: {
      comparePasswords () {
        return this.password !== this.passwordConf ? 'Passwords do not match' : null
      }
    },
    methods: {
      onSignup () {
        this.axios.post(this.$store.getters.baseUrl + '/register',
          {
            name: this.name,
            email: this.email,
            password: this.password
          }, this.$store.getters.requestConfig)
          .then((response) => {
            console.log(response)
            if (response.status === 200) {
              this.$router.push('/signin')
            }
          })
          .catch((error) => {
            console.log(error)
          })
      }
    }
  }
</script>
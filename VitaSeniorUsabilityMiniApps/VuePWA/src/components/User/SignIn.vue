<template>
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-form>
              <img src="/static/img/vita_logo.png" width="150px" height="150px" style="margin-left:115px; margin-top:50px"/>
              <v-text-field v-model="name" name="name" label="name" type="text"></v-text-field>
              <v-text-field v-model="email" name="email" label="Email" type="text"></v-text-field>
              <v-text-field v-model="password" name="password" label="Password" id="password" type="password"></v-text-field>
              <v-btn color="primary" block v-on:click = "login">Login</v-btn>
              <v-btn color="primary" block v-on:click = "register">Register</v-btn>
            </v-form>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
</template>

<script>
  export default {
    created: function () {
      /*
      if (window.localStorage.getItem('userInfo') !== null && window.localStorage.getItem('userInfo') !== '') {
        var userInfo = JSON.parse(window.localStorage.getItem('userInfo'))
        this.$store.dispatch('autoLogin', userInfo)
      }
      */
    },
    data: () => ({
      drawer: null,
      email: '',
      password: ''
    }),
    methods: {
      login () {
        var data = { email: this.email, password: this.password }
        this.$store.dispatch('login', data)
      },
      register () {
        var data = { name: this.name, email: this.email, password: this.password }
        this.$store.dispatch('login', data)
      }
    },
    computed: {
      user () {
        return this.$store.getters.user
      }
    },
    /*
    watch: {
      user (value) {
        if (value !== null && value !== undefined) {
          this.$router.push('/')
        }
      }
    }
    ,
    */
    props: {
      source: String
    }
  }
</script>
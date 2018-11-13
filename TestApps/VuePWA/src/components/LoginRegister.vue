<template>
  <div>
    <p class="centerText">{{status}}</p>
    <p><input v-model="numberOfTimes" placeholder="Number of request to make"></p>
    <p><input class="centerText" prepend-icon="person" v-model="email" name="email" label="Email" type="text"/></p>
    <p><input class="centerText" prepend-icon="lock" v-model="password" name="password" label="Password" id="password" type="password"/></p>
    <p><button class="centerText" @click="startProcess(true)">Register</button><button class="centerText" @click="startProcess(false)">Login</button></p>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      settings: {
        baseUrl: 'https://vitasenior.eu-gb.mybluemix.net',
        config: {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': '',
            'Accept-Language': 'pt',
            'Accept-Version': '1.0.0'
          },
          responseType: 'application/json'
        }
      },
      email: '',
      password: '',
      numberOfTimes: undefined,
      status: 'Processing, please wait',
      requestsToMake: 0,
      requestMade: 0
    }
  },
  methods: {
    startProcess (register) {
      this._data.status = 'Processing, please wait'
      this._data.requestMade = 0
      var start = Date.now()
      var ctx = this
      var callback = function () {
        ctx._data.requestMade = ctx._data.requestMade + 1
        if (ctx._data.requestsToMake === ctx._data.requestMade.toString()) {
          ctx._data.status = 'Task finished in: ' + Math.ceil(Date.now() - start) + 'ms'
        }
      }
      ctx._data.requestsToMake = ctx._data.numberOfTimes
      for (var i = 0; i < ctx._data.numberOfTimes; i++) {
        this.data = { 'email': this._data.email, 'name': 'Ricardo', 'password': this._data.password }
        if (register) {
          var split = ctx._data.email.split('@')
          this.data.email = split[0] + i + '@' + split[1]
          ctx.makePostRequest('/register', this.data, ctx._data.settings.config, callback)
        } else {
          ctx.makePostRequest('/login', this.data, ctx._data.settings.config, callback)
        }
      }
    },
    makePostRequest (path, data, options, callback) {
      axios
        .post(
          this._data.settings.baseUrl + path,
          data,
          options
        )
        .then(response => {
          if (response.status === 200) {
            callback(response)
          }
        })
        .catch(error => {
          console.log('Error:', error)
        })
    }
  }
}
</script>
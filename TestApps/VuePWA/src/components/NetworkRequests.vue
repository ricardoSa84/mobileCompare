<template>
  <div>
    <p class="centerText">{{status}}</p>
    <p><input v-model="numberOfTimes" placeholder="Number of request to make"></p>
    <p><input class="centerText" prepend-icon="person" v-model="email" name="email" label="Email" type="text"/></p>
    <p><input class="centerText" prepend-icon="lock" v-model="password" name="password" label="Password" id="password" type="password"/></p>
    <p><button class="centerText" @click="startProcess">Start requests</button></p>
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
    startProcess () {
      this._data.status = 'Processing, please wait'
      var start = Date.now()
      this._data.requestMade = 0
      var data = { email: this.email, password: this.password }
      var ctx = this
      this.makePostRequest('/login', data, ctx._data.settings.config, function (response) {
        ctx._data.settings.config.headers.Authorization = response.data.token
        for (var i = 0; i < ctx._data.numberOfTimes; i++) {
          ctx.makeGetRequest('/vitabox', ctx._data.settings.config, function (response) {
            ctx._data.requestsToMake += response.data.vitaboxes.length
            response.data.vitaboxes.forEach(vitabox => {
              ctx.makeGetRequest('/vitabox/' + vitabox.id + '/patient', ctx._data.settings.config, function () {
                ctx._data.requestMade += 1
                if (ctx._data.requestsToMake === ctx._data.requestMade) {
                  ctx._data.status = 'Task finished in: ' + Math.ceil(Date.now() - start) + 'ms'
                }
              })
            })
          })
        }
      })
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
    },
    makeGetRequest (path, options, callback) {
      axios
        .get(
          this._data.settings.baseUrl + path,
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
import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '../router/index'

Vue.use(Vuex)

const state = {
  settings: {
    baseUrl: 'https://vitasenior.eu-gb.mybluemix.net',
    config: {
      headers: {
        'Accept-Version': '1.0.0',
        'Content-Type': 'application/json',
        Authorization: '' },
      responseType: 'application/json'
    }
  },
  patientStatus: [
    {
      id: 'xfsadfasdfasdgasdf',
      image: 'http://i.dailymail.co.uk/i/pix/2016/07/28/15/36AB9FA500000578-3712825-image-a-1_1469715827980.jpg',
      statusText: 'There seems to be a irregularity with the heart rate.',
      danger: true
    },
    {
      id: 'kjhlkwhdaskjdgldsf',
      image: 'http://images.medicaldaily.com/sites/medicaldaily.com/files/2014/06/06/old-lady.jpg',
      statusText: 'All parameters within normal range.',
      danger: false
    }
  ],
  patientsInfo: [
    {
      info: {
        id: 'a77ea0fe-5e34-4189-9702-95cb69b4cd1d',
        birthdate: '1987-02-28',
        name: 'José António',
        gender: 'male',
        since: '2018-02-19T14:55:59.000Z'
      },
      data: {
        heartBeats: 60,
        O2: 50,
        bpHight: 13,
        bpLow: 8
      }
    },
    {
      info: {
        id: 'a77ea0fe-5e34-4189-9702-95cb69b4cd1b',
        birthdate: '1989-02-31',
        name: 'Maria Antonieta',
        gender: 'female',
        since: '2018-02-19T14:55:59.000Z'
      },
      data: {
        heartBeats: 70,
        O2: 60,
        bpHight: 12,
        bpLow: 7.5
      }
    }
  ],
  dashData: {},
  chartOptions: {
    labels: [],
    datasets: [
      {
        label: '',
        backgroundColor: '#43A047',
        data: []
      }
    ]},
  heartBeats: {},
  temps: {},
  humidity: {},
  CO2: {},
  users: {},
  userInfo: {
    name: '',
    age: '',
    gender: '',
    email: '',
    phone: ''
  },
  userData: null,
  userHeaders: {}
}

const mutations = {
  getHeartBeats: (state, data) => {
    if (data !== undefined) {
      state.heartBeats.updateTime = new Date()
      state.heartBeats.data = data
    }
    return state.users.data
  },
  getTemps: (state, data) => {
    if (data !== undefined) {
      state.temps.updateTime = new Date()
      state.temps.data = data
    }
    return state.users.data
  },
  getHumidity: (state, data) => {
    if (data !== undefined) {
      state.humidity.updateTime = new Date()
      state.humidity.data = data
    }
    return state.users.data
  },
  getCO2: (state, data) => {
    if (data !== undefined) {
      state.CO2.updateTime = new Date()
      state.CO2.data = data
    }
    return state.users.data
  },
  getUsers: (state, data) => {
    if (data !== undefined) {
      state.users.updateTime = new Date()
      state.users.data = data
    }
    return state.users.data
  },
  getUserHeaders: (state, data) => {
    if (data !== undefined) {
      state.userHeaders.updateTime = new Date()
      state.userHeaders.data = data
    }
    return state.userHeaders.data
  },
  login: (state, data) => {
    if (data != null) {
      console.log('login dados:', data.token)
      state.userData = {}
      state.settings.config.headers.Authorization = data.token
      state.userInfo = {
        name: 'Ricardo Anacleto',
        age: '34',
        gender: 'Male',
        email: 'ricardosa.mail@gmail.com',
        phone: '912058923'
      }
      router.push('/boxlist')
    } else {
      state.userData = null
    }
    window.localStorage.setItem('userInfo', JSON.stringify(data))
  },
  editItem: (state, data) => {
    if (data !== undefined) {
      state.users.updateTime = new Date()
      Object.assign(state.users.data[data.index], data.obj)
    }
  },
  addItem: (state, data) => {
    if (data !== undefined) {
      state.users.updateTime = new Date()
      state.users.data.push(data)
    }
  },
  prepareDashData: (state, data) => {
    if (data !== undefined) {
      state.dashData = data
    }
  },
  setChartData: (state, data) => {
    if (data != null) {
      state.chartOptions.label = data.label
      data.records.forEach(function (record) {
        var dt = Date.parse(record.datetime)
        state.chartOptions.labels.push(dt.toLocaleString('pt-PT'))
        state.chartOptions.datasets.data.push(record.value)
      })
    }
  }
}

const actions = {
  getHeartBeats: context => {
    var dados
    // limitar os pedidos por x tempo
    if (state.heartBeats.updateTime === undefined || new Date() - Date.parse(state.heartBeats.updateTime) > 1000) {
      /*
      $.ajax({
        url: root + '/users',
        method: 'GET'
      }).then(function (data) {
        context.commit('getUsers', data)
      })
      */
      dados = {
        labels: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'],
        datasets: [
          {
            label: 'HeartBeats',
            backgroundColor: '#43A047',
            data: [40, 20, 12, 39, 10, 40, 39, 80, 40, 20, 12, 11]
          }
        ]
      }
      state.heartBeats = dados
      context.commit('getHeartBeats', dados.data)
    }
  },
  getTemps: context => {
    var dados
    // limitar os pedidos por x tempo
    if (state.temps.updateTime === undefined || new Date() - Date.parse(state.temps.updateTime) > 1000) {
      /*
      $.ajax({
        url: root + '/users',
        method: 'GET'
      }).then(function (data) {
        context.commit('getUsers', data)
      })
      */
      dados = {
        labels: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'],
        datasets: [
          {
            label: 'Temps',
            backgroundColor: '#43A047',
            data: [40, 20, 12, 39, 10, 40, 39, 80, 40, 20, 12, 11]
          }
        ]
      }
      state.temps = dados
      context.commit('getTemps', dados.data)
    }
  },
  getHumidity: context => {
    var dados
    // limitar os pedidos por x tempo
    if (state.humidity.updateTime === undefined || new Date() - Date.parse(state.humidity.updateTime) > 1000) {
      /*
      $.ajax({
        url: root + '/users',
        method: 'GET'
      }).then(function (data) {
        context.commit('getUsers', data)
      })
      */
      dados = {
        labels: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'],
        datasets: [
          {
            label: 'humidity',
            backgroundColor: '#43A047',
            data: [40, 20, 12, 39, 10, 40, 39, 80, 40, 20, 12, 11]
          }
        ]
      }
      state.humidity = dados
      context.commit('getHumidity', dados.data)
    }
  },
  getCO2: context => {
    var dados
    // limitar os pedidos por x tempo
    if (state.CO2.updateTime === undefined || new Date() - Date.parse(state.CO2.updateTime) > 1000) {
      /*
      $.ajax({
        url: root + '/users',
        method: 'GET'
      }).then(function (data) {
        context.commit('getUsers', data)
      })
      */
      dados = {
        labels: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'],
        datasets: [
          {
            label: 'CO2',
            backgroundColor: '#43A047',
            data: [40, 20, 12, 39, 10, 40, 39, 80, 40, 20, 12, 11]
          }
        ]
      }
      state.CO2 = dados
      context.commit('getCO2', dados.data)
    }
  },
  getUsers: context => {
    var dados
    // limitar os pedidos por x tempo
    if (state.users.updateTime === undefined || new Date() - Date.parse(state.users.updateTime) > 1000) {
      /*
      $.ajax({
        url: root + '/users',
        method: 'GET'
      }).then(function (data) {
        context.commit('getUsers', data)
      })
      */
      dados = {
        updateTime: '25/02/2018 16:00',
        data: [
          {
            id: '585402ef-68dd-44a4-a44b-04152e659d11',
            email: 'donaldtrump@usa.com',
            since: '2018-02-19T14:41:13.000Z',
            sponsor: false
          },
          {
            id: '78007a69-baa2-4b24-b936-234883811b6a',
            email: 'queenelizabeth@majesty.uk',
            since: '2018-02-19T14:40:14.000Z',
            sponsor: true
          }
        ]
      }
      state.users = dados
      context.commit('getUsers', dados.data)
    }
  },
  getUserHeaders: context => {
    var dados
    // limitar os pedidos por x tempo
    if (state.userHeaders.updateTime === undefined || new Date() - Date.parse(state.userHeaders.updateTime) > 1000) {
      /*
      $.ajax({
        url: root + '/users',
        method: 'GET'
      }).then(function (data) {
        context.commit('getUserHeaders', data)
      })
      */
      dados = {
        updateTime: '25/02/2018 16:00',
        data: [
          { text: 'Id', align: 'left', sortable: false, value: 'Id' },
          { text: 'Email', value: 'Email', sortable: true },
          { text: 'Since', value: 'Since', sortable: true },
          { text: 'Actions', value: 'Actions', sortable: false }
        ]
      }
      state.userHeaders = dados
      context.commit('getUserHeaders', dados.data)
    }
  },
  login: (context, data) => {
    console.log('data: ', data)
    axios.post(store.getters.baseUrl + '/login',
      data, store.getters.requestConfig)
      .then((response) => {
        console.log('Resposta:', response)
        if (response.status === 200) {
          context.commit('login', response.data)
        }
      })
      .catch((error) => {
        console.log('Error:', error)
      })
  },
  autoLogin: (context, data) => {
    context.commit('login', data)
  },
  logout: (context) => {
    context.commit('login', null)
    window.localStorage.removeItem('userInfo')
  },
  editItem: (context, data) => {
    /*
      $.ajax({
        url: root + '/users',
        method: 'GET'
      }).then(function (data) {
        context.commit('getUserHeaders', data)
      })
      */
    context.commit('editItem', data)
  },
  addItem: (context, data) => {
    /*
      $.ajax({
        url: root + '/users',
        method: 'GET'
      }).then(function (retData) {
        data.id = redData.id
        context.commit('addItem', data)
      })
    */
    data.id = 123
    context.commit('addItem', data)
  },
  prepareDashData: (context) => {
    var vitaboxes = []
    var boxes2go = 999
    // get vitaboxes
    axios.get(store.getters.baseUrl + '/vitabox', store.getters.requestConfig)
      .then((response) => {
        if (response.status === 200) {
          boxes2go = response.data.vitaboxes.length
          // foreach vita get boards
          response.data.vitaboxes.forEach(function (box) {
            console.log('Box', box)
            axios.get(store.getters.baseUrl + '/vitabox/' + box.id + '/board', store.getters.requestConfig)
              .then((response) => {
                console.log('Boards:', response.data.boards)
                if (response.status === 200) {
                  box.boards = response.data.boards
                  // get patients
                  axios.get(store.getters.baseUrl + '/vitabox/' + box.id + '/patient', store.getters.requestConfig)
                    .then((response) => {
                      console.log('Boards:', response.data.boards)
                      if (response.status === 200) {
                        box.patients = response.data.patients
                        vitaboxes.push(box)
                        boxes2go = boxes2go - 1
                        if (boxes2go === 0) {
                          // commit com os dados completos
                          context.commit('prepareDashData', vitaboxes)
                        }
                      }
                    })
                    .catch((error) => {
                      console.log(error)
                    })
                }
              })
              .catch((error) => {
                console.log(error)
              })
          })
        }
      })
      .catch((error) => {
        console.log(error)
      })
  },
  setChartData: (context, data) => {
    axios.get(store.getters.baseUrl + '/record/sensor/' + data.id + '/page/1',
      store.getters.requestConfig)
      .then((response) => {
        if (response.status === 200) {
          response.data.label = data.label
          context.commit('setChartData', response.data)
        }
      })
      .catch((error) => {
        console.log('Error:', error)
      })
  },
  getVitaBoxes: (context) => {
    axios.get(store.getters.baseUrl + '/vitabox', store.getters.requestConfig)
      .then((response) => {
        if (response.status === 200) {
          console.log('vitaboxes:', response.data.vitaboxes)
        }
      })
      .catch((error) => {
        console.log(error)
      })
  }
}

const getters = {
  user (state) {
    return state.userData
  },
  userInfo (state) {
    return state.userInfo
  },
  baseUrl (state) {
    return state.settings.baseUrl
  },
  requestConfig (state) {
    return state.settings.config
  },
  patientStatus (state) {
    return state.patientStatus
  },
  patientsInfo (state) {
    return state.patientsInfo
  },
  dashData (state) {
    return state.dashData
  },
  chartOptions (state) {
    return state.chartOptions
  }
}

export const store = new Vuex.Store({
  state,
  mutations,
  actions,
  getters})

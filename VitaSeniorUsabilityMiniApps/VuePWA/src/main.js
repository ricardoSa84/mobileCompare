// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Historydatatable from './components/User/historyDataTable.vue'
import BoxItemsList from './components/BoxItem.vue'
import PatientInfo from './components/PatientInfo.vue'
// import LineChart from './components/Charts/LineChart.vue'
import {
  Vuetify,
  VApp,
  VNavigationDrawer,
  VFooter,
  VList,
  VBtn,
  VIcon,
  VGrid,
  VToolbar,
  VCard,
  Vform,
  VtextField,
  Vtooltip,
  transitions,
  VDataTable,
  VDialog,
  VSelect,
  VAvatar,
  VDivider
} from 'vuetify'
import '../node_modules/vuetify/src/stylus/app.styl'
import {store} from './store/store'
import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.use(VueAxios, axios)
Vue.use(Vuetify, {
  components: {
    VApp,
    VNavigationDrawer,
    VFooter,
    VList,
    VBtn,
    VIcon,
    VGrid,
    VToolbar,
    VCard,
    Vform,
    VtextField,
    Vtooltip,
    transitions,
    VDataTable,
    VDialog,
    VSelect,
    VAvatar,
    VDivider
  },
  theme: {
    primary: '#F05A28',
    secondary: '#F7931D',
    accent: '#F7931D',
    error: '#f44336',
    warning: '#ffeb3b',
    info: '#2196f3',
    success: '4caf50'
  }
})

Vue.component('historydatatable', Historydatatable)
Vue.component('boxItemsList', BoxItemsList)
Vue.component('patientInfo', PatientInfo)
// Vue.component('lineChart', LineChart)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>'
})

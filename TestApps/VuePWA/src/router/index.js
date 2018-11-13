import Vue from 'vue'
import Router from 'vue-router'
import MainMenu from '@/components/MainMenu'
import CPUIntensive from '@/components/CPUIntensive'
import ImageFrom from '@/components/ImageFrom'
import NetWorkRequests from '@/components/NetworkRequests'
import LoginRegister from '@/components/LoginRegister'
import MemoryManagement from '@/components/MemoryManagement'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'MainMenu',
      component: MainMenu
    },
    {
      path: '/cpuIntensive/:mode',
      name: 'cpuIntensive',
      component: CPUIntensive
    },
    {
      path: '/ImageFrom/:mode',
      name: 'ImageFrom',
      component: ImageFrom
    },
    {
      path: '/networkRequests',
      name: 'NerWorkRequests',
      component: NetWorkRequests
    },
    {
      path: '/loginRegister',
      name: 'LoginRegister',
      component: LoginRegister
    },
    {
      path: '/memoryManagement',
      name: 'memoryManagement',
      component: MemoryManagement
    },
    {
      path: '/memoryManagement/:counter/:start',
      name: 'memoryManagement',
      component: MemoryManagement
    }
  ]
})

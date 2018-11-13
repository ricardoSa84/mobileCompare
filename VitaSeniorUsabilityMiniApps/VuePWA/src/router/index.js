import Vue from 'vue'
import Router from 'vue-router'
import Dashboard from '@/components/Dashboard'
import Users from '@/components/sponsor/Users'
import SignUp from '@/components/User/SignUp'
import SignIn from '@/components/User/SignIn'
import DataVisualisation from '@/components/User/DataVisualisation'
import PassWordRecovery from '@/components/User/RecoverPassword'
import ResetPassword from '@/components/User/ResetPassword'
import BoxList from '@/components/hardware/Devices'
import AuthGuard from './authGard'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'SignIn',
      component: SignIn
    },
    {
      path: '/boxList',
      name: 'boxlist',
      component: BoxList,
      beforeEnter: AuthGuard
    },
    {
      path: '/',
      name: 'SignIn',
      component: SignIn
    },
    {
      path: '/signup',
      name: 'SigUp',
      component: SignUp
    },
    {
      path: '/recoverpassword',
      name: 'Password Recovery',
      component: PassWordRecovery
    },
    {
      path: '/resetpassword',
      name: 'Reset Password',
      component: ResetPassword
    },
    {
      path: '/users',
      name: 'Users',
      component: Users,
      beforeEnter: AuthGuard
    },
    {
      path: '/dashboard',
      name: 'Dashboard',
      component: Dashboard,
      beforeEnter: AuthGuard
    },
    {
      path: '/DataVisualisation/:chartType',
      name: 'Data Visualisation with id',
      component: DataVisualisation,
      beforeEnter: AuthGuard
    },
    {
      path: '/DataVisualisation/',
      name: 'Data Visualisation',
      component: Dashboard,
      beforeEnter: AuthGuard
    }
  ],
  mode: 'history'
})

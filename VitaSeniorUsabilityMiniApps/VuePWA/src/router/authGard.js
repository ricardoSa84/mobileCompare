import { store } from '../store/store'
export default (to, from, next) => {
  if (store.getters.user != null || (window.localStorage.getItem('userInfo') !== null && window.localStorage.getItem('userInfo') !== '')) {
    next()
  } else {
    next('/signin')
  }
}

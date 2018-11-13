<template>
  <v-app dark class="black">
    <v-content>
      <router-view/>
    </v-content>
  </v-app>
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
  data () {
    return {
      clipped: true,
      drawer: false,
      fixed: false,
      miniVariant: false,
      right: true,
      rightDrawer: false,
      title: 'VITASENIOR'
    }
  },
  computed: {
    menuItems () {
      let items = [
        {
          icon: 'lock',
          title: 'SignIn/SignUp',
          link: '/signin'
        }]
      if (this.isUserLogedIn) {
        items = [{
          icon: 'people',
          title: 'Users',
          link: '/users'
        },
        {
          icon: 'dashboard',
          title: 'Dashboard',
          link: '/dashboard'
        }]
      }
      return items
    },
    userInfo () {
      console.log('userInfo:', this.$store.getters.userInfo)
      return this.$store.getters.userInfo
    },
    isUserLogedIn () {
      return this.$store.getters.user !== null && this.$store.getters.user !== undefined
    }
  },
  methods: {
    logOut () {
      console.log('logout')
      this.$store.dispatch('logout')
      this.$router.push('/')
    }
  },
  name: 'App'
}
</script>

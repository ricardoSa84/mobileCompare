<template>
  <div>
    <v-data-table
      :headers="headers"
      :items="items"
      hide-actions
      class="elevation-1"
    >
      <template slot="items" slot-scope="props">
        <td>{{ props.item.id }}</td>
        <td class="text-xs-right">{{ props.item.email }}</td>
        <td class="text-xs-right">{{ props.item.since }}</td>
      </template>
      <template slot="no-data">
        <v-btn color="primary" @click="initialize">Reset</v-btn>
      </template>
    </v-data-table>
  </div>
</template>

<script>

export default {
  data: () => ({}),
  computed: {
    items () {
      return this.$store.state.users.data
    },
    headers () {
      return this.$store.state.userHeaders.data
    }
  },
  created () {
    this.initialize()
  },
  methods: {
    initialize () {
      // this.$store.dispatch('getUserHeaders')
      setTimeout(() => {
      // this.$store.dispatch('getTemps')
      }, 5000)
    }
  }
}
</script>
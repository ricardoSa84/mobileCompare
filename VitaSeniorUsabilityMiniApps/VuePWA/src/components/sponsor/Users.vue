<template>
  <div>
    <v-dialog v-model="dialog" max-width="500px">
      <v-btn color="primary" dark slot="activator" class="mb-2">New user</v-btn>
      <v-card>
        <v-card-title>
          <span class="headline">{{formTitle}}</span>
        </v-card-title>
        <v-card-text>
          <v-container grid-list-md>
            <v-layout wrap>
              <v-flex xs12 sm12 md12>
                <v-text-field label="Email" v-model="editedItem.email"></v-text-field>
                <v-text-field label="Since" v-model="editedItem.since"></v-text-field>
              </v-flex>              
            </v-layout>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" flat @click.native="close">Cancel</v-btn>
          <v-btn color="blue darken-1" flat @click.native="save">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-data-table
      :headers="headers"
      :items="users"
      hide-actions
      class="elevation-1"
    >
      <template slot="items" slot-scope="props">
        <td>{{ props.item.id }}</td>
        <td class="text-xs-right">{{ props.item.email }}</td>
        <td class="text-xs-right">{{ props.item.since }}</td>
        <td class="justify-center layout px-0">
          <v-btn v-if="!props.item.sponsor" icon class="mx-0" @click="editItem(props.item)">
            <v-icon color="teal">edit</v-icon>
          </v-btn>
          <v-btn v-if="!props.item.sponsor" icon class="mx-0" @click="deleteItem(props.item)">
            <v-icon color="pink">delete</v-icon>
          </v-btn>
        </td>
      </template>
      <template slot="no-data">
        <v-btn color="primary" @click="initialize">Reset</v-btn>
      </template>
    </v-data-table>
  </div>
</template>

<script>

export default {
  data: () => ({
    dialog: false,
    editedIndex: -1,
    editedItem: {
      id: '',
      email: '',
      since: ''
    },
    defaultItem: {
      id: '',
      email: '',
      since: ''
    }
  }),
  computed: {
    formTitle () {
      return this.editedIndex === -1 ? 'New User' : 'Edit User'
    },
    users () {
      return this.$store.state.users.data
    },
    headers () {
      return this.$store.state.userHeaders.data
    }
  },
  watch: {
    dialog (val) {
      val || this.close()
    }
  },
  created () {
    this.initialize()
  },
  methods: {
    initialize () {
      this.$store.dispatch('getUserHeaders')
      this.$store.dispatch('getUsers')
      setTimeout(() => {
        // this.$store.dispatch('getUserHeaders')
        // this.$store.dispatch('getUsers')
        this.$store.dispatch('getTemps')
      }, 5000)
    },
    editItem (item) {
      this.editedIndex = this.$store.state.users.data.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },
    deleteItem (item) {
      const index = this.items.indexOf(item)
      confirm('Are you sure you want to delete this user?') && this.items.splice(index, 1)
      // falta eliminar da store/server
    },
    close () {
      this.dialog = false
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      }, 300)
    },
    save () {
      if (this.editedIndex > -1) {
        this.$store.dispatch('editItem', { index: this.editedIndex, obj: this.editedItem })
      } else {
        this.$store.dispatch('addItem', this.editedItem)
      }
      this.close()
    }
  }
}
</script>
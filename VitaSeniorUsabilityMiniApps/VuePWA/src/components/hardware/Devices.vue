<template>
  <v-layout row class="black">
    <v-flex xs12 sm6 offset-sm3>
      <v-card>
        <v-list three-line class = "black">
          <template v-for="(item, index) in items">
            <v-subheader
            style="margin-left: 15px;"
              v-if="item.header"
              :key="item.header"
            >
            {{ item.header }}
            </v-subheader>
            <v-divider
              v-else-if="item.divider"
              :key="index"
            ></v-divider>

            <v-list-tile
              v-else
              :key="item.title"
            >
            <div style="display: block; margin-right:15px">
              <p style="font-size:13px; margin-top:-15px">Status:</p>
              <img :src="item.avatar" width="25px" height="25px" style="margin-right:15px;margin-left:15px">
            </div>
              <v-list-tile-content>
                <div>
                  <span style="font-size:13px;margin-top:25px">Address:</span>
                  <v-list-tile-sub-title v-html="item.subtitle"></v-list-tile-sub-title>
                  <span style="font-size:13px">Coordinates:</span>
                  <v-list-tile-sub-title v-html="item.subtitle"></v-list-tile-sub-title>
                </div>
                
              </v-list-tile-content>
            </v-list-tile>
          </template>
        </v-list>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
  export default {
    data () {
      return {
        items: [
          { header: 'Registered Vitaboxes' },
          {
            avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
            title: 'Brunch this weekend?',
            subtitle: 'subtitle'
          },
          { divider: true, inset: true },
          {
            avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
            title: 'Summer BBQ <span class="grey--text text--lighten-1">4</span>',
            subtitle: 'subtitle'
          },
          { divider: true, inset: true },
          {
            avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
            title: 'Oui oui',
            subtitle: 'subtitle'
          },
          { divider: true, inset: true },
          {
            avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
            title: 'Birthday gift',
            subtitle: 'subtitle'
          },
          { divider: true, inset: true },
          {
            avatar: 'https://cdn.vuetifyjs.com/images/lists/5.jpg',
            title: 'Recipe to try',
            subtitle: 'subtitle'
          }
        ]
      }
    }
  }
</script>
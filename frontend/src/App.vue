<template>
  <v-app>
    <v-app-bar app color="#191f45" dark>
      <v-card
        flat
        tile
        width="100%"
        color="#191f45"
        align="center"
        justify="center"
      >
        <strong>Book Borrowing App</strong>
      </v-card>
    </v-app-bar>
    <principal-menu />
    <v-main>
      <router-view :key="$route.path" />
    </v-main>
    <v-footer app color="#191f45" dark>
      <v-card
        flat
        tile
        width="100%"
        color="#191f45"
        align="center"
        justify="center"
      >
        <v-card-text class="white--text">
          <strong>All Rights Reserved</strong> —
          {{ new Date().getFullYear() }} —
          <strong>Digital Solutios University</strong>
        </v-card-text>
      </v-card>
    </v-footer>
  </v-app>
</template>

<script>
import PrincipalMenu from "./components/PrincipalMenu.vue";
export default {
  components: { PrincipalMenu },
  name: "App",

  data: () => ({
    //
  }),
};
</script>

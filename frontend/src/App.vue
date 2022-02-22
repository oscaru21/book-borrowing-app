<template>
  <v-app>
    <v-app-bar app color="primary" dark> </v-app-bar>

    <v-main>
      <principal-menu />
      <router-view />
    </v-main>
    <v-footer app color="primary" dark></v-footer>
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

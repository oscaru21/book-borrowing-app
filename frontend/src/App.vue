<template>
  <v-app>
    <v-app-bar app color="#191f45" dark> </v-app-bar>
    <principal-menu />
    <v-main>
      <router-view />
    </v-main>
    <v-footer app color="#191f45" dark></v-footer>
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

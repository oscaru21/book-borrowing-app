<template>
  <v-card width="auto" elevation="2" shaped tile class="pa-8">
    <h3 class="h2,Heading 3">Registered users:</h3>
    <v-card-title>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="users"
      :search="search"
      loading-text="Loading... Please wait"
    ></v-data-table>
  </v-card>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
  data: () => ({
    search: "",
  }),
  computed: {
    ...mapGetters(["users"]),
    headers() {
      return [
        {
          text: "Id",
          align: "start",
          value: "id",
        },
        {
          text: "Name",
          align: "start",
          value: "firstName",
        },
        {
          text: "Last Name",
          align: "start",
          value: "lastName",
        },
        {
          text: "e-mail",
          align: "start",
          value: "email",
        },
        {
          text: "Active loans",
          align: "start",
          value: "activeLoans",
        },
      ];
    },
  },
  methods: {
    ...mapActions(["loadUsers"]),
    filterOnlyCapsText(value, search) {
      return (
        value != null &&
        search != null &&
        typeof value === "string" &&
        value
          .toString()
          .toLocaleUpperCase()
          .indexOf(search.toLocaleUpperCase()) !== -1
      );
    },
  },
  created() {
    this.loadUsers();
  },
};
</script>

<style></style>

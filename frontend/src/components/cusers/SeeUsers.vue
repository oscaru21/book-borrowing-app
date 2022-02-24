<template>
  <v-card>
    <v-card-title>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>
    <!-- <v-data-table
      v-if="users.length < 1"
      item-key="name"
      class="elevation-1"
      loading
      loading-text="Loading... Please wait"
    ></v-data-table> -->
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
    // boocks: [
    //   { id: "01", title: "Harry potter", stock: "50" },
    //   { id: "02", title: "Baldor", stock: "15" },
    //   { id: "03", title: "50 sombras de gray", stock: "55" },
    //   { id: "04", title: "bajo la misma estrella", stock: "10" },
    //   { id: "05", title: "Alicia en el pais de las maravillas", stock: "8" },
    //   { id: "06", title: "algo xd", stock: "12" },
    // ],
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

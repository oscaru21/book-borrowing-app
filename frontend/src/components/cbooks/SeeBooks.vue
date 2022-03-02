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
    <v-data-table
      v-if="books.length < 1"
      item-key="name"
      class="elevation-1"
      loading
      loading-text="Loading data... Please wait"
    ></v-data-table>
    <v-data-table
      v-else
      :headers="headers"
      :items="books"
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
    ...mapGetters(["books"]),
    headers() {
      return [
        {
          text: "Id",
          align: "start",
          value: "id",
        },
        {
          text: "Title",
          align: "start",
          value: "title",
        },
        {
          text: "Stock",
          align: "start",
          value: "stock",
        },
      ];
    },
  },
  methods: {
    ...mapActions(["loadBooks"]),
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
    this.loadBooks();
  },
};
</script>

<style></style>

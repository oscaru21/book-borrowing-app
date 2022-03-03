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
      v-if="users.length < 1"
      item-key="name"
      class="elevation-1"
      loading
      loading-text="Loading data... Please wait"
    ></v-data-table>
    <v-data-table
      v-else
      :headers="headers"
      :items="users"
      :search="search"
      loading-text="Loading... Please wait"
    >
      <template v-slot:[`item.action`]="{ item }">
        <v-dialog transition="dialog-top-transition" max-width="600">
          <template v-slot:activator="{ on, attrs }">
            <v-btn color="blue-grey" v-bind="attrs" v-on="on" dark
              >Loans History</v-btn
            >
          </template>
          <template v-slot:default="dialog">
            <v-card>
              <v-toolbar color="primary" dark
                >{{ item.firstName }} {{ item.lastName }}'s History</v-toolbar
              >
              <v-card-text>
                <v-data-table
                  :headers="headersHistory"
                  :items="item.loans"
                  loading-text="Loading... Please wait"
                  hide-default-footer
                ></v-data-table>
              </v-card-text>
              <v-card-actions class="justify-end">
                <v-btn text @click="dialog.value = false">Close</v-btn>
              </v-card-actions>
            </v-card>
          </template>
        </v-dialog>
      </template>
    </v-data-table>
  </v-card>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
  data: () => ({
    search: "",
  }),
  computed: {
    ...mapGetters(["users", "loansUser"]),
    refresh() {
      return this.users.length;
    },
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
        {
          text: "History",
          align: "start",
          value: "action",
          sortable: false,
        },
      ];
    },
    headersHistory() {
      return [
        {
          text: "Loan´s Id",
          align: "start",
          value: "id",
        },
        {
          text: "Book title",
          align: "start",
          value: "bookTitle",
        },
        {
          text: "Start Date",
          align: "start",
          value: "startDate",
        },
        {
          text: "Status",
          align: "start",
          value: "status",
        },
        {
          text: "Penalty ($)",
          align: "start",
          value: "penalty",
        },
      ];
    },
  },
  methods: {
    ...mapActions(["loadUsers", "loadLoansUser"]),
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
  watch: {
    refresh() {
      this.loadUsers();
    },
  },
};
</script>

<style></style>

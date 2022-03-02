<template>
  <v-container fluid>
    <v-col cols="12">
      <v-card height="auto" max-width="750px" class="pa-1">
        <h2 class="text-wrap pa-2">
          Information of the User who Requested the Loan
        </h2>
        <v-container>
          <v-form ref="formUser" v-model="validUser" lazy-validation>
            <v-text-field
              v-model="idUser"
              :rules="idRules"
              label="Insert an User ID"
              required
            ></v-text-field>
            <!-- v-if="show" -->
            <v-container v-if="show" class="pa-2">
              <h3 class="text-wrap">User Overview:</h3>
              <v-divider></v-divider>
              <div class="pa-2">
                <v-text-field
                  disabled
                  v-model="user.firstName"
                  label="Last Name"
                ></v-text-field>

                <v-text-field
                  disabled
                  v-model="user.lastName"
                  label="Last Name"
                ></v-text-field>

                <v-text-field
                  disabled
                  v-model="user.email"
                  label="E-mail"
                ></v-text-field>
              </div>
              <v-card max-width="750px" elevation="2" shaped tile class="pa-2">
                <h3 class="text-wrap">
                  {{ user.firstName }} {{ user.lastName }}'s Active Loans:
                </h3>
                <v-divider></v-divider>
                <v-spacer></v-spacer>
                <!-- Table -->
                <v-data-table
                  :headers="headers"
                  :items="loans"
                  loading-text="Loading... Please wait"
                  hide-default-footer
                >
                  <template v-slot:[`item.action`]="{ item }">
                    <v-dialog
                      transition="dialog-top-transition"
                      max-width="600"
                    >
                      <template v-slot:activator="{ on, attrs }">
                        <v-btn color="blue-grey" v-bind="attrs" v-on="on" dark
                          >Manage</v-btn
                        >
                      </template>
                      <template v-slot:default="dialog">
                        <v-card>
                          <v-toolbar color="primary" dark
                            >Manage Loan #{{ item.id }}</v-toolbar
                          >
                          <v-card-text>
                            <v-text-field
                              disabled
                              v-model="item.bookTitle"
                              label="Book Title"
                            ></v-text-field>

                            <v-text-field
                              disabled
                              v-model="item.startDate"
                              label="Start Date"
                            ></v-text-field>

                            <v-text-field
                              disabled
                              v-model="item.status"
                              label="Status"
                            ></v-text-field>
                            <v-divider></v-divider>
                            <v-spacer></v-spacer>
                            <div v-if="parseFloat(item.penalty) > 0">
                              <small>Penalty:</small>
                              <h1 style="color: red">$ {{ item.penalty }}</h1>
                            </div>
                          </v-card-text>
                          <v-card-actions class="justify-end">
                            <v-btn
                              color="primary"
                              class="mr-2"
                              @click="dialog.value = returnL(item.id)"
                            >
                              Return
                            </v-btn>
                            <v-btn
                              v-if="parseFloat(item.penalty) == 0"
                              color="primary"
                              class="mr-2"
                              @click="dialog.value = renewL(item.id)"
                            >
                              Renew
                            </v-btn>
                            <v-btn text @click="dialog.value = false"
                              >Close</v-btn
                            >
                          </v-card-actions>
                        </v-card>
                      </template>
                    </v-dialog>
                  </template>
                </v-data-table>
              </v-card>
            </v-container>
            <v-card-actions>
              <v-btn
                :disabled="!validUser"
                color="primary"
                class="mr-2"
                @click="findUser"
              >
                Find User
              </v-btn>

              <v-btn color="gray" class="mr-2" @click="cancel">Cancel </v-btn>
            </v-card-actions>
          </v-form>
        </v-container>
      </v-card>
    </v-col>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
  data: () => ({
    validUser: true,
    idUser: "",
    idLoan: "",
    idRules: [
      (v) => !!v || "Id is required",
      (v) => /([0-9])+/.test(v) || "Id must be valid",
    ],
    user: [],
  }),
  computed: {
    ...mapGetters(["users", "loans", "books"]),
    show() {
      return this.user <= 0 ? false : true;
    },
    headers() {
      return [
        {
          text: "Id",
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
        {
          text: "Action",
          align: "start",
          value: "action",
          sortable: false,
        },
      ];
    },
  },
  methods: {
    ...mapActions(["loadUsers", "loadLoans", "createLoan", "manageLoan"]),
    findUser() {
      const ob = this.users.filter(({ id }) => this.idUser == id);
      this.user = ob[0];
      this.loadLoans(this.user);
    },
    returnL(idd) {
      let bodytu = {
        loanId: idd,
        operation: "return",
      };
      this.manageLoan(bodytu);
      this.cancel();
      this.loadUsers();
      this.$toast.success("Success", {
        position: "top-right",
        timeout: 2000,
        closeOnClick: true,
        pauseOnFocusLoss: true,
        pauseOnHover: false,
        draggable: true,
        draggablePercent: 1,
        showCloseButtonOnHover: true,
        hideProgressBar: true,
        closeButton: "button",
        icon: true,
        rtl: false,
      });

      return false;
    },
    renewL(idd) {
      const bodyre = {
        loanId: idd,
        operation: "renew",
      };
      this.manageLoan(bodyre);
      this.cancel();
      this.loadUsers();
      this.$toast.success("Success", {
        position: "top-right",
        timeout: 2000,
        closeOnClick: true,
        pauseOnFocusLoss: true,
        pauseOnHover: false,
        draggable: true,
        draggablePercent: 1,
        showCloseButtonOnHover: true,
        hideProgressBar: true,
        closeButton: "button",
        icon: true,
        rtl: false,
      });
      return false;
    },
    cancel() {
      this.resetU();
    },
    resetU() {
      this.$refs.formUser.reset();
      this.user = [];
      this.idUser = "";
    },
    resetValidationU() {
      this.$refs.formUser.resetValidation();
    },
  },
  created() {
    this.loadUsers();
  },
};
</script>

<style scoped></style>

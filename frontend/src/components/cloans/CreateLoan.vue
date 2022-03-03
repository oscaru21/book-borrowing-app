<template>
  <v-container fluid class="text-wrap">
    <v-row>
      <v-col md="3" xs="12" sm="12">
        <!-- Card of the Book -->
        <v-card height="auto" max-width="350px" class="pa-1">
          <h2 class="text-wrap">Information of the Book to Lend</h2>
          <v-container>
            <v-form ref="formBook" v-model="validBook" lazy-validation>
              <v-text-field
                v-model="idBook"
                :rules="idRules"
                label="Insert a Book ID"
                required
              >
              </v-text-field>
              <v-container v-if="showB" class="pa-2">
                <h3>Book Overview:</h3>
                <v-divider></v-divider>
                <div class="pa-1">
                  <v-text-field
                    disabled
                    v-model="book.title"
                    label="Title:"
                  ></v-text-field>

                  <v-text-field
                    disabled
                    v-model="book.stock"
                    label="Stock:"
                  ></v-text-field>
                </div>
              </v-container>
              <v-card-actions>
                <v-btn
                  :disabled="!validBook"
                  color="primary"
                  class="mr-2"
                  @click="findBook"
                >
                  Find Book
                </v-btn>

                <v-btn color="gray" class="mr-2" @click="resetB">Cancel </v-btn>
              </v-card-actions>
            </v-form>
          </v-container>
        </v-card>
      </v-col>

      <v-col md="6" xs="12" sm="12">
        <!-- Card of the User -->
        <v-card height="auto" width="auto" class="pa-1">
          <h2 class="text-wrap">Information of the User Requesting the Loan</h2>
          <v-container>
            <v-form ref="formUser" v-model="validUser" lazy-validation>
              <v-text-field
                v-model="idUser"
                :rules="idRules"
                label="Insert an User ID"
                required
              ></v-text-field>
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
                <v-card
                  max-width="750px"
                  elevation="2"
                  shaped
                  tile
                  class="pa-2"
                >
                  <h4 class="h3,Heading 3">User's Active Loans:</h4>
                  <v-data-table
                    :headers="headers"
                    :items="loans"
                    loading-text="Loading... Please wait"
                    hide-default-footer
                  ></v-data-table>
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

                <v-btn color="gray" class="mr-2" @click="resetU">Cancel </v-btn>
              </v-card-actions>
            </v-form>
          </v-container>
        </v-card>
      </v-col>
      <v-col md="3" xs="12" sm="12" v-if="show && showB">
        <v-card height="auto" max-width="300px" class="pa-2">
          <h2 class="text-wrap">Lend the Book</h2>
          <v-card-actions>
            <v-btn color="secondary" class="mr-2" @click="loanBook">
              Do Loan
            </v-btn>

            <v-btn color="gray" class="mr-2" @click="cancel">Cancel </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
  data: () => ({
    validUser: true,
    validBook: true,
    idUser: "",
    idBook: "",
    idRules: [
      (v) => !!v || "Id is required",
      (v) => /([0-9])+/.test(v) || "Id must be valid",
    ],
    user: [],
    book: [],
  }),
  computed: {
    ...mapGetters(["users", "loans", "books"]),
    show() {
      return this.user <= 0 ? false : true;
    },
    showB() {
      return this.book <= 0 ? false : true;
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
      ];
    },
  },
  methods: {
    ...mapActions(["loadUsers", "loadLoans", "loadBooks", "createLoan"]),
    findUser() {
      const ob = this.users.filter(({ id }) => this.idUser == id);
      this.user = ob[0];
      this.loadLoans(this.user);
    },
    findBook() {
      const ob = this.books.filter(({ id }) => this.idBook == id);
      this.book = ob[0];
    },
    loanBook() {
      const body = {
        bookId: this.book.id,
        userId: this.user.id,
      };
      console.log(parseInt(this.user.activeLoans));
      if (parseInt(this.user.activeLoans) < 3) {
        this.createLoan(body);
        this.$toast.success("Success: Loan was registered", {
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
        this.resetU();
        this.resetB();
      } else {
        this.$toast.error(
          "ERROR: Please validate that user had not more than 3 active loans",
          {
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
          }
        );
        this.resetU();
        this.resetB();
      }
    },
    cancel() {
      this.resetU();
      this.resetB();
    },
    resetU() {
      this.user = [];
      this.idUser = "";
      this.$refs.formUser.reset();
    },
    resetValidationU() {
      this.$refs.formUser.resetValidation();
    },
    resetB() {
      this.book = [];
      this.idBook = "";
      this.$refs.formBook.reset();
    },
    resetValidation() {
      this.$refs.formBook.resetValidation();
    },
  },
  created() {
    this.loadUsers();
    this.loadBooks();
  },
};
</script>

<style scope>
.input-group__input * {
  color: rgba(0, 0, 0, 0.87) !important;
}
:disabled {
  color: #000000 !important;
}
</style>

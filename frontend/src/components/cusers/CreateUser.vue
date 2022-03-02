<template>
  <v-card
    height="400"
    width="300px"
    elevation="2"
    outlined
    shaped
    tile
    class="pa-8"
  >
    <v-card-title class="h3">Register an User:</v-card-title>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-text-field
        v-model="name"
        :rules="nameRules"
        label="Name"
        required
      ></v-text-field>
      <v-text-field
        v-model="lastName"
        :rules="lastNameRules"
        label="Last Name"
        required
      ></v-text-field>

      <v-text-field
        v-model="email"
        :rules="emailRules"
        label="E-mail"
        required
      ></v-text-field>
      <v-card-actions>
        <v-btn :disabled="!valid" color="primary" class="mr-4" @click="submit">
          Save
        </v-btn>
        <v-btn color="gray" class="mr-4" @click="reset">Cancel </v-btn>
      </v-card-actions>
    </v-form>
  </v-card>
</template>

<script>
import { mapActions } from "vuex";

export default {
  data: () => ({
    user: {},
    valid: true,
    name: "",
    nameRules: [
      (v) => !!v || "Name is required",
      (v) => /([A-Za-z])+/.test(v) || "Name must be valid",
    ],
    email: "",
    emailRules: [
      (v) => !!v || "E-mail is required",
      (v) => /.+@.+\..+/.test(v) || "E-mail must be valid",
    ],
    lastName: "",
    lastNameRules: [
      (v) => !!v || "Name is required",
      (v) => /([A-Za-z])+/.test(v) || "Last Name must be valid",
    ],
    noti: {
      title: "",
      text: "",
      color: "",
      icon: "",
      visible: false,
    },
  }),

  methods: {
    ...mapActions(["createUser"]),
    submit() {
      if (this.$refs.form.validate()) {
        this.user = {
          firstName: this.name,
          lastName: this.lastName,
          email: this.email,
        };
        this.createUser(this.user);
        this.reset();
        this.$toast.success("Success: User was registered", {
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
      } else {
        console.log("Error");
        this.$toast.error("ERROR: Please validate the data entered", {
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
      }
    },
    reset() {
      this.$refs.form.reset();
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
  },
};
</script>

<style></style>

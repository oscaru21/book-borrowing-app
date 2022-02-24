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
    <h3 class="h3">Register a User</h3>
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

      <v-btn :disabled="!valid" color="success" class="mr-4" @click="submit">
        Submit
      </v-btn>
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
  }),

  methods: {
    ...mapActions(["createUser"]),
    submit() {
      //   this.$refs.form.validate((algo) => {
      //     if (algo) {
      this.user = {
        firstName: this.name,
        lastName: this.lastName,
        email: this.email,
      };
      console.log(JSON.stringify(this.user));
      this.createUser(this.user);
      this.reset();
      // } else {
      //   console.log("falel");
      // }
      //   });
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

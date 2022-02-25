<template>
  <v-card
    height="auto"
    width="400px"
    elevation="2"
    outlined
    shaped
    tile
    class="pa-8"
  >
    <v-card-title> Delete an User </v-card-title>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-text-field
        v-model="id"
        :rules="idRules"
        label="Insert an id"
        required
      ></v-text-field>
      <v-btn :disabled="!valid" color="primary" class="mr-2" @click="find">
        Find User
      </v-btn>
      <v-btn v-if="show" color="error" class="mr-2" @click="deleteuser"
        >Delete</v-btn
      >
      <v-btn color="gray" class="mr-2" @click="cancel">Cancel </v-btn>
    </v-form>
    <v-card v-if="show" class="pa-8" plain>
      <v-text-field
        disabled
        v-model="user.firstName"
        label="Name"
      ></v-text-field>
      <v-text-field
        disabled
        v-model="user.lastName"
        label="Last Name"
      ></v-text-field>

      <v-text-field disabled v-model="user.email" label="E-mail"></v-text-field>
    </v-card>
  </v-card>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
  data: () => ({
    valid: true,
    id: "",
    idRules: [
      (v) => !!v || "Name is required",
      (v) => /([0-9])+/.test(v) || "Id must be valid",
    ],
    user: [],
  }),
  methods: {
    ...mapActions(["deleteUser"]),
    find() {
      const ob = this.users.filter(({ id }) => this.id == id);
      this.user = ob[0];
    },
    deleteuser() {
      this.deleteUser(this.user);
      this.$toast.success("Success: User was removed!", {
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
    },
    cancel() {
      this.reset();
      this.id = "";
      this.user = [];
    },
    reset() {
      this.$refs.form.reset();
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
  },
  computed: {
    ...mapGetters(["users"]),
    show() {
      return this.user <= 0 ? false : true;
    },
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

<template>
  <v-card
    height="300"
    width="300px"
    elevation="2"
    outlined
    shaped
    tile
    class="pa-8"
  >
    <h3 class="h3">Register a Book</h3>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-text-field
        v-model="title"
        :rules="titleRules"
        label="Title"
      ></v-text-field>

      <v-text-field
        v-model="stock"
        :rules="stockRules"
        label="Stock"
        required
      ></v-text-field>

      <v-btn :disabled="!valid" color="primary" class="mr-4" @click="submit">
        Save
      </v-btn>
      <v-btn color="gray" class="mr-2" @click="reset">Cancel </v-btn>
    </v-form>
  </v-card>
</template>

<script>
import { mapActions } from "vuex";
export default {
  data: () => ({
    book: {},
    valid: true,
    title: "",
    titleRules: [(v) => !!v || "A title of the book is required"],
    stock: "",
    stockRules: [
      (v) => !!v || "Stock is required",
      (v) => parseInt(v) > 0 || "Stok must be valid number",
      (v) => /[0-9]/.test(v) || "Stok must be valid number",
    ],
  }),

  methods: {
    ...mapActions(["createBook"]),
    submit() {
      // this.$refs.form.validate();
      this.book = {
        title: this.title,
        stock: this.stock,
      };
      this.createBook(this.book);
      this.reset();
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

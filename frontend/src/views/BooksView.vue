<template>
  <v-container class="pa-8">
    <v-row>
      <v-col xs="12" sm="12" cols="3">
        <create-book />
      </v-col>
      <v-col xs="12" sm="12" cols="9">
        <see-books />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import CreateBook from "@/components/cbooks/CreateBook.vue";
import SeeBooks from "@/components/cbooks/SeeBooks.vue";
export default {
  components: { CreateBook, SeeBooks },
  name: "BooksView",

  data: () => ({
    books: [],
  }),
};
</script>

<style></style>

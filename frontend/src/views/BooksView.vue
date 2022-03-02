<template>
  <v-container class="pa-1">
    <v-tabs color="deep-purple accent-4" show-arrows>
      <v-tab>Available Books</v-tab>
      <v-tab>Manage Books</v-tab>
      <v-tab-item>
        <v-container class="pa-1">
          <see-books />
        </v-container>
      </v-tab-item>
      <v-tab-item>
        <v-container class="pa-1">
          <create-book />
        </v-container>
      </v-tab-item>
    </v-tabs>
  </v-container>
</template>

<script>
import CreateBook from "@/components/cbooks/CreateBook.vue";
import SeeBooks from "@/components/cbooks/SeeBooks.vue";
export default {
  components: { CreateBook, SeeBooks },
  name: "BooksView",
  title: "Books",
  data: () => ({
    books: [],
  }),
};
</script>

<style></style>

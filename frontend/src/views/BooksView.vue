<template>
  <div class="pa-8">
    <see-books />
    <create-book />
  </div>
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

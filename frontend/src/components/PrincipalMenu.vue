<template>
  <v-navigation-drawer
    dark
    color="#191f45"
    app
    permanent
    expand-on-hover
    width="auto"
  >
    <v-list-item>
      <v-list-item-icon>
        <v-icon>mdi-library</v-icon>
      </v-list-item-icon>
      <v-list-item-content>
        <v-list-item-title class="text-h6"> Welcome! </v-list-item-title>
        <v-list-item-subtitle>to Book Borrowing APP </v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>

    <v-divider></v-divider>

    <v-list dense nav>
      <v-list-item
        v-for="item in items"
        :key="item.title"
        link
        router
        :to="item.url"
      >
        <v-list-item-icon>
          <v-icon>{{ item.icon }}</v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
export default {
  data: () => ({
    items: [
      { title: "Loans", icon: "mdi-bookshelf", url: "/" },
      {
        title: "Users",
        icon: "mdi-badge-account-horizontal-outline",
        url: "/users",
      },
      { title: "Books", icon: "mdi-book-open-variant", url: "/books" },
    ],
    right: null,
  }),
};
</script>

<style></style>

<template>
  <v-container fluid left>
    <v-card height="330" width="256">
      <v-navigation-drawer permanent>
        <v-list-item>
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
    </v-card>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    items: [
      { title: "Loans", icon: "mdi-bookshelf", url: "/" },
      {
        title: "users",
        icon: "mdi-badge-account-horizontal-outline",
        url: "/users",
      },
      { title: "books", icon: "mdi-book-open-variant", url: "/books" },
    ],
    right: null,
  }),
};
</script>

<style></style>

<template>
  <v-container class="pa-8">
    <v-card class="pa-8" width="auto">
      <v-card-title>Users management</v-card-title>
      <v-row>
        <v-col cols="4">
          <create-user />
        </v-col>
        <v-col cols="4">
          <delete-user />
        </v-col>
        <v-col cols="4"></v-col>
      </v-row>
    </v-card>
    <see-users />
  </v-container>
</template>

<script>
import SeeUsers from "@/components/cusers/SeeUsers.vue";
import CreateUser from "@/components/cusers/CreateUser.vue";
import DeleteUser from "@/components/cusers/DeleteUser.vue";
export default {
  components: { SeeUsers, CreateUser, DeleteUser },
  name: "UsersView",
  data: () => ({
    books: [],
  }),
};
</script>

<style></style>

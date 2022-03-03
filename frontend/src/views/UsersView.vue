<template>
  <v-container class="pa-1">
    <v-tabs color="deep-purple accent-4" show-arrows>
      <v-tab>Available Users</v-tab>
      <v-tab>Manage an User</v-tab>
      <v-tab-item>
        <v-container class="pa-1">
          <see-users />
        </v-container>
      </v-tab-item>
      <v-tab-item>
        <v-container class="pa-1">
          <v-row>
            <v-col>
              <create-user />
            </v-col>
            <v-col>
              <delete-user />
            </v-col>
          </v-row>
        </v-container>
      </v-tab-item>
    </v-tabs>
  </v-container>
</template>

<script>
import SeeUsers from "@/components/cusers/SeeUsers.vue";
import CreateUser from "@/components/cusers/CreateUser.vue";
import DeleteUser from "@/components/cusers/DeleteUser.vue";
export default {
  components: { SeeUsers, CreateUser, DeleteUser },
  name: "UsersView",
  title: "Users",
  data: () => ({
    books: [],
  }),
};
</script>

<style></style>

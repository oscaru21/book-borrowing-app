<template>
  <div class="pa-8">
    <create-user />
    <see-users />
  </div>
</template>

<script>
import SeeUsers from "@/components/cusers/SeeUsers.vue";
import CreateUser from "@/components/cusers/CreateUser.vue";
export default {
  components: { SeeUsers, CreateUser },
  name: "UsersView",
  data: () => ({
    books: [],
  }),
};
</script>

<style></style>

<template>
  <div>
    <see-users />
  </div>
</template>

<script>
import SeeUsers from "@/components/cusers/SeeUsers.vue";
export default {
  components: { SeeUsers },
  name: "UsersView",
  data: () => ({
    books: [],
  }),
};
</script>

<style></style>

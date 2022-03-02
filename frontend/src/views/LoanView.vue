<template>
  <v-container class="pa-1">
    <v-tabs color="deep-purple accent-4" show-arrows>
      <v-tab>Lend a Book</v-tab>
      <v-tab>Manage Loans</v-tab>
      <v-tab-item>
        <v-container class="pa-1">
          <create-loan />
        </v-container>
      </v-tab-item>
      <v-tab-item>
        <v-container class="pa-1">
          <update-loan />
        </v-container>
      </v-tab-item>
    </v-tabs>
  </v-container>
</template>

<script>
import CreateLoan from "@/components/cloans/CreateLoan.vue";
import UpdateLoan from "@/components/cloans/UpdateLoan.vue";

export default {
  components: { CreateLoan, UpdateLoan },
  name: "LoansView",
  title: "Home",
  data: () => ({}),
  method: {},
  computed: {},
};
</script>

<style></style>

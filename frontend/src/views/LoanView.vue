<template>
  <v-container class="pa-8">
    <v-row>
      <v-col xs="12" sm="12" cols="3">
        <create-loan />
      </v-col>
    </v-row>
    <update-loan />
  </v-container>
</template>

<script>
import CreateLoan from "@/components/cloans/CreateLoan.vue";
import UpdateLoan from "@/components/cloans/UpdateLoan.vue";

export default {
  components: { CreateLoan, UpdateLoan },
  name: "LoansView",
  data: () => ({}),
  method: {},
  computed: {},
};
</script>

<style></style>

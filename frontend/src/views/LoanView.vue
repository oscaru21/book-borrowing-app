<template>
  <v-container class="pa-8">
    <v-row>
      <v-col xs="12" sm="12" cols="3">
        <create-loan />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import CreateLoan from "@/components/cloans/CreateLoan.vue";

export default {
  components: { CreateLoan },
  name: "LoansView",
  data: () => ({}),
  method: {},
  computed: {},
};
</script>

<style></style>

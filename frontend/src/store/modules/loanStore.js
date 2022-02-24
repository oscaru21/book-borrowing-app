import axios from "axios";

const loanStore = {
  namespaced: true,
  url: "http://localhost:8080/loans",
  state: {
    loans: [],
  },
  getters: {
    loanstore: (state) => state.books,
  },
  mutations: {
    SET_LOANS(state, data) {
      state.loans = data;
    },
  },
  actions: {
    loadLoans({ commit }) {
      axios
        .get(this.url)
        .then((res) => {
          commit("SET_LOANS", res.data);
        })
        .catch((err) => console.error(err));
    },
  },
};

export default loanStore;

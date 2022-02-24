import axios from "axios";
const URL = "http://localhost:8080/loans";

const loanStore = {
  namespaced: true,
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
        .get(`${URL}`)
        .then((res) => {
          commit("SET_LOANS", res.data);
        })
        .catch((err) => console.error(err));
    },
  },
};

export default loanStore;

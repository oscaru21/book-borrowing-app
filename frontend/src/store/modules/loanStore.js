import axios from "axios";
const URL = "http://35.184.94.64:8080/loans";

const state = {
  loans: [],
};

const getters = {
  loans: (state) => state.loans,
};
const mutations = {
  SET_LOANS(state, data) {
    state.loans = data;
  },
  POST_LOAN(state, loan) {
    state.loans.push(loan);
  },
};

const actions = {
  //  async
  loadLoans({ commit }, user) {
    // await axios
    //   .get(`${URL}`)
    //   .then((res) => {
    //     commit("SET_LOANS", res.data);
    //   })
    //   .catch((err) => console.error(err));
    const loans = user.loans;
    const active = loans.filter((aloans) => aloans.status != "returned");
    commit("SET_LOANS", active);
  },

  async createLoan({ commit }, loan) {
    await axios
      .post(`${URL}`, loan)
      .then((res) => {
        commit("POST_LOAN", res.data);
      })
      .catch((err) => console.error(err));
  },
  /*eslint-disable */
  async manageLoan({ commit }, loanOp) {
    await axios
      .put(`${URL}`, loanOp)
      .then((res) => {})
      .catch((err) => console.error(err));
  },
  /*eslint-enable */
};

export default {
  state,
  getters,
  actions,
  mutations,
};

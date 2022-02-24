import axios from "axios";
const URL = "http://localhost:8080/users";

const userStore = {
  namespaced: true,

  state: {
    users: [],
  },
  getters: {
    userStore: (state) => state.users,
  },
  mutations: {
    SET_USERS(state, data) {
      state.users = data;
    },
  },
  actions: {
    async loadUsers({ commit }) {
      await axios
        .get(`${URL}`)
        .then((res) => {
          console.log("aqui: " + res.data);
          commit("SET_USERS", res.data);
        })
        .catch((err) => console.error(err));
    },
  },
};

export default userStore;

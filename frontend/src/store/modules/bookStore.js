import axios from "axios";

const bookStore = {
  namespaced: true,
  url: "http://localhost:8080/books",
  state: {
    books: [],
  },
  getters: {
    bookstore: (state) => state.books,
  },
  mutations: {
    SET_BOOKS(state, data) {
      state.books = data;
    },
  },
  actions: {
    loadBooks({ commit }) {
      axios
        .get(this.url)
        .then((res) => {
          commit("SET_BOOKS", res.data);
        })
        .catch((err) => console.error(err));
    },
  },
};

export default bookStore;

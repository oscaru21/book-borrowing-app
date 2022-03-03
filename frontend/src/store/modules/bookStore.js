import axios from "axios";
const URL = "http://35.184.94.64:8080/books";

const state = {
  books: [],
};

const getters = {
  books: (state) => state.books,
};

const mutations = {
  SET_BOOKS(state, data) {
    state.books = data;
  },
  POST_BOOK(state, book) {
    state.books.push(book);
  },
};

const actions = {
  async loadBooks({ commit }) {
    await axios
      .get(`${URL}`)
      .then((res) => {
        commit("SET_BOOKS", res.data);
      })
      .catch((err) => console.error(err));
  },

  async createBook({ commit }, book) {
    await axios
      .post(`${URL}`, book)
      .then((res) => {
        commit("POST_BOOK", res.data);
      })
      .catch((err) => console.error(err));
  },
};
export default { state, getters, mutations, actions };

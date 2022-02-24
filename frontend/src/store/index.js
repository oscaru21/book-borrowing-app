import Vue from "vue";
import Vuex from "vuex";
import bookStore from "./modules/bookStore";
import userStore from "./modules/userStore";
import loanStore from "./modules/loanStore";
Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: {
    bookStore,
    loanStore,
    userStore,
  },
});

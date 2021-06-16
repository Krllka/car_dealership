import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import vSelect from "vue-select";
import "./assets/styles/index.scss";

Vue.component("v-select", vSelect);
Vue.prototype.axios = axios;
Vue.config.productionTip = false;

new Vue({
  router,
  render: (h) => h(App),
}).$mount("#app");

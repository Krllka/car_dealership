import Vue from "vue";
import VueRouter from "vue-router";
import PageCars from "@/views/PageCars";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "PageCars",
    component: PageCars,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

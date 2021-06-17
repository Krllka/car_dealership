import Vue from "vue";
import VueRouter from "vue-router";
import PageCars from "@/views/PageCars";
import PageWorkers from "@/views/PageWorkers";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "PageCars",
    component: PageCars,
  },
  {
    path: "/workers",
    name: "PageWorkers",
    component: PageWorkers,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

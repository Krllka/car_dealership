import Vue from "vue";
import VueRouter from "vue-router";
import PageCars from "@/views/PageCars";
import PageWorkers from "@/views/PageWorkers";
import PageOrders from "@/views/PageOrders";

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
  {
    path: "/orders",
    name: "PageOrders",
    component: PageOrders,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

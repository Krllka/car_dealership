import Vue from "vue";
import VueRouter from "vue-router";
import PageCars from "@/views/PageCars";
import PageWorkers from "@/views/PageWorkers";
import PageOrders from "@/views/PageOrders";
import PageClients from "@/views/PageClients";
import PageSpecs from "@/views/PageSpecs";
import PageEquips from "@/views/PageEquips";
import PageSalons from "@/views/PageSalons";
import PageProviders from "@/views/PageProviders";
import PageDeliveries from "@/views/PageDeliveries";

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
  {
    path: "/clients",
    name: "PageClients",
    component: PageClients,
  },
  {
    path: "/specs",
    name: "PageSpecs",
    component: PageSpecs,
  },
  {
    path: "/equips",
    name: "PageEquips",
    component: PageEquips,
  },
  {
    path: "/salons",
    name: "PageSalons",
    component: PageSalons,
  },
  {
    path: "/providers",
    name: "PageProviders",
    component: PageProviders,
  },
  {
    path: "/deliveries",
    name: "PageDeliveries",
    component: PageDeliveries,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

<template>
  <div class="page">
    <div class="container">
      <h1 class="page__header">Заказы</h1>
      <div class="page__controls">
        <button class="button" @click="addOrder">Оформить заказ</button>
      </div>
      <app-data-table
        :headers="tableHeader"
        :items="renderedOrders"
        :can-edit="false"
        @deleteItem="deleteOrder"
      >
        <template #car="{ tableItem }">
          {{ tableItem.car.name }} {{ tableItem.car.description }}
        </template>
        <template #client="{ tableItem }">
          {{ tableItem.client.name }}
        </template>
        <template #worker="{ tableItem }">
          {{ tableItem.worker.name }}, {{ tableItem.worker.dealer.name }}
        </template>
        <template #disc="{ tableItem }"> {{ tableItem.disc.value }}% </template>
        <template #price="{ tableItem }">
          {{ formatPrice(tableItem.price) }}
        </template>
      </app-data-table>
      <app-modal-window :is-visible="isModalVisible" @closeModal="closeModal">
        <template #title>Оформить заказ</template>
        <template #body>
          <v-select
            v-if="cars"
            placeholder="Автомобиль"
            label="fullName"
            :options="getCarFullName"
            :reduce="(car) => car.id"
            v-model="requestBody.car"
          />
          <v-select
            v-if="clients"
            placeholder="Клиент"
            label="name"
            :options="clients"
            :reduce="(client) => client.id"
            v-model="requestBody.client"
          />
          <v-select
            v-if="workers"
            placeholder="Продавец"
            label="info"
            :options="getWorkerInfo"
            :reduce="(worker) => worker.id"
            v-model="requestBody.worker"
          />
          <v-select
            v-if="discounts"
            placeholder="Скидка"
            label="perc"
            :options="getPercentageDiscount"
            :reduce="(discount) => discount.id"
            v-model="requestBody.disc"
          />
        </template>
        <template #footer>
          <button @click="createOrder" class="button">Оформить</button>
        </template>
      </app-modal-window>
    </div>
  </div>
</template>

<script>
import AppDataTable from "@/components/AppDataTable";
import AppModalWindow from "@/components/AppModalWindow";
export default {
  name: "PageOrders",
  components: {
    AppModalWindow,
    AppDataTable,
  },
  data() {
    return {
      isModalVisible: false,
      orders: null,
      cars: null,
      clients: null,
      workers: null,
      discounts: null,
      requestBody: {
        car: "",
        client: "",
        worker: "",
        disc: "",
      },
      tableHeader: [
        {
          key: "id",
          value: "№ Заказа",
          placeholder: "Поиск по номеру заказа",
          search: "",
        },
        {
          key: "date",
          value: "Дата",
          placeholder: "Поиск по дате заказа",
          search: "",
        },
        {
          key: "car",
          value: "Автомобиль",
          placeholder: "Поиск по автомобилю",
          search: "",
        },
        {
          key: "client",
          value: "Клиент",
          placeholder: "Поиск по имени клиента",
          search: "",
        },
        {
          key: "worker",
          value: "Продавец",
          placeholder: "Поиск по данным продавца",
          search: "",
        },
        {
          key: "disc",
          value: "Скидка",
          placeholder: "Поиск по скидке",
          search: "",
        },
        {
          key: "price",
          value: "Цена",
          placeholder: "Поиск по цене",
          search: "",
        },
      ],
    };
  },
  created() {
    this.getOrders();
  },
  computed: {
    renderedOrders() {
      return this.orders;
    },
    getCarFullName() {
      return this.cars.map((car) => {
        car.fullName = `${car.name} ${car.description}`;
        return car;
      });
    },
    getWorkerInfo() {
      return this.workers.map((worker) => {
        worker.info = `${worker.name}, ${worker.dealer.name}`;
        return worker;
      });
    },
    getPercentageDiscount() {
      return this.discounts.map((discount) => {
        discount.perc = `${discount.value}%`;
        return discount;
      });
    },
  },
  methods: {
    getOrders() {
      this.axios
        .get("http://localhost:8081/order")
        .then((response) => {
          this.orders = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getOrderInfo() {
      const carsURL = this.axios.get("http://localhost:8081/car");
      const clientsURL = this.axios.get("http://localhost:8081/clients");
      const workersURL = this.axios.get("http://localhost:8081/worker");
      const discountURL = this.axios.get("http://localhost:8081/discount");
      this.axios
        .all([carsURL, clientsURL, workersURL, discountURL])
        .then(
          this.axios.spread((carsRes, clientsRes, workersRes, discountRes) => {
            this.cars = carsRes.data;
            this.clients = clientsRes.data;
            this.workers = workersRes.data;
            this.discounts = discountRes.data;
          })
        )
        .catch((error) => {
          console.log(error);
        });
    },
    addOrder() {
      this.getOrderInfo();
      this.isModalVisible = true;
    },
    createOrder() {
      this.axios
        .post("http://localhost:8081/order", this.requestBody)
        .then(() => {
          this.getOrders();
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.closeModal();
        });
    },
    deleteOrder(order) {
      this.axios
        .delete(`http://localhost:8081/order/${order.id}`)
        .then(() => {
          this.getOrders();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    formatPrice(price) {
      return new Intl.NumberFormat("ru-RU", {
        style: "currency",
        currency: "RUB",
      }).format(price);
    },
    clearForm() {
      Object.keys(this.requestBody).forEach(
        (key) => (this.requestBody[key] = "")
      );
    },
    closeModal() {
      this.isModalVisible = false;
      this.clearForm();
    },
  },
};
</script>

<style scoped></style>

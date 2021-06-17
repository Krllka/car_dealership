<template>
  <div class="page">
    <div class="container">
      <h1 class="page__header">Поставки</h1>
      <div class="page__controls">
        <button @click="addDelivery" class="button">Оформить поставку</button>
      </div>
      <app-data-table
        :can-edit="false"
        :headers="tableHeaders"
        :items="renderedDeliveries"
        @deleteItem="deleteDelivery"
      >
        <template #prov="{ tableItem }">
          {{ tableItem.prov.name }}
        </template>
        <template #cars="{ tableItem }">
          <app-list v-if="tableItem.cars" :items="tableItem.cars">
            <template #item="{ listItem }">
              {{ listItem.name }} {{ listItem.description }}
            </template>
          </app-list>
        </template>
      </app-data-table>
      <app-modal-window :is-visible="isModalVisible" @closeModal="closeModal">
        <template #title>Оформить поставку</template>
        <template #body>
          <v-select
            v-if="providers"
            placeholder="Поставщик"
            label="name"
            :options="providers"
            :reduce="(provider) => provider.id"
            v-model="requestBody.prov"
          />
          <v-select
            v-if="cars"
            multiple
            placeholder="Автомобили"
            label="fullName"
            :reduce="(car) => ({ id: car.id })"
            :options="getCarFullName"
            v-model="requestBody.cars"
          />
        </template>
        <template #footer>
          <button @click="createDelivery" class="button">Оформить</button>
        </template>
      </app-modal-window>
    </div>
  </div>
</template>

<script>
import AppDataTable from "@/components/AppDataTable";
import AppList from "@/components/AppList";
import AppModalWindow from "@/components/AppModalWindow";
export default {
  name: "PageDeliveries",
  components: {
    AppModalWindow,
    AppList,
    AppDataTable,
  },
  data() {
    return {
      isModalVisible: false,
      deliveries: null,
      providers: null,
      cars: null,
      requestBody: {
        prov: "",
        cars: [],
      },
      tableHeaders: [
        {
          key: "id",
          value: "№ поставки",
          placeholder: "Поиск по номеру поставки",
          search: "",
        },
        {
          key: "date",
          value: "Дата поставки",
          placeholder: "Поиск по дате поставки",
          search: "",
        },
        {
          key: "prov",
          value: "Поставщик",
          placeholder: "Поиск по поставщику",
          search: "",
        },
        {
          key: "cars",
          value: "Поставялемые автомобили",
          placeholder: "Поиск по автомобилям",
          search: "",
        },
      ],
    };
  },
  created() {
    this.getDeliveries();
  },
  computed: {
    renderedDeliveries() {
      return this.deliveries;
    },
    getCarFullName() {
      return this.cars.map((car) => {
        car.fullName = `${car.name} ${car.description}`;
        return car;
      });
    },
  },
  methods: {
    getDeliveries() {
      this.axios
        .get("http://localhost:8081/delivery")
        .then((response) => {
          this.deliveries = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getDeliveryInfo() {
      const providersURL = this.axios.get("http://localhost:8081/provider");
      const carsURL = this.axios.get("http://localhost:8081/car");
      this.axios
        .all([providersURL, carsURL])
        .then(
          this.axios.spread((providersRes, carsRes) => {
            this.providers = providersRes.data;
            this.cars = carsRes.data;
          })
        )
        .catch((error) => {
          console.log(error);
        });
    },
    addDelivery() {
      this.getDeliveryInfo();
      this.isModalVisible = true;
    },
    createDelivery() {
      this.axios
        .post("http://localhost:8081/delivery", this.requestBody)
        .then(() => {
          this.getDeliveries();
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.closeModal();
        });
    },
    deleteDelivery(delivery) {
      this.axios
        .delete(`http://localhost:8081/delivery/${delivery.id}`)
        .then(() => {
          this.getDeliveries();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    closeModal() {
      this.isModalVisible = false;
      this.requestBody.prov = "";
      this.requestBody.cars = [];
    },
  },
};
</script>

<style scoped></style>

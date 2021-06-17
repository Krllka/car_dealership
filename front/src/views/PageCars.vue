<template>
  <div class="page">
    <div class="container">
      <h1 class="page__header">Автомобили</h1>
      <div class="page__controls">
        <button class="button" @click="addCar">Добавить автомобиль</button>
      </div>
      <app-data-table
        :headers="tableHeaders"
        :items="renderedCars"
        @editItem="editCar"
        @deleteItem="deleteCar"
      >
        <template #body="{ tableItem }">
          {{ tableItem.body.name }}
        </template>
        <template #specs="{ tableItem }">
          <app-list v-if="tableItem.specs" :items="tableItem.specs">
            <template #item="{ listItem }">
              {{ listItem.name }}: {{ listItem.value }}
            </template>
          </app-list>
        </template>
        <template #equip="{ tableItem }">
          <app-list v-if="tableItem.equip" :items="tableItem.equip">
            <template #item="{ listItem }">
              {{ listItem.name }}
            </template>
          </app-list>
        </template>
        <template #price="{ tableItem }">
          {{ formatPrice(tableItem.price) }}
        </template>
      </app-data-table>
      <app-modal-window :is-visible="isModalVisible" @closeModal="closeModal">
        <template v-if="editMode" #title>Редактировать автомобиль</template>
        <template v-else #title>Добавить автомобиль</template>
        <template #body>
          <app-input
            :type="'text'"
            :placeholder="'Марка'"
            v-model="requestBody.name"
          />
          <app-input
            :type="'text'"
            :placeholder="'Модель'"
            v-model="requestBody.description"
          />
          <app-input
            :type="'number'"
            :placeholder="'Цена'"
            v-model="requestBody.price"
          />
          <v-select
            v-if="bodies"
            placeholder="Кузов"
            label="name"
            :options="bodies"
            :reduce="(body) => body.id"
            v-model="requestBody.body"
          />
          <app-input
            v-for="spec in requestBody.specs"
            :key="spec.id"
            :placeholder="spec.name"
            v-model="spec.value"
          />
          <v-select
            v-if="equips"
            multiple
            placeholder="Опции"
            label="name"
            :options="equips"
            v-model="requestBody.equip"
          />
        </template>
        <template #footer>
          <button v-if="!editMode" @click="createCar" class="button">
            Сохранить
          </button>
          <button v-else @click="updateCar" class="button">Сохранить</button>
        </template>
      </app-modal-window>
    </div>
  </div>
</template>

<script>
import AppDataTable from "@/components/AppDataTable";
import AppList from "@/components/AppList";
import AppModalWindow from "@/components/AppModalWindow";
import AppInput from "@/components/AppInput";
export default {
  name: "PageCars",
  components: {
    AppInput,
    AppModalWindow,
    AppList,
    AppDataTable,
  },
  data() {
    return {
      isModalVisible: false,
      editMode: false,
      currentCarId: null,
      cars: null,
      bodies: null,
      specifications: null,
      equips: null,
      requestBody: {
        name: "",
        description: "",
        body: "",
        specs: [],
        equip: [],
        price: "",
      },
      tableHeaders: [
        {
          key: "id",
          value: "ID",
          placeholder: "Поиск по ID",
          search: "",
        },
        {
          key: "name",
          value: "Марка",
          placeholder: "Поиск по марке",
          search: "",
        },
        {
          key: "description",
          value: "Модель",
          placeholder: "Поиск по модели",
          search: "",
        },
        {
          key: "body",
          value: "Кузов",
          placeholder: "Поиск по кузову",
          search: "",
        },
        {
          key: "specs",
          value: "Характеристики",
          placeholder: "Поиск по характеристике",
          search: "",
        },
        {
          key: "equip",
          value: "Опции",
          placeholder: "Поиск по опции",
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
    this.getCars();
  },
  computed: {
    renderedCars() {
      return this.cars;
    },
  },
  methods: {
    getCars() {
      this.axios
        .get("http://localhost:8081/car")
        .then((response) => {
          this.cars = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getCarInfo() {
      const bodiesURL = this.axios.get("http://localhost:8081/body");
      const specsURL = this.axios.get("http://localhost:8081/spec");
      const equipULR = this.axios.get("http://localhost:8081/equip");
      this.axios
        .all([bodiesURL, specsURL, equipULR])
        .then(
          this.axios.spread((bodiesRes, specsRes, equipRes) => {
            this.bodies = bodiesRes.data;
            this.specifications = specsRes.data;
            this.equips = equipRes.data;
          })
        )
        .catch((error) => {
          console.log(error);
        });
    },
    addCar() {
      this.getCarInfo();
      this.requestBody.specs = this.specifications;
      this.isModalVisible = true;
    },
    createCar() {
      this.axios
        .post("http://localhost:8081/car", this.requestBody)
        .then(() => {
          this.getCars();
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.closeModal();
        });
    },
    editCar(car) {
      this.currentCarId = car.id;
      this.getCarInfo();
      this.requestBody.specs = this.specifications;
      this.isModalVisible = true;
      this.editMode = true;
      Object.keys(this.requestBody).forEach((key) => {
        if (key !== "body" && key !== "price") {
          this.requestBody[key] = car[key];
        } else if (key === "body") {
          this.requestBody[key] = car[key].id;
        } else if (key === "price") {
          this.requestBody[key] = car[key].toString();
        }
      });
      console.log(this.requestBody);
    },
    updateCar() {
      this.axios
        .put(`http://localhost:8081/car/${this.currentCarId}`, this.requestBody)
        .then(() => {
          this.getCars();
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.closeModal();
        });
    },
    deleteCar(car) {
      this.axios
        .delete(`http://localhost:8081/car/${car.id}`)
        .then(() => {
          this.getCars();
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
      Object.keys(this.requestBody).forEach((key) => {
        if (typeof this.requestBody[key] === "string") {
          this.requestBody[key] = "";
        } else if (Array.isArray(this.requestBody[key])) {
          this.requestBody[key] = [];
        }
      });
    },
    closeModal() {
      this.isModalVisible = false;
      this.editMode = false;
      this.currentCarId = null;
      this.clearForm();
    },
  },
};
</script>

<style scoped lang="scss"></style>

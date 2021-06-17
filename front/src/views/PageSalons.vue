<template>
  <div class="page">
    <div class="container">
      <h1 class="page__header">Салоны</h1>
      <div class="page__controls">
        <button @click="addSalon" class="button">Добавить салон</button>
      </div>
      <app-data-table
        :headers="tableHeaders"
        :items="renderedSalons"
        @editItem="editSalon"
        @deleteItem="deleteSalon"
      />
      <app-modal-window :is-visible="isModalVisible" @closeModal="closeModal">
        <template v-if="!editMode" #title>Добавить салон</template>
        <template v-else #title>Редактировать салон</template>
        <template #body>
          <app-input
            :type="'text'"
            :placeholder="'Название салона'"
            v-model="requestBody.name"
          />
          <app-input
            :type="'text'"
            :placeholder="'Адрес'"
            v-model="requestBody.address"
          />
        </template>
        <template #footer>
          <button v-if="!editMode" class="button" @click="createSalon">
            Сохранить
          </button>
          <button v-else class="button" @click="updateSalon">Сохранить</button>
        </template>
      </app-modal-window>
    </div>
  </div>
</template>

<script>
import AppDataTable from "@/components/AppDataTable";
import AppModalWindow from "@/components/AppModalWindow";
import AppInput from "@/components/AppInput";
export default {
  name: "PageSalons",
  components: {
    AppInput,
    AppModalWindow,
    AppDataTable,
  },
  data() {
    return {
      isModalVisible: false,
      editMode: false,
      currentSalonId: null,
      salons: null,
      requestBody: {
        name: "",
        address: "",
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
          value: "Название салона",
          placeholder: "Поиск по названию",
          search: "",
        },
        {
          key: "address",
          value: "Адрес",
          placeholder: "Поиск по адресу",
          search: "",
        },
      ],
    };
  },
  created() {
    this.getSalons();
  },
  computed: {
    renderedSalons() {
      return this.salons;
    },
  },
  methods: {
    getSalons() {
      this.axios
        .get("http://localhost:8081/saloon")
        .then((response) => {
          this.salons = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addSalon() {
      this.isModalVisible = true;
    },
    createSalon() {
      this.axios
        .post("http://localhost:8081/saloon", this.requestBody)
        .then(() => {
          this.getSalons();
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.closeModal();
        });
    },
    editSalon(salon) {
      this.currentSalonId = salon.id;
      this.editMode = true;
      this.isModalVisible = true;
      Object.keys(this.requestBody).forEach((key) => {
        this.requestBody[key] = salon[key];
      });
    },
    updateSalon() {
      this.axios
        .put(
          `http://localhost:8081/saloon/${this.currentSalonId}`,
          this.requestBody
        )
        .then(() => {
          this.getSalons();
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.closeModal();
        });
    },
    deleteSalon(salon) {
      this.axios
        .delete(`http://localhost:8081/saloon/${salon.id}`)
        .then(() => {
          this.getSalons();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    clearForm() {
      this.requestBody.name = "";
      this.requestBody.address = "";
    },
    closeModal() {
      this.isModalVisible = false;
      this.editMode = false;
      this.currentSalonId = null;
      this.clearForm();
    },
  },
};
</script>

<style scoped></style>

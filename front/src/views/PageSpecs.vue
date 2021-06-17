<template>
  <div class="page">
    <div class="container">
      <h1 class="page__header">Характеристики</h1>
      <div class="page__controls">
        <button @click="addSpec" class="button">Добавить характеристику</button>
      </div>
      <app-data-table
        :headers="tableHeaders"
        :items="renderedSpecs"
        @editItem="editSpec"
        @deleteItem="deleteSpec"
      />
      <app-modal-window :is-visible="isModalVisible" @closeModal="closeModal">
        <template v-if="!editMode" #title>Добавить характеристику</template>
        <template v-else #title>Редактировать характеристику</template>
        <template #body>
          <app-input
            :type="'text'"
            :placeholder="'Название характеристики'"
            v-model="requestBody.name"
          />
        </template>
        <template #footer>
          <button v-if="!editMode" class="button" @click="createSpec">
            Сохранить
          </button>
          <button v-else class="button" @click="updateSpec">Сохранить</button>
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
  name: "PageSpecs",
  components: {
    AppDataTable,
    AppModalWindow,
    AppInput,
  },
  data() {
    return {
      isModalVisible: false,
      editMode: false,
      currentSpecId: null,
      specs: null,
      requestBody: {
        name: "",
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
          value: "Название характеристики",
          placeholder: "Поиск по харатеристике",
          search: "",
        },
      ],
    };
  },
  created() {
    this.getSpecs();
  },
  computed: {
    renderedSpecs() {
      return this.specs;
    },
  },
  methods: {
    getSpecs() {
      this.axios
        .get("http://localhost:8081/spec")
        .then((response) => {
          this.specs = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addSpec() {
      this.isModalVisible = true;
    },
    createSpec() {
      this.axios
        .post("http://localhost:8081/spec", this.requestBody)
        .then(() => {
          this.getSpecs();
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.closeModal();
        });
    },
    editSpec(spec) {
      this.currentSpecId = spec.id;
      this.editMode = true;
      this.isModalVisible = true;
      Object.keys(this.requestBody).forEach((key) => {
        this.requestBody[key] = spec[key];
      });
    },
    updateSpec() {
      this.axios
        .put(
          `http://localhost:8081/clients/${this.currentSpecId}`,
          this.requestBody
        )
        .then(() => {
          this.getSpecs();
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.closeModal();
        });
    },
    deleteSpec(spec) {
      this.axios
        .delete(`http://localhost:8081/clients/${spec.id}`)
        .then(() => {
          this.getSpecs();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    clearForm() {
      this.requestBody.name = "";
    },
    closeModal() {
      this.isModalVisible = false;
      this.editMode = false;
      this.currentSpecId = null;
      this.clearForm();
    },
  },
};
</script>

<style scoped></style>

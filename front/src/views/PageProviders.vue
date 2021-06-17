<template>
  <div class="page">
    <div class="container">
      <h1 class="page__header">Поставщики</h1>
      <div class="page__controls">
        <button @click="addProvider" class="button">Добавить поставщика</button>
      </div>
      <app-data-table
        :headers="tableHeaders"
        :items="renderedProviders"
        @editItem="editProvider"
        @deleteItem="deleteProvider"
      />
      <app-modal-window :is-visible="isModalVisible" @closeModal="closeModal">
        <template v-if="!editMode" #title>Добавить поставщика</template>
        <template v-else #title>Редактировать данные поставщика</template>
        <template #body>
          <app-input
            :type="'text'"
            :placeholder="'Название компании'"
            v-model="requestBody.name"
          />
        </template>
        <template #footer>
          <button v-if="!editMode" class="button" @click="createProvider">
            Сохранить
          </button>
          <button v-else class="button" @click="updateProvider">
            Сохранить
          </button>
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
  name: "PageProviders",
  components: {
    AppInput,
    AppModalWindow,
    AppDataTable,
  },
  data() {
    return {
      isModalVisible: false,
      editMode: false,
      currentProviderId: null,
      providers: null,
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
          value: "Название компании",
          placeholder: "Поиск по названию",
          search: "",
        },
      ],
    };
  },
  created() {
    this.getProviders();
  },
  computed: {
    renderedProviders() {
      return this.providers;
    },
  },
  methods: {
    getProviders() {
      this.axios
        .get("http://localhost:8081/provider")
        .then((response) => {
          this.providers = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addProvider() {
      this.isModalVisible = true;
    },
    createProvider() {
      this.axios
        .post("http://localhost:8081/provider", this.requestBody)
        .then(() => {
          this.getProviders();
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.closeModal();
        });
    },
    editProvider(provider) {
      this.currentProviderId = provider.id;
      this.editMode = true;
      this.isModalVisible = true;
      Object.keys(this.requestBody).forEach((key) => {
        this.requestBody[key] = provider[key];
      });
    },
    updateProvider() {
      this.axios
        .put(
          `http://localhost:8081/provider/${this.currentProviderId}`,
          this.requestBody
        )
        .then(() => {
          this.getProviders();
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.closeModal();
        });
    },
    deleteProvider(provider) {
      this.axios
        .delete(`http://localhost:8081/provider/${provider.id}`)
        .then(() => {
          this.getProviders();
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
      this.currentProviderId = null;
      this.clearForm();
    },
  },
};
</script>

<style scoped lang="scss"></style>

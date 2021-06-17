<template>
  <div class="page">
    <div class="container">
      <h1 class="page__header">Клиенты</h1>
      <div class="page__controls">
        <button class="button" @click="addClient">Добавить клиента</button>
      </div>
      <app-data-table
        :headers="tableHeaders"
        :items="renderedClients"
        @editItem="editClient"
        @deleteItem="deleteClient"
      />
      <app-modal-window :is-visible="isModalVisible" @closeModal="closeModal">
        <template v-if="!editMode" #title>Добавить клиента</template>
        <template v-else #title>Редактировать данные клиента</template>
        <template #body>
          <app-input
            :type="'text'"
            :placeholder="'ФИО клиента'"
            v-model="requestBody.name"
          />
          <app-input
            :type="'text'"
            :placeholder="'Паспортные данные'"
            v-model="requestBody.passport"
          />
        </template>
        <template #footer>
          <button v-if="!editMode" class="button" @click="createClient">
            Сохранить
          </button>
          <button v-else class="button" @click="updateClient">Сохранить</button>
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
  name: "PageClients",
  components: {
    AppInput,
    AppModalWindow,
    AppDataTable,
  },
  data() {
    return {
      isModalVisible: false,
      editMode: false,
      currentClientId: null,
      clients: null,
      requestBody: {
        name: "",
        passport: "",
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
          value: "Имя клиента",
          placeholder: "Поиск по имени",
          search: "",
        },
        {
          key: "passport",
          value: "Паспортные данные",
          placeholder: "Поиск по паспортным данным",
          search: "",
        },
      ],
    };
  },
  created() {
    this.getClients();
  },
  computed: {
    renderedClients() {
      return this.clients;
    },
  },
  methods: {
    getClients() {
      this.axios
        .get("http://localhost:8081/clients")
        .then((response) => {
          this.clients = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addClient() {
      this.isModalVisible = true;
    },
    createClient() {
      this.axios
        .post("http://localhost:8081/clients", this.requestBody)
        .then(() => {
          this.getClients();
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.closeModal();
        });
    },
    editClient(client) {
      this.currentClientId = client.id;
      this.editMode = true;
      this.isModalVisible = true;
      Object.keys(this.requestBody).forEach((key) => {
        this.requestBody[key] = client[key];
      });
    },
    updateClient() {
      this.axios
        .put(
          `http://localhost:8081/clients/${this.currentClientId}`,
          this.requestBody
        )
        .then(() => {
          this.getClients();
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.closeModal();
        });
    },
    deleteClient(client) {
      this.axios
        .delete(`http://localhost:8081/clients/${client.id}`)
        .then(() => {
          this.getClients();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    clearForm() {
      Object.keys(this.requestBody).forEach(
        (key) => (this.requestBody[key] = "")
      );
    },
    closeModal() {
      this.isModalVisible = false;
      this.editMode = false;
      this.currentClientId = null;
      this.clearForm();
    },
  },
};
</script>

<style scoped></style>

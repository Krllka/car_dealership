<template>
  <div class="page">
    <div class="container">
      <h1 class="page__header">Сотрудники</h1>
      <div class="page__controls">
        <button @click="addWorker" class="button">Добавить сотрудника</button>
      </div>
      <app-data-table
        :headers="tableHeaders"
        :items="renderedWorkers"
        @editItem="editWorker"
        @deleteItem="deleteWorker"
      >
        <template #sex="{ tableItem }">
          {{ tableItem.sex.name }}
        </template>
        <template #post="{ tableItem }">
          {{ tableItem.post.name }}
        </template>
        <template #dealer="{ tableItem }">
          {{ tableItem.dealer.name }}, {{ tableItem.dealer.address }}
        </template>
      </app-data-table>
      <app-modal-window :is-visible="isModalVisible" @closeModal="closeModal">
        <template v-if="editMode" #title
          >Редактировать данные сотрудника</template
        >
        <template v-else #title>Добавить сотрудника</template>
        <template #body>
          <app-input
            :type="'text'"
            :placeholder="'Имя сотрудника'"
            v-model="requestBody.name"
          />
          <v-select
            v-if="genders"
            placeholder="Пол сотрудника"
            label="name"
            :options="genders"
            :reduce="(gender) => gender.id"
            v-model="requestBody.sex"
          />
          <app-input
            :type="'text'"
            :placeholder="'Паспортные данные'"
            v-model="requestBody.passport"
          />
          <v-select
            v-if="jobs"
            placeholder="Должность"
            label="name"
            :options="jobs"
            :reduce="(job) => job.id"
            v-model="requestBody.post"
          />
          <v-select
            v-if="salons"
            placeholder="Салон"
            label="name"
            :options="salons"
            :reduce="(salon) => salon.id"
            v-model="requestBody.dealer"
          />
        </template>
        <template #footer>
          <button v-if="!editMode" @click="createWorker" class="button">
            Сохранить
          </button>
          <button v-else @click="updateWorker" class="button">Сохранить</button>
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
  name: "PageWorkers",
  components: {
    AppInput,
    AppModalWindow,
    AppDataTable,
  },
  data() {
    return {
      isModalVisible: false,
      editMode: false,
      currentWorkerId: null,
      workers: null,
      jobs: null,
      salons: null,
      genders: null,
      requestBody: {
        name: "",
        post: "",
        dealer: "",
        sex: "",
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
          value: "Имя сотрудника",
          placeholder: "Поиск по имени",
          search: "",
        },
        {
          key: "sex",
          value: "Пол",
          placeholder: "Поиск по полу",
          search: "",
        },
        {
          key: "post",
          value: "Должность",
          placeholder: "Поиск по должности",
          search: "",
        },
        {
          key: "dealer",
          value: "Салон",
          placeholder: "Поиск по салону",
          search: "",
        },
        {
          key: "passport",
          value: "Паспортные данные",
          placeholder: "Поиск по паспорту",
          search: "",
        },
      ],
    };
  },
  created() {
    this.getWorkers();
  },
  computed: {
    renderedWorkers() {
      return this.workers;
    },
  },
  methods: {
    getWorkers() {
      this.axios
        .get("http://localhost:8081/worker")
        .then((response) => {
          this.workers = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getWorkerInfo() {
      const positionsURL = this.axios.get("http://localhost:8081/posts");
      const dealershipURL = this.axios.get("http://localhost:8081/saloon");
      const sexURL = this.axios.get("http://localhost:8081/sex");
      this.axios
        .all([positionsURL, dealershipURL, sexURL])
        .then(
          this.axios.spread((posRes, dealersRes, sexRes) => {
            this.jobs = posRes.data;
            this.salons = dealersRes.data;
            this.genders = sexRes.data;
          })
        )
        .catch((error) => {
          console.log(error);
        });
    },
    addWorker() {
      this.getWorkerInfo();
      this.isModalVisible = true;
    },
    createWorker() {
      this.axios
        .post("http://localhost:8081/worker", this.requestBody)
        .then(() => {
          this.getWorkers();
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.closeModal();
        });
    },
    editWorker(worker) {
      this.currentWorkerId = worker.id;
      this.getWorkerInfo();
      this.isModalVisible = true;
      this.editMode = true;
      Object.keys(this.requestBody).forEach((key) => {
        if (key === "sex" || key === "post" || key === "dealer") {
          this.requestBody[key] = worker[key].id;
        } else {
          this.requestBody[key] = worker[key];
        }
      });
    },
    updateWorker() {
      this.axios
        .put(
          `http://localhost:8081/worker/${this.currentWorkerId}`,
          this.requestBody
        )
        .then(() => {
          this.getWorkers();
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.closeModal();
        });
    },
    deleteWorker(worker) {
      this.axios
        .delete(`http://localhost:8081/worker/${worker.id}`)
        .then(() => {
          this.getWorkers();
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
      this.currentWorkerId = null;
      this.clearForm();
    },
  },
};
</script>

<style scoped></style>

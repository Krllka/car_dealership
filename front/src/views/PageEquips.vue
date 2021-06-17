<template>
  <div class="page">
    <div class="container">
      <h1 class="page__header">Комплектации</h1>
      <div class="page__controls">
        <button @click="addEquip" class="button">Добавить опцию</button>
      </div>
      <app-data-table
        :headers="tableHeaders"
        :items="renderedEquips"
        @editItem="editEquip"
        @deleteItem="deleteEquip"
      />
      <app-modal-window :is-visible="isModalVisible" @closeModal="closeModal">
        <template v-if="!editMode" #title>Добавить опцию</template>
        <template v-else #title>Редактировать опцию</template>
        <template #body>
          <app-input
            :type="'text'"
            :placeholder="'Название опции'"
            v-model="requestBody.name"
          />
        </template>
        <template #footer>
          <button v-if="!editMode" class="button" @click="createEquip">
            Сохранить
          </button>
          <button v-else class="button" @click="updateEquip">Сохранить</button>
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
  name: "PageEquips",
  components: {
    AppInput,
    AppModalWindow,
    AppDataTable,
  },
  data() {
    return {
      isModalVisible: false,
      editMode: false,
      currentEquipId: null,
      equips: null,
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
          value: "Название опции",
          placeholder: "Поиск по опции",
          search: "",
        },
      ],
    };
  },
  created() {
    this.getEquips();
  },
  computed: {
    renderedEquips() {
      return this.equips;
    },
  },
  methods: {
    getEquips() {
      this.axios
        .get("http://localhost:8081/equip")
        .then((response) => {
          this.equips = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addEquip() {
      this.isModalVisible = true;
    },
    createEquip() {
      this.axios
        .post("http://localhost:8081/equip", this.requestBody)
        .then(() => {
          this.getEquips();
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.closeModal();
        });
    },
    editEquip(equip) {
      this.currentEquipId = equip.id;
      this.editMode = true;
      this.isModalVisible = true;
      Object.keys(this.requestBody).forEach((key) => {
        this.requestBody[key] = equip[key];
      });
    },
    updateEquip() {
      this.axios
        .put(
          `http://localhost:8081/equip/${this.currentEquipId}`,
          this.requestBody
        )
        .then(() => {
          this.getEquips();
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.closeModal();
        });
    },
    deleteEquip(equip) {
      this.axios
        .delete(`http://localhost:8081/equip/${equip.id}`)
        .then(() => {
          this.getEquips();
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
      this.currentEquipId = null;
      this.clearForm();
    },
  },
};
</script>

<style scoped></style>

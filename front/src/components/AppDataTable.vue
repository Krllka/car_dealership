<template>
  <table class="table">
    <thead class="table__header">
      <tr class="row">
        <th v-for="header in headers" :key="header.key" class="row__header">
          {{ header.value.toUpperCase() }}
        </th>
        <th class="row__header">ДЕЙСТВИЯ</th>
      </tr>
    </thead>
    <tbody class="table__body">
      <tr class="row">
        <td v-for="header in headers" :key="header.key" class="row__data">
          <app-search
            :placeholder="header.placeholder"
            v-model="header.search"
          />
        </td>
        <td class="row__data"></td>
      </tr>
      <tr v-for="item in filteredItems" :key="item.id" class="row">
        <td v-for="header in headers" :key="header.key" class="row__data">
          <slot :name="header.key" :tableItem="item">
            {{ item[header.key] }}
          </slot>
        </td>
        <td class="row__data actions">
          <img
            v-if="canEdit"
            @click="$emit('editItem', item)"
            class="icon edit"
            src="../assets/icons/edit.svg"
            alt="edit"
          />
          <img
            @click="$emit('deleteItem', item)"
            class="icon delete"
            src="../assets/icons/delete.svg"
            alt="delete"
          />
        </td>
      </tr>
    </tbody>
    <tfoot class="table__footer"></tfoot>
  </table>
</template>

<script>
import AppSearch from "@/components/AppSearch";
export default {
  name: "AppDataTable",
  components: { AppSearch },
  props: {
    headers: {
      type: Array,
      default: () => [],
    },
    items: {
      type: Array,
      default: () => [],
    },
    canEdit: {
      type: Boolean,
      default: true,
    },
  },
  computed: {
    filteredItems() {
      const filledSearch = this.headers.filter((item) => item.search);
      if (!filledSearch.length) return this.items;
      let result = this.items;
      filledSearch.forEach((val) => {
        result = result.filter((item) => {
          if (typeof item[val.key] === "string") {
            if (
              item[val.key].toLowerCase().includes(val.search.toLowerCase())
            ) {
              return item;
            }
          } else if (Array.isArray(item[val.key])) {
            for (const el in item[val.key]) {
              if (
                typeof item[val.key][el] === "object" &&
                Object.values(item[val.key][el])
                  .join(" - ")
                  .toLowerCase()
                  .includes(val.search.toLowerCase())
              ) {
                return item;
              }
            }
            if (
              item[val.key]
                .join(", ")
                .toLowerCase()
                .includes(val.search.toLowerCase())
            ) {
              return item;
            }
          }
        });
      });
      return result;
    },
  },
};
</script>

<style scoped lang="scss">
@import "src/assets/styles/variables";
.table {
  width: 100%;
  margin-bottom: 10vh;
  border: 2px solid $background-light;
  border-radius: 3px;
  background-color: $table-border;
}
.row {
  &__header,
  &__data {
    padding: 10px;
  }
  &__header {
    background-color: $background-light;
    color: $text-light;
    text-align: left;
    font-size: 15px;
  }
  &__data {
    font-size: 14px;
    background-color: $table-content-bg;
  }
  &:last-child > .row__data {
    border-bottom: none;
  }
  .actions {
    text-align: center;
  }
  .icon {
    width: 25px;
    height: 25px;
    cursor: pointer;
    transition: all 0.3s ease;
    padding: 5px;
    margin: 0 5px;
    border-radius: 6px;
    &:hover {
      background-color: $table-icons-bg;
    }
  }
}
</style>

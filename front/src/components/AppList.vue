<template>
  <ul class="list">
    <li v-for="(item, index) in items" :key="index" class="list__item">
      <slot name="item" :listItem="item" />
    </li>
  </ul>
</template>

<script>
export default {
  name: "AppList",
  props: {
    items: {
      type: Array,
      default: () => [],
    },
  },
};
</script>

<style scoped lang="scss">
.list__item {
  margin-left: 10px;
  list-style-image: url("../assets/icons/right-arrow.svg");
}
</style>

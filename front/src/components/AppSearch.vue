<template>
  <input
    type="search"
    :value="value"
    class="input"
    :placeholder="placeholder"
    @input="$emit('input', $event.target.value)"
  />
</template>

<script>
export default {
  name: "AppSearch",
  props: {
    value: {
      type: String,
      default: "",
    },
    placeholder: {
      type: String,
      default: "",
    },
  },
};
</script>

<style scoped lang="scss">
@import "../assets/styles/variables";

.input {
  width: 100%;
  padding: 2px 3px;
  font-size: 12px;
  border: 2px solid $table-icons-bg;
  border-radius: 3px;
  transition: all 0.3s ease;
  &:focus {
    box-shadow: 0 0 10px $table-icons-bg;
  }
}
</style>

<template>
  <div class="input-wrapper">
    <input
      :type="type"
      class="input"
      :placeholder="placeholder"
      :value="value"
      @input="$emit('input', $event.target.value)"
    />
  </div>
</template>

<script>
export default {
  name: "AppInput",
  props: {
    placeholder: {
      type: String,
      default: "",
    },
    type: {
      type: String,
      default: "text",
    },
    value: {
      type: String,
      default: "",
    },
  },
};
</script>

<style scoped lang="scss">
@import "../assets/styles/variables";

.input {
  height: 32px;
  font-size: 15px;
  padding: 2px 5px;
  border: 1px solid $background-light;
  border-radius: 3px;
  transition: all 0.3s ease;
  margin: 10px 0;
  //&:focus {
  //  box-shadow: 0 0 5px $background-light;
  //}
  &::placeholder {
    color: $placeholder-color;
  }
}

.input-wrapper {
  margin: 0 10px;
  width: 190px;
}
</style>

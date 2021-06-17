<template>
  <div
    v-if="isVisible"
    class="modal-container"
    @mousedown.self="$emit('closeModal')"
  >
    <div class="modal">
      <div class="modal__header">
        <h3 class="header__title">
          <slot name="title">Модальное окно</slot>
        </h3>
        <img
          src="../assets/icons/cancel.svg"
          alt="закрыть"
          class="header__close"
          @click="$emit('closeModal')"
        />
      </div>
      <div class="modal__body">
        <slot name="body">Тело</slot>
      </div>
      <div class="modal__footer">
        <slot name="footer">Футер</slot>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AppModalWindow",
  props: {
    isVisible: {
      type: Boolean,
      default: false,
    },
  },
};
</script>

<style scoped lang="scss">
@import "../assets/styles/variables";

.modal-container {
  position: fixed;
  top: 0;
  left: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100vw;
  height: 100vh;
  z-index: 100;
  background-color: rgba(0, 0, 0, 0.5);
}

.modal {
  min-width: 25vw;
  max-width: 700px;
  border: 3px solid $background-light;
  border-radius: 6px;
  box-shadow: 0 0 5px $background-light;
  background-color: $text-light;
  &__header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 15px;
    border-bottom: 2px solid $background-light;
    .header__title {
      font-size: 16px;
    }
    .header__close {
      width: 25px;
      height: 25px;
      cursor: pointer;
      transition: all 0.3s ease;
      padding: 5px 0;
      border-radius: 6px;
      &:hover {
        background-color: $table-icons-bg;
      }
    }
  }
  &__body {
    padding: 15px 5px;
    max-height: 50vh;
    width: 700px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    flex-wrap: wrap;
  }
  &__footer {
    padding: 15px;
  }
}
</style>

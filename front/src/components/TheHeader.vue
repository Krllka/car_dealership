<template>
  <header class="header">
    <div class="container">
      <nav class="menu">
        <h1 class="title">
          <router-link to="/">База данных автосалона</router-link>
        </h1>
        <ul class="menu__list">
          <li class="menu__item" @click="$router.push('/')">Автомобили</li>
          <li class="menu__item" @click="$router.push('/workers')">
            Сотрудники
          </li>
          <li class="menu__item" @click="$router.push('/orders')">Заказы</li>
          <li class="menu__item">
            <a href="#">Еще</a>
            <div class="dropdown">
              <ul class="dropdown__list">
                <li class="dropdown__item">
                  <router-link to="/clients">Клиенты</router-link>
                </li>
                <li class="dropdown__item"><a href="#">Поставки</a></li>
                <li class="dropdown__item">
                  <router-link to="/specs">Характеристики</router-link>
                </li>
                <li class="dropdown__item"><a href="#">Комплектации</a></li>
              </ul>
            </div>
          </li>
        </ul>
      </nav>
    </div>
  </header>
</template>

<script>
export default {
  name: "TheHeader",
};
</script>

<style scoped lang="scss">
@import "../assets/styles/variables";

.header {
  //height: 100px;
  //padding: 25px 0;
  background-color: $background-main;
  box-shadow: 0 0 5px $background-main;
}

.menu {
  display: flex;
  justify-content: space-between;
  align-items: center;
  a {
    color: $text-light;
  }
  &__list {
    display: flex;
    justify-content: flex-end;
    align-items: center;
    width: 30%;
  }
  &__item {
    cursor: pointer;
    position: relative;
    padding: 15px;
    color: $text-light;
    transition: all 0.3s ease;
    &:hover {
      background-color: $background-dark;
      .dropdown {
        display: block;
        z-index: 10;
        cursor: default;
        position: absolute;
        border: 1px solid $background-dark;
        background-color: $background-main;
        margin-top: 15px;
        margin-left: -87px;
        &__list {
          display: block;
          margin: 10px;
        }
        &__item {
          padding: 10px 0;
          border-bottom: 1px solid $background-dark;
          a {
            padding-bottom: 1px;
            &:hover {
              border-bottom: 1px solid $text-light;
            }
          }
          &:last-child {
            border-bottom: none;
            padding-bottom: 0;
          }
        }
      }
    }
  }
  .dropdown {
    display: none;
  }
}
</style>

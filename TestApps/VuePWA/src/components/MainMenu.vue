<template>
  <div>
    <p class="centerText">Routine tasks</p>
    <div class="buttonLike"><router-link to='/loginRegister'><p class="centerText">LOGIN</p></router-link></div>
    <div class="buttonLike"><router-link to='/ImageFrom/1'><p class="centerText">LOAD IMAGE FROM DISK</p></router-link></div>
    <div class="buttonLike"><router-link to='/ImageFrom/2'><p class="centerText">LOAD IMAGE FROM NETWORK</p></router-link></div>
    <p class="centerText">Performance related tasks</p>
    <div class="buttonLike"><router-link :to="'/cpuIntensive/0'"><p class="centerText">CPU INTENSIVE TASK (ST)</p></router-link></div>
    <div class="buttonLike"><router-link to='/cpuIntensive/1'><p class="centerText">CPU INTENSIVE TASK (MT)</p></router-link></div>
    <div class="buttonLike"><router-link to='/memoryManagement'><p class="centerText">MEMORY MANAGEMENT</p></router-link></div>
    <div class="buttonLike"><router-link to='/networkRequests'><p class="centerText">PERFORM NETWORK REQUESTS</p></router-link></div>
  </div>
</template>

<script>
export default {
  name: 'hello'
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.centerText {
  display: inline-block;
  margin: 16px;
}
.buttonLike {
  background-color: darkgrey;
  height: 50px;
  margin: 25px;
}
</style>

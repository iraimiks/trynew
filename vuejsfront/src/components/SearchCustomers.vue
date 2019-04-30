<template>
  <div class="searchform">
    <h4>Find by Age</h4>
    <div class="form-group">
      <input type="number" class="form-control" id="age" required v-model="age" name="age">
    </div>
 
    <div class="btn-group">
      <button v-on:click="searchCustomers" class="btn btn-success">Search</button>
    </div>
 
    <ul class="search-result">
      <li v-for="(custome, index) in customer" :key="index">
        <h6>{{custome.name}} ({{custome.age}})</h6>
      </li>
    </ul>
  </div>
</template>
 
<script>
import http from "../http-common";
 
export default {
  name: "search-customer",
  data() {
    return {
      age: 0,
      customer: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    searchCustomers() {
      http
        .get("/customer/age/" + this.age)
        .then(response => {
          this.customer = response.data; // JSON are parsed automatically.
          console.log(response.data);
        })
        .catch(e => {
          console.log(e.response);
        });
    }
    /* eslint-enable no-console */
  }
};
</script>
 
<style>
.searchform {
  max-width: 300px;
  margin: auto;
}
.search-result {
  margin-top: 20px;
  text-align: left;
}
</style>
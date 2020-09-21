<template>
  <div class="container mx-auto">
    <div class="form-container mx-auto">
      <h1>Ladda upp Cv</h1>
      <h6>Här kan du ladda upp ditt Cv till ITHS Cv-databas.</h6>
      <form>
        <div class="form-group mt-5">
          <label for="exampleFormControlFile1">Ladda upp Cv</label>
          <input type="file" class="form-control-file mt-3" id="exampleFormControlFile1" />
          <button type="submit" class="btn-color mt-5">Spara</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "LaddaCv",
};
</script>

<style scoped>
.form-container {
  width: 500px;
  border: 2px solid #eee;
  margin-top: 5em;
  padding: 3em;
}
@media (max-width: 580px) {
  .form-container {
    width: 95%;
  }
}
</style>
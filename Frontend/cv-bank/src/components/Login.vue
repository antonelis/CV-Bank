<template>
  <div>
    <div class="container mt-5">
      <h3 class="text-center title-Login">Välkommen till IT-Högskolans Cv-databas</h3>
      <div class="form-container mx-auto">
        <form>
          <div class="form-group">
            <label for>Användarnamn:</label>
            <input type="email" class="form-control" aria-describedby="emailHelp" />
          </div>
          <div class="form-group">
            <label for="exampleInputPassword1">Lösenord:</label>
            <input type="password" class="form-control" id="exampleInputPassword1" />
          </div>
          <button type="submit" class="btn-color">Logga in</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
};
</script>

<style scoped>
.form-container {
  width: 500px;
  border: 2px solid #eee;
  margin-top: 5em;
}

form {
  padding: 2em;
}

@media (max-width: 580px) {
  .title-Login{
    font-size: 1.8rem;
  }
  .form-container {
    width: 75%;
    border: 2px solid rgb(110, 3, 3);
    margin-top: 5em;
  }
}
</style>
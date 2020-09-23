<template>
  <div class="container mt-5">
    <h1 class="mb-3">Cv-databas</h1>
    <!-- COLLAPS -->
    <div class="CVdataBasBox accordion" id="accordionExample">
      <div class="card">
        <div class="card-header" id="headingOne">
          <h2 class="mb-0">
            <button
              class="btn btn-block text-left"
              type="button"
              data-toggle="collapse"
              data-target="#collapseOne"
              aria-expanded="true"
              aria-controls="collapseOne"
            >
              <h5>Java</h5>
            </button>
          </h2>
        </div>
        <div
          id="collapseOne"
          class="collapse show"
          aria-labelledby="headingOne"
          data-parent="#accordionExample"
        >
          <div class="card-body">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">Namn:</th>
                  <th scope="col">CV</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Kalle</td>
                  <td>
                    <a href="#">Öppna</a>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CvLista",
};
</script>

<style scoped>
@media (max-width: 580px) {
  .CVdataBasBox {
    padding: 0 15px;
  }
}
</style>
let app = Vue.createApp({
  data: function () {
    return {};
  },
});

app.component("navbar", {
  props: {
    backBtn: Boolean,
  },
  template: /* html */ `
    <nav class="navbar navbar-expand-lg fixed-top navbar-light bg-light">
  <a class="navbar-brand" href="#">EGGPENSAS</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a :v-if="backBtn" class="nav-item nav-link active" href="#">VOLVER</a>
      <a class="nav-item nav-link" href="#">LOG OUT</a>
    </div>
  </div>
</nav>
      `,
});

app.mount("#app");

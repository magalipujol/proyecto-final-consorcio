let app = Vue.createApp({
  data: function () {
    return {};
  },
});
// pq no está funcionando puta madre
app.component("navbar", {
  props: {
    backBtn: Boolean,
    backBtnRef: String,
  },
  methods: {
    alertLogOut: function () {
      confirm('Está seguro que desea cerrar su sesión?'); ;
    },
  },
  template: /* html */ `
    <nav class="navbar navbar-expand-lg sticky-top navbar-light bg-light">
      <a class="navbar-brand" href="#">EGGSPENSAS</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
          <!--<a class="nav-item nav-link" href="/">INICIO</a>-->
          <a class="nav-item nav-link" href="/logout" v-on:click="alertLogOut" style="margin-left: 1500px">LOG OUT</a>
          <a class="nav-item nav-link" href="/usuario/cambiarContrasenia">CAMBIAR CONTRASEÑA</a>
        </div>
      </div>
    </nav>
      `,
});

app.mount("#app");

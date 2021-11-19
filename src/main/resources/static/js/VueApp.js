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
  <a class="navbar-brand" href="#">EGGPENSAS</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link" href="@{/cambiarContrasenia}">CAMBIAR CONTRASEÑA</a>
      <a class="nav-item nav-link" href="@{/}">INICIO</a>
      <a class="nav-item nav-link" href="@{/login}" v-on:click="alertLogOut">LOG OUT</a>
    </div>
  </div>
</nav>
      `,
});

app.component("vistas", {
template: /* html */ `
<nav class="vistas navbar navbar-expand-lg navbar-light bg-light">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link" href="index.html">Index</a>
      <a class="nav-item nav-link" href="login.html">Login</a>
      <a class="nav-item nav-link" href="verEdificios.html">Ver edificos</a>
      <a class="nav-item nav-link" href="expensasVistaAdmi.html">Expensas admin</a>
      <a class="nav-item nav-link" href="formCrearEdificio.html">Crear edificio</a>
      <a class="nav-item nav-link" href="cargarMovimientos.html">Cargar mov</a>
      <a class="nav-item nav-link" href="expensasVistaUsuario.html">Expensas usuario</a>
      <a class="nav-item nav-link" href="formAgregarDepartamento.html">Agregar depto</a>
    </div>
  </div>
</nav>
`
})

app.mount("#app");

<!doctype html>
<html lang="es">
  <head>
  	<title>Registar Habitacion</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="css/style1.css">
  </head>
  <body>
		
		<div class="wrapper d-flex align-items-stretch">
			<nav id="sidebar">
				<div class="p-4 pt-5">
                                    <a href="Menu.jsp" class="img logo rounded-circle mb-5" style="background-image: url(images/logo.jpg);"></a>
	        <ul class="list-unstyled components mb-5">
	          <li >
	            <a href="#huesped" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Huesped</a>
	            <ul class="collapse list-unstyled" id="huesped">
                <li>
                    <a href="RegistarHuesped.jsp">Registrar Huesped</a>
                </li>
                <li>
                    <a href="SvConsultaHuesped">Ver Huesped</a>
                </li>
	            </ul>
	          </li>
	          
	          <li>
              <a href="#empleado" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Empleados</a>
              <ul class="collapse list-unstyled" id="empleado">
                <li>
                    <a href="SvConsultaEmpleado">Ver Empleados</a>
                </li>
                
              </ul>
	          </li>
                  
                  <li>
              <a href="#habitacion" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Habitaciones</a>
              <ul class="collapse list-unstyled" id="habitacion">
                <li>
                    <a href="RegistarHabitacion.jsp">Registar Habitacion</a>
                </li>
              </ul>
	          </li>
                  <li>
              <a href="#reserva" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Reserva</a>
              <ul class="collapse list-unstyled" id="reserva">
                <li>
                    <a href="SvMostrarEnReserva">Hacer Reserva</a>
                </li>
                <li>
                    <a href="SvReservaPorDia">Buscar Reserva</a>
                </li>
              </ul>
	          </li>
	          
	        </ul>

	        

	      </div>
    	</nav>

        <!-- Page Content  -->
      <div id="content" class="p-4 p-md-5">

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <div class="container-fluid">

            <button type="button" id="sidebarCollapse" class="btn btn-primary">
              <i class="fa fa-bars"></i>
              <span class="sr-only"></span>
            </button>
            <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fa fa-bars"></i>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="nav navbar-nav ml-auto">
                <li class="nav-item active">
                   <a href="SvLogOut" class="btn btn-warning" role="button">Salir</a>
                </li>               
              </ul>
            </div>
          </div>
        </nav>

          <h2 class="mb-4">Registar Habitacion.</h2>
           
          <form action="SvAltaHabitacion" method="POST">
    <div class="form-group col-md-6">
    <label for="nombre">Nombre:</label>
    <input type="text" class="form-control" id="nombre" name="nombre" required autocomplete="off">
  </div>
  <div class="form-group col-md-6">
    <label for="precio">Precio:</label>
    <input type="number" class="form-control" id="precio" name="precio" step="0.01" required autocomplete="off">
  </div>
  <div class="form-row">
    <div class="form-group col-md-2">
      <label for="piso">Piso:</label>
      <select name="piso" id="piso" class="form-control">
        <option selected>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
        <option>5</option>
        <option>6</option>
        <option>7</option>
        <option>8</option>
        <option>9</option>
        <option>10</option>
      </select>
    </div>
       <div class="form-group col-md-2">
      <label for="tipo">Tipo:</label>
      <select name="tipo" id="tipo" class="form-control" >
        <option selected>Single</option>
                <option>Doble</option>
                <option>Triple</option>
                <option>Multiple</option>
      </select>
    </div>
       <div class="form-group col-md-3">
      <label for="cantPersona">Cantidad Max de personas:</label>
      <select id="cantPersona" class="form-control" name="cantPersona">
        <option selected>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
        <option>5</option>
        <option>6</option>
        <option>7</option>
        <option>8</option>
      </select>
    </div>
  </div>
  <div class="form-group">
   
  </div>
  <button type="submit" class="btn btn-primary">Registrar</button>
</form>
      </div>
		</div>

    <script src="js/jquery.min.js"></script>
    <script src="js/popper.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
  </body>
</html>
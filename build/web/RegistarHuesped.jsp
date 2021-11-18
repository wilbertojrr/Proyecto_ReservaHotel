<!doctype html>
<html lang="en">
  <head>
  	<title>Registar Huesped</title>
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

          <h2 class="mb-4">Registrar Huesped</h2>
          <form action="SvAltaHuesped" method="POST">
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="dni">Dni:</label>
      <input name="dni" type="number" class="form-control" id="dni" required autocomplete="off">
    </div>
    <div class="form-group col-md-6">
      <label for="nombre">Nombre:</label>
      <input type="text" class="form-control" id="nombre" name="nombre" required autocomplete="off">
    </div>
      </div>
      
        <div class="form-row">
    <div class="form-group col-md-6">
      <label for="apellido">Apellido:</label>
      <input type="text" class="form-control" id="apellido" name="apellido" required autocomplete="off">
    </div>
    <div class="form-group col-md-6">
      <label for="fecha_nac">Fecha de nacimiento:</label>
      <input type="date" class="form-control" id="fecha_nac" name="fecha_nac" required autocomplete="off">
    </div>
            </div>
    <div class="form-row">
    <div class="form-group col-md-6">
      <label for="direccion">Dirreccion:</label>
      <input type="text" class="form-control" id="direccion" name="direccion" required autocomplete="off">
    </div>
    <div class="form-group col-md-6">
      <label for="profesion">Profesion:</label>
      <input type="text" class="form-control" id="profesion" name="profesion" required autocomplete="off">
    </div>
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
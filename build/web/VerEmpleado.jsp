<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="Logica.Empleado"%>
<%@page import="java.util.List"%>
<!doctype html>
<html lang="en">
  <head>
  	<title>MENU</title>
    <meta charset="utf-8">
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

          <h2 class="mb-4">Empleados Registrados:</h2>
           <table class="table">
  <thead>
    <tr>
      <th scope="col">Nombre:</th>
      <th scope="col">Apellido:</th>
      <th scope="col">DNI:</th>
      <th scope="col">Direccion:</th>
      <th scope="col">Fecha de nacimineto:</th>
      <th scope="col">Cargo:</th>

     
    </tr>
  </thead>
  <tbody>
      <% HttpSession misesion = request.getSession();
      List<Empleado> listaEmpleado = (List) request.getSession().getAttribute("listaEmpleado");
      for (Empleado emple : listaEmpleado) {
      %>
    <tr>
      <%
      String nombre = emple.getNombre();
      String apellido = emple.getApellido();
      String dni = emple.getDni();
      String direccion = emple.getDireccion();
      SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
      String fecha = formato.format(emple.getFecha_nac());
      String cargo = emple.getCargo();
      %>
    
        <td scope="row"><%=nombre%></td>
        <td><%=apellido%></td>
        <td><%=dni%></td>
        <td><%=direccion%></td>
        <td><%=fecha%></td>
        <td><%=cargo%></td>
        </tr>
  </tbody>
        <%}%>
</table>
      
      </div>
		</div>

    <script src="js/jquery.min.js"></script>
    <script src="js/popper.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
  </body>
</html>
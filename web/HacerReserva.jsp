<%@page import="Logica.Habitacion"%>
<%@page import="Logica.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<%@page import="Logica.Huesped"%>
<!doctype html>
<html lang="en">
    <head>
        <title>Reserva</title>
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

                <h2 class="mb-4">RESERVA</h2>
                <form action="SvHacerReserva" method="POST">
                    <div class="form-row">

                        <div class="form-group col-md-6">
                            <label for="hues">Huesped:</label>
                            <select id="hues" name="huesped" class="form-control">
                                <% HttpSession misesion = request.getSession();
                                    List<Huesped> listaHuesped = (List) request.getSession().getAttribute("listaHuesped");
                                    for (Huesped hues : listaHuesped) {
                                        String nombre = hues.getNombre() + " " + hues.getApellido();
                                        long id = hues.getId_persona();
                                %>
                                <option value="<%=id%>"> <%=nombre%></option>
                                <% } %>   
                            </select>
                        </div>

                        <div class="form-group col-md-6">
                            <label for="emple">Empleado</label>
                            <select id="emple" name="empleado" class="form-control">
                                <% List<Empleado> listaEmpleado = (List) request.getSession().getAttribute("listaEmpleado");
                                    for (Empleado emple : listaEmpleado) {
                                        String nombre = emple.getNombre() + " " + emple.getApellido();
                                        long id = emple.getId_persona();
                                %>
                                <option value="<%=id%>"><%=nombre%></option>
                                <% }%>
                            </select>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="cantPers">Cant de personas a hospedarse:</label>
                            <input type="number" class="form-control" id="cantPers" name="cant_personas" min="1" required autocomplete="off">
                        </div>                             
                        <div class="form-group col-md-6">
                            <label for="habita">Habitacion:</label>
                            <select id="habita" name="habita" class="form-control" >
                                <% List<Habitacion> listaHabitacion = (List) request.getSession().getAttribute("listaHabitacion");
                                    for (Habitacion habi : listaHabitacion) {
                                        String hab = habi.getNombre() + "; " + habi.getTipo() + " (Cant Max Personas: " + habi.getCantPersona() + ")";
                                        long id = habi.getId_habitacion();
                                %>
                                <option value="<%=id%>"><%=hab%></option>
                                <% }%>
                            </select>
                        </div>
                    </div>
                            <div class="form-row">
                              <div class="form-group col-md-6">
                            <label for="checkIn">CheckIn:</label>
                            <input type="date" class="form-control" id="checkIn" name="checkIn" required autocomplete="off">
                        </div>   
                                
                                <div class="form-group col-md-6">
                            <label for="checkOut">checkOut:</label>
                            <input type="date" class="form-control" id="checkOut" name="checkOut" required autocomplete="off">
                        </div> 
                            </div>
                    <button type="submit" class="btn btn-primary">Hacer Reserva</button>

                </form>

            </div>
        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/popper.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
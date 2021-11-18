
<!DOCTYPE html>
<html lang="es" >
<head>
 
  <title>LOGIN</title>
  <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  <link rel="stylesheet" href="Assets/Css/style.css">

</head>
<body>
<!-- partial:index.partial.html -->


<div class="form" >
      
      <ul class="tab-group">
        <li class="tab active"><a href="#signup">Registro</a></li>
        <li class="tab"><a href="#login">Ingreso</a></li>
      </ul>
      
      <div class="tab-content">
        <div id="signup">   
          <h1>Registra un empleado</h1>
          
          <form action="SvAltaEmple" method="post">
          
          <div class="top-row">
            <div class="field-wrap">
              <label>
                DNI*<span class="req"></span>
              </label>
                <input type="number" name="dni" required autocomplete="off" />
            </div>
        
            <div class="field-wrap">
              <label>
                Nombre*<span class="req"></span>
              </label>
                <input type="text" name="nombre" required autocomplete="off"/>
            </div>
          </div>
              
              <div class="top-row">
            <div class="field-wrap">
              <label>
                Apellido*<span class="req"></span>
              </label>
                <input type="text" name="apellido" required autocomplete="off" />
            </div>
        
            <div class="field-wrap">
              <label>
                <span class="req"></span>
              </label>
              <input type="text" name="fecha_nac" placeholder=" Fecha de Nacimiento*" onclick="ocultarError();" onfocus="(this.type='date')" onblur="(this.type='text')">
            </div>
          </div>
              <div class="top-row">
            <div class="field-wrap">
              <label>
                Direccion*<span class="req"></span>
              </label>
                <input type="text" name="direccion" required autocomplete="off" />
            </div>
        
            <div class="field-wrap">
              <label>
                Cargo*<span class="req"></span>
              </label>
                <input type="text" name="cargo" required autocomplete="off"/>
            </div>
          </div>
              <div class="top-row">
            <div class="field-wrap">
              <label>
               Email*<span class="req"></span>
              </label>
                <input type="email" name="usuario" required autocomplete="off" />
            </div>
        
            <div class="field-wrap">
              <label>
                Contraseña*<span class="req"></span>
              </label>
                <input type="password" name="clave" required autocomplete="off"/>
            </div>
          </div>
              
          
          
          
          
          <button type="submit" class="button button-block">Registrar</button>
          
          </form>

        </div>
        
        <div id="login">   
          <h1>BIENVENIDO</h1>
          
          <form action="SvUsuario" method="post">
          
            <div class="field-wrap">
            <label>
                Email<span class="req">*</span>
            </label>
                <input type="email" name="usuario" required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Contraseña<span class="req">*</span>
            </label>
              <input type="password" name="clave" required autocomplete="off"/>
          </div>
          
          
          
          <button class="button button-block"/>Log In</button>
          
          </form>

        </div>
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->
<!-- partial -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script  src="Assets/JavaScript/script.js"></script>

</body>
</html>

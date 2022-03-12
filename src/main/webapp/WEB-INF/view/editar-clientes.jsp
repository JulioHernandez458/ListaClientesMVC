<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Editar Cliente</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />		
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
		<!-- Bootstrap CSS -->
    	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
	<body>
		
		<header id="main-header" class="py-2 bg-info text-white">
  <div class="container">
    <div class="row">
      <div class="col-md-6">
        <h1>
          <i class="fas fa-user"></i> Editar Cliente
        </h1>
      </div>
    </div>
  </div>
</header>
      

      <form:form method="POST" action="agregarCliente" modelAttribute="cliente"> 
        
    <!--  EDITAR -->

  <!--Botones-->
  <section id="actions" class="py-4 mb-4 bg-light">
    <div class="container">
      <div class="row">
        <div class="col-md-3">
        
        <c:url var="linkHome" value="/clientes/lista">	    </c:url>
			    
          <a href="${linkHome}" class="btn btn-light btn-block">
            <i class="fas fa-arrow-left"></i> Regresar al Inicio
          </a>
        </div>
        <div class="col-md-3">
          <button type="submit" class="btn btn-success btn-block">
            <i class="fas fa-check"></i> Guardar Cliente
          </button>
        </div>
        
        <c:url var="linkEliminar" value="/clientes/eliminarCliente">
			<c:param name="id" value="${cliente.id}"></c:param>
		</c:url>
			    
        <div class="col-md-3">
          <a href="${linkEliminar}" 
            onclick="return confirm('¿Desea Eliminar el Registro?');" 
            class="btn btn-danger btn-block">
            <i class="fas fa-trash"></i> Eliminar Cliente
          </a>
        </div>
      </div>
    </div>
  </section>

  <section id="details">
    <div class="container">
      <div class="row">
        <div class="col">
          <div class="card">
            <div class="card-header">
              <h4>Editar Cliente</h4>
            </div>
            <div class="card-body">
            
            <form:hidden path="id"></form:hidden>
              
               <div class="form-group">
            <label>Nombre</label>
            <form:input class="form-control" path="nombre"></form:input>
          </div>

          <div class="form-group">
            <label>Apellido</label>
            <form:input class="form-control" path="apellido"></form:input>
          </div>

          <div class="form-group">
            <label>Email</label>
            <form:input class="form-control" type="email" path="email"></form:input>
          </div>

   
              
              
              
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
    </form:form>



	</body>
	
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
      <!DOCTYPE html>
      <html>

      <head>
        <title>Lista de Clientes</title>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
      </head>

      <body>


        <header id="main-header" class="py-2 bg-info text-white">
          <div class="container">
            <div class="row">
              <div class="col-md-8">
                <h1>
                  <i class="fas fa-cog"></i> Control de Clientes
                </h1>
              </div>
            </div>
          </div>
        </header>



        <!-- Botones -->
        <section id="actions" class="py-4 mb-4 bg-light">
          <div class="container">
            <div class="row">
              <div class="col-md-3">
                <a href="#" class="btn btn-primary btn-block" data-toggle="modal" data-target="#agregarClienteModal">
                  <i class="fas fa-plus"></i> Agregar Cliente
                </a>
              </div>
            </div>
          </div>
        </section>

        <!--Listado Clientes-->
        <section id="clientes">
          <div class="container">
            <div class="row">
              <div class="col-md-9">
                <div class="card">
                  <div class="card-header">
                    <h4>Listado de Clientes</h4>
                  </div>
                  <table class="table table-striped">
                    <thead class="thead-dark">
                      <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Email</th>
                        <th></th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="cliente" items="${clientes}">
                        <tr>
                          <td>${cliente.id}</td>
                          <td>${cliente.nombre}</td>
                          <td>${cliente.apellido}</td>
                          <td>${cliente.email}</td>

                          <c:url var="linkEditar" value="/clientes/editarCliente">
                            <c:param name="id" value="${cliente.id}"></c:param>
                          </c:url>

                          <td>
                            <a href="${linkEditar}" class="btn btn-secondary">
                              <i class="fas fa-angle-double-right"></i> Editar
                            </a>
                          </td>
                        </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
              <!--Tarjetas laterales-->
              <div class="col-md-3">
                <!-- 
        <div class="card text-center bg-danger text-white mb-3">
          <div class="card-body">
            <h3>Saldo Total</h3>
            <h4 class="display-4">
               Saldo total 
            </h4>
          </div>
        </div>
        -->

                <div class="card text-center bg-success text-white mb-3">
                  <div class="card-body">
                    <h3>Total Clientes</h3>
                    <h4 class="display-4">
                      <i class="fas fa-users"></i> ${total}
                    </h4>
                  </div>
                </div>
              </div>
              <!--Fin tarjetas laterales-->

            </div>
          </div>
        </section>


        <!--Agregar cliente modal-->
        <div class="modal fade" id="agregarClienteModal">
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
              <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Cliente</h5>
                <button class="close" data-dismiss="modal" #botonCerrar>
                  <span>&times;</span>
                </button>
              </div>



              <form:form method="POST" action="agregarCliente" modelAttribute="cliente">
                <div class="modal-body">

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



                  <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" value="">Guardar</button>
                  </div>

                </div>
              </form:form>
            </div>
          </div>



      </body>

      <!-- Optional JavaScript -->
      <!-- jQuery first, then Popper.js, then Bootstrap JS -->
      <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
      <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

      </html>
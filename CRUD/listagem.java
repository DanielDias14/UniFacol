<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!doctype html>
<html lang="en">
<head>
    <title>Cadastro de Clientes</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container">
<table class="table">
    <thead>
        <tr>
            <th>id</th>
            <th>Nome</th>
            <th>Email</th>
            <th>Telefone</th>
        </tr>
    </thead>
    <tbody>
        <%
            Connection conexao = null;
            Statement stmt = null;
            ResultSet resultado_clientes = null;
            try {
                String url = "http://localhost/phpmyadmin/index.php?route=/database/structure&db=javawork";
                String usuario = "localhost";
                String senha = "";
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexao = DriverManager.getConnection(url, usuario, senha);
                
                String buscar_clientes = "SELECT * FROM clientes";
                stmt = conexao.createStatement();
                resultado_clientes = stmt.executeQuery(buscar_clientes);
                
                while (resultado_clientes.next()) {
                    int id = resultado_clientes.getInt("id");
                    String nome = resultado_clientes.getString("nome");
                    String email = resultado_clientes.getString("email");
                    String telefone = resultado_clientes.getString("telefone");
        %>
        
        <tr>
            <td scope="row"> <%= id %></td>
            <td><input type="text" name="nome" value="<%= nome %>"></td>
            <td><input type="text" name="email" value="<%= email %>"></td>
            <td><input type="text" name="telefone" value="<%= telefone %>"></td>
            <td>   
                <form action="excluir.jsp" method="post">
                    <input type="hidden" name="id" value="<%= id %>">
                    <input type="submit" value="Excluir">
                </form>
            </td>
        </tr>
        
        <%
                }
            } catch (Exception e) {
                out.println("<tr><td colspan='5'>Erro ao buscar clientes: " + e.getMessage() + "</td></tr>");
            } finally {
                if (resultado_clientes != null) resultado_clientes.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            }
        %>
        
        <tr> 
            <form action="cadastro.jsp" method="post">
                <td></td>
                <td><input type="text" name="nome"></td>
                <td><input type="text" name="email"></td>
                <td><input type="text" name="telefone"></td>
                <td><input type="submit" value="Cadastro"></td>
            </form>
        </tr>
    </tbody>
</table>
</div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
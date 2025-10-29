<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="conexao.jsp" %>

<%
    String nome = request.getParameter("nome");
    String email = request.getParameter("email");
    String telefone = request.getParameter("telefone");
    
    if (nome != null && !nome.trim().isEmpty() && 
        email != null && !email.trim().isEmpty() && 
        telefone != null && !telefone.trim().isEmpty()) {
        
        if (conexao != null) {
            PreparedStatement pstmt = null;
            try {
                String sql = "INSERT INTO clientes (nome, email, telefone) VALUES (?, ?, ?)";
                pstmt = conexao.prepareStatement(sql);
                pstmt.setString(1, nome);
                pstmt.setString(2, email);
                pstmt.setString(3, telefone);
                
                int linhasAfetadas = pstmt.executeUpdate();
                
                if (linhasAfetadas > 0) {
                } else {
                    out.println("Nenhum registro inserido.");
                }
            } catch (Exception e) {
                out.println("Erro ao cadastrar cliente: " + e.getMessage());
            } finally {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
        } else {
            out.println("Erro: Não foi possível conectar ao banco.");
        }
    } else {
        out.println("Preencha todos os campos obrigatórios.");
    }

    response.sendRedirect("listagem.jsp");
%>

<!doctype html>
<html lang="en">
<head>
    <title>Cadastro</title>
</head>
<body>
    <p>Cliente cadastrado com sucesso! Redirecionando...</p>
</body>
</html>
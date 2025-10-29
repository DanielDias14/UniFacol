<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="conexao.jsp" %>

<%
    String idStr = request.getParameter("id");
    String nome = request.getParameter("nome");
    String email = request.getParameter("email");
    String telefone = request.getParameter("telefone");
    

    if (idStr != null && !idStr.trim().isEmpty() && 
        nome != null && !nome.trim().isEmpty() && 
        email != null && !email.trim().isEmpty() && 
        telefone != null && !telefone.trim().isEmpty()) {
        
        int id = Integer.parseInt(idStr);
        
        if (conexao != null) {
            PreparedStatement pstmt = null;
            try {
                String sql = "UPDATE clientes SET nome = ?, email = ?, telefone = ? WHERE id = ?";
                pstmt = conexao.prepareStatement(sql);
                pstmt.setString(1, nome);    
                pstmt.setString(2, email); 
                pstmt.setString(3, telefone);
                pstmt.setInt(4, id);
                
                int linhasAfetadas = pstmt.executeUpdate();
                
                if (linhasAfetadas > 0) {
                } else {
                    out.println("Nenhum registro atualizado. ID pode não existir.");
                }
            } catch (Exception e) {
                out.println("Erro ao atualizar cliente: " + e.getMessage());
            } finally {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
        } else {
            out.println("Erro: Não foi possível conectar ao banco.");
        }
    } else {
        out.println("Parâmetros inválidos ou incompletos.");
    }
    
    response.sendRedirect("listagem.jsp");
%>

<!doctype html>
<html lang="en">
<head>
    <title>Atualização</title>
</head>
<body>
    <p>Cliente atualizado com sucesso! Redirecionando...</p>
</body>
</html>
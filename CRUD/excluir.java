<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="conexao.jsp" %>

<%
    String idStr = request.getParameter("id");
    if (idStr != null && !idStr.trim().isEmpty()) {
        int id = Integer.parseInt(idStr);
        
        if (conexao != null) {
            PreparedStatement pstmt = null;
            try {

                String sql = "DELETE FROM clientes WHERE id = ?";
                pstmt = conexao.prepareStatement(sql);
                pstmt.setInt(1, id);  
                pstmt.executeUpdate();  
                
            } catch (Exception e) {
                out.println("Erro ao excluir cliente: " + e.getMessage());
            } finally {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
        } else {
            out.println("Erro: Não foi possível conectar ao banco.");
        }
    } else {
        out.println("ID inválido.");
    }
        response.sendRedirect("cadastro.jsp"); 
%>

<!doctype html>
<html lang="en">
<head>
    <title>Exclusão</title>
</head>
<body>
    <p>Cliente excluído com sucesso! Redirecionando...</p>
</body>
</html>
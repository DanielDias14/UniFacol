<%@ page import="java.sql.*" %>
<%
    String host = "localhost";
    String user = "root";
    String password = "";
    String bd_name = "javawork";
    
    String url = "jdbc:mysql://" + host + ":3306/" + bd_name;
    
    Connection conexao = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        conexao = DriverManager.getConnection(url, user, password);
        
    } catch (Exception e) {
        out.println("Connection failed: " + e.getMessage());
        conexao = null; 
    }
%>
<%@page import="br.com.fabricadeprogramador.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de usuários</title>
</head>
<body>
	<%
			List<Usuario> lista = (List<Usuario>)request.getAttribute("lista");
		%>
	<table border= 2>
		<tr>
			<td>ID</td>
			<td>Nome</td>
		</tr>	
		<%	for(Usuario u : lista){%>
		<tr>
			<td><%=u.getId() %></td>
			<td><%=u.getNome() %></td>
		<%}%>
	</table>
</body>
</html>
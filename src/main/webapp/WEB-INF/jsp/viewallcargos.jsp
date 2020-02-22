<%@ page import="ru.epam.javacore.lesson_24_db_web.homework.cargo.domain.Cargo" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.epam.javacore.lesson_24_db_web.homework.application.serviceholder.ServiceHolder" %><%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2/10/2020
  Time: 8:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<%
    List<Cargo> cargos = (List<Cargo>) request.getAttribute("allCargos");
    for (int i = 0; i < cargos.size(); i++) { %>


     <h2><%=cargos.get(i).getName()%></h2>


<% } %>

<%="lhlhlih"%>
</body>
</html>

<%-- 
    Document   : CustomerMain
    Created on : Dec 30, 2013, 9:58:35 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 
    <%--Header--%>
    <%@include file="../WEB-INF/jspf/Cusheader.jspf" %>
    <%--/Header--%>
    <%--Nav--%>
    
    <div id="navigation">
        <%@include file="../WEB-INF/jspf/CusNav.jspf" %>
        </div>
    
    <%--/Nav--%>
    
    <%--Body--%>
    
    <div id="main">
        <h1>Customer Main</h1>
        <p>Good to see you ${Customer.firstName}</p>
    </div>
    
    <%--/Body--%>
    <%@include file="../WEB-INF/jspf/CusFooter.jspf" %>
    
</html>

<%-- 
    Document   : index
    Created on : Jul 7, 2016, 10:42:50 AM
    Author     : pavel
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <%@include file="/WEB-INF/jspf/css.jspf" %>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.menu.jspf" %>
        
        <div class="container">
            <div class="page-header">
                <h1>Shop Off-Line</h1>
            </div>

            <div class="row">
                <div class="col-md-3 list-group">
                    <c:forEach items="${brandList}" var="brand">

                        <a href="${pageContext.servletContext.contextPath}/?brand_id=${brand.id}"
                           class="list-group-item ${brand.id == param.brand_id ? 'active' : ''}">
                            ${brand.name}
                        </a>

                    </c:forEach>

                </div>
                <div class="col-md-9">
                    
                        <table class="table table-striped table-hover">
                            <thead>
                             
                            <th>#</th>
                            <th>Brand</th>
                            <th>Model</th>
                            <th>Cost</th>
                            <th>Price</th>
                            
                            </thead>
                            <tbody>
                                <c:forEach items="${phoneList}" var="phone">
                                    <tr>
                                        
                                        <td>${phone.id}</td>
                                        <td>${phone.brand.name}</td>
                                        <td>${phone.model}</td>
                                        <td><fmt:formatNumber value="${phone.cost}" type="currency" currencySymbol="$" minFractionDigits="2" maxFractionDigits="2" /></td>
                                        <td><fmt:formatNumber value="${phone.cost + 100}"  type="currency" currencySymbol="$" minFractionDigits="2" maxFractionDigits="2" /></td>
                                        
                                    </tr>
                                </c:forEach>
                            </tbody>
                            
                        </table>
                    
                </div>
            </div>
            <%@include file="/WEB-INF/jspf/js.jspf" %>
    </body>
</html>

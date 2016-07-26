<%-- 
    Document   : brand.edit
    Created on : Jul 18, 2016, 2:24:13 PM
    Author     : user
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/admin/header.menu.jspf" %>
        <div class="container">
            <h1>ADD NEW PHONE</h1>

            <div class="panel panel-blue margin-bottom-40">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-tasks"></i> Basic Form</h3>
                </div>
                <div class="panel-body">
                    <form method="post" class="margin-bottom-40">

                        <div class="form-group">
                            <label>name:</label>
                            <input class="form-control" type="text" name="name" value="${foundBrand.name}" />
                        </div>
                        <div class="form-group">
                            <label>country:</label>
                            <input class="form-control" type="text" name="country" value="${foundBrand.country}" />
                        </div>
                        <div class="form-group">
                            <div  class="col-lg-2"> <a href="${pageContext.servletContext.contextPath}/admin/brands">&lt; back to table</a></div>
                            <div  class="col-lg-2"> <button type="submit" class="btn-u btn-u-blue">Save</button></div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

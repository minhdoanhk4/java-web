<%-- 
    Document   : createAccount
    Created on : Nov 3, 2025, 7:44:05 AM
    Author     : msi2k
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <h1>
            Create Account
        </h1>
        <form action="DispatcherServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
            Username* <input type="text" name="txtUsername" 
                             value="${param.txtUsername}" />(6 - 20 characters).<br/>
                <c:if test="${not empty errors.usernameLenghtErr}">
                    <font color ="red">
                    ${errors.usernameLenghtErr}
                    </font><br/>
                </c:if>
                <c:if test="${not empty errors.usernameIsExitErr}">
                    <font color ="red">
                    ${errors.usernameIsExitErr}
                    </font><br/>
                </c:if>
            Password* <input type="password" name="txtPassword" value="" />(6 - 30 characters).<br/>
                <c:if test="${not empty errors.passwordLenghtErr}">
                    <font color ="red">
                    ${errors.passwordLenghtErr}
                    </font><br/>
                </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /><br/>
                <c:if test="${not empty errors.confirmNotMatched}">
                    <font color ="red">
                    ${errors.confirmNotMatched}
                    </font><br/>
                </c:if>
            Full  name* <input type="text" name="txtFullname" 
                               value="${param.txtFullname}" />(2 - 50 characters).<br/>
                <c:if test="${not empty errors.fullNameLenghtErr}">
                    <font color ="red">
                    ${errors.fullNameLenghtErr}
                    </font><br/>
                </c:if>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>

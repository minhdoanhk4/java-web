<%-- 
    Document   : search
    Created on : Oct 13, 2025, 8:46:15 AM
    Author     : msi2k
--%>

<%-- <%@page import="minhda.controller.MainController"%>
<%@page import="minhda.registration.RegistrationDTO"%>
<%@page import="java.util.List"%> <%-- jsp directive--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <%-- SỬ DỤNG JSTL ĐỂ KIỂM TRA SESSION --%>
    <c:if test="${empty sessionScope.USERINFO}">
        <%-- Nếu đối tượng USERINFO không tồn tại trong session (đã logout), chuyển hướng về trang login --%>
        <c:redirect url="login.html"/>
    </c:if>
    <body>
        <%-- Welcome --%>
        <font color = "red">
        Welcome, ${sessionScope.USERINFO.lastname}
        </font>
        <%-- Logout --%>
        <c:url var="logoutLink" value="DispatcherServlet" >
            <c:param name="btAction" value="logout"/>
        </c:url>
        <a href="${logoutLink}">Click here to Sign out</a>

        <h1>Search Page</h1>
        <form action="DispatcherServlet">
            <input type="text" name="txtSearchValue" 
                   value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="btAction" />
        </form><br/>
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Full name</th>
                        <th>Role</th>
                    </tr>
                </thead>

                <c:forEach var="blo" items="${result}" varStatus="counter">
                    <form action="DispatcherServlet" method="POST">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${blo.username}
                                <!-- create hidden text box -->
                                <input type="hidden" name="txtUsername" 
                                       value="${blo.username}" />
                            </td>
                            <td>
                                <input type="text" name="txtPassword" 
                                       value="${blo.password}" />

                            </td>
                            <td>
                                ${blo.lastname}
                            </td>
                            <td>
                                <input type="checkbox" name="chkAdmin" value="ON" 
                                       <c:if test = "${blo.isAdmin}">
                                           checked =" checked";
                                       </c:if>       
                                       />
                                <!-- check box phai dc check thi parameter moi ton tai -->
                            </td>
                            <td>
                                <c:url var="deleteLink" value="DispatcherServlet">
                                    <c:param name="btAction" value="delete"/>
                                    <c:param name="pk" value="${blo.username}"/>
                                    <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>
                                </c:url>
                                <a href="${deleteLink}">Delete</a>
                            </td>
                            <td>
                                <input type="submit" value="Update" name="btAction" />
                                <input type="hidden" value="${param.txtSearchValue}" 
                                       name="lastSearchValue" />
                            </td>
                        </tr>
                    </form>

                </c:forEach>

            </table>

        </c:if>
        <c:if test="${empty result and not empty searchValue}">
            <h2>
                <font color = "red">
                Not found!!!!!
                </font>
            </h2>
        </c:if>
        <%-- <form action="MainController">
             <input type="text" name="txtSearchValue" 
                    value="<%= request.getParameter("txtSearchValue")%>" />
             <input type="submit" value="Search" name="btAction" />
         </form><br/>

        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
                if (result != null) {//search found
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full name</th>
                    <th>Role</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String url = "MainController "
                                + "?btAction=delete "
                                + "& "
                                + "pk=" + dto.getUsername() 
                                + "&lastSearchValue=" + searchValue;
                %>
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= dto.getUsername()%>
                    </td>
                    <td>
                        <%= dto.getPassword()%>
                    </td>
                    <td>
                        <%= dto.getFullname()%>
                    </td>
                    <td>
                        <%= dto.getRole()%>
                    </td>
                    <td>
                        <a href="<%= url %>">Delete</a>
                    </td>

                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <%
        } else {//not found
        %>
        <h2>
            <font color="red"> 
            No data
            </font>
        </h2>
        <%
                }
            }//searchValue must be valid
        %> --%>

    </body>
</html>

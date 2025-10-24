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
    <body>
        <h1>Search Page</h1>
        <form action="DispatcherServlet">
            <input type="text" name="txtSearchValue" 
                   value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="btAction" />
        </form><br/>
        <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
        <c:if test="${not empty result}">
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

                <c:forEach var="dto" items="${result}" varStatus="counter">
                    <form action="DispatcherServlet" method="POST">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.username}
                                <!-- create hidden text box -->
                                <input type="hidden" name="txtUsername" 
                                       value="${dto.username}" />
                            </td>
                            <td>
                                <input type="text" name="txtPassword" 
                                       value="${dto.password}" />

                            </td>
                            <td>
                                ${dto.fullname}
                            </td>
                            <td>
                                <input type="checkbox" name="chkAdmin" value="ON" 
                                       <c:if test = "${dto.role}">
                                           checked =" checked";
                                       </c:if>       
                                       />
                                <!-- check box phai dc check thi parameter moi ton tai -->
                            </td>
                            <td>
                                <c:url var="deleteLink" value="DispatcherServlet">
                                    <c:param name="btAction" value="delete"/>
                                    <c:param name="pk" value="${dto.username}"/>
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
        <c:if test="${empty result}">
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

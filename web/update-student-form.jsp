<%--
  Created by IntelliJ IDEA.
  User: NgenziDanny
  Date: 07/10/2018
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Student</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
Hello ... form placeHolder

<div id="wrapper">
    <div id="header">
        <h2>FooBar University</h2>
    </div>
</div>
<div id="container">

    <h3> UPDATE Student</h3>

    <form action="StudentControllerServlet" method="GET">
        <input type="hidden" name="command" value="UPDATE">
        <input type="hidden" name="studentId" value="${The_Student.id}">

        <table>
            <tbody>
                <tr>

                    <td><label>First Name:</label></td>
                    <td><input type="text" name="firstName"
                    value="${The_Student.firstName}"/></td>

                </tr>
                <tr>

                    <td><label>Last Name:</label></td>
                    <td><input type="text" name="lastName"
                               value="${The_Student.lastName}"/></td>

                </tr>
                <tr>

                    <td><label>email:</label></td>
                    <td><input type="text" name="email"
                               value="${The_Student.email}"/></td>

                </tr>
                <tr>

                    <td><label>First Name:</label></td>
                    <td><input type="submit" value="Update" class="save"/></td>

                </tr>



            </tbody>

        </table>
    </form>

    <div style="clear: both;"></div>

    <p>
        <a href="StudentControllerServlet">Back to List</a>
    </p>

</div>


</body>
</html>

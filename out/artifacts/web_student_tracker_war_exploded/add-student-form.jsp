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
    <title>Add Student</title>
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

    <h3> Add Student</h3>

    <form action="StudentControllerServlet" method="GET">
        <input type="hidden" name="command" value="ADD">

        <table>
            <tbody>
                <tr>

                    <td><label>First Name:</label></td>
                    <td><input type="text" name="firstName"/></td>

                </tr>
                <tr>

                    <td><label>Last Name:</label></td>
                    <td><input type="text" name="lastName"/></td>

                </tr>
                <tr>

                    <td><label>email:</label></td>
                    <td><input type="text" name="email"/></td>

                </tr>
                <tr>

                    <td><label>First Name:</label></td>
                    <td><input type="submit" value="Save" class="save"/></td>

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

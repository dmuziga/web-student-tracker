package com.love2code.web.jdbc;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "TestServlet", urlPatterns = "/TestServlet")
public class TestServlet extends HttpServlet {

    //Define dataSource/Connection pool for Resource Injection

    @Resource(name="jdbc/web_student_tracker")
    private DataSource dataSource;



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //step 1: set up the PrintWriter

        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");

        //step 2 : Get a connection to the Database

        Connection myConn= null;
        Statement myStmt= null;
        ResultSet myRs = null;

        try{
            myConn=dataSource.getConnection();


            //Step 3: Create a SQL Statements
            String sql ="select * from student";

            //Step 4: Create a SQL Statements
            myStmt = myConn.createStatement();

            //Step 5: Execute the result set

            myRs = myStmt.executeQuery(sql);

            while (myRs.next()){

                String email = myRs.getString("email");
                out.println(email);
            }


        }catch (Exception exc){

            exc.printStackTrace();

        }

    }
}

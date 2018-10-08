package com.love2code.web.jdbc;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDbUtil {

    private DataSource dataSource;


    public StudentDbUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public List<Student> getStudents()throws Exception{

        List<Student> students= new ArrayList<>();

        Connection myconn= null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try{
            //get a connection
            myconn = dataSource.getConnection();
            //write a sql statement
            String sql = "select * from student order by last_name";
            myStmt = myconn.createStatement();
            //execute the sql

            myRs= myStmt.executeQuery(sql);


            //process result set

            while(myRs.next()){

                //retrieve data from result

                int id = myRs.getInt("id");
                String fistName = myRs.getString("first_name");
                String LastName = myRs.getString("last_name");
                String email= myRs.getString("email");

                //create a new object

                Student student = new Student(id,fistName,LastName,email);

                //add it to the list of Students

                students.add(student);


            }

            //close JDBC object

            myconn = dataSource.getConnection();

        }finally {

            //close JDBC Objects

            close(myconn,myStmt,myRs);

        }


        return students;

    }
    private void close (Connection myconn, Statement myStmt, ResultSet myRs){

        try {
            if(myRs !=null){

                myRs.close();
            }
            if (myStmt !=null){

                myStmt.close();
            }

            if (myconn !=null){

                myconn.close();
            }
        }
        catch (Exception exc){

            exc.printStackTrace();
        }



    }


    public void addStudent(Student theStudent) throws Exception {

        //get all the Connection

        Connection myconn= null;
        PreparedStatement myStmt = null;

        try {

            myconn=dataSource.getConnection();

            //create sql for insert

            String sql = "insert into student"
                    + "(first_name, last_name,email)"
                    + "values (?,?,?)";

            myStmt = myconn.prepareStatement(sql);

            //set the param values for the Student
            myStmt.setString(1,theStudent.getFirstName());
            myStmt.setString(2,theStudent.getLastName());
            myStmt.setString(3,theStudent.getEmail());


            //execute sql insert
            myStmt.execute();



            //clean up JDBC objects

        }finally {


            close(myconn,myStmt,null);
        }




    }

    public Student getStudent(String theStudentId) throws Exception {

        Student theStudent=null;
        Connection myConnn=null;

        PreparedStatement myStmt =null;
        ResultSet myRs=null;

        int StudentId;

        try {

            //convert student id to int

            StudentId = Integer.parseInt(theStudentId);


            //get connection to database
            myConnn = dataSource.getConnection();

            //create sql to get selected student

            String sql = "select * from Student where id=?";


            //create prepared statement
            myStmt = myConnn.prepareStatement(sql);

            //set params

            myStmt.setInt(1,StudentId);

            //execute statement

            myRs = myStmt.executeQuery();

            //retrieve data from results set row

            if (myRs.next()){

                String firstName = myRs.getString("first_name");
                String lastName = myRs.getString("last_name");
                String email = myRs.getString("email");

                //use the StudentId during Construction

                theStudent = new Student(StudentId,firstName,lastName, email);
            }else{

                throw new Exception("Could not find student id:" + StudentId);
            }

            return  theStudent;
        }finally {

            close(myConnn,myStmt,myRs);
        }




    }

    public void updateStudent(Student theStudent) throws Exception {

        // get  db the connection

        Connection myconn= null;
        PreparedStatement mystmt = null;

        try {



            //get db connection
            myconn = dataSource.getConnection();

            //SQL Query

            String sql = "update student "
                       + "set first_name=?, last_name=?, email=? "
                       + "where id=? ";

            mystmt = myconn.prepareStatement(sql);


            //get the Student id

           /* int studentId = theStudent.getId();*/

            mystmt.setString(1,theStudent.getFirstName());
            mystmt.setString(2,theStudent.getLastName());
            mystmt.setString(3,theStudent.getEmail());
            mystmt.setInt(4,theStudent.getId());

            //execute the sql statement

            mystmt.execute();

        }finally {
            close(myconn,mystmt,null);
        }




        // prepare the sql command
        // execute the query
        //close the connection



    }

    public void deleteStudent(int theStudentId) throws Exception {

        //get the Database connection

        Connection myConn = null;
        PreparedStatement myst= null;


        try{


            myConn = dataSource.getConnection();

            String sql = "delete from Student where id =?";

            myst = myConn.prepareStatement(sql);

            myst.setInt(1,theStudentId);

            myst.execute();


        }finally {
            close(myConn,myst,null);
        }






    }
}

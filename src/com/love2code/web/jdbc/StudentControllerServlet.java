package com.love2code.web.jdbc;

import javax.annotation.Resource;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentControllerServlet", urlPatterns = "/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {

    private StudentDbUtil studentDbUtil;

    @Resource(name="jdbc/web_student_tracker")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        //create our student db util ... and pass in the conn pool /datasource

        try {
            studentDbUtil = new StudentDbUtil(dataSource);
        }catch (Exception exc){
            throw new ServletException(exc);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //list the Students .. in MVC fashion
        try {

            //read the command parameter

            String theCommand = request.getParameter("command");


            //if the command is missing , then default to listing students

            if (theCommand == null){

                theCommand= "LIST";
            }

            switch (theCommand){

                case "LIST":
                    listStudents(request, response);
                    break;

                case "ADD":
                    addStudent(request,response);
                    break;
                case "LOAD":
                     loadStudent(request,response);
                             break;
                case "UPDATE":
                      updateStudent(request, response);
                      break;

                case "DELETE":
                    deleteStudent(request, response);
                    break;


                    default:
                        listStudents(request,response);

            }


        } catch (Exception e) {
            throw new ServletException(e);
        }


    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {


        //get the student Id

        int theStudentId = Integer.parseInt(request.getParameter("studentId"));

        // Delete the Student
        studentDbUtil.deleteStudent(theStudentId);

        listStudents(request, response);

    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //read student info from the form data

        int StudentId = Integer.parseInt(request.getParameter("studentId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");


        //create a new Student object

        Student theStudent = new Student(StudentId, firstName, lastName, email);

        //perform update on database

        studentDbUtil.updateStudent(theStudent);

        //send them back to the List

        listStudents(request,response);





    }

    private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{

        //read student id from the form data

        String theStudentId = request.getParameter("studentId");



        //get student from database (db util)

        Student theStudent = studentDbUtil.getStudent(theStudentId);



        //place student in the request attribute

        request.setAttribute("The_Student", theStudent);

        //send to JSP page: update student-form.jsp

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/update-student-form.jsp");

        dispatcher.forward(request,response);






    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //read student info from the form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email   = request.getParameter("email");
        //create a new Student Object
          Student theStudent = new Student(firstName, lastName, email);
        //add the Student to the database

        studentDbUtil.addStudent(theStudent);

        //send back to main page (the Student List)

        listStudents(request, response);



    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //get students from db utils
        List<Student> students = studentDbUtil.getStudents();

        //add students to the request

        request.setAttribute("STUDENT_LIST", students);


        //send to JSP page (view)

        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
        dispatcher.forward(request,response);



    }
}

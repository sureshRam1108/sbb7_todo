package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Register;
import dao.ToDoDAO;
import dao.ToDoDAOImpl;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	public void doPost(
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		// read form data
		String fname=request.getParameter("fname").trim();
		String lname=request.getParameter("lname").trim();
		String email=request.getParameter("email").trim();
		String pass=request.getParameter("pass").trim();
		long mobile=Long.parseLong(request.getParameter("mobile").trim());
		String address=request.getParameter("address").trim();
		
		// call the dao method
		ToDoDAO dao=ToDoDAOImpl.getInstance();
		Register r=new Register(0,fname,lname,email,pass,mobile,address);
		int i=dao.register(r);
		if(i>0)
			response.sendRedirect("Login.html");
		else
			out.println("registration Failed");
	}
}

Login.html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <!-- Bootstrap CSS Link -->
    <link href="bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Login Form</h2>
        <form method="post" action="./LoginServlet">
            <div class="row mb-3">
                <label for="email" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
            </div>
            <div class="row mb-3">
                <label for="pass" class="col-sm-2 col-form-label">Pass</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="pass" name="pass" required>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-sm-2"></div>
                <div class="col-sm-10">
                    <button type="submit" class="btn btn-primary" name="submit">Login</button>
                    <button type="reset" class="btn btn-secondary" name="reset">Clear</button>
                </div>
            </div>
        </form>
    </div>
	<div class="text-center mt-3">
        <p>New User? <a href="Register.html">SignUp</a></p>
    </div>
    <!-- Bootstrap JS & Popper.js Link -->
    <script src="popper.min.js"></script>
    <script src="bootstrap.min.js"></script>
</body>
</html>

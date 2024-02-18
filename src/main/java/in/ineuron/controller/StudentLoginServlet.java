package in.ineuron.controller;

import java.io.IOException;
import java.sql.SQLException;

import in.ineuron.dto.Student;
import in.ineuron.service.IStudentService;
import in.ineuron.serviceFactory.ServiceFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*
 * This servlet handles all login related activities of the student
 * 
 * */
@WebServlet(urlPatterns = "/studentLogin",loadOnStartup = 1)
public class StudentLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HttpSession session;
	private static RequestDispatcher rd;
	private static IStudentService studentService;

	static {
		System.out.println("Student Login Servlet.class file is loading");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private static void doProcess(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("StudentLoginServlet:: " + request.getRequestURI());

		Student student = null;
		String stdPassword = null;
		String target = null;
		String errorMessage = null;

		try {
			String mail = request.getParameter("stdMail");
			String password = request.getParameter("studentPassword");


			studentService = ServiceFactory.getStudentServie();
			if (studentService != null) {
				student = studentService.selectStudentDataByMailId(mail);

				if (student != null && student.getStatus() != null) {

					if (student.getStatus().equalsIgnoreCase("active")) {
						stdPassword = student.getSpassword();

						if (password.equals(stdPassword)) {
							session = request.getSession();
							session.setAttribute("studentData", student);
							target = "./studentHome.jsp";
						} else {
							errorMessage = "Invalid Password";
							request.setAttribute("errorMessage", errorMessage);
							target = "./studentLogin.jsp";
						}
					}
				}else {
						errorMessage = "Invalid username";
						request.setAttribute("errorMessage", errorMessage);
						target = "./studentLogin.jsp";
					}

				
			}else {
					errorMessage = "Some issue has occured please try after some time";
					request.setAttribute("errorMessage", errorMessage);
					target = "./studentLogin.jsp";
				}
				rd = request.getRequestDispatcher(target);
				rd.forward(request, response);
			
		} catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

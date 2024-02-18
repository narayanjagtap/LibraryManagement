package in.ineuron.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = "/student/*")
public class StudentFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher rd;

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		System.out.println("In Student Filter::" + request.getRequestURI());

		if (request.getRequestURI().endsWith("searchStudentById")
				|| request.getRequestURI().endsWith("toPayFineSearchStudentById")) {

			String targetpage = null;
			String studentID = request.getParameter("studentId");
			if (studentID != null) {
				chain.doFilter(request, response);
			} else {
				request.setAttribute("error", "Please enter student Id");
				targetpage = "../nullValue.jsp";

				rd = request.getRequestDispatcher(targetpage);
				rd.forward(request, response);
			}
		} else if (request.getRequestURI().endsWith("toPayFine")) {
			String fineAmount = request.getParameter("fineAmount");
			String targetpage = null;
			if (fineAmount != null) {
				chain.doFilter(request, response);
			} else {
				request.setAttribute("error", "Please enter valid data");
				targetpage = "../nullValue.jsp";

				rd = request.getRequestDispatcher(targetpage);
				rd.forward(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}

	}

	public void destroy() {
	}
}

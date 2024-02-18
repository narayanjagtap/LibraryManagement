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

@WebFilter("/adminLogin")
public class AdminLoginFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	private  RequestDispatcher rd;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("Admin Login Filter..");
		String mail = request.getParameter("adminMail");
		String password = request.getParameter("adminPassword");

		if (mail != null && password != null && mail.endsWith("ineuron.ai")) {
			chain.doFilter(request, response);
		} else {
			String errorMessage = "Invalid UserName or Password";
			request.setAttribute("errorMessage", errorMessage);
			String target = "./adminLogin.jsp";

			rd = request.getRequestDispatcher(target);
			rd.forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}

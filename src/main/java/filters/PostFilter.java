package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request,
											 ServletResponse response,
											 FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		if(httpServletRequest.getMethod().equals("POST")){
			chain.doFilter(request,response);
		}
		else {
			((HttpServletResponse)response).sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

	@Override
	public void destroy() {

	}
}

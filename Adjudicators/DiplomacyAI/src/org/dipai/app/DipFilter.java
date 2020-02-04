package org.dipai.app;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.dsrg.soenea.application.filter.PermalinkFilter;

@WebFilter( 
		urlPatterns="/Dip/*",
		dispatcherTypes={DispatcherType.REQUEST}
)
public class DipFilter extends PermalinkFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		super.doFilter(request, response, chain);
		request.getServletContext().getRequestDispatcher("/DiplomacyAIServlet").include(request, response);
	}
}

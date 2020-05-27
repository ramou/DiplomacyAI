package org.dipai.app;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dipai.dom.model.game.Game;
import org.dipai.dom.model.game.mapper.GameOutputMapper;
import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.application.servlet.dispatcher.HttpServletHelper;
import org.dsrg.soenea.application.servlet.impl.AuthorizationException;
import org.dsrg.soenea.application.servlet.impl.SmartDispatcherServlet;
import org.dsrg.soenea.application.servlet.service.DispatcherFactory;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.domain.user.User;
import org.dsrg.soenea.domain.user.mapper.UserOutputMapper;
import org.dsrg.soenea.service.logging.Logging;
import org.dsrg.soenea.uow.MapperFactory;
import org.dsrg.soenea.uow.UoW;


@WebServlet("/DiplomacyAIServlet")
public class DipFC extends SmartDispatcherServlet {

	private static final Log log = LogFactory.getLog(SmartDispatcherServlet.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	
	protected String getDefaultCommand() {
		return "org.dsrg.soenea.application.servlet.dispatcher.impl.DefaultCommand";
	}

	@Override
	public void init(final ServletConfig config) throws ServletException {
	    super.init(config);
	    this.
	    getServletContext().setAttribute("PROVINCES_URL_DEFINITION", "https://raw.githubusercontent.com/ramou/DiplomacyAI/master/province.txt");
	    getServletContext().setAttribute("COUNTRIES_DEFINITION", new String[] {"italy", "france", "austria", "germany", "england", "turkey", "russia"});
		setupUoW();
	}
	
	 public static void setupUoW() {
		 MapperFactory myDomain2MapperMapper = new MapperFactory();
		 myDomain2MapperMapper.addMapping(User.class, UserOutputMapper.class);
		 myDomain2MapperMapper.addMapping(Game.class, GameOutputMapper.class);
		 
		 //TODO: Add other mappings
		 
		 UoW.initMapperFactory(myDomain2MapperMapper);
	 } 
	

	/** 
	 * Processes requests for both HTTP <code>GET</code> and 
	 * <code>POST</code> methods.
	 * 
	 * All this does is attempt to create a FrontCommand and execute it. 
	 * The only time this method does anything itself besides initiating 
	 * the above actions is when there is a problem, at which point it will
	 * forward to the Error JSP page.
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void processRequest(
		final HttpServletRequest request,
		final HttpServletResponse response)
		throws ServletException, java.io.IOException //
	{
		preProcessRequest(request, response);
		
		helper.set(new HttpServletHelper(request));
		
		helper.get().logParameters();
		try {
			Logging.logDebug("RequestURI: " + request.getRequestURI());
			
			request.setCharacterEncoding("UTF-8");

			helper.get().setRequestAttribute("currentTimeMillis", System.currentTimeMillis());		
			helper.get().setRequestAttribute("ip", 0); // TODO: put a real ip address in here
			helper.get().setRequestAttribute("method", request.getMethod());
			String commandName = getCommandName();
			
			Logging.log("Command: " + commandName);
			
			processAuthentication();
			
			IUser currentUser = (IUser)helper.get().getAttribute(org.dsrg.soenea.application.servlet.impl.RequestAttributes.CURRENT_USER);
						
			processAuthorization(request, commandName, currentUser);
			
			processDispatcher(request, response, commandName);

		} catch (final AuthorizationException e) {
			log.debug("", e);
			List<String> notifications = (List<String>)request.getAttribute("notifications");
			if(notifications == null) notifications = new Vector<String>();
			request.setAttribute("notifications", notifications);
			
			List<String> notify_fields = (List<String>)request.getAttribute("notify_fields");
			if(notify_fields == null) notify_fields = new Vector<String>();
			request.setAttribute("notify_fields", notify_fields);

			notify_fields.add("notifications");
			notifications.add(e.getMessage());		

			
			if ("XML".equalsIgnoreCase(request.getParameter("mode"))) {
				request.getRequestDispatcher(getXMLErrorTemplate()).forward(request, response);
			} else if ("JSON".equalsIgnoreCase(request.getParameter("mode"))) {
				request.getRequestDispatcher(getJSONErrorTemplate()).forward(request, response);
			} else {
				final String template = getErrorTemplate();
				request.getRequestDispatcher(template).forward(request, response);
			}
		} catch (final Exception exception) {
			Throwable e = exception;
			while (e.getCause() != null) e = e.getCause();
			Logging.logError(getClass().getName() + "processRequest");
			Logging.logError(exception);
			request.setAttribute("errorMessage", e.getMessage());
			request.setAttribute("exception", e);
			final String template = getErrorTemplate();
			request.getRequestDispatcher(template).include(request, response);
			
		}
		postProcessRequest(request, response);
	}

	private void processDispatcher(final HttpServletRequest request,
			final HttpServletResponse response, String commandName)
			throws Exception, ServletException, IOException {
		final Dispatcher d = DispatcherFactory.getInstance(commandName);
		d.init(request, response);
		d.execute();
	}

	protected String getXMLErrorTemplate() {return "/WEB-INF/jsp/fail.jsp";}	
	protected String getJSONErrorTemplate() {return "/WEB-INF/jsp/fail.jsp";}
	protected String getErrorTemplate() {return "/WEB-INF/jsp/fail.jsp";}
	protected String getMessageTemplate() {return "/WEB-INF/jsp/success.jsp";}
	protected String getMainTemplate() {return "/WEB-INF/jsp/success.jsp";}
}

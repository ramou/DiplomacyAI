package org.dipai.app.dispatcher.game;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dipai.dom.command.user.LoginCommand;
import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.application.servlet.impl.RequestAttributes;
import org.dsrg.soenea.domain.command.CommandException;

public class Create extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		LoginCommand c = new LoginCommand(myHelper);
		try {
			myRequest.getSession(true).invalidate();
			c.execute();
			myRequest.getSession(true).setAttribute(RequestAttributes.CURRENT_USER_ID, c.CurrentUser.getId());
			forward("/WEB-INF/jsp/success.jsp");
		} catch (CommandException e) {
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}

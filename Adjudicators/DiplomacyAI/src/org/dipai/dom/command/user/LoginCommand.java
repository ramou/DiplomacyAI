package org.dipai.dom.command.user;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.domain.user.mapper.UserInputMapper;

public class LoginCommand extends ValidatorCommand {

	@Source
	public String username;
	
	@Source
	public String password;
	
	@SetInRequestAttribute
	public IUser CurrentUser;
	
	public LoginCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		try {
			CurrentUser = UserInputMapper.find(username, password);
		} catch (Exception e) {
			e.printStackTrace();
			addNotification(e.getMessage());
			throw new CommandException(e);
		}
	}

}

package org.dipai.dom.command.user;

import java.util.ArrayList;
import java.util.List;

import org.dipai.dom.model.role.RegisteredRole;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.role.IRole;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.domain.user.UserFactory;
import org.dsrg.soenea.domain.user.mapper.UserInputMapper;


public class RegisterCommand extends ValidatorCommand {

	@Source
	public String username;
	
	@Source
	public String password;
	
	@SetInRequestAttribute
	public IUser CurrentUser;
	
	public RegisterCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		try {
			System.out.println("ARGHHH!!");
			try{
				CurrentUser = UserInputMapper.find(username);
				String message = "User already exists!";
				throw new CommandException(message);
			} catch (MapperException e) {}
			
			List<IRole> roles = new ArrayList<IRole>();
			//roles.add(new GuestRole());
			roles.add(new RegisteredRole());

			CurrentUser = UserFactory.createNew(username, password, roles);			
		} catch (Exception e) {
			e.printStackTrace();
			addNotification(e.getMessage());
			throw new CommandException(e);
		}
	}

}

package org.dipai.dom.command.game;

import org.dipai.dom.command.DiplomacyCommand;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;

public class CreateCommand extends DiplomacyCommand {

	@Source
	public String name;
	
	public CreateCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		try {

			
		} catch (Exception e) {
			e.printStackTrace();
			addNotification(e.getMessage());
			throw new CommandException(e);
		}
	}

}

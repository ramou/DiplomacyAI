package org.dipai.dom.command;

import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.AttributeSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.IUser;

public abstract class DiplomacyCommand extends ValidatorCommand {

	public DiplomacyCommand(Helper helper) {
		super(helper);
	}

	@Source(sources= {AttributeSource.class})
	public IUser CurrentUser;

}

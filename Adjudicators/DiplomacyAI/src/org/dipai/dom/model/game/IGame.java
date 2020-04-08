package org.dipai.dom.model.game;

import org.dsrg.soenea.domain.interf.IDomainObject;
import org.dsrg.soenea.domain.user.IUser;

public interface IGame extends IDomainObject<Long>{

	String getName();

	void setName(String name);

	IUser getCreator();

	void setCreator(IUser creator);

	int getTurn();

	void setTurn(int turn);

}
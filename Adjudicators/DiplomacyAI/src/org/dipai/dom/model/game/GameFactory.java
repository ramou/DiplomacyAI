package org.dipai.dom.model.game;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.uow.UoW;

public class GameFactory {

	public static Game createNew(long id, long version, String name, IUser creator, int turn) throws SQLException, MapperException{
		Game c = new Game(id, version, name, creator, turn);
		UoW.getCurrent().registerNew(c);
		return c;
	}

	public static Game createClean(long id, long version, String name, IUser creator, int turn) throws SQLException, MapperException{
		Game c = new Game(id, version, name, creator, turn);
		UoW.getCurrent().registerClean(c);
		return c;
	}

}

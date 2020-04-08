package org.dipai.dom.model.game;

import java.sql.SQLException;

import org.dipai.dom.model.game.mapper.GameInputMapper;
import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.dsrg.soenea.domain.user.IUser;

public class GameProxy extends DomainObjectProxy<Long, Game> implements IGame{

	protected GameProxy(Long id) {
		super(id);
	}
	
	@Override
	public Long getId() {
		return getInnerObject().getId();
	}

	@Override
	public long getVersion() {
		return getInnerObject().getVersion();
	}

	@Override
	public void setVersion(long new_version) {
		getInnerObject().setVersion(new_version);
	}

	@Override
	public String getName() {
		return getInnerObject().getName();
	}

	@Override
	public void setName(String name) {
		getInnerObject().setName(name);
	}

	@Override
	public IUser getCreator() {
		return getInnerObject().getCreator();
	}

	@Override
	public void setCreator(IUser creator) {
		getInnerObject().setCreator(creator);
	}
	
	@Override
	public int getTurn() {
		return getInnerObject().getTurn();
	}

	@Override
	public void setTurn(int turn) {
		getInnerObject().setTurn(turn);
	}

	@Override
	protected Game getFromMapper(Long id) throws MapperException, DomainObjectCreationException {
		try {
			return GameInputMapper.find(getId());
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}


}

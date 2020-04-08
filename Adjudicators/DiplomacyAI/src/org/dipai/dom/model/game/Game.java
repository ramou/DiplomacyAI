package org.dipai.dom.model.game;

import org.dsrg.soenea.domain.DomainObject;
import org.dsrg.soenea.domain.user.IUser;

public class Game extends DomainObject<Long> implements IGame {
	private String name;
	private IUser creator;
	private int turn;


	public Game(Long id, Long version, String name, IUser creator, int turn) {
		super(id, version);
		this.name = name;
		this.creator = creator;
		this.turn = turn;
	}
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public IUser getCreator() {
		return creator;
	}
	@Override
	public void setCreator(IUser creator) {
		this.creator = creator;
	}
	
	@Override
	public int getTurn() {
		return turn;
	}

	@Override
	public void setTurn(int turn) {
		this.turn = turn;
	}

}

package org.dipai.dom.model.game.mapper;

import java.sql.SQLException;

import org.dipai.dom.model.game.Game;
import org.dipai.dom.model.game.tdg.GameTDG;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;


public class GameOutputMapper extends GenericOutputMapper<Long, Game>{

	public void delete(Game d) throws MapperException {
		try {
			int count = GameTDG.delete(d.getId(), d.getVersion());
			if(count == 0) throw new LostUpdateException("When trying to delete, Game with version: " + d.getVersion() + " and id: " + d.getId() + " was not found.");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MapperException(e);
		}

	}
	public void insert(Game d) throws MapperException {
		try {
			GameTDG.insert(d.getId(), d.getVersion(), 
					d.getName(), d.getCreator().getId(), d.getTurn());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MapperException(e);
		}
	}

	@Override
	public void update(Game d) throws MapperException {
		try {
			int count = GameTDG.update(d.getId(), d.getVersion(), 
					d.getName(), d.getCreator().getId(), d.getTurn());
			if(count == 0) throw new LostUpdateException("When trying to update, Game with version: " + d.getVersion() + " and id: " + d.getId() + " was not found.");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MapperException(e);
		}
	}

}


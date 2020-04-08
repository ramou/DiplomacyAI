package org.dipai.dom.model.game.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dipai.dom.model.country.Country;
import org.dipai.dom.model.game.Game;
import org.dipai.dom.model.game.GameFactory;
import org.dipai.dom.model.game.IGame;
import org.dipai.dom.model.game.tdg.GameFinder;
import org.dipai.dom.model.game.tdg.PlaysFinder;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.dsrg.soenea.domain.producer.IdentityBasedProducer;
import org.dsrg.soenea.domain.producer.IdentityBasedProducerMethod;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.domain.user.User;
import org.dsrg.soenea.domain.user.UserProxy;

public class GameInputMapper implements IdentityBasedProducer {

	/**
	 * This method will return all Games in the data store.
	 */
	
	public static List<IGame> findAll() throws SQLException, MapperException {
		ArrayList<IGame> l = new ArrayList<IGame>();
		
		ResultSet rs = GameFinder.findAll();
		while(rs.next()) {
			try {
				l.add(IdentityMap.get(rs.getLong("id"), Game.class));
				continue;
			} catch (DomainObjectNotFoundException e) {	}
			l.add(getGame(rs));
		}
		
		return l;
	}

	@IdentityBasedProducerMethod
	public static Game find(long id)  throws SQLException, MapperException {
		try {
			return IdentityMap.get(id, Game.class);
		} catch (DomainObjectNotFoundException e) {
		}
		ResultSet rs = GameFinder.find(id);
		if(!rs.next()) throw new MapperException("Game with id " + id + " does not exist.");
		return getGame(rs);
	}
	
	public static List<IGame> find(IUser u) throws SQLException, MapperException {
		ArrayList<IGame> l = new ArrayList<IGame>();
		
		ResultSet rs = PlaysFinder.findByUser(u.getId());
		while(rs.next()) {
			try {
				l.add(IdentityMap.get(rs.getLong("g.id"), Game.class));
				continue;
			} catch (DomainObjectNotFoundException e) {	}
			l.add(getGame(rs));
		}
		
		return l;
	}
	
	public static IUser[] findPlayers(IGame game)  throws SQLException, MapperException {
		IUser u[] = new IUser[Country.Countries.values().length];
		ResultSet rs = PlaysFinder.find(game.getId());
		while(rs.next()) {
			try {
				u[rs.getInt("country")] = IdentityMap.get(rs.getLong("player"), User.class);
				continue;
			} catch (DomainObjectNotFoundException e) {	}
			u[rs.getInt("country")] = new UserProxy(rs.getLong("id"));
		}
		return u;
	}

	private static Game getGame(ResultSet rs) throws SQLException, MapperException {
		return GameFactory.createClean(rs.getLong("g.id"), 
				rs.getInt("g.version"),
				rs.getString("g.name"),
				new UserProxy(rs.getLong("g.creator")),
				rs.getInt("g.turn")
				);
	}
	
}

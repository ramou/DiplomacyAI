package org.dipai.dom.model.game.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class PlaysFinder {
	
	public static String fields = "p.game, p.player, p.country";
	
	public static String SELECT_BY_GAME_SQL = 
			"SELECT " + fields + " FROM " + PlaysTDG.PLAYS_TABLE + " AS p " +
			"WHERE game=?;";

	public static String SELECT_ALL_SQL = 
			"SELECT " + fields + " FROM " + PlaysTDG.PLAYS_TABLE+ " AS p ;";
	
	public static String SELECT_GAMES_BY_PLAYER_SQL = 
			"SELECT " + GameFinder.fields + " FROM " + GameTDG.GAME_TABLE + " AS g " +
			"LEFT JOIN " + PlaysTDG.PLAYS_TABLE  + " AS p ON g.id = p.game " +
			"WHERE p.player=?;";

	public static ResultSet find(long game) throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_BY_GAME_SQL);
		ps.setLong(1, game);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findAll() throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_ALL_SQL);
		return SQLLogger.processQuery(ps);
	}

	public static ResultSet findByUser(Long id) throws SQLException{
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_GAMES_BY_PLAYER_SQL);
		ps.setLong(1, id);
		return SQLLogger.processQuery(ps);
	}
	
}

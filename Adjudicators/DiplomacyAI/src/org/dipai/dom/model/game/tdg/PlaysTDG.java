package org.dipai.dom.model.game.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.UniqueIdFactory;
import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class PlaysTDG {

	private static final String BASE = "Plays";
	public static String PLAYS_TABLE = DbRegistry.getTablePrefix()+BASE;

	public static String CREATE_SQL = 
		 "CREATE TABLE IF NOT EXISTS " + PLAYS_TABLE + " ( " +
		 "game INTEGER, " +
		 "player INTEGER, " +
		 "country INTEGER, " +
		 "UNIQUE (game, country), " +
		 "UNIQUE (game, player), " +
		 "PRIMARY KEY (game) " +
		 ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

	public static String DROP_SQL = 
			"DROP TABLE " + PLAYS_TABLE + ";";
	
	public static String INSERT_SQL = 
		"INSERT INTO " + PLAYS_TABLE + " " +
		"(game, player, country) VALUES(?, ?, ?);";
	
	public static String DELETE_SQL = 
			"DELETE FROM " + PLAYS_TABLE + " " +
			"WHERE game=?;";
	
	public static String DELETE_BY_PLAYER_SQL = 
			"DELETE FROM " + PLAYS_TABLE + " " +
			"WHERE game=? AND player=?;";

	public static void createTable() throws SQLException {
		SQLLogger.processUpdate(CREATE_SQL);
	}
	
	public static void dropTable() throws SQLException {
		SQLLogger.processUpdate(DROP_SQL);
	}

	public static int insert(long game, long player, long country) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(INSERT_SQL);
		ps.setLong(1, game);
		ps.setLong(2, player);
		ps.setLong(3, country);
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}

	public static int delete(long game) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(DELETE_SQL);
		ps.setLong(1, game);
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}
	
	public static int delete(long game, long player) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(DELETE_BY_PLAYER_SQL);
		ps.setLong(1, game);
		ps.setLong(2, player);
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}

	public static long getMaxId() throws SQLException {
		return UniqueIdFactory.getMaxId(BASE, "id");
	}

}


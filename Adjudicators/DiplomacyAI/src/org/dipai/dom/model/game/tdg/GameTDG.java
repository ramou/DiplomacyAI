package org.dipai.dom.model.game.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.UniqueIdFactory;
import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class GameTDG {

	private static final String BASE = "Game";
	public static String GAME_TABLE = DbRegistry.getTablePrefix()+BASE;

	public static String CREATE_SQL = 
		 "CREATE TABLE IF NOT EXISTS " + GAME_TABLE + " ( " +
		 "id INTEGER, " +
		 "version INTEGER, " +
		 "creator INTEGER, " +
		 "turn INTEGER, " +
		 "name VARCHAR(64), " +
		 "PRIMARY KEY (id) " +
		 ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

	public static String DROP_SQL = 
			"DROP TABLE " + GAME_TABLE + ";";
	
	public static String INSERT_SQL = 
		"INSERT INTO " + GAME_TABLE + " " +
		"(id, version, name, creator, turn) VALUES(?, ?, ?, ?, ?);";

	public static String UPDATE_SQL = 
			"UPDATE " + GAME_TABLE + " " +
			"SET version=version+1, name=?, creator=?, turn=? " +
			"WHERE id=? AND version=?;";
	
	public static String DELETE_SQL = 
			"DELETE FROM " + GAME_TABLE + " " +
			"WHERE id=? AND version=?;";


	public static void createTable() throws SQLException {
		SQLLogger.processUpdate(CREATE_SQL);
	}
	
	public static void dropTable() throws SQLException {
		SQLLogger.processUpdate(DROP_SQL);
	}

	public static int insert(long id, long version, String name, long creator, int turn) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(INSERT_SQL);
		ps.setLong(1, id);
		ps.setLong(2, version);
		ps.setString(3, name);
		ps.setLong(4, creator);
		ps.setLong(5, turn);
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}
	
	public static int update(long id, long version, String name, long creator, int turn) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE_SQL);
		ps.setLong(1, id);
		ps.setString(2, name);
		ps.setLong(3, creator);
		ps.setLong(4, turn);
		ps.setLong(5, version);
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}

	public static int delete(long id, long version) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(DELETE_SQL);
		ps.setLong(1, id);
		ps.setLong(2, version);
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}

	public static long getMaxId() throws SQLException {
		return UniqueIdFactory.getMaxId(BASE, "id");
	}
	
}


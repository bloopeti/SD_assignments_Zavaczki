package sd.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;

import sd.connection.ConnectionFactory;
import sd.model.Game;

public class GameDAO 
{

	protected static final Logger LOGGER = Logger.getLogger(GameDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO game (id, points_p1, points_p2, match_id)"
			+ " VALUES (?, ?, ?, ?)";
	private final static String findStatementString = "SELECT * FROM game where id = ?";
	private final static String deleteStatementString = "DELETE FROM game WHERE id = ?";
	private final static String editStatementString = "UPDATE game SET name = ? WHERE id = ?";
	private final static String findAllStatementString = "SELECT * FROM game";

	public static Game findById(int gameId) 
	{
		Game toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try 
		{
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setInt(1, gameId);
			rs = findStatement.executeQuery();
			rs.next();

			int points_p1 = rs.getInt("points_p1");
			int points_p2 = rs.getInt("points_p2");
			int match_id = rs.getInt("match_id");
			toReturn = new Game(gameId, points_p1, points_p2, match_id);
		} 
		catch (SQLException e) 
		{
			LOGGER.log(Level.WARNING,"GameDAO:findByGameId " + e.getMessage());
		} 
		finally 
		{
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public static int insert(Game game) 
	{
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = 0;
		try 
		{
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, game.getId());
			insertStatement.setInt(2, game.getPoints_p1());
			insertStatement.setInt(3, game.getPoints_p2());
			insertStatement.setInt(4, game.getMatch_id());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) 
			{
				insertedId = rs.getInt(1);
			}
		} 
		catch (SQLException e) 
		{
			LOGGER.log(Level.WARNING, "GameDAO:insert " + e.getMessage());
		} 
		finally 
		{
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	public static Game delete(int gameId) 
	{
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;
		Game deleted = null;
		try 
		{
			deleted = findById(gameId);
			
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setInt(1, gameId);
			deleteStatement.executeUpdate();
		} 
		catch (SQLException e) 
		{
			LOGGER.log(Level.WARNING, "GameDAO:insert " + e.getMessage());
		} 
		finally
		{
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		return deleted;
	}
	
	public static int edit(Game game) 
	{
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement editStatement = null;
		int editedId = 0;
		try 
		{
			editStatement = dbConnection.prepareStatement(editStatementString, Statement.RETURN_GENERATED_KEYS);
			editStatement.setInt(1, game.getId());
			editStatement.setInt(2, game.getPoints_p1());
			editStatement.setInt(3, game.getPoints_p2());
			editStatement.setInt(4, game.getMatch_id());
			editStatement.executeUpdate();

			ResultSet rs = editStatement.getGeneratedKeys();
			if (rs.next()) 
			{
				editedId = rs.getInt(1);
			}
		} 
		catch (SQLException e) 
		{
			LOGGER.log(Level.WARNING, "GameDAO:edit " + e.getMessage());
		} 
		finally 
		{
			ConnectionFactory.close(editStatement);
			ConnectionFactory.close(dbConnection);
		}
		return editedId;
	}
	
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    java.sql.ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);
	}
	
	public static DefaultTableModel findAll() 
	{
		@SuppressWarnings("unused")
		int toReturn = 0;
		DefaultTableModel returning = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findAllStatement = null;
		ResultSet rs = null;
		try 
		{
			findAllStatement = dbConnection.prepareStatement(findAllStatementString);
			rs = findAllStatement.executeQuery();
			returning = buildTableModel(rs);
			while(rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				System.out.println("id: " + id + " name: " + name);
				toReturn++;
			}
		} 
		catch (SQLException e) 
		{
			LOGGER.log(Level.WARNING,"GameDAO:findAll " + e.getMessage());
		} 
		finally 
		{
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findAllStatement);
			ConnectionFactory.close(dbConnection);
		}
		return returning;
	}
}
package sd.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sd.connection.ConnectionFactory;
import sd.model.Match;

public class MatchDAO 
{

	protected static final Logger LOGGER = Logger.getLogger(MatchDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO match (id, level, player1, player2, tournament_name)"
			+ " VALUES (?, ?, ?, ?, ?)";
	private final static String findStatementString = "SELECT * FROM match where id = ?";
	private final static String deleteStatementString = "DELETE FROM match WHERE id = ?";
	private final static String editStatementString = "UPDATE match SET name = ? WHERE id = ?";
	private final static String findAllStatementString = "SELECT * FROM ttenistourney.`match`";

    private static ObservableList<Match> createMatchList(ResultSet rs) throws SQLException {
    	ObservableList<Match> matches = FXCollections.observableArrayList();
    	
        while (rs.next()){
            // get fields
            int id = rs.getInt("id");
            int level = rs.getInt("level");
            String player1 = rs.getString("player1");
            String player2 = rs.getString("player2");
            String tournament_name = rs.getString("tournament_name");

            matches.add(new Match(id, level, player1, player2, tournament_name));
        }
        return matches;
    }
	/*
	public static User findByMail(String userMail) 
	{
		User toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try 
		{
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setString(1, userMail);
			rs = findStatement.executeQuery();
			rs.next();

			String pass = rs.getString("pass");
			String pass_nohash = rs.getString("pass_nohash");
			int is_admin = rs.getInt("is_admin");
			toReturn = new User(userMail, pass, pass_nohash, is_admin);
		} 
		catch (SQLException e) 
		{
			LOGGER.log(Level.WARNING,"UserDAO:findByUserMail " + e.getMessage());
		} 
		finally 
		{
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public static String insert(User user) 
	{
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		String insertedMail = "NULL";
		try 
		{
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, user.getMail());
			insertStatement.setString(2, user.getPass());
			insertStatement.setString(3, user.getPass_nohash());
			insertStatement.setInt(4, user.getIs_admin());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			//something might be fucky (was designed to get id of next row)
			if (rs.next()) 
			{
				insertedMail = rs.getString(1);
			}
		} 
		catch (SQLException e) 
		{
			LOGGER.log(Level.WARNING, "UserDAO:insert " + e.getMessage());
		} 
		finally 
		{
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedMail;
	}
	
	public static User delete(String userMail) 
	{
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;
		User deleted = null;
		try 
		{
			deleted = findByMail(userMail);
			
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setString(1, userMail);
			deleteStatement.executeUpdate();
		} 
		catch (SQLException e) 
		{
			LOGGER.log(Level.WARNING, "UserDAO:insert " + e.getMessage());
		} 
		finally
		{
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		return deleted;
	}
	
	public static String edit(User user) 
	{
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement editStatement = null;
		String editedMail = "NULL";
		try 
		{
			editStatement = dbConnection.prepareStatement(editStatementString, Statement.RETURN_GENERATED_KEYS);
			editStatement.setString(1, user.getMail());
			editStatement.setString(2, user.getPass());
			editStatement.setString(3, user.getPass_nohash());
			editStatement.setInt(4, user.getIs_admin());
			editStatement.executeUpdate();

			ResultSet rs = editStatement.getGeneratedKeys();
			//something might be fucky (was designed to get id of next row)
			if (rs.next()) 
			{
				editedMail = rs.getString(1);
			}
		} 
		catch (SQLException e) 
		{
			LOGGER.log(Level.WARNING, "UserDAO:edit " + e.getMessage());
		} 
		finally 
		{
			ConnectionFactory.close(editStatement);
			ConnectionFactory.close(dbConnection);
		}
		return editedMail;
	}*/
	
	public static ObservableList<Match> findAll() 
	{
		@SuppressWarnings("unused")
		int toReturn = 0;
		ObservableList<Match> returning = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findAllStatement = null;
		ResultSet rs = null;
		try 
		{
			findAllStatement = dbConnection.prepareStatement(findAllStatementString);
			rs = findAllStatement.executeQuery();
			if(rs.next())
			{
                rs.beforeFirst();
                returning = createMatchList(rs);
			}
		} 
		catch (SQLException e) 
		{
			LOGGER.log(Level.WARNING,"MatchDAO:findAll " + e.getMessage());
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
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
import sd.model.User;

public class UserDAO 
{

	protected static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO user (mail, pass, pass_nohash, is_admin)"
			+ " VALUES (?, ?, ?, ?)";
	private final static String findStatementString = "SELECT * FROM user where mail = ?";
	private final static String deleteStatementString = "DELETE FROM user WHERE mail = ?";
	private final static String editStatementString = "UPDATE user SET (pass, pass_nohash, is_admin) = (?, ?, ?, ?) WHERE mail = ?";
	private final static String findAllStatementString = "SELECT * FROM user";

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
			
			if(rs.next())
			{
				String pass = rs.getString("pass");
				String pass_nohash = rs.getString("pass_nohash");
				int is_admin = rs.getInt("is_admin");
				toReturn = new User(userMail, pass, pass_nohash, is_admin);
			}
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
			LOGGER.log(Level.WARNING,"UserDAO:findAll " + e.getMessage());
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
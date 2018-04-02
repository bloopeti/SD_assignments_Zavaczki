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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sd.connection.ConnectionFactory;
import sd.model.Tournament;

public class TournamentDAO 
{

	protected static final Logger LOGGER = Logger.getLogger(TournamentDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO tournament (name, start_date)"
			+ " VALUES (?, ?)";
	private final static String findStatementString = "SELECT * FROM tournament where name = ?";
	private final static String deleteStatementString = "DELETE FROM tournament WHERE name = ?";
	private final static String editStatementString = "UPDATE tournament SET name = ? WHERE name = ?";
	private final static String findAllStatementString = "SELECT * FROM tournament";

	

    private static ObservableList<Tournament> createTournamentList(ResultSet rs) throws SQLException {
    	ObservableList<Tournament> tournaments = FXCollections.observableArrayList();

        while (rs.next()){
            // get fields
            String name = rs.getString("name");
            String start_date = rs.getString("start_date");

            tournaments.add(new Tournament(name, start_date));
        }
        return tournaments;
    }
	
	public static Tournament findByName(String tournamentName) 
	{
		Tournament toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try 
		{
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setString(1, tournamentName);
			rs = findStatement.executeQuery();
			rs.next();

			String start_date = rs.getString("start_date");
			toReturn = new Tournament(tournamentName, start_date);
		} 
		catch (SQLException e) 
		{
			LOGGER.log(Level.WARNING,"TournamentDAO:findByTournamentName " + e.getMessage());
		} 
		finally 
		{
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public static String insert(Tournament tournament) 
	{
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		String insertedName = "NULL";
		try 
		{
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, tournament.getName());
			insertStatement.setString(2, tournament.getStart_date());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			//something might be fucky (was designed to get id of next row)
			if (rs.next()) 
			{
				insertedName = rs.getString(1);
			}
		} 
		catch (SQLException e) 
		{
			LOGGER.log(Level.WARNING, "TournamentDAO:insert " + e.getMessage());
		} 
		finally 
		{
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedName;
	}
	
	public static Tournament delete(String tournamentName) 
	{
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;
		Tournament deleted = null;
		try 
		{
			deleted = findByName(tournamentName);
			
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setString(1, tournamentName);
			deleteStatement.executeUpdate();
		} 
		catch (SQLException e) 
		{
			LOGGER.log(Level.WARNING, "TournamentDAO:delete " + e.getMessage());
		} 
		finally
		{
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		return deleted;
	}
	
	public static String edit(Tournament tournament) 
	{
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement editStatement = null;
		String editedName = "NULL";
		try 
		{
			editStatement = dbConnection.prepareStatement(editStatementString, Statement.RETURN_GENERATED_KEYS);
			editStatement.setString(1, tournament.getName());
			editStatement.setString(2, tournament.getStart_date());;
			editStatement.executeUpdate();

			ResultSet rs = editStatement.getGeneratedKeys();
			//something might be fucky (was designed to get id of next row)
			if (rs.next()) 
			{
				editedName = rs.getString(1);
			}
		} 
		catch (SQLException e) 
		{
			LOGGER.log(Level.WARNING, "TournamentDAO:edit " + e.getMessage());
		} 
		finally 
		{
			ConnectionFactory.close(editStatement);
			ConnectionFactory.close(dbConnection);
		}
		return editedName;
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
	
	public static ObservableList<Tournament> findAll() 
	{
		@SuppressWarnings("unused")
		int toReturn = 0;
		ObservableList<Tournament> returning = null;

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
                returning = createTournamentList(rs);
			}
		} 
		catch (SQLException e) 
		{
			LOGGER.log(Level.WARNING,"TournamentDAO:findAll " + e.getMessage());
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
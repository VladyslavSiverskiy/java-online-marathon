import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Collections;


public class MyUtils {
	private Connection connection;
	private Statement statement;
	private String schemaName;

	public Connection createConnection() throws SQLException {
		DriverManager.registerDriver(new org.h2.Driver());
		connection = DriverManager.getConnection("jdbc:h2:mem:test", "", "");
		return connection;
	}

	public void closeConnection() throws SQLException {

		if(connection != null) connection.close();
	}
	
	public Statement createStatement() throws SQLException {
		if(connection == null) throw new SQLException("Connection is not created");
		statement = connection.createStatement();
		return statement;
	}

	public void closeStatement() throws SQLException {
		if (statement != null) statement.close();
	}
	
	public void createSchema(String schemaName) throws SQLException {
		if (statement == null) {
			throw new SQLException("Statement is not created.");
		}
		statement.executeUpdate("CREATE SCHEMA IF NOT EXISTS " + schemaName);
		this.schemaName = schemaName;
	}

	public void dropSchema() throws SQLException {
		if (statement == null) {
			throw new SQLException("Statement is not created.");
		}
		statement.executeUpdate("DROP SCHEMA IF EXISTS " + this.schemaName + " CASCADE ");
		this.schemaName = null;
	}
	
	public void useSchema() throws SQLException {
		if (statement == null) throw new SQLException("Statement is not created. Call createStatement() first.");
		if(schemaName == null) throw new SQLException("Schema doesn't exist");
		statement.executeUpdate("USE " + schemaName.toUpperCase());
	}

	public void createTableRoles() throws SQLException {
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS Roles (" +
						  "id INTEGER AUTO_INCREMENT PRIMARY KEY," +
						  "roleName VARCHAR(255) NOT NULL" +
						  ");");
	}
	
	public void createTableDirections() throws SQLException {
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS Directions (" +
						  "id INTEGER AUTO_INCREMENT PRIMARY KEY," +
						  "directionName VARCHAR(255) NOT NULL" +
						  ")");
	}
	
	public void createTableProjects() throws SQLException {
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS Projects (" +
								"id INT AUTO_INCREMENT PRIMARY KEY," +
								"projectName VARCHAR(255)," +
								"directionId INT," +
								"FOREIGN KEY (directionId) REFERENCES Directions(id)" +
								")");
	}
	
	public void createTableEmployee() throws SQLException {
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS Employees (" +
								"id INT AUTO_INCREMENT PRIMARY KEY," +
								"firstName VARCHAR(255)," +
								"roleId INT," +
								"projectId INT," +
								"FOREIGN KEY (roleId) REFERENCES Roles(id)," +
								"FOREIGN KEY (projectId) REFERENCES Projects(id)" +
								")");
	}
	
	public void dropTable(String tableName) throws SQLException {
		statement.executeUpdate("DROP TABLE IF EXISTS " + tableName + " CASCADE");
	}
	
	public void insertTableRoles(String roleName) throws SQLException {
		statement.executeUpdate("INSERT INTO Roles (roleName) VALUES ('" + roleName +"');");
	}

	public void insertTableDirections(String directionName) throws SQLException {
		statement.executeUpdate("INSERT INTO Directions (directionName) VALUES ('" + directionName +"');");
	}

	public void insertTableProjects(String projectName, String directionName) throws SQLException {
		int directionId = getDirectionId(directionName);
		String insertProjectQuery = "INSERT INTO Projects (projectName, directionId) VALUES ('" + projectName + "', " + directionId + ")";
		statement.executeUpdate(insertProjectQuery);
	}

	public void insertTableEmployee(String firstName, String roleName, String projectName) throws SQLException {
		//your code
		int roleId = getRoleId(roleName);
		int projectId = getProjectId(projectName);
		statement.executeUpdate("INSERT INTO Employees (firstName, roleId, projectId) VALUES ('" +
								firstName+ "'," + roleId + "," + projectId + ")");
	}



	public int getRoleId(String roleName) throws SQLException {
		ResultSet resultSet = statement.executeQuery("SELECT * FROM ROLES");
		while(resultSet.next()){
			if(resultSet.getString(2).equals(roleName)){
				return resultSet.getInt(1);
			}
		}
		throw new SQLException("Role with name " + roleName + " doesn't exist");
	}
	
	public int getDirectionId(String directionName) throws SQLException {
		ResultSet resultSet = statement.executeQuery("SELECT * FROM DIRECTIONS");
		while(resultSet.next()){
			if(resultSet.getString(2).equals(directionName)){
				return resultSet.getInt(1);
			}
		}
		throw new SQLException("Direction with name " + directionName + " doesn't exist");
	}
	
	public int getProjectId(String projectName) throws SQLException {
		ResultSet resultSet = statement.executeQuery("SELECT * FROM PROJECTS");
		while(resultSet.next()){
			if(resultSet.getString(2).equals(projectName)){
				return resultSet.getInt(1);
			}
		}
		throw new SQLException("Project with name " + projectName + " doesn't exist");
	}
	
	public int getEmployeeId(String firstName) throws SQLException {
		ResultSet resultSet = statement.executeQuery("SELECT * FROM Employees");
		while(resultSet.next()){
			if(resultSet.getString(2).equals(firstName)){
				return resultSet.getInt(1);
			}
		}
		throw new SQLException("Employee with name " + firstName + " doesn't exist");
	}
	
	public List<String> getAllRoles() throws SQLException {
		List<String> roles = new ArrayList<>();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM Roles");

		while (resultSet.next()){
			roles.add(resultSet.getString(2));
		}
		return roles;
	}

	public List<String> getAllDirestion() throws SQLException {
		List<String> directions = new ArrayList<>();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM Directions");

		while (resultSet.next()){
			directions.add(resultSet.getString(2));
		}
		return directions;
	}

	public List<String> getAllProjects() throws SQLException {
		List<String> projects = new ArrayList<>();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM Projects");

		while (resultSet.next()){
			projects.add(resultSet.getString(2));
		}
		return projects;
	}

	public List<String> getAllEmployee() throws SQLException {
		List<String> employees = new ArrayList<>();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM Employees");
		while (resultSet.next()){
			employees.add(resultSet.getString(2));
		}
		return employees;
	}
	
	public List<String> getAllDevelopers() throws SQLException {
		List<String> employees = new ArrayList<>();
		int developerRoleId = getRoleId("Developer");
		ResultSet resultSet = statement.executeQuery("SELECT * FROM Employees");
		while (resultSet.next()){
			if (resultSet.getInt(3) == developerRoleId) employees.add(resultSet.getString(2));
		}
		return employees;
	}

	public List<String> getAllJavaProjects() throws SQLException {
		List<String> projects = new ArrayList<>();
		int javaDirectionId = getDirectionId("Java");
		ResultSet resultSet = statement.executeQuery("SELECT * FROM Projects");
		while (resultSet.next()){
			if(resultSet.getInt(3) == javaDirectionId) projects.add(resultSet.getString(2));
		}
		return projects;
	}

	public List<String> getAllJavaDevelopers() throws SQLException {
		List<String> javaDevelopers = new ArrayList<>();
		int developerRoleId = getRoleId("Developer");
		int javaDirectionId = getDirectionId("Java");
		Set<Integer> javaProjects = new HashSet<>();
		ResultSet projectsId = statement.executeQuery("SELECT * FROM Projects");
		while (projectsId.next()){
			if(projectsId.getInt(3) == javaDirectionId) javaProjects.add(projectsId.getInt(1));
		}
		ResultSet resultSet = statement.executeQuery("SELECT * FROM Employees");
		while (resultSet.next()){
			if (resultSet.getInt(3) == developerRoleId && javaProjects.contains(resultSet.getInt(4))) javaDevelopers.add(resultSet.getString(2));
		}
		Collections.sort(javaDevelopers);
		return javaDevelopers;
	}

}

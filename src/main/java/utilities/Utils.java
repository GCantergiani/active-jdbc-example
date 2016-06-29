package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {

	public void loadScriptSQL(String file) throws IOException, SQLException {

		Connection mConnection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			mConnection = DriverManager.getConnection("jdbc:mysql://localhost/jawira?user=root&password=admin");
		} catch (ClassNotFoundException e) {
			System.err.println("Unable to get mysql driver: " + e);
		} catch (SQLException e) {
			System.err.println("Unable to connect to server: " + e);
		}
		ScriptRunner runner = new ScriptRunner(mConnection, false, false);

		InputStream is = getClass().getClassLoader().getResourceAsStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		runner.runScript(br);

	}

}

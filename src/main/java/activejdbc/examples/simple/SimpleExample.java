package activejdbc.examples.simple;

import java.io.IOException;

import java.sql.SQLException;
import java.sql.Timestamp;

import org.javalite.activejdbc.Base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.Topology;
import entities.User;
import utilities.Utils;

public class SimpleExample {
	final static Logger logger = LoggerFactory.getLogger(SimpleExample.class);

	public static void main(String[] args) {
		
		if (args.length > 0 && args[0].equals("-C")) {
			logger.info("=========> the Middleware will create database");
			Utils utils = new Utils();
			try {
				utils.loadScriptSQL("create.sql");
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
		}

		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/jawira", "root", "admin");

		logger.info("=========> Create User:");
		createUser();
		logger.info("=========> Select User:");
		selectUser();
		logger.info("=========> Update User:");
		updateUser();
		logger.info("=========> Select Users :");
		selectAlluser();
		logger.info("=========> Delete Users :");
		deleteuser();
		logger.info("=========> Adding topology :");
		addTopologyToUser();

		Base.close();
	}

	private static void createUser() {

		java.util.Date today = new java.util.Date();
		Timestamp timestamp = new java.sql.Timestamp(today.getTime());

		User u = new User();
		u.set("username", "username_test");
		u.set("password", "password_test");
		u.set("type", "user");
		u.set("email", "admin@gmail.com");
		u.set("enable", 1);
		u.set("user_created", timestamp);
		u.saveIt();
	}

	private static void selectUser() {
		User u = User.findFirst("username = ?", "username_test");
		logger.info(u.toString());
	}

	private static void updateUser() {
		int u = User.update("enable = ?", "username = ?", "0", "username_test");
		logger.info("Update Record: " + u);
	}

	private static void selectAlluser() {
		logger.info("Employees list: " + User.findAll());
	}

	private static void deleteuser() {
		User u = User.findFirst("username = ?", "username_test");
		u.delete();
	}

	private static void addTopologyToUser() {

		Topology t = new Topology();
		t.set("name", "username_test");
		t.set("IdStormTopology", "idStormTest");
		t.set("contentRaw", "user");

		User u = User.findFirst("username = ?", "admin");
		u.add(t);
	}

	/*
	 * private static void deleteAllEmployees() { Employee.deleteAll(); }
	 */

}

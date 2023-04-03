package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ConnectionManager {
	
	public DataSource getDataSource() {
		System.out.println("prob basladı");
		MysqlDataSource dataSource = null;
		//Properties properties = new Properties();
		//InputStream inputStream = getClass().getResourceAsStream("db.properties");

		try {
			//properties.load(inputStream);
			dataSource = new MysqlDataSource();
			dataSource.setUrl("jdbc:mysql://localhost:3306/database");;
			dataSource.setUser("root");
			dataSource.setPassword("password");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSource;
	}
}

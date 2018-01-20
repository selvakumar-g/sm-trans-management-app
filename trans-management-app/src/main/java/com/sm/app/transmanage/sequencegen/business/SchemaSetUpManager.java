package com.sm.app.transmanage.sequencegen.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SchemaSetUpManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchemaSetUpManager.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void clearSchema() throws SQLException {
		if (validateSchema()) {
			LOGGER.info("Tables present. hence clearSchema started..");
			doProcess("drop_tables.sql");
			LOGGER.info("Schema is cleared..");
		} else
			LOGGER.info("Tables are not present. hence clearschema aborted");
	}

	public void initiateSetup() throws SQLException {
		if (!validateSchema()) {
			LOGGER.info("Tables not present. hence initial set up started..");
			doProcess("tables.sql");
			LOGGER.info("Table creation completed..");
			printTablesInfo();
		} else
			LOGGER.info("Tables present. hence intialsetup aborted");

	}

	private boolean validateSchema() throws SQLException {
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		ResultSet resultSet = null;
		try {
			DatabaseMetaData metaData = connection.getMetaData();
			resultSet = metaData.getTables(null, null, null, new String[] { "TABLE" });
			return resultSet.next();
		} finally {
			if (resultSet != null)
				resultSet.close();
			connection.close();
		}
	}

	private void printTablesInfo() throws SQLException {
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		ResultSet resultSet = null;
		try {
			DatabaseMetaData metaData = connection.getMetaData();
			resultSet = metaData.getTables(null, null, null, new String[] { "TABLE" });
			LOGGER.info("Tables created so far ..!! >>");
			while (resultSet.next()) {
				LOGGER.info(resultSet.getString("TABLE_NAME"));
			}
		} finally {
			if (resultSet != null)
				resultSet.close();
			connection.close();
		}
	}

	private void doProcess(String sqlFile) {
		String line = null;
		StringBuilder sbul = new StringBuilder();
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(new ClassPathResource(sqlFile).getInputStream());
			br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				sbul.append(line);
			}
			String[] stmtStrs = sbul.toString().split(";");
			for (String sql : stmtStrs) {
				LOGGER.info(sql);
				jdbcTemplate.execute(sql);
			}
		} catch (IOException ioe) {
			LOGGER.error("Exception while reading SQL file in Servlet starup", ioe);
		} finally {
			try {
				if (br != null)
					br.close();
				if (isr != null)
					isr.close();
			} catch (IOException e1) {
				LOGGER.error("Exception while CLOSING SQL file readers in Servlet starup", e1);
			}
		}
	}
}

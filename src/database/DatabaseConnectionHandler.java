package database;

import util.PrintablePreparedStatement;

import javax.swing.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnectionHandler {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private Connection connection;

    public DatabaseConnectionHandler() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
        }
    }

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            JOptionPane.showMessageDialog(new JFrame(), "Connected to Oracle!");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
            return false;
        }
    }

    public void databaseSetup() {
        dropBranchTableIfExists();

        try {
            BufferedReader reader = new BufferedReader(new FileReader (new File("./src/script/setup.sql")));
            String         line = null;
            StringBuffer sb = new StringBuffer();
            BufferedReader br = new BufferedReader(reader);
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            String[] instructions = sb.toString().split(";");
            for (int i = 0; i < instructions.length; i++) {
                PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(instructions[i]), instructions[i], false);
                ps.executeUpdate();
                connection.commit();
                ps.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Fail to setup Database because:" + e.getMessage());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Cannot find sql file");
        }  catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Fail to read sql file because:" + e.getMessage());
        }
    }

    private void dropBranchTableIfExists() {
        try {
            String query = "select table_name from user_tables";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("branch")) {
                    ps.execute("DROP TABLE branch");
                    break;
                }
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Fail to drop existed table becuase:" + e.getMessage());
        }
    }

    private void rollbackConnection() {
        try  {
            connection.rollback();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
        }
    }
}

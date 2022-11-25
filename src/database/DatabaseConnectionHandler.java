package database;

import util.PrintablePreparedStatement;

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

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

    public ArrayList<String> getMuseumNames() {
        ArrayList<String> result = new ArrayList<>();
        try {
            String query = "SELECT Mname FROM Museum";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();
            int index = 0;
            while(rs.next()) {
                result.add(rs.getString("Mname"));
                index++;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Fail to get museum names because: " + e.getMessage());
        }
        return result;
    }

    public Vector<Vector> getContents(String query) {
        Vector<Vector> result = new Vector<>();
        try {
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            result.add(new Vector<>());
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                result.get(0).add(rsmd.getColumnName(i));
            }
            int index = 1;
            while(rs.next()) {
                for (int i=1; i<=rsmd.getColumnCount(); i++) {
                    result.add(new Vector<>());
                    result.get(index).add(rs.getString(i));
                }
                index++;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Fail to get content because: " + e.getMessage());
        }
        return result;
    }

    private void rollbackConnection() {
        try  {
            connection.rollback();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
        }
    }
}

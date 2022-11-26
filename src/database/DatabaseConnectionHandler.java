package database;

import util.PrintablePreparedStatement;

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
    // Use this version of the ORACLE_URL if you are running the code off of the server
//	private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
    // Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";

    private Connection connection = null;

    public DatabaseConnectionHandler() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
            BufferedReader reader = new BufferedReader(new FileReader(new File("./src/script/setup.sql")));
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

    public void remove(String table, int id) {
        String query = "";
        try {
            switch (table) {
                case "Activity":
                    query = "DELETE FROM Activity WHERE AID = ?";
                    break;
                case "Film":
                    query = "DELETE FROM Film WHERE FID = ?";
                    break;
                case "Souvenir":
                    query = "DELETE FROM Souvenir WHERE SID = ?";
                    break;
            }

            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, id);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                JOptionPane.showMessageDialog(new JFrame(), "The data does not exist!");
            }

            connection.commit();

            ps.close();
            JOptionPane.showMessageDialog(new JFrame(), "The data is successfully removed");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Fail to remove the data because: " + e.getMessage());
            rollbackConnection();
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

    public ArrayList<String> getHallsInMuseum(String museum) {
        ArrayList<String> result = new ArrayList<>();
        try {
            String query = "SELECT ZNAME FROM EXHIBITIONHALL, MUSEUM\n" +
                    "WHERE EXHIBITIONHALL.MID = MUSEUM.MID AND MUSEUM.MNAME = \'" + museum + "\'";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();
            int index = 0;
            while(rs.next()) {
                result.add(rs.getString("ZNAME"));
                index++;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Fail to get halls in museum because: " + e.getMessage());
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

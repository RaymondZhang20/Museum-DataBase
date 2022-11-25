package GUI;

import database.DatabaseConnectionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class TablePanel extends JPanel {
    public static final int WIDTH = 1300;
    public static final int HEIGHT = 1000;
    private JTable table;
    private Vector<String> titles;
    private Vector<Vector> tableData;
    private DefaultTableModel tableModel;
    private DatabaseConnectionHandler dbHandler;

    public TablePanel(DatabaseConnectionHandler dbHandler, String query) {
        this.dbHandler = dbHandler;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BorderLayout());
        initializeTable();
        add(initializeButtons(),BorderLayout.NORTH);
        Vector<Vector> all = dbHandler.getContents(query);
        titles = all.get(0);
        all.remove(0);
        tableData = all;
//        for (Vector<String> vector:tableData){
//            for (String s: vector) {
//                System.out.println(s);
//            }
//        }
        tableModel = new DefaultTableModel(tableData,titles);
        table = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        table.getTableHeader().setFont(new Font("TimesRoman", Font.BOLD, 18));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    private JPanel initializeButtons() {
        return new JPanel();
    }

    private void initializeTable() {

    }
}

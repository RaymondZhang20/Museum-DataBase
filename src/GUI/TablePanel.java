package GUI;

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

    public TablePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
//        setBackground(Color.BLUE);
        setLayout(new BorderLayout());
        initializeTable();
        add(initializeButtons(),BorderLayout.NORTH);
    }

    private JPanel initializeButtons() {
        return new JPanel();
    }

    private void initializeTable() {

    }
}

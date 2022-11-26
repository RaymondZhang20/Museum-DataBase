package GUI;

import database.DatabaseConnectionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class TablePanel extends JPanel implements ActionListener {
    public static final int WIDTH = 1300;
    public static final int HEIGHT = 1000;
    private JTable table;
    private Vector<String> titles;
    private Vector<Vector> tableData;
    private DefaultTableModel tableModel;
    private DatabaseConnectionHandler dbHandler;
    private ArrayList meList = new ArrayList<JRadioButton>();
    private String query;
    private String division;

    public TablePanel(DatabaseConnectionHandler dbHandler, String query, String divisionType) {
        this.division = divisionType;
        this.query = query;
        this.dbHandler = dbHandler;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BorderLayout());
        if (divisionType!=null) initializeButtons();
        initializeData(query);
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
//        tableData.clear();
        tableModel.fireTableDataChanged();
//        System.out.println(tableModel.getRowCount());
    }

    private void initializeButtons() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        for (String s : dbHandler.getMuseumNames()) {
            JRadioButton r = new JRadioButton(s);
            r.addActionListener(this);
            buttonsPanel.add(r);
            meList.add(r);
        }
        add(buttonsPanel,BorderLayout.NORTH);
    }

    private void initializeData(String newQuery) {
        Vector<Vector> all = dbHandler.getContents(newQuery);
        titles = all.get(0);
        all.remove(0);
        tableData = all;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String divideQuery = "";
        boolean isAnd = true;
        for (Object b : meList) {
            JRadioButton button = (JRadioButton) b;
            if (button.isSelected()) {
                if (isAnd) divideQuery += " AND MUSEUM.MNAME = \'" + button.getText() + "\'";
                else divideQuery += " OR MUSEUM.MNAME = \'" + button.getText() + "\'";
                isAnd = false;
            }
        }
        if (divideQuery.equals("")) {
            initializeData(query);
        } else {
            if (division.equals("Film")) {
                initializeData(query + " AND NOT EXISTS((SELECT ZID FROM CINEMA, MUSEUM WHERE CINEMA.MID = MUSEUM.MID" +
                        divideQuery + ") MINUS (SELECT ZID FROM PLAY WHERE PLAY.FID = F.FID))");
            } else if (division.equals("Souvenir")) {
                initializeData("SELECT SNAME, PRICE, SUM(INVENTORY) AS TOTAL_INVENTORY FROM SOUVENIR, SELL\n" +
                        "WHERE SOUVENIR.SID = SELL.SID AND NOT EXISTS((SELECT ZID FROM GIFTSTORE, MUSEUM\n" +
                        "    WHERE GIFTSTORE.MID = MUSEUM.MID" +
                        divideQuery + ") MINUS (SELECT ZID FROM GIFTSTORE WHERE SELL.ZID = GIFTSTORE.ZID))\n" +
                        "GROUP BY SNAME, PRICE HAVING SOUVENIR.PRICE > 5");
            }
        }
        tableModel.setDataVector(tableData,titles);
        tableModel.fireTableDataChanged();
    }
}

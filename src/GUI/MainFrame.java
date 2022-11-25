package GUI;

import database.DatabaseConnectionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
//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
public class MainFrame extends JFrame {
    public static final int WIDTH = 1700;
    public static final int HEIGHT = 1000;
    private JPanel mainPanel = new JPanel();
    private CardLayout cardLayout= new CardLayout();
    private DatabaseConnectionHandler dbHandler;

    public MainFrame(DatabaseConnectionHandler dbHandler) {
        this.dbHandler = dbHandler;
//        dbHandler.databaseSetup();
        setJMenuBar(InitializeMenuBar());
        initializeMainPanel();
        setTitle("Museum Data Management");
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeMainPanel() {
        mainPanel.setLayout(cardLayout);
        String[] TreeInitial = {"Museum", "Activity", "Exhibits2", "Film", "Souvenir"};
        for (int i = 0; i < 5; i++) {
            mainPanel.add("p" + i,new InformationPanel(TreeInitial[i], dbHandler));
        }
        this.add(mainPanel);
    }

    private JMenuBar InitializeMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        Font f = new Font("TimesRoman", Font.PLAIN, 30);
        UIManager.put("Menu.font", f);
        UIManager.put("MenuItem.font", f);
        JMenu menu = new JMenu("Settings");
        JMenuItem quit = new JMenuItem("quit");
        JMenuItem save = new JMenuItem("save");
        JSeparator s1 = new JSeparator();
        JSeparator s2 = new JSeparator();
        menu.add(quit);
        menu.add(save);
        menuBar.add(menu);
        menuBar.add(s1);
        JMenu table = new JMenu("Table");
        JMenuItem museum = new JMenuItem("Museum");
        JMenuItem activity = new JMenuItem("Activity");
        JMenuItem exhibit = new JMenuItem("Exhibit");
        JMenuItem film = new JMenuItem("Film");
        JMenuItem souvenir = new JMenuItem("Souvenir");
        table.add(museum);
        table.add(activity);
        table.add(exhibit);
        table.add(film);
        table.add(souvenir);
        menuBar.add(table);
        menuBar.add(s2);

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        museum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "p0");
            }
        });
        activity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "p1");
            }
        });
        exhibit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "p2");
            }
        });
        film.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "p3");
            }
        });
        souvenir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "p4");
            }
        });
        return menuBar;
    }
}

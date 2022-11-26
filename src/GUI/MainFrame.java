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
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private DatabaseConnectionHandler dbHandler;

    public MainFrame(DatabaseConnectionHandler dbHandler) {
        this.dbHandler = dbHandler;
//        dbHandler.databaseSetup();
        initializeMainPanel();
        InitializeMenuBar();
        setTitle("Museum Data Management");
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeMainPanel() {
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        String[] TreeInitial = {"Museum", "Activity", "Exhibit", "Film", "Souvenir"};
        for (int i = 0; i < 5; i++) {
            mainPanel.add("p" + i,new InformationPanel(TreeInitial[i], dbHandler));
        }
        this.add(mainPanel);
    }

    private void InitializeMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        Font f = new Font("TimesRoman", Font.PLAIN, 30);
        UIManager.put("Menu.font", f);
        UIManager.put("MenuItem.font", f);
        JMenu menu = new JMenu("Settings");
        JMenuItem quit = new JMenuItem("quit");
        JMenuItem save = new JMenuItem("save");
        JSeparator s1 = new JSeparator();
        JSeparator s2 = new JSeparator();
        JSeparator s3 = new JSeparator();
        JSeparator s4 = new JSeparator();
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
        JMenu add = new JMenu("Add data");
        JMenuItem addActivity = new JMenuItem("Activity");
        JMenuItem addFilm = new JMenuItem("Film");
        JMenuItem addSouvenir = new JMenuItem("Souvenir");
        add.add(addActivity);
        add.add(addFilm);
        add.add(addSouvenir);
        menuBar.add(add);
        menuBar.add(s3);
        JMenu remove = new JMenu("Remove data");
        JMenuItem remActivity = new JMenuItem("Activity");
        JMenuItem remFilm = new JMenuItem("Film");
        JMenuItem remSouvenir = new JMenuItem("Souvenir");
        remove.add(remActivity);
        remove.add(remFilm);
        remove.add(remSouvenir);
        menuBar.add(remove);
        menuBar.add(s4);
        JMenu update = new JMenu("Update data");
        JMenuItem updExhibit = new JMenuItem("Exhibit");
        update.add(updExhibit);
        menuBar.add(update);

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
        remActivity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog(new JFrame(), "Input the AID of the activity you want to delete"));
                dbHandler.remove("Activity", id);
                dipo();
            }
        });
        remFilm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog(new JFrame(), "Input the FID of the film you want to delete"));
                dbHandler.remove("Film", id);
                dipo();
            }
        });
        remSouvenir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog(new JFrame(), "Input the SID of the souvenir you want to delete"));
                dbHandler.remove("Souvenir", id);
                dipo();
            }
        });
        this.setJMenuBar(menuBar);
        addActivity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InsertQueryFrame("Activity", dbHandler, returnThis());
            }
        });
        updExhibit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int Eid = Integer.parseInt(JOptionPane.showInputDialog(new JFrame(), "input the EID of the exhibit you want ot update"));
                String name = JOptionPane.showInputDialog(new JFrame(), "input the new name of the exhibit");
                dbHandler.updateExhibit(Eid, name);
                dipo();
            }
        });
    }

    public void dipo() {
        new MainFrame(dbHandler);
        this.dispose();
    }

    private MainFrame returnThis() {return this;}
}

package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    public static final int WIDTH = 1700;
    public static final int HEIGHT = 1000;
    private JPanel mainPanel = new JPanel();
    private List<TablePanel> tablesPanels = new ArrayList<>();
    private List<TreePanel> treePanels = new ArrayList<>();
    private CardLayout cardLayout= new CardLayout();
    private JSplitPane splitPane;

    public MainFrame() {
        setJMenuBar(InitializeMenuBar());
        initializeMainPanel();
        splitPane.setDividerLocation(320);
        setTitle("Museum Data Management");
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeMainPanel() {
        mainPanel.setLayout(cardLayout);
        String[][] TreeInitial = {{"Museum"},
                                  {"Activity", "Museum"},
                                  {"Exhibit", "Museum"},
                                  {"Film", "Museum"},
                                  {"Souvenir", "Museum"}};
        for (int i = 0; i < 5; i++) {
            JPanel curr = new JPanel();
            treePanels.add(new TreePanel(TreeInitial[i]));
            tablesPanels.add(new TablePanel());
            splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,treePanels.get(i),tablesPanels.get(i));
            curr.add(splitPane);
            splitPane.setOneTouchExpandable(true);
            mainPanel.add("p" + i,curr);
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

    public static void main(String[] args) {
        new MainFrame();
    }
}

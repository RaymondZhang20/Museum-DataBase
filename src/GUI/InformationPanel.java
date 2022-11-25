package GUI;

import database.DatabaseConnectionHandler;
import javafx.scene.control.SplitPane;
import model.Museum;

import javax.swing.*;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class InformationPanel extends JPanel implements TreeSelectionListener {
    public static final int TREE_WIDTH = 300;
    public static final int TABLE_WIDTH = 1300;
    public static final int HEIGHT = 1000;
    private JTree tree = new JTree();
    private JPanel treePart = new JPanel();
    private JPanel tablePart = new JPanel();
    private CardLayout cardLayout = new CardLayout();
    private DatabaseConnectionHandler dbHandler;

    public InformationPanel(String s, DatabaseConnectionHandler dbHandler) {
        this.dbHandler = dbHandler;
        setPreferredSize(new Dimension(TREE_WIDTH + TABLE_WIDTH, HEIGHT));
        treePart.setPreferredSize(new Dimension(TREE_WIDTH, HEIGHT));
        tablePart.setPreferredSize(new Dimension(TABLE_WIDTH, HEIGHT));
        tablePart.setLayout(cardLayout);
        initialTree(s);
        initialTable(s);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePart, tablePart);
        splitPane.setDividerLocation(320);
        add(splitPane);
        splitPane.setOneTouchExpandable(true);
    }

    private void initialTable(String s) {
        switch (s) {
            case "Museum":
                tablePart.add("Museums", new TablePanel(dbHandler, "SELECT * FROM Museum"));
                String[] museumNames =dbHandler.getMuseumNames().toArray(new String[dbHandler.getMuseumNames().size()]);
                for (String mu : museumNames) {
                    tablePart.add("Activities_" + mu, new TablePanel(dbHandler,
                            "SELECT DISTINCT TITLE, ADATE AS Activity_Date, AID, ZNAME AS Location FROM Activity, Museum, EXHIBITIONHALL\n" +
                                    "WHERE Activity.ZID = EXHIBITIONHALL.ZID AND EXHIBITIONHALL.MID = Museum.MID AND Museum.Mname = \'" + mu + "\'"));
                    tablePart.add("Exhibition Halls_" + mu, new TablePanel(dbHandler,
                            "SELECT ZNAME AS Hall_Name, ZID AS Zoon_ID, ISOPEN, FLOOR FROM ExhibitionHall, Museum\n" +
                                    "WHERE EXHIBITIONHALL.MID = MUSEUM.MID AND MUSEUM.MNAME = \'" + mu + "\'"));
                    tablePart.add("Guides_" + mu, new TablePanel(dbHandler,
                            "SELECT DISTINCT GUIDE1.GID AS GID, GNAME AS Guide_name, GUIDE2.FIELD AS Field, OFFICE\n" +
                                    "FROM GUIDE1, GUIDE2, EXHIBITS3, EXHIBITS4, MUSEUM\n" +
                                    "WHERE GUIDE1.FIELD = GUIDE2.FIELD AND GUIDE1.GID = EXHIBITS3.GID AND\n" +
                                    "      EXHIBITS3.ZID = EXHIBITS4.ZID AND EXHIBITS4.MID = MUSEUM.MID\n AND MUSEUM.MNAME = \'" + mu + "\'"));
                    tablePart.add("Exhibits_" + mu, new TablePanel(dbHandler,
                            "SELECT DISTINCT ENAME, BIRTHPLACE, EYEAR, CATEGORY FROM EXHIBITS2, EXHIBITS3, EXHIBITS4, MUSEUM\n" +
                                    "WHERE EXHIBITS2.GID = EXHIBITS3.GID AND EXHIBITS3.ZID = EXHIBITS4.ZID AND EXHIBITS4.MID = MUSEUM.MID AND MUSEUM.MNAME = \'" + mu + "\'"));
                    tablePart.add("Films_" + mu, new TablePanel(dbHandler,
                            "SELECT DISTINCT FNAME, SHOWTIME, FILM.FID AS FID, FTIME AS TIME FROM FILM, PLAY, CINEMA, MUSEUM\n" +
                                    "WHERE FILM.FID = PLAY.FID AND CINEMA.ZID = PLAY.ZID AND CINEMA.MID = MUSEUM.MID AND MUSEUM.MNAME = \'" + mu + "\'"));
                    tablePart.add("Souvenirs_" + mu, new TablePanel(dbHandler,
                            "SELECT DISTINCT SNAME, PRICE, INVENTORY, SELL.SID AS SID FROM SOUVENIR, SELL, GIFTSTORE, MUSEUM\n" +
                                    "WHERE SOUVENIR.SID = SELL.SID AND SELL.ZID = GIFTSTORE.ZID AND GIFTSTORE.MID = MUSEUM.MID AND MUSEUM.MNAME = \'" + mu + "\'"));
                }
                break;
            case "Activity":
                tablePart.add("Activities", new TablePanel(dbHandler, "SELECT * FROM Activity"));
                break;
            case "Exhibit":

                break;
            case "Film":

                break;
            case "Souvenir":

                break;
        }
    }

    private void initialTree(String s) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(s);
        switch (s) {
            case "Museum":
                MuseumRoot(root);
                break;
            case "Activity":
                ActivityRoot(root);
                break;
            case "Exhibit":
                ExhibitRoot(root);
                break;
            case "Film":
                FilmRoot(root);
                break;
            case "Souvenir":
                SouvenirRoot(root);
                break;
        }
        tree.setModel(new DefaultTreeModel(root));
        tree.setPreferredSize(new Dimension(TREE_WIDTH, HEIGHT));
        tree.addTreeSelectionListener(this);
        tree.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        treePart.add(tree);
    }

    private void SouvenirRoot(DefaultMutableTreeNode root) {

    }

    private void FilmRoot(DefaultMutableTreeNode root) {

    }

    private void ExhibitRoot(DefaultMutableTreeNode root) {

    }

    private void ActivityRoot(DefaultMutableTreeNode root) {
        DefaultMutableTreeNode activities = new DefaultMutableTreeNode("Activities");
    }

    private void MuseumRoot(DefaultMutableTreeNode root) {
        DefaultMutableTreeNode museums = new DefaultMutableTreeNode("Museums");
        DefaultMutableTreeNode halls = new DefaultMutableTreeNode("Exhibition Halls");
        DefaultMutableTreeNode activities = new DefaultMutableTreeNode("Activities");
        DefaultMutableTreeNode guides = new DefaultMutableTreeNode("Guides");
        DefaultMutableTreeNode exhibits = new DefaultMutableTreeNode("Exhibits");
        DefaultMutableTreeNode films = new DefaultMutableTreeNode("Films");
        DefaultMutableTreeNode souvenirs = new DefaultMutableTreeNode("Souvenirs");
        root.add(museums);
        root.add(halls);
        root.add(activities);
        root.add(guides);
        root.add(exhibits);
        root.add(films);
        root.add(souvenirs);
        String[] museumNames =dbHandler.getMuseumNames().toArray(new String[dbHandler.getMuseumNames().size()]);
        for (String s: museumNames) {
            halls.add(new DefaultMutableTreeNode(s));
            activities.add(new DefaultMutableTreeNode(s));
            guides.add(new DefaultMutableTreeNode(s));
            exhibits.add(new DefaultMutableTreeNode(s));
            films.add(new DefaultMutableTreeNode(s));
            souvenirs.add(new DefaultMutableTreeNode(s));
        }
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        String s = e.getNewLeadSelectionPath().getLastPathComponent().toString();
        switch (s) {
            case "Museums":
                cardLayout.show(tablePart, "Museums");
                break;
            case "Activities":
                cardLayout.show(tablePart, "Activities");
                break;
            case "Exhibits":
                cardLayout.show(tablePart, "Exhibits");
                break;
            case "Films":
                cardLayout.show(tablePart, "Films");
                break;
            case "Souvenirs":
                cardLayout.show(tablePart, "Souvenirs");
                break;
            default:
                String event = e.getNewLeadSelectionPath().getParentPath().getLastPathComponent().toString() +
                        "_" + e.getNewLeadSelectionPath().getLastPathComponent().toString();
                System.out.println(event);
                cardLayout.show(tablePart, event);
        }
    }
}

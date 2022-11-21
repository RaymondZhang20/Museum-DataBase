package GUI;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.List;

public class TreePanel extends JPanel {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 1000;
    private JTree tree = new JTree();

    public TreePanel(String[] ls) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(ls[0]);
        for (String s: ls) {
            root.add(new DefaultMutableTreeNode(s));
        }
//        if (teams != null) {
//            for (Team t : teams) {
//                team.add(new DefaultMutableTreeNode(t.getName()));
//            }
//        }
        tree.setModel(new DefaultTreeModel(root));
        tree.setPreferredSize(new Dimension(WIDTH, HEIGHT));
//        tree.addTreeSelectionListener(main);
        tree.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        add(tree);
    }
}

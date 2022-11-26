package GUI;
import database.DatabaseConnectionHandler;
import model.ExhibitionHall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class InsertQueryFrame extends JFrame implements ActionListener {
    private final DatabaseConnectionHandler dbhandler;
    private String[] Museums = {"India","Aus","U.S.A","England","Newzealand", "China"};
    private String[] ExhibitionHall = {"India","Aus","U.S.A","England"};
//    private String[] Museums = (String[]) dbhandler.getMuseumNames().toArray();

    //or retrieve from database and
    private JTextField ActivityAid = new JTextField(10);
    private JTextField ActivityTitle = new JTextField(80);
    private JTextField ActivityDate = new JTextField(15);
    private JComboBox ActivityMuseumDropDown = new JComboBox(Museums);
    private JComboBox ActivityExhibitionHallDropDown = new JComboBox(ExhibitionHall);


    private JTextField ExhibitsEid = new JTextField(10);
    private JTextField ExhibitsName = new JTextField(50);
    private JTextField ExhibitsBirthplace = new JTextField(50);
    private JTextField ExhibitsYear = new JTextField(10);
    private JTextField ExhibitsCategory = new JTextField(50);
    private JTextField ExhibitsGid = new JTextField(10);

    private JComboBox ExhibitsMuseumDropDown = new JComboBox(Museums);
    private JComboBox ExhibitsExhibitionHallDropDown = new JComboBox(ExhibitionHall);

//    private JTextField GuideGid;
//    private JTextField GuideName;
//    private JTextField GuideField;
//    private JTextField GuideOffice;

    private JTextField SouvenirSid = new JTextField(10);
    private JTextField SouvenirName = new JTextField(50);
    private JTextField SouvenirPrice = new JTextField(10);

    private JTextField SouvenirInventory = new JTextField(10);
    private JCheckBox Sms1, Sms2, Sms3, Sms4, Sms5, Sms6;

    private JTextField FilmFid = new JTextField(10);
    private JTextField FilmName = new JTextField(50);
    private JTextField FilmTime = new JTextField(10);

    private JCheckBox Fms1, Fms2, Fms3, Fms4, Fms5, Fms6;


    private final String tableName;
    private static final String ADD_ACTIVITY_ACTION = "ADD_ACTIVITY_ACTION";
    private static final String ADD_EXHIBIT_ACTION = "ADD_EXHIBIT_ACTION";
    private static final String ADD_SOUVENIR_ACTION = "ADD_SOUVENIR_ACTION";
    private static final String ADD_FILM_ACTION = "ADD_FILM_ACTION";
    private static final String SOUVENIR_MORE_ACTION = "SOUVENIR_MORE_ACTION";
    private static final String FILM_MORE_ACTION = "FILM_MORE_ACTION";


    public InsertQueryFrame(String tableName, DatabaseConnectionHandler dbhandler){
        super("Insert data");
        this.dbhandler = dbhandler;
        this.tableName = tableName;
        switch (tableName) {
            case "Activity":
                this.setActivityLabelsFieldsButtons();
                break;
            case "Exhibits":
                this.setExhibitsLabelsFieldsButtons();
                break;
            case "Souvenir":
                this.setSouvenirLabelsFieldsButtons();
                break;
            case "Souvenir More Info":
                this.setSouvenirMoreInfo();
                break;
            case "Film":
                this.setFilmLabelsFieldsButtons();
                break;
            case "Film More Info":
                this.setFilmMoreInfo();
                break;
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        ActivityTitle.getFont();
    }




    private void addTextLabel(String text, int y, int width) {
        JLabel aLabel = new JLabel(text);
        aLabel.setBounds(50, y, width, 20);
        add(aLabel);
        aLabel.setForeground(Color.darkGray);
    }
    private void addTextField(JTextField field, int y) {
        field.setBounds(50, y, 300, 20);
        add(field);
    }

    private void addComboBoxDropDown(String[] strings, int y) {
        JComboBox DropDown = new JComboBox(strings);
        DropDown.setBounds(50, y,300,20);
        add(DropDown);
        DropDown.setForeground(Color.darkGray);
    }

    private void addFinalButton(String text, int y, String action) {
        JButton addButton = new JButton(text);
        addButton.setBounds(155, y,150,20);
        addButton.setActionCommand(action);
        addButton.addActionListener(this);
        addButton.setForeground(Color.darkGray);
        add(addButton);
    }


    private void setActivityLabelsFieldsButtons() {
        setPreferredSize(new Dimension(480, 450));
        setLayout(null);

        addTextLabel("Enter Activity ID: ", 40, 350);
        addTextField(ActivityAid, 70);
        addTextLabel("Enter Activity title: ", 100, 350);
        addTextField(ActivityTitle, 130);
        addTextLabel("Enter activity date: (In a date format yyyy-MM-dd, e.g. 2021-12-31)", 160, 450);
        addTextField(ActivityDate, 190);
        addTextLabel("Select Museum: ", 220, 300);


        ActivityMuseumDropDown.setBounds(50, 250,300,20);
        add(ActivityMuseumDropDown);
        ActivityMuseumDropDown.setForeground(Color.darkGray);

        addTextLabel("Select Exhibition Hall: ", 280, 300);

        ActivityExhibitionHallDropDown.setBounds(50, 310,300,20);
        add(ActivityExhibitionHallDropDown);
        ActivityExhibitionHallDropDown.setForeground(Color.darkGray);

        addFinalButton("Add this activity!", 340, ADD_ACTIVITY_ACTION);
    }


    private void setExhibitsLabelsFieldsButtons() {
        setPreferredSize(new Dimension(480, 660));
        setLayout(null);

        addTextLabel("Enter Exhibits ID: ", 40, 350);
        addTextField(ExhibitsEid, 70);
        addTextLabel("Enter Exhibits Name: ", 100, 350);
        addTextField(ExhibitsName, 130);
        addTextLabel("Enter Exhibits Birthplace: ", 160, 350);
        addTextField(ExhibitsBirthplace, 190);
        addTextLabel("Enter Exhibits Year: ", 220, 350);
        addTextField(ExhibitsYear, 250);
        addTextLabel("Enter Exhibits category: ", 280, 400);
        addTextField(ExhibitsCategory, 310);
        addTextLabel("Enter Gid of the guide responsible: ", 340, 400);
        addTextField(ExhibitsGid, 370);
        addTextLabel("Select Museum: ", 400, 300);

        ExhibitsMuseumDropDown.setBounds(50, 430,300,20);
        add(ExhibitsMuseumDropDown);
        ExhibitsMuseumDropDown.setForeground(Color.darkGray);

        addTextLabel("Select Exhibition Hall: ", 460, 300);

        ExhibitsExhibitionHallDropDown.setBounds(50, 490,300,20);
        add(ExhibitsExhibitionHallDropDown);
        ExhibitsExhibitionHallDropDown.setForeground(Color.darkGray);

        addFinalButton("Add this Exhibit!", 540, ADD_EXHIBIT_ACTION);
    }

    private void setSouvenirLabelsFieldsButtons() {
        setPreferredSize(new Dimension(480, 400));
        setLayout(null);

        addTextLabel("Enter Souvenir ID: ", 40, 350);
        addTextField(SouvenirSid, 70);
        addTextLabel("Enter Souvenir Name: ", 100, 350);
        addTextField(SouvenirName, 130);
        addTextLabel("Enter Souvenir Price: ", 160, 350);
        addTextField(SouvenirPrice, 190);
        addFinalButton("Add this Souvenir!", 230, ADD_SOUVENIR_ACTION);
    }
    private void setSouvenirMoreInfo() {
        setPreferredSize(new Dimension(480, 500));
        setLayout(null);

        addTextLabel("Select Museums this souvenir will be sold at: ", 40, 400);

        Sms1 = new JCheckBox(Museums[0]);
        Sms1.setBounds(50,100,300,20);
        Sms2 = new JCheckBox(Museums[1]);
        Sms2.setBounds(50,130,300,20);
        Sms3 = new JCheckBox(Museums[2]);
        Sms3.setBounds(50,160,300,20);
        Sms4 = new JCheckBox(Museums[3]);
        Sms4.setBounds(50,190,300,20);
        Sms5 = new JCheckBox(Museums[4]);
        Sms5.setBounds(50,220,300,20);
        Sms6 = new JCheckBox(Museums[5]);
        Sms6.setBounds(50,250,300,20);
        add(Sms1);add(Sms2);add(Sms3);add(Sms4);add(Sms5);add(Sms6);

        addTextLabel("Enter Inventory: ", 280, 400);
        addTextField(SouvenirInventory, 310);

        addFinalButton("Confirm Selection", 360, SOUVENIR_MORE_ACTION);

    }
    private void setFilmLabelsFieldsButtons() {
        setPreferredSize(new Dimension(480, 400));
        setLayout(null);

        addTextLabel("Enter Film ID: ", 40, 350);
        addTextField(FilmFid, 70);
        addTextLabel("Enter Film Name: ", 100, 350);
        addTextField(FilmName, 130);
        addTextLabel("Enter Film Time: (film length in minutes, e.g. 65)", 160, 400);
        addTextField(FilmTime, 190);
        addFinalButton("Add this Film!", 230, ADD_FILM_ACTION);
    }
    private void setFilmMoreInfo() {

        setPreferredSize(new Dimension(480, 400));
        setLayout(null);

        addTextLabel("Select Museums this film will be played at: ", 40, 400);

        Fms1 = new JCheckBox(Museums[0]);
        Fms1.setBounds(50,100,300,20);
        Fms2 = new JCheckBox(Museums[1]);
        Fms2.setBounds(50,130,300,20);
        Fms3 = new JCheckBox(Museums[2]);
        Fms3.setBounds(50,160,300,20);
        Fms4 = new JCheckBox(Museums[3]);
        Fms4.setBounds(50,190,300,20);
        Fms5 = new JCheckBox(Museums[4]);
        Fms5.setBounds(50,220,300,20);
        Fms6 = new JCheckBox(Museums[5]);
        Fms6.setBounds(50,250,300,20);
        add(Fms1);add(Fms2);add(Fms3);add(Fms4);add(Fms5);add(Fms6);

        addFinalButton("Confirm Selection", 300, FILM_MORE_ACTION);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case ADD_ACTIVITY_ACTION:
                if (ActivityAid.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Your activity must have an Aid!");
                    return;
                }
                Integer Aid = null;
                try {
                    Aid = Integer.parseInt(ActivityAid.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage() + "is not a valid Aid!");
                    return;
                }
                String title = ActivityTitle.getText();
                String Adate = ActivityDate.getText();
                if (title.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Your activity must have a title!");
                    return;
                }
                if (Adate.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Your activity must have a date!");
                    return;
                }

                String Amuseum = (String) ActivityMuseumDropDown.getItemAt(ActivityMuseumDropDown.getSelectedIndex());
                String AexhibitionHall = (String) ActivityExhibitionHallDropDown.getItemAt(ActivityExhibitionHallDropDown.getSelectedIndex());


                JOptionPane.showMessageDialog(null,
                         " Aid: " + Aid +"title: " + title + " Adate: "
                                 + Adate + " Museum: "+Amuseum + " ExhibtionHall: "+ AexhibitionHall);
                break;
            case ADD_EXHIBIT_ACTION:
                if (ExhibitsEid.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Your activity must have an Eid!");
                    return;
                }
                Integer Eid = null;
                try {
                    Eid = Integer.parseInt(ExhibitsEid.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage() + "is not a valid Eid!");
                    return;
                }
                String Ename = ExhibitsName.getText();
                if (Ename.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Your Exhibit must have a name!");
                    return;
                }
                String birthplace = ExhibitsBirthplace.getText();
                String Eyear = ExhibitsYear.getText();
                try {
                    if (!Eyear.isEmpty()){
                        Integer.parseInt(Eyear);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage() + "is not a valid year!");
                    return;
                }
                String category = ExhibitsCategory.getText();
                if (category.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Your Exhibit must have a category!");
                    return;
                }
                if (ExhibitsGid.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Your activity must have an Gid!");
                    return;
                }
                Integer Gid = null;
                try {
                    Gid = Integer.parseInt(ExhibitsGid.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage() + "is not a valid Gid!");
                    return;
                }
                String Emuseum = (String) ExhibitsMuseumDropDown.getItemAt(ExhibitsMuseumDropDown.getSelectedIndex());
                String EexhibitionHall = (String) ExhibitsExhibitionHallDropDown.getItemAt(ExhibitsExhibitionHallDropDown.getSelectedIndex());


                JOptionPane.showMessageDialog(null,
                        " Eid: " + Eid +"Ename: " + Ename + " birth: "
                                + birthplace + " Eyear: "+Eyear + " category: "+ category
                                +" Gid: "+ Gid + " Museum: "+Emuseum + " ExhibtionHall: "+ EexhibitionHall);
                break;
            case ADD_SOUVENIR_ACTION:
                if (SouvenirSid.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Your souvenir must have an Sid!");
                    return;
                }
                Integer Sid = null;
                try {
                    Sid = Integer.parseInt(SouvenirSid.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage() + "is not a valid Sid!");
                    return;
                }
                String Sname = SouvenirName.getText();
                if (Sname.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Your Souvenir must have a name!");
                    return;
                }
                if (SouvenirPrice.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Your Souvenir must have a price!");
                    return;
                }
                Double price = null;
                try {
                    price = Double.parseDouble(SouvenirPrice.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage() + "is not a valid price!");
                    return;
                }


                JOptionPane.showMessageDialog(null,
                        " Sid: " + Sid +"Sname: " + Sname + " price: "+price);

                new InsertQueryFrame("Souvenir More Info",null);

                break;
            case ADD_FILM_ACTION:
                if (FilmFid.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Your film must have an Fid!");
                    return;
                }
                Integer Fid = null;
                try {
                    Fid = Integer.parseInt(FilmFid.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage() + "is not a valid Fid!");
                    return;
                }
                String Fname = FilmName.getText();
                if (Fname.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Your film must have a name!");
                    return;
                }

                if (FilmTime.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Your film must have an time!");
                    return;
                }

                Integer Ftime = null;
                try {
                    Ftime = Integer.parseInt(FilmTime.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage() + "is not a valid time!");
                    return;
                }
                JOptionPane.showMessageDialog(null,
                        " Fid: " + Fid +"Fname: " + Fname + " Ftime: "+Ftime);

                new InsertQueryFrame("Film More Info",null);

                break;
            case SOUVENIR_MORE_ACTION:
                String S1 = " ", S2= " ", S3= " ", S4= " ", S5= " ", S6 = " ";
                if(Sms1.isSelected()){
                    S1=Museums[0];
                }
                if(Sms2.isSelected()){
                    S2=Museums[1];
                }
                if(Sms3.isSelected()){
                    S3=Museums[2];
                }
                if(Sms4.isSelected()){
                    S4=Museums[3];
                }
                if(Sms5.isSelected()){
                    S5=Museums[4];
                }
                if(Sms6.isSelected()){
                    S6=Museums[5];
                }
                String[] Sv = {S1, S2, S3, S4, S5, S6};
                for (String var : Sv)
                {
                    if(var!=" ") {
                        JOptionPane.showMessageDialog(null, var);
                    }
                }
                if (SouvenirInventory.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Your souvenir must have an inventory!");
                    return;
                }

                Integer inventory = null;
                try {
                    inventory = Integer.parseInt(SouvenirInventory.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage() + "is not a valid inventory!");
                    return;
                }
                JOptionPane.showMessageDialog(null, inventory);
                break;
            case FILM_MORE_ACTION:
                //
                break;
        }

    }

    public static void main(String[] args) {
        new InsertQueryFrame("Souvenir",null);
    }
}

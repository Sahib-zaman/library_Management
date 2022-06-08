package Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class design  implements ItemListener,ActionListener {
    LinkedList<String> al=new LinkedList<String>();

    String [] array = new String[100];
    String set;
int i;
int size =0;
Connection conn;
Statement statement;

     LocalDateTime dates = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
String date=dates.format(formatter);


    JTextArea trying = new JTextArea();
        JLabel name = new JLabel("Name");
        JLabel id = new JLabel("Name");
        JTextField namet=new JTextField();
        JTextField ids=new JTextField();

    Font font= new Font("Courier", Font.PLAIN, 20);
    JFrame f = new JFrame();
    JButton logOut = new JButton("OK");
    JLabel library= new JLabel("Library");
    JButton issueBook = new JButton("");
    JButton returnBook = new JButton("");
    JButton availableBook = new JButton("");
    JButton addBook = new JButton();
    JButton EditionButton = new JButton();
    JComboBox comboBox = new JComboBox();
    JTextArea textArea=new JTextArea("",0,TextArea.SCROLLBARS_VERTICAL_ONLY);
    JScrollPane scroll = new JScrollPane ();

    JLabel nameL=new JLabel();
    JLabel cnicL=new JLabel();
     JTextArea text = new JTextArea();
    JTextArea Searchbar = new JTextArea();

    public void frame(){

//        trying.set

        f.setLayout(null);
        f.setSize(1000, 600);
        f.setTitle("Management");
        f.setResizable(false);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(new Color(22, 100, 159));
        f.setLocationRelativeTo(null);
        System.out.println("Successful");
        library.setFont(new Font("Monospaced", Font.BOLD + Font.ITALIC, 40));
        library.setBounds(200,10,500,100);
        library.setText("Library Management");
        library.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
        library.setForeground(Color.white);


        logOut.setBounds(900-20,10,100,30);
        logOut.setText("Log Out");
        logOut.setBackground(Color.white);
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            logInPage login = new logInPage();
                if (e.getSource()==logOut){
                    f.setVisible(false);
                    login.login();
                }
            }
        });





          textArea.setBounds(450,150,500,400);
          textArea.setFont(new Font("Arial",Font.PLAIN,15));


        String box[]={"Select","Available","Return","Issue","Add","delete"};
        for (int b = 0; b < box.length; b++) {
            comboBox.addItem(box[b]);
        }
        comboBox.setFont(font);
        comboBox.setBounds(100,300,300,30+10);
        comboBox.addItemListener(this);

        text.setLineWrap(true);
        text.setBounds(100,360,300,30);
        text.setFont(new Font("courier",Font.PLAIN,20));


        EditionButton.setBounds(300,400,100,30);
        EditionButton.setText("Search");
        EditionButton.addActionListener(this);


        f.add(namet);
        f.add(nameL);
        f.add(ids);
        f.add(id);
        f.add(textArea);
        f.add(logOut);
        f.add(scroll);
        f.add(library);
        f.add(availableBook);
        f.add(issueBook);
        f.add(EditionButton);
        f.add(returnBook);
        f.add(text);
        f.add(comboBox);
        f.add(Searchbar);
        f.add(addBook);
    }



    public void availableBook(){
        try{
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/librarymanagement", "root", "Wrqgwjkp@967@#5");
            statement = conn.createStatement();
            String sql = "SELECT * FROM librarymanagement.availablebook";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                set=resultSet.getString("availablebook");
                textArea.append(set+"\n");
                al.add(set);
                array[i]=set;
                size++;
            }
            String a = text.getText();
             for (int i = 0;i<size;i++){
                 if (a==array[i]){
                     textArea.setText("yes");
                     System.out.println("its available");
                 }
                 else System.out.println("not ");
             }
        }
        catch (Exception e){
            System.out.printf(String.valueOf(e));
        }
    }


    public void addBook(){
        try{
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/librarymanagement", "root", "Wrqgwjkp@967@#5");
            statement = conn.createStatement();
            String record =
                    "INSERT INTO `librarymanagement`.`availablebook` (`availablebook`) VALUES ('"+text.getText()+"')";
            statement.executeUpdate(record);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void search() {
        try{
            String text=this.text.getText();
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/librarymanagement", "root", "Wrqgwjkp@967@#5");
            statement = conn.createStatement();
            String sql = "SELECT * FROM librarymanagement.availablebook where availablebook='" + text + "' ";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next() ){
                this.textArea.setText("This book is available: "+text);
                this.textArea.setForeground(Color.black);
            }
            else textArea.setText("not found");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void issueBook(){
        try {

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/librarymanagement", "root", "Wrqgwjkp@967@#5");
            statement = conn.createStatement();
            String record =
                    "INSERT INTO `librarymanagement`.`issue` (`name`,`cnic`,`issuebook`,`date`) VALUES ('"+namet.getText()+"','"+ids.getText()+"','"+text.getText()+"','"+date+"')";
            statement.executeUpdate(record);

            delete();
        }
        catch (Exception e){
            System.out.println(e);

        }
    }


    public void delete(){

try {
    conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/librarymanagement", "root", "Wrqgwjkp@967@#5");
    statement = conn.createStatement();

    String delete=
            "delete from  librarymanagement.availablebook where  availablebook=('"+text.getText()+"')";
    statement.executeUpdate(delete);
    PreparedStatement preparedStmt = conn.prepareStatement(delete);
    preparedStmt.execute();
    conn.close();

    }
catch (Exception e){
    System.out.println(e);
}

    }


    public void issueRecord(){
        textArea.setText("");
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/librarymanagement", "root", "Wrqgwjkp@967@#5");
            statement = conn.createStatement();
            String sql = "SELECT * FROM librarymanagement.issue";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String cnic = resultSet.getString("cnic");
                String issuebook = resultSet.getString("issuebook");
                String date = resultSet.getString("date");
                textArea.append("Name: "+name+"    Registration No: "+cnic+"    Book: "+issuebook+"    Date: "+date+"\n");

            }
        text.setText("");
        } catch (Exception e) {
            System.out.printf(String.valueOf(e));

        }

    }

    public void returnBook(){

        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/librarymanagement", "root", "Wrqgwjkp@967@#5");
            statement = conn.createStatement();

 String sql = "SELECT * FROM librarymanagement.issue where name='" + namet.getText() + "' and cnic='" + ids.getText()+ "'";
                                                  ResultSet resultSet = statement.executeQuery(sql);
//
                                                  if (resultSet.next() ) {
                             String delete=
                               "delete from  librarymanagement.issue where  issuebook=('"+text.getText()+"')";
                                                       PreparedStatement preparedStmt = conn.prepareStatement(delete);
                                                      preparedStmt.execute();

                                                      System.out.println("delete hogya ab ye");
                                                      String record =
                               "INSERT INTO `librarymanagement`.`availablebook` (`availablebook`) VALUES ('"+text.getText()+"')";
                                                      statement.executeUpdate(record);
                                                  textArea.setText("this book is return: "+text.getText());
                                                  }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }






    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource()==comboBox) {

            if (comboBox.getSelectedIndex()==0) {
                EditionButton.setText("Search");
                textArea.setText("");
                availableBook();
                form(1);
                }
        else    if (comboBox.getSelectedIndex() == 1) {
                 textArea.setText("");
                EditionButton.setText("Search");
                availableBook();
                form(1);
            }
        else if (comboBox.getSelectedIndex() == 4) {
                EditionButton.setText("Add");
                form(1);

            }
            else if (comboBox.getSelectedIndex() == 2) {
                EditionButton.setText("Return");
                form();
            }
            else if (comboBox.getSelectedIndex() == 3) {
                form();
                EditionButton.setText("Issues");
            }
            else if (comboBox.getSelectedIndex() == 5) {
                EditionButton.setText("Delete");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==EditionButton){
            if (EditionButton.getText()=="Add"){
                addBook();
                String a ="";
                String b =null;
                if (text.getText()==a || text.getText()==b){

                    textArea.setText("Add Book");
                }
                else {
                    textArea.setText("This Book is add: "+text.getText());
                    System.out.printf("AddBook");
                    text.setText("");
                }
             }
            if (EditionButton.getText()=="Return"){
                returnBook();
            }
            if (EditionButton.getText()=="Issues"){
                issueBook();
                issueRecord();
            }
            if (EditionButton.getText()=="Delete"){
                deleteRecord();
                System.out.println("delete");
            }
            if (comboBox.getSelectedIndex()==0||comboBox.getSelectedIndex()==1){
                try {
                    search();
                }catch (Exception exception){
                    System.out.print(exception);
                }
            }


   }
        }








    public void form(int a){
        ids.setVisible(false);
        id.setVisible(false);
        namet.setVisible(false);
        nameL.setVisible(false);

    }








    public void form(){
        Font font = new Font("arial",Font.PLAIN,30);
        nameL.setText("Name");
        nameL.setVisible(true);
        nameL.setBounds(100,100,300,30+10);
        nameL.setForeground(Color.white);
        nameL.setFont(font);


        namet.setFont(new Font("arial",Font.BOLD,20));
        namet.setVisible(true);
        namet.setBounds(100,200-50,300,30+10);

        id.setText("Registration No:");
        id.setVisible(true);
        id.setBounds(100,200-50+50,300,30+10);
        id.setForeground(Color.white);
        id.setFont(font);

        ids.setVisible(true);
    ids.setBounds(100,300-55,300,30+10);
    ids.setFont(new Font("arial",Font.BOLD,20));
}


public void deleteRecord(){

    try {
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/librarymanagement", "root", "Wrqgwjkp@967@#5");
        statement = conn.createStatement();
        String delete=
                "delete from  librarymanagement.availablebook where  availablebook=('"+text.getText()+"')";
        statement.executeUpdate(delete);
        PreparedStatement preparedStmt = conn.prepareStatement(delete);
        preparedStmt.execute();
        System.out.println("hogya");
        textArea.setText("This book is delete form list: "+text.getText());
        conn.close();

    }
    catch (Exception e){
        System.out.println(e);
    }

}





































































































}

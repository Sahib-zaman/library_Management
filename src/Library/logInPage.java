package Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class logInPage  {

    JFrame f = new JFrame("login");
    JTextField textField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton();
    JButton signUp = new JButton();
    JButton show = new JButton();
    JLabel label = new JLabel("sign up or login");

    public void login(){

        f.setLayout(null);
        f.setSize(500, 400);
        f.setTitle("Management");
        f.setResizable(false);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(new Color(22, 100, 159));
        f.setLocationRelativeTo(null);

        int w = 500;
        int h=400;
        int x = (500-200)/2;

        label.setBounds(x,10,200,30);
        label.setForeground(Color.white);
        label.setFont(new Font("Arial", Font.ITALIC, 25));

        int t = (500-300)/2;

        textField.setFont(new Font("Arial", Font.ITALIC, 15));
        textField.setBounds(t,10+30+30,300,30);

        int p = (500-300)/2;

        passwordField.setFont(new Font("Arial", Font.ITALIC, 15));
        passwordField.setBounds(p,10+40+70,300,30);
        passwordField.setEchoChar('*');
        passwordField.setFont(new Font("Arial", Font.ITALIC, 15));

        show.setBounds(300+10+p,120,70,30);
        show.setText("Show");
        show.addActionListener(new ActionListener() {


        boolean b = true;

//        Using anonymous

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==show){
                if (b==true){
                passwordField.setEchoChar((char) 0);
                b=false;
                }
                
                else if (b==false){
                passwordField.setEchoChar('*');
                b=true;
                }
                }
                }
                });


        int l = (500-100)/2;
        loginButton.setBounds(l,50+120,100,30);
        loginButton.setText("Login");
        loginButton.setFont(new Font("Arial", Font.ITALIC, 15));

        loginButton.addActionListener(new ActionListener() {
                                            Connection conn;
                                            Statement statement;
                                            ResultSet resultSet;
                                          @Override
                                          public void actionPerformed(ActionEvent e) {
            //                                what is            JOptionPane
                                              try {
                                                  design des= new design();
                                       conn = DriverManager.getConnection(
                                           "jdbc:mysql://localhost:3306/librarymanagement", "root", "Wrqgwjkp@967@#5");
                                  statement = conn.createStatement();
                                  String sql = "SELECT * FROM librarymanagement.loging where id='" + textField.getText() + "' and pass='" + passwordField.getText().toString() + "'";
                                  ResultSet resultSet = statement.executeQuery(sql);
                               String lengths=textField.getText();
                                            if (resultSet.next() ){
                                                       des.frame();
                                                       des.f.setVisible(true);
                                                       f.setVisible(false);
                                                       System.out.println("SuccessFull");
                                                  }

                                             else    if(resultSet.next()){
                                                      String a =lengths;
                                                      if (a==null || a == ""){
                                                          System.out.println("false");
//                                                          des.f.setVisible(true);

                                                      }
                                                      else {
                                                          System.out.println("true");
                                                      }
                                                  }
                                                   else {
                                                        textField.setText("Invalid email or password");
                                                   }
                                              } catch (Exception eeee) {
                                                  System.out.println(eeee);
                                              }
                                          }
                                      });
        int pW = (500-100)/2;
        signUp.setBounds(pW,220,100,30);
        signUp.setText("sign Up");
        signUp.setFont(new Font("Arial", Font.ITALIC, 15));

        signUp.addActionListener(new ActionListener() {
            Connection conn;
            Statement statement;
            ResultSet resultSet;
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField.getText();
                String passing=passwordField.getText();
                if (e.getSource()==signUp) {
               try {
                    conn = DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/librarymanagement","root","Wrqgwjkp@967@#5");
                   statement = conn.createStatement();
                   String record =
                   "INSERT INTO `librarymanagement`.`loging` (`id`, `pass`) VALUES ('"+id+"', '"+passing+"')";
                    statement.executeUpdate(record);
               }
               catch (Exception e3){
                   System.out.println(e3);
               }
               textField.setText(null);
               passwordField.setText(null);
                }
            }
        });

        f.add(signUp);
        f.add(loginButton);
        f.add(passwordField);
        f.add(show);
        f.add(label);
        f.add(textField);
        f.setVisible(true);

    }
}


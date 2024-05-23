package com.patikadev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Example extends JFrame {
    private JPanel wrapper;
    private JPanel w_top;
    private JTextField fld_username;
    private JPasswordField fld_pasword;
    private JButton bt_login;

    public Example() {
        add(wrapper);
        setSize(500,500);
        setTitle("Uygulamam");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width -getSize().width)/2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height -getSize().height)/2;
        setLocation(x,y);
        setResizable(false);
        setVisible(true);


        bt_login.addActionListener(e -> {
        if (fld_username.getText().length()==0 || fld_pasword.getText().length()==0){
            JOptionPane.showMessageDialog(null,"Tüm alanları doldurun","HATA",JOptionPane.INFORMATION_MESSAGE);
        }



        } );
    }


}

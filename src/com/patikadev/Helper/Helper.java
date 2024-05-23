package com.patikadev.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static void setLayout () {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){

                if ("Nimbus".equals(info.getName())) {
                    try {
                        UIManager.setLookAndFeel(info.getClassName());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }
        }
    }
    public static int ScreenCenter(String eksen, Dimension size){
        int point;
        switch (eksen){
            case "x":
                point= (Toolkit.getDefaultToolkit().getScreenSize().width-size.width)/2;
                break;
            case "y":
                point= (Toolkit.getDefaultToolkit().getScreenSize().height-size.height)/2;
                break;
            default:
                point= 0;
        }
        return point;
    }

    public static boolean isFieldEmpty (JTextField field){
        return field.getText().trim().isEmpty();

    }

    public static void showmsg(String str){
        String mesaj;
        String title;
        switch (str){
            case "fill":
                mesaj="Lütfen Tüm Alanları Doldurunuz ! ";
                title="Hata";
                break;
            case "done":
                mesaj="İşlem Başarılı. ";
                title="Sonuç";
                break;
            case "error":
                mesaj="İşlem Başarısız!  ";
                title="Hata";
                break;
            default:
                mesaj=str;
                title="Mesaj";
        }

        JOptionPane.showMessageDialog(null,mesaj,title,JOptionPane.INFORMATION_MESSAGE);
    }



}

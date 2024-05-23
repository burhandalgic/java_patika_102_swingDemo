package com.patikadev.Model;

import com.patikadev.Helper.DbConnector;
import com.patikadev.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
    private int id ;
    private String name;
    private String uname;
    private String password;
    private String type;
    public User(){}

    public User(int id, String name, String uname, String password, String type) {
        this.id = id;
        this.name = name;
        this.uname = uname;
        this.password = password;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static ArrayList<User> getList () {
        ArrayList<User> list = new ArrayList<>();
        String query = "Select * from user";

        try {
            Statement st = DbConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                User obj=new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setPassword(rs.getString("password"));
                obj.setUname(rs.getString("username"));
                obj.setType(rs.getString("type"));
                list.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static boolean add (String name, String uname, String password, String type){
        String query = "INSERT INTO user (name,username,password,type) values (?,?,?,?)";
        if (getFetch(uname)!=null){
            Helper.showmsg("Aynı kullanıcıyı ekleyemezsiniz ! ");
            return false;
        }

        try {
            PreparedStatement pr = DbConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,uname);
            pr.setString(3,password);
            pr.setString(4,type);
            if (pr.executeUpdate() ==-1) {
                Helper.showmsg("error");
                return false;
            }else {
                Helper.showmsg("done");
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Helper.showmsg("error");
            return false;
        }

    }

    public static User getFetch (String uname){
        User user = null;
        String query = " SELECT * from user where username=? ";
        try {
            PreparedStatement st = DbConnector.getInstance().prepareStatement(query);
            st.setString(1,uname);
            ResultSet res = st.executeQuery();
            if (res.next()){
                user =new User();
                user.setId(res.getInt("id"));
                user.setName(res.getString("name"));
                user.setPassword(res.getString("password"));
                user.setUname(res.getString("username"));
                user.setType(res.getString("type"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    public static boolean delete (int id) {
        String query = "DELETE from user where id=?";
        int result = -1;
        try {
            PreparedStatement pr = DbConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            result = pr.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (result == 1) {
            Helper.showmsg("done");
            return true;
        }else {
            Helper.showmsg("error");

            return false;
        }

    }

    public static boolean update (int id,String name, String username, String password, String type) {
        String query = "UPDATE user set name=?, username=?, password=?, type=? where id=? ";
        int result = -1;

        User finuser = getFetch(username);
        if ( finuser !=null && finuser.getId() !=id ){
            Helper.showmsg("Aynı kullanıcıyı ekleyemezsiniz ! ");
            return false;
        }

        try {
            PreparedStatement pr = DbConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, username);
            pr.setString(3, password);
            pr.setString(4, type);
            pr.setInt(5, id);
            System.out.println(query);
            result = pr.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (result == 1) {
            Helper.showmsg("done");
            return true;
        }else {
            Helper.showmsg("error");

            return false;
        }

    }




    public static ArrayList<User> searchList (String query) {
        ArrayList<User> list = new ArrayList<>();

        try {
            Statement st = DbConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                User obj=new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setPassword(rs.getString("password"));
                obj.setUname(rs.getString("username"));
                obj.setType(rs.getString("type"));
                list.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static String searchQuery(String name, String uname, String type){
        String query="Select * from user where name LIKE '%" + name  + "%' and username LIKE '%" + uname  + "%' and type LIKE '%" + type + "%' ";

        return query;
    }



}





package com.patikadev.View;

import com.patikadev.Helper.*;
import com.patikadev.Model.Operator;
import com.patikadev.Model.Patika;
import com.patikadev.Model.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class OperatorGUI extends JFrame {


    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JButton çıkışYapButton;
    private JPanel jp;
    private JLabel lbl_welcome;
    private JScrollPane scrl_userlist;
    private JTable tbl_userlist;
    private JTextField fld_name;
    private JTextField fld_username;
  //  private JPasswordField fld_pasword;
    private JButton bt_add;
    private JComboBox combo_type;
    private JButton bt_delete;
    private JTextField fld_delete_id;
    private JTextField fld_src_name;
    private JTextField fld_src_username;
    private JButton bt_src_arama;
    private JComboBox cmb_src_type;
    private JTable tbl_patika;
    private DefaultTableModel mdl_user_list;
    private final Operator operator;

    private Object[] row_user_list;


    public OperatorGUI(Operator operator)  {
        this.operator = operator;
        add(wrapper);
        setSize(1000,500);
        setTitle(Config.PROJECT_TITLE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(Helper.ScreenCenter("x",getSize()),Helper.ScreenCenter("y",getSize()));
        setVisible(true);
        lbl_welcome.setText("Hoşgeldiniz : " + operator.getName());

        mdl_user_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_user_list = {"ID", "Ad Soyad", "Kullanıcı Adı", "Şifre", "Üyelik Tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);
        row_user_list = new Object[col_user_list.length];




        tbl_userlist.setModel(mdl_user_list);

        tbl_userlist.getTableHeader().setReorderingAllowed(false); // sıralamaya izin vermez

        tbl_userlist.getSelectionModel().addListSelectionListener(e -> {

        try {
            String select_user_id = tbl_userlist.getValueAt(tbl_userlist.getSelectedRow(), 0).toString();
            fld_delete_id.setText(select_user_id);
        }catch (Exception ex){

        }

        });

        tbl_userlist.getModel().addTableModelListener(e -> {
            if (e.getType()==TableModelEvent.UPDATE){
                int user_id = Integer.parseInt(tbl_userlist.getValueAt(tbl_userlist.getSelectedRow(),0).toString());
                String user_name = tbl_userlist.getValueAt(tbl_userlist.getSelectedRow(),1).toString();
                String user_username = tbl_userlist.getValueAt(tbl_userlist.getSelectedRow(),2).toString();
                String user_password = tbl_userlist.getValueAt(tbl_userlist.getSelectedRow(),3).toString();
                String user_type = tbl_userlist.getValueAt(tbl_userlist.getSelectedRow(),4).toString();
                if (user_type.equals("student") || user_type.equals("operator") || user_type.equals("educater")) {
                    User.update(user_id, user_name, user_username, user_password, user_type);
                }
                else {
                    Helper.showmsg("Geçersiz üye tipi ! ");
                }
                loadUserModel();


            }
        });

        loadUserModel();





        bt_add.addActionListener(e -> {
         /*   if (Helper.isFieldEmpty(fld_name) || Helper.isFieldEmpty(fld_pasword)||Helper.isFieldEmpty(fld_username)){
                Helper.showmsg("fill");
            }else {
                if ( User.add(fld_name.getText(),fld_username.getText(),fld_pasword.getText(),combo_type.getSelectedItem().toString())){
                    loadUserModel();
                    fld_pasword.setText(null);
                    fld_username.setText(null);
                    fld_name.setText(null);
                }
            }*/
        });


        bt_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_delete_id) ){
                Helper.showmsg("fill");
            }else {
               if (User.delete( Integer.parseInt(fld_delete_id.getText()) )) {
                    loadUserModel();
                    fld_delete_id.setText(null);
                }
            }
        });


        bt_src_arama.addActionListener(e -> {
            String name = fld_src_name.getText();
            String uname = fld_src_username.getText();
            String type = cmb_src_type.getSelectedItem().toString();
            String query = User.searchQuery(name,uname,type);
            loadUserModel(User.searchList(query));

        });
        çıkışYapButton.addActionListener(e -> {
            dispose();
        });
    }



    public void loadUserModel(){
        DefaultTableModel clearmodel= (DefaultTableModel) tbl_userlist.getModel();
        clearmodel.setRowCount(0);
        for (User user : User.getList() ){
            row_user_list[0] = user.getId();
            row_user_list[1] = user.getName();
            row_user_list[2] = user.getUname();
            row_user_list[3] = user.getPassword();
            row_user_list[4] = user.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }
    public void loadUserModel(ArrayList<User> list){
        DefaultTableModel clearmodel= (DefaultTableModel) tbl_userlist.getModel();
        clearmodel.setRowCount(0);
        for (User user : list ){
            row_user_list[0] = user.getId();
            row_user_list[1] = user.getName();
            row_user_list[2] = user.getUname();
            row_user_list[3] = user.getPassword();
            row_user_list[4] = user.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }

    public static void main(String[] args) {
        Helper.setLayout();
        Operator op = new Operator();
        op.setId(1);
        op.setName("MUSTAFA ÇETİNDAĞ");
        op.setUname("mustafa");
        op.setType("operator");
        OperatorGUI opGui = new OperatorGUI(op);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


}

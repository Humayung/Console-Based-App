/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Components.CButton;
import Components.CFrame;
import Components.CLabel;
import Components.CLayout;
import Components.CListView;
import Components.CTextBox;
import Components.Component;
import Main.MainClass;
import Utils.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cornoblue
 */
public class HapusDataPegawaiForm extends CLayout{
    public HapusDataPegawaiForm(){
        super();
    }
    
    protected void  createLayout(){
        new CFrame(this, 62, 24, "Hapus Data Pegawai", "hapusPegawai_frame");
        new CLabel(this, 49, 2, "Masukkan ID", "id_label");
        new CTextBox(this, 49, 4, 12, 1, "id_textBox");
        new CButton(this, 49, 6, " Hapus ", "hapus_button");
        new CButton(this, 49, 22, "Kembali", "kembali_button");
        
        CLabel loginFailed_label = new CLabel(this, 47, 8, "ahmad Tak ada!", "gagalHapus_label");
        loginFailed_label.foregroundColor(Color.RED);
        loginFailed_label.hide();
        
        CListView listView = new CListView(this, 2, 2, "listPegawai_listView", 20);
        listView.addColumn("#", 3);
        listView.addColumn("Username", 8);
        listView.addColumn("Nama Pegawai", 15);
        listView.addColumn("Password", 7);
    }
    
    public void form_load(){
        String sql = "SELECT * from `pegawai` where `status` = 1";
        ResultSet res = MainClass.koneksiSQL.selectDB(sql, "");
        CListView listViewLapangan = (CListView)getComponentById("listPegawai_listView");
        listViewLapangan.clear();
        try {
            int no = 1;
            while(res.next()){
                String pwdMask = "";
                String pwd = res.getString("password");
                for(int i = 0; i < pwd.length(); i++){
                    pwdMask += "*";
                }
                
                listViewLapangan.addRow(String.valueOf(no), res.getString("username"), res.getString("nama_pegawai"), pwdMask);
                no++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LapanganForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        MainClass.koneksiSQL.closeConn();
    }
    
    public void hapus_button_touched(){
        String username = ((CTextBox)getComponentById("id_textBox")).getAndClearText();
        String sql = "UPDATE pegawai SET status = 0 WHERE username = ?";
        int res = MainClass.koneksiSQL.updateDB(sql, username);
        
        if(res == 0){
            Component component = (Component)getComponentById("gagalHapus_label");
            component.show();
            component.render();   
            getCommand(true);
        }else{
            Component component = (Component)getComponentById("gagalHapus_label");
            component.hide();
            getCommand(true);
        }
        

    }
    
    public void kembali_button_touched(){
        MainClass.pegawaiForm.getCommand(true);
    }
}

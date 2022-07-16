package Forms;

import Components.CLayout;
import Components.CListView;
import Components.CTextBox;
import Components.CFrame;
import Components.CButton;
import Components.*;
import Main.MainClass;
import Utils.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PegawaiForm extends CLayout {
    public PegawaiForm(){
        super();
    }

    protected void createLayout(){
        new CFrame(this, 63, 24, "Data Pegawai", "pegawai_frame");
        new CButton(this, 50, 2, "Tambah Pegawai", "tambahPegawai_button");
        new CButton(this, 50, 4, "Hapus Pegawai", "hapusPegawai_button");
        new CButton(this, 50, 22, "    Kembali   ", "kembali_button");
        
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
                StringBuilder pwdMask = new StringBuilder();
                String pwd = res.getString("password");
                pwdMask.append("*".repeat(pwd.length()));
                
                listViewLapangan.addRow(String.valueOf(no), res.getString("username"), res.getString("nama_pegawai"), pwdMask.toString());
                no++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LapanganForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        MainClass.koneksiSQL.closeConn();
    }
    
    public void kembali_button_touched(){
        MainClass.menuUtamaForm.getCommand(true);
    }
    
    public void tambahPegawai_button_touched(){
        
        MainClass.isiDataLapanganForm.getCommand(true);
    }
    
    public void hapusPegawai_button_touched(){
        MainClass.hapusDataPegawaiForm.getCommand(true);
    }
}

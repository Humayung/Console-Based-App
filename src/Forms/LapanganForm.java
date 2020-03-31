package Forms;

import Components.CLayout;
import Components.CListView;
import Components.CTextBox;
import Components.CFrame;
import Components.CButton;
import Components.*;
import Main.MainClass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LapanganForm extends CLayout {
    public LapanganForm(){
        super();
    }

    protected void createLayout(){
        new CFrame(this, 60, 25, "Lapangan", "lapangan_frame");
        new CButton(this, 48, 2, "Kembali", "kembali_button");
        CListView listView = new CListView(this, 2, 2, "listLapangan_listView", 20);
        listView.addColumn("#", 3);
        listView.addColumn("ID", 8);
        listView.addColumn("Nama", 15);
        listView.addColumn("Ukuran", 7);
    }
    
    public void form_load(){
        String sql = "SELECT * from `lapangan`";
        ResultSet res = MainClass.koneksiSQL.selectDB(sql, "");
        CListView listViewLapangan = (CListView)getComponentById("listLapangan_listView");
        listViewLapangan.clear();
        try {
            int no = 1;
            while(res.next()){
                listViewLapangan.addRow(String.valueOf(no), res.getString("id_lapang"), res.getString("nama_lapang"), res.getString("ukuran"));
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
}

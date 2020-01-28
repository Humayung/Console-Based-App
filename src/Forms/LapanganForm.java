package Forms;

import Components.CLayout;
import Components.CListView;
import Components.CTextBox;
import Components.CFrame;
import Components.CButton;
import Components.*;
import Main.MainClass;

public class LapanganForm extends CLayout {
    public LapanganForm(){
        super();
    }

    protected void createLayout(){
        new CFrame(this, 80, 25, "Lapangan", "lapangan_frame");
        new CButton(this, 49, 4, "Tambah", "tambah_button");
        new CButton(this, 49, 6, "Hapus", "hapus_button");
        new CButton(this, 49, 20, "Kembali", "kembali_button");
        new CTextBox(this, 68, 1, 12, 1, "cari_textBox");
        new CButton(this, 55, 1, "Cari", "cari_button");
        CListView listView = new CListView(this, 2, 2, "listLapangan_listView", 20);
        listView.addColumn("#", 3);
        listView.addColumn("ID", 8);
        listView.addColumn("Nama", 15);
        listView.addColumn("Ukuran", 7);
    }
    
    public void form_load(){
        String sql = "SELECT * FROM pegawai where USERNAME=? and PASSWORD=?";
    }

    public void tambah_button_touched(){
        MainClass.isiDataLapanganForm.getCommand(false);
    }
    
    public void kembali_button_touched(){
        MainClass.transaksiForm.getCommand(true);
    }
}

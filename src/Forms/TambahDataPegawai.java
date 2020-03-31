package Forms;

import Components.CLayout;
import Components.CLabel;
import Components.CTextBox;
import Components.CFrame;
import Components.CButton;
import Components.*;
import Main.MainClass;
import Utils.Color;

public class TambahDataPegawai extends CLayout {
    public TambahDataPegawai(){
        super();
    }

    protected void createLayout(){
        new CFrame(this, 39, 15 , "Tambah Pegawai", "tambahPegawai_frame");
        
        new CLabel(this, 3, 3, "Username", "username_label");
        new CTextBox(this, 14, 3, 21, 1, "username_textBox");
        
        new CLabel(this, 3, 5, "Nama", "nama_label");
        new CTextBox(this, 14, 5, 21, 1, "nama_textBox");
        
        new CLabel(this, 3, 7, "Password", "password_label");
        new CTextBox(this, 14, 7, 21, 1, "password_textBox", "*");
        
        new CLabel(this, 3, 9, "Verifikasi", "verifikasi_label");
        new CTextBox(this, 14, 9, 21, 1, "verifikasi_textBox", "*");
        
        new CButton(this, 15, 12, "Tambah", "tambah_button");
        
        CLabel tambahFailed_label = new CLabel(this, 5, 14, "Password verifikasi tak cocok!", "tambahFailed_label");
        tambahFailed_label.foregroundColor(Color.RED);
        tambahFailed_label.hide();
    }

    public void tambah_button_touched(){
        String username = ((CTextBox)getComponentById("username_textBox")).getAndClearText();
        String password = ((CTextBox)getComponentById("password_textBox")).getAndClearText();  
        String verifikasi = ((CTextBox)getComponentById("verifikasi_textBox")).getAndClearText();  
        String nama = ((CTextBox)getComponentById("nama_textBox")).getAndClearText();  
        
        if(password.equals(verifikasi)){
            String sql = "INSERT INTO `pegawai`(`username`, `nama_pegawai`, `password`)VALUES(?,?,?);";
            MainClass.koneksiSQL.updateDB(sql, username.toLowerCase(), nama, password);
            MainClass.koneksiSQL.closeConn();
            MainClass.pegawaiForm.form_load();
            MainClass.pegawaiForm.getCommand(true);
        }else{
            Component component = (Component)getComponentById("tambahFailed_label");
            component.show();
            component.render();
        }
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Components.*;
import Main.MainClass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class TransaksiForm extends CLayout{

    public TransaksiForm() {
        super();
    }
    
    protected void createLayout(){
        new CFrame(this, 151, 32, "Penyewaan", "penyewaan_frame");
        new CLine(this, 50, 1, 31, "vertical", "line1_line");
        new CLine(this, 0, 18, 50, "horizontal", "line2_line");
        
        new CButton(this, 1, 1, "Kembali", "kembali_button");
        
        new CLine(this, 0, 3, 151, "horizontal", "line3_line");
        new CLabel(this, 3, 3, "Input Data Penyewa", "inputDataPenyewa_label");
        
        new CLabel(this, 2, 5, "Tanggal", "tanggal_label");
        new CTextBox(this, 14, 5, 10, 1, "tanggal_textBox");
        new CLabel(this, 26, 5, "YYYY/MM/DD", "format_label");
        
        new CLabel(this, 2, 7, "Jam Mulai", "jamMulai_label");
        new CTextBox(this, 14, 7, 10, 1, "jamMulai_textBox");
        new CLabel(this, 26, 7, "HH:MM", "format2_label");
        
        new CLabel(this, 2, 9, "Jam Selesai", "jamSelesai_label");
        new CTextBox(this, 14, 9, 10, 1, "jamSelesai_textBox");
        new CLabel(this, 26, 9, "HH:MM", "format3_label");
       
        new CLabel(this, 2,11, "Nama Club", "namaClub_label");
        new CTextBox(this, 14, 11, 20, 1, "namaClub_textBox");
        
        new CLabel(this, 2,13, "No Telp", "noTelp_label");
        new CTextBox(this, 14, 13, 20, 1, "noTelp_textBox");
        
        new CLabel(this, 2,15, "ID Lapangan", "lapangan_label");
        new CTextBox(this, 14, 15, 10, 1, "idlapangan_textBox");
        
        new CButton(this, 17, 17, "Sewa", "sewa_button");
        
        
        //Data Final
        new CLabel(this, 2, 18, "Data Final", "dataFinal_label");
        new CLabel(this, 2, 20, String.format("""
                        Penyewa dengan
                          Nama Club %s dan
                          Nomor Telepon %s
                          Menyewa Lapangan Futsal (%s)
                          Pada Tanggal %s
                          Dari Jam %s\s
                          sampai dengan Jam %s (Durasi %s)\s
                          Dengan biaya penyewaan sebesar %s/jam
                          Sudah melakukan pembayaran sebanyak %s
                         \s"""
                , "_", "_", "_", "_", "_", "_", "_", "_", "_"), "kesimpulan_label");
        new CButton(this, 17, 30, "Konfirmasi", "konfirmasi_button");
        
        //Data Transaksi
        new CLabel(this,93, 3, "Daftar Transaksi", "daftarTransaksi_label");
        CListView listView;
        listView = new CListView(this, 52, 5, "listTransaksi_listView", 25);
        listView.addColumn("Kode Tr", 7);
        listView.addColumn("Club", 10);
        listView.addColumn("NoHp", 13);
        listView.addColumn("Lapangan", 8);
        listView.addColumn("Tanggal", 10);
        listView.addColumn("Mulai", 5);
        listView.addColumn("Selesai", 7);
        listView.addColumn("Biaya", 5);
        listView.addColumn("Petugas", 7);
    }
    
    public void kembali_button_touched(){
        MainClass.menuUtamaForm.getCommand(true);
    }

    
    public void sewa_button_touched(){
        try {
            String tanggal = ((CTextBox)getComponentById("tanggal_textBox")).getText();
            String  jamMulai = ((CTextBox)getComponentById("jamMulai_textBox")).getText();
            String  jamSelesai = ((CTextBox)getComponentById("jamSelesai_textBox")).getText();
            String  namaClub = ((CTextBox)getComponentById("namaClub_textBox")).getText();
            String  noTelp = ((CTextBox)getComponentById("noTelp_textBox")).getText();
            String  idlapangan = ((CTextBox)getComponentById("idlapangan_textBox")).getText();
            
            String sql = "select tarif.`harga` " +
                    "from tarif " +
                    "where	tarif.`id_lapang` = ? and " +
                    " ? between tarif.`jam_mulai` and tarif.`jam_selesai`";
            ResultSet res = MainClass.koneksiSQL.selectDB(sql, idlapangan, jamMulai);
            int harga = 0;
            while(res.next()){
                harga = Integer.parseInt(res.getString("harga"));
            }
//            while()
            String bayar = String.valueOf(harga * hitungDurasi(jamMulai, jamSelesai));
            CLabel kesimpulan_label = (CLabel)getComponentById("kesimpulan_label");
            int durasi = hitungDurasi(jamMulai, jamSelesai);
            kesimpulan_label.format(namaClub, noTelp, idlapangan, tanggal, jamMulai, jamSelesai, durasi + " Jam", "Rp " + String.valueOf(harga), "Rp " + bayar);   
            render();
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiForm.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            MainClass.koneksiSQL.closeConn();
        }
    }
    
    public void form_load(){
        
        loadDataTransaksi();
    }
    
    public void konfirmasi_button_touched(){
        try {
            String tanggal = ((CTextBox)getComponentById("tanggal_textBox")).getAndClearText();
            String  jamMulai = ((CTextBox)getComponentById("jamMulai_textBox")).getAndClearText();
            String  jamSelesai = ((CTextBox)getComponentById("jamSelesai_textBox")).getAndClearText();
            String  namaClub = ((CTextBox)getComponentById("namaClub_textBox")).getAndClearText();
            String  noTelp = ((CTextBox)getComponentById("noTelp_textBox")).getAndClearText();
            String  idlapangan = ((CTextBox)getComponentById("idlapangan_textBox")).getAndClearText();
            
            String sql = "insert into transaksi(nama_club,no_hp,id_lapang,tgl_booking,jam_mulai,jam_selesai,petugas,biaya) values (?,?,?,?,?,?,?,?);";
            String username = MainClass.loginForm.username;
            String sql2 = "select tarif.`harga` " +
                    "from tarif " +
                    "where	tarif.`id_lapang` = ? and " +
                    " ? between tarif.`jam_mulai` and tarif.`jam_selesai`";
            ResultSet res = MainClass.koneksiSQL.selectDB(sql2, idlapangan, jamMulai);
            int harga = 0;
            while(res.next()){
                try {
                    harga = Integer.parseInt(res.getString("harga"));
                } catch (SQLException ex) {
                    Logger.getLogger(TransaksiForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            String biaya = String.valueOf(hitungDurasi(jamMulai, jamSelesai) * harga) ;
            MainClass.koneksiSQL.updateDB(sql, namaClub, noTelp, idlapangan, tanggal, jamMulai, jamSelesai, username, biaya);
            ((CLabel)getComponentById("kesimpulan_label")).setCaption(String.format("""
                            Penyewa dengan
                              Nama Club %s dan
                              Nomor Telepon %s
                              Menyewa Lapangan Futsal (%s)
                              Pada Tanggal %s
                              Dari Jam %s\s
                              sampai dengan Jam %s (Durasi %s)\s
                              Dengan biaya penyewaan sebesar %s/jam
                              Sudah melakukan pembayaran sebanyak %s
                             \s"""
                , "_", "_", "_", "_", "_", "_", "_", "_", "_"));
            form_load();
            render();
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadDataTransaksi(){
        try {
            CListView dataTransaksi = (CListView)getComponentById("listTransaksi_listView");
            dataTransaksi.clear();
            String sql = "SELECT * FROM `transaksi` ;";
            ResultSet res = MainClass.koneksiSQL.selectDB(sql, "");
            int no = 1;
            while(res.next()){
                String id_lapang = res.getString("id_lapang");
                String nama_club = res.getString("nama_club");
                String noHp = res.getString("no_hp");
                String tgl_booking = res.getString("tgl_booking");
                String jam_mulai = res.getString("jam_mulai").substring(0, 5);
                String jam_selesai = res.getString("jam_selesai").substring(0, 5);
                String biaya = res.getString("biaya");
                String petugas = res.getString("petugas");
                String kode_transaksi = res.getString("kode_transaksi");
                
                dataTransaksi.addRow(kode_transaksi, nama_club, noHp, id_lapang, tgl_booking, jam_mulai, jam_selesai, biaya, petugas);
                no++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    int hitungDurasi(String jamMulai, String jamSelesai){
        String[] jam_mulai = jamMulai.split(":");
        String[] jam_selesai = jamSelesai.split(":");
        int jam;
        int menit;
        
        menit = Integer.parseInt(jam_mulai[1]);
        jam = Integer.parseInt(jam_mulai[0]);
        int mulai_menit = jam * 60 + menit;
        
        menit = Integer.parseInt(jam_selesai[1]);
        jam = Integer.parseInt(jam_selesai[0]);
        int selesai_menit = jam * 60 + menit;

        return (selesai_menit - mulai_menit) / 60;
    }
    
}

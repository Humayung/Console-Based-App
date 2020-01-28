/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Components.*;
import Main.MainClass;

/**
 *
 * @author Asus
 */
public class TransaksiForm extends CLayout{

    public TransaksiForm() {
        super();
    }
    
    public void createLayout(){
        new CFrame(this, 149, 39, "Penyewaan", "penyewaan_frame");
        new CLine(this, 50, 1, 38, "vertical", "line1_line");
        new CLine(this, 0, 20, 50, "horizontal", "line2_line");
        new CButton(this, 0, 1, "Lapangan", "lapangan_button");
        new CButton(this, 13, 1, "Logout", "logout_button");
        new CLine(this, 0, 3, 130, "horizontal", "line3_line");
        new CLabel(this, 3, 3, "Input Data Penyewa", "inputDataPenyewa_label");
        
        new CLabel(this, 2, 5, "Tanggal", "tanggal_label");
        new CTextBox(this, 14, 5, 10, 1, "tanggal_textBox");
        
        new CLabel(this, 2, 7, "Jam Mulai", "jamMulai_label");
        new CTextBox(this, 14, 7, 10, 1, "jamMulai_textBox");
        
        new CLabel(this, 2 + 23, 7, "Jam Selesai", "jamSelesai_label");
        new CTextBox(this, 15 + 23, 7, 10, 1, "jamSelesai_textBox");
       
        new CLabel(this, 2,9, "Nama Club", "namaClub_label");
        new CTextBox(this, 14, 9, 20, 1, "namaClub_textBox");
        
        new CLabel(this, 2,11, "No Telp", "noTelp_label");
        new CTextBox(this, 14, 11, 20, 1, "noTelp_textBox");
        
        new CLabel(this, 2,13, "Bayar", "Bayar_label");
        new CTextBox(this, 14, 13, 10, 1, "Bayar_textBox");
        
        new CLabel(this, 2+22,13, "/50%", "Dp_label");
        new CLabel(this, 2+30,13, "Total: _", "Harga_label");
        
        new CLabel(this, 2,15, "ID Lapangan", "lapangan_label");
        new CTextBox(this, 14, 15, 10, 1, "idlapangan_textBox");
        
        new CButton(this, 17, 18, "Sewa", "sewa_button");
        
        
        //Data Final
        new CLabel(this, 2, 20, "Data Final", "dataFinal_label");
                String[] format = new String[]{"FutsalOK", "082285755043", "Lapangan 1", "30-01-2020", "16.04", "18.04", "8 jam", "150000", "30000", "123000"};

        new CLabel(this, 2, 22, String.format("Penyewa dengan\n  "
                + "Nama Club %s dan\n  "
                + "Nomor Telepon %s\n\n  "
                + "Menyewa Lapangan Futsal (%s)\n  "
                + "Pada Tanggal %s\n  "
                + "Dari Jam %s \n  sampai dengan Jam %s (Durasi %s) \n\n  "
                + "Dengan biaya penyewaan sebesar %s\n\n  "
                + "Sudah melakukan pembayaran sebanyak %s\n  "
                + "dengan sisa yang harus dibayar sebanyak %s"
                , "_", "_", "_", "_", "_", "_", "_", "_", "_", "_"), "kesimpulan_label");
        new CButton(this, 17, 37, "Konfirmasi", "konfirmasi_button");
        
        //Data Transaksi
        new CLabel(this, 82, 3, "Daftar Transaksi", "daftarTransaksi_label");
        CListView listView;
        listView = new CListView(this, 52, 5, "listTransaksi_listView", 32);
        listView.addColumn("#", 3);
        listView.addColumn("Kode Tr", 7);
        listView.addColumn("Club", 5);
        listView.addColumn("Lapangan", 8);
        listView.addColumn("Tanggal", 5);
        listView.addColumn("Mulai", 5);
        listView.addColumn("Selesai", 7);
        listView.addColumn("Biaya", 5);
        listView.addColumn("Dibayar", 6);
        listView.addColumn("Sisa", 6);
        listView.addColumn("Petugas", 7);
        
        
    }
    
    public void lapangan_button_touched(){
        MainClass.lapanganForm.getCommand(true);
    }
    
    public void sewa_button_touched(){
        String tanggal = ((CTextBox)getComponentById("tanggal_textBox")).getText();
        String  jamMulai = ((CTextBox)getComponentById("jamMulai_textBox")).getText();
        String  jamSelesai = ((CTextBox)getComponentById("jamSelesai_textBox")).getText();
        String  namaClub = ((CTextBox)getComponentById("namaClub_textBox")).getText();
        String  noTelp = ((CTextBox)getComponentById("noTelp_textBox")).getText();
        String  bayar = ((CTextBox)getComponentById("Bayar_textBox")).getText();
        String  idlapangan = ((CTextBox)getComponentById("idlapangan_textBox")).getText();
        
        CLabel kesimpulan_label = (CLabel)getComponentById("kesimpulan_label");
        int durasi = hitungDurasi(jamMulai, jamSelesai);
        kesimpulan_label.format(namaClub, noTelp, idlapangan, tanggal, jamMulai, jamSelesai, durasi + " Jam", bayar, "10000");   
    }
    
    int hitungDurasi(String jamMulai, String jamSelesai){
        String[] jam_mulai = jamMulai.split(".");
        String[] jam_selesai = jamSelesai.split(".");
        System.out.println(jam_mulai.length + " " + jam_selesai.length);
        System.exit(0);
        int jam;
        int menit;
        
        menit = Integer.parseInt(jam_mulai[1]);
        jam = Integer.parseInt(jam_mulai[0]);
        int mulai_menit = jam * 60 + menit;
        
        menit = Integer.parseInt(jam_selesai[1]);
        jam = Integer.parseInt(jam_selesai[0]);
        int selesai_menit = jam * 60 + menit;
        
        int durasi = (selesai_menit - mulai_menit) / 60;
        return durasi;
    }
    
}

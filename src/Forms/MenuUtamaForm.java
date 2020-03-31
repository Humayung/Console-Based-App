/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Components.CButton;
import Components.CFrame;
import Components.CLayout;
import Main.MainClass;

/**
 *
 * @author Asus
 */
public class MenuUtamaForm extends CLayout{
    public MenuUtamaForm(){
        super();
    }
    
    protected void createLayout(){
        new CFrame(this, 40, 15, "Form Menu Utama", "menuUtama_frame");
        new CButton(this, 15, 4, "Data Transaksi", "dataTransaksi_button");
        new CButton(this, 15, 6, "Data  Lapangan", "dataLapangan_button");
        new CButton(this, 15, 8, " Data Pegawai ", "dataPegawai_button");
        new CButton(this, 15, 10, "    Logout    ", "logout_button");
    }
    
    public void dataTransaksi_button_touched(){
        MainClass.transaksiForm.getCommand(true);
    }
    public void dataLapangan_button_touched(){
        MainClass.lapanganForm.getCommand(true);
    }
    public void dataPegawai_button_touched(){
        MainClass.pegawaiForm.getCommand(true);
    }
    public void logout_button_touched(){
        MainClass.loginForm.getCommand(true);
    }
}

package Main;

import Forms.HapusDataPegawaiForm;
import Forms.TambahDataPegawai;
import Forms.LapanganForm;
import Forms.LoginForm;
import Forms.MenuUtamaForm;
import Forms.PegawaiForm;
import Forms.TransaksiForm;
import Koneksi.Koneksi;
public class MainClass {
    public static Koneksi koneksiSQL;
    public static LoginForm loginForm;
    public static LapanganForm lapanganForm;
    public static TambahDataPegawai isiDataLapanganForm;
    public static TransaksiForm transaksiForm;
    public static MenuUtamaForm menuUtamaForm;
    public static PegawaiForm pegawaiForm;
    public static HapusDataPegawaiForm hapusDataPegawaiForm;
    static String user="root";
    static String pwd="";
    static String host="localhost";
    static String db="db_lapangan";
    
    public static void main(String[] args){      
        koneksiSQL = new Koneksi(user, pwd, host, db);
        loginForm = new LoginForm();
        lapanganForm = new LapanganForm();
        isiDataLapanganForm = new TambahDataPegawai();
        transaksiForm = new TransaksiForm();
        menuUtamaForm = new MenuUtamaForm();
        pegawaiForm = new PegawaiForm();
        hapusDataPegawaiForm = new HapusDataPegawaiForm();
        loginForm.getCommand(true);
    }
}

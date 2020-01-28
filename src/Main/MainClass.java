package Main;

import Forms.IsiDataLapanganForm;
import Forms.LapanganForm;
import Forms.LoginForm;
import Forms.TransaksiForm;
import Koneksi.Koneksi;
public class MainClass {
    public static Koneksi koneksiSQL;
    public static LoginForm loginForm;
    public static LapanganForm lapanganForm;
    public static IsiDataLapanganForm isiDataLapanganForm;
    public static TransaksiForm transaksiForm;
    static String user="root";
    static String pwd="";
    static String host="localhost";
    static String db="db_lapangan";
    
    public static void main(String[] args){
        
        koneksiSQL = new Koneksi(user, pwd, host, db);
        loginForm = new LoginForm();
        lapanganForm = new LapanganForm();
        isiDataLapanganForm = new IsiDataLapanganForm();
        transaksiForm = new TransaksiForm();
        
        transaksiForm.getCommand(true);
//        loginForm.getCommand(true);
    }
}

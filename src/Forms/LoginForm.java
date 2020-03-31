package Forms;

import Components.CLayout;
import Components.CLabel;
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

public class LoginForm extends CLayout {

    public String username = "";
    public LoginForm(){
        super();
    }

    protected void createLayout(){
        new CFrame(this, 39, 12, "Silakan Login Dulu", "login_frame");
        new CLabel(this, 3, 3, "Username", "username_label");
        new CLabel(this, 3, 5, "Password", "password_label");
        new CTextBox(this, 13, 3, 21, 1, "username_textBox").setText("admin");
        new CTextBox(this, 13, 5, 21, 1, "password_textBox", "*").setText("admin");
        new CButton(this, 15, 8, "Login", "login_button");
        CLabel loginFailed_label = new CLabel(this, 5, 10, "Password atau Username Salah!", "loginFailed_label");
        loginFailed_label.foregroundColor(Color.RED);
        loginFailed_label.hide();
    }

    /**
     * Semua method yang ada dibawah ini, merupakan method yang dipanggil
     * melalui event-event seperti tombol diklik dll
     */
    
    public void login_button_touched(){
        String sql = "SELECT * FROM pegawai where USERNAME=? and PASSWORD=?";
//        String sql = "INSERT INTO `lapangan`(`nama_lapang`)"+"VALUES(?);";

        String username = ((CTextBox)getComponentById("username_textBox")).getAndClearText();
        String password = ((CTextBox)getComponentById("password_textBox")).getAndClearText();
        ResultSet res = MainClass.koneksiSQL.selectDB(sql, username, password);
        if(!(username.equals("") && password.equals(""))){
            int baris = 0;
            try {
                while (res.next()) {
                    baris = res.getRow();
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(baris == 1){
                this.username = username;
                Component component = (Component)getComponentById("loginFailed_label");
                component.hide();
                MainClass.menuUtamaForm.getCommand(true);
            }else{
                Component component = (Component)getComponentById("loginFailed_label");
                component.show();
                component.render();
            }
        }
        
    }
}

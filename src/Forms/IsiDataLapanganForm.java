package Forms;

import Components.CLayout;
import Components.CLabel;
import Components.CTextBox;
import Components.CFrame;
import Components.CButton;
import Components.*;
import Main.MainClass;

public class IsiDataLapanganForm extends CLayout {
    public IsiDataLapanganForm(){
        super();
    }

    public void createLayout(){
        new CFrame(this, 39, 12, "Tambah Lapangan", "tambahLapangan_frame");
        new CLabel(this, 3, 3, "ID", "id_label");
        new CLabel(this, 3, 5, "Nama", "nama_label");
        new CLabel(this, 3, 7, "Ukuran", "ukuran_label");

        new CTextBox(this, 12, 3, 21, 1, "id_textBox");
        new CTextBox(this, 12, 5, 21, 1, "nama_textBox");
        new CTextBox(this, 12, 7, 21, 1, "ukuran_textBox");
        new CButton(this, 15, 10, "Tambah", "tambah_button");
    }

    public void tambah_button_touched(){
        String id = ((CTextBox)getComponentById("id_textBox")).getAndClearText();
        String nama = ((CTextBox)getComponentById("nama_textBox")).getAndClearText();  
        String ukuran = ((CTextBox)getComponentById("ukuran_textBox")).getAndClearText(); 
        CListView listView = (CListView)MainClass.lapanganForm.getComponentById("listLapangan_listView");
        String[] lasRow = listView.get(listView.rowNumber - 1);
        int lastRowIndex = 0;
        if(lasRow != null) {
            lastRowIndex = Integer.parseInt(lasRow[0]);
        }
        listView.addRow(String.valueOf(lastRowIndex + 1), id, nama, ukuran); 
        MainClass.lapanganForm.getCommand(true);
    }
}

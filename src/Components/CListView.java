package Components;

import Utils.Color;
import Utils.ConsoleUtils;
import Utils.LabeledRect;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class CListView extends Component {
    int x;
    int y;
    String id;
    ArrayList<SingleList> columns;
    CLayout parent;
    boolean visible = true;
    int initialRow;
    boolean disabled = false;

    public CListView(CLayout parent, int x, int y, String id) {
        this(parent, x, y, id, 10);
    }

    public CListView(CLayout parent, int x, int y, String id, int initialRow) {
        columns = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.id = id;
        this.parent = parent;
        this.initialRow = initialRow;
        parent.addComponent(this);
    }

    public String[] get(int irow){
        String[] row = null;
        if(columns.get(0).list.size() > 0) {
            row = new String[columns.size()];
            for (int i = 0; i < columns.size(); i++) {
                row[i] = columns.get(i).list.get(irow);
            }
        }
        return row;
    }

    @Override public String getId() {
        return id;
    }
    
    @Override public boolean visible(){
        return visible;
    }

    public void remove(int index) {
        for (SingleList list : columns) {
            list.remove(index);
        }
    }
    
    public void clear(){
        for(int i = columns.get(0).list.size() - 1; i >= 0; i--){
            remove(i);
        }
    }

    public void addColumn(String label, int length) {
        addColumn(label, length, 1);
    }

    public void addColumn(String label, int length, int justify) {
        int x;
        if (columns.isEmpty()) {
            x = this.x;
        } else {
            SingleList lastColumn = columns.get(columns.size() - 1);
            x = lastColumn.x + lastColumn.header.w + 1;
        }
        int y = this.y;
        SingleList newColumn = new SingleList(x, y, label, length, justify, initialRow);
        columns.add(newColumn);
    }

    @Override public void render() {
        for (SingleList list : columns) {
            list.render();
        }
    }

    public void addRow(String... row) {
        for (int i = 0; i < row.length; i++) {
            columns.get(i).add(row[i]);
        }
    }

    @Override public void highlight() {

    }

    @Override public String getType() {
        return getClass().toString().replace("class ", "");
    }

    @Override public void invoke() {
        try {
            parent.getClass().getDeclaredMethod(id + "_touched").invoke(parent);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override public void hide(){
        visible = false;
    }

    @Override public void show(){
        visible = true;
    }
}

class SingleList extends ConsoleUtils {
    ArrayList<String> list;
    int x;
    int y;
    LabeledRect header;
    int justify;
    int initialRow;

    SingleList(int x, int y, String label, int length, int justify, int initialRow) {
        this.x = x;
        this.y = y;
        list = new ArrayList<>();
        header = new LabeledRect(x, y, label, Color.BRIGHT_WHITE, Color.BRIGHT_BLACK, Color.WHITE);
        header.setWidth(length);
        this.justify = justify;
        this.initialRow = initialRow;
    }

    void add(String text) {
        list.add(text);
    }

    void remove(int index) {
        list.remove(index);
    }
 

    void render() {
        resetColor();
        header.render();
        boldOff();
        bgColor(Color.WHITE);
        fill(x, y + header.h, header.w, Math.max(list.size(), initialRow));
        for (int i = 0; i < list.size(); i++) {
            String text = list.get(i).length() > header.w ? list.get(i).substring(0, header.w + 1) : list.get(i);
            int textX = 0;
            switch (justify) {
                case 0:
                    textX = header.x;
                    break;
                case 1:
                    textX = header.w / 2 - text.length() / 2 + x;
                    break;

                case 2:
                    textX = header.x + header.w - text.length();
                    break;
            }
            fgColor(Color.BLACK);
            text(text, textX, y + i + 1);
        }
    }


}

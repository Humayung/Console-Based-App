package Components;

import Utils.Color;
import static Utils.ConsoleUtils.*;

public class CFrame extends Component {
    String label;
    public int width;
    public int height;
    String id;
    CLayout parent;
    int labelX;
    boolean visible = true;

    public CFrame(CLayout parent, int width, int height, String label, String id) {
        this.width = width;
        this.height = height;
        this.label = label;
        this.id = id;
        this.parent = parent;
        this.labelX = width / 2 - label.length() / 2;
        parent.addComponent(this);
    }

    @Override public String getId() {
        return id;
    }

    @Override public void invoke() {

    }
    @Override public boolean visible(){
        return visible;
    }
    @Override public void render() {
        if(visible) {
            bgColor(Color.BRIGHT_WHITE);
            fill(0, 0, width, height);
            resetColor();
            bgColor(Color.WHITE);
            fill(0, 0, width, 1);
            textBold();
            resetColor();
            bgColor(Color.WHITE);
            fgColor(Color.BLACK);
            text(label, labelX, 0);
        }
    }

    @Override public void hide(){
        visible = false;
    }

    @Override public void show(){
        visible = true;
    }

    @Override
    public void highlight() {

    }

    @Override
    public String getType() {
        return getClass().toString().replace("class ", "");
    }



}

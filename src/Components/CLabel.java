package Components;

import Utils.Color;
import static Utils.ConsoleUtils.*;

public class CLabel extends Component{
    CLayout parent;
    int x;
    int y;
    String caption;
    String id;
    int w;
    int fgColor;
    int bgColor;
    boolean visible = true;
    public CLabel(CLayout parent, int x, int y, String caption, String id){
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.caption = caption;
        this.id = id;
        this.w = caption.length();
        this.fgColor = Color.BLACK;
        this.bgColor = Color.BRIGHT_WHITE;
        parent.addComponent(this);
    }
    
    public void format(String ... format){
        for (String s : format) {
            caption = caption.replaceFirst("_", s);
        }
        render();
    }

    @Override public String getType() {
        return getClass().toString().replace("class ", "");
    }
    
    public void setCaption(String caption){
        this.caption = caption;
        render();
    }

    @Override public String getId(){
        return id;
    }

    @Override public void invoke() {

    }

    @Override public void highlight() {

    }

    public void foregroundColor(int color){
        this.fgColor = color;
    }

    public void backgroundColor(int color){
        this.bgColor = color;
    }

    @Override public void hide(){
        visible = false;
    }

    @Override public void show(){
        visible = true;
    }
    @Override public boolean visible(){
        return visible;
    }

    @Override public void render(){
        if(visible) {
            resetColor();
            bgColor(bgColor);
            fgColor(fgColor);
            text(caption, x, y);
        }
    }

}

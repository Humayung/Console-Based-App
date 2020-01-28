package Components;

import Utils.Color;


import static Utils.ConsoleUtils.*;

public class CTextBox extends Component{
    int x;
    int y;
    int w;
    int h;
    String text;
    String id;
    CLayout parent;
    String mask;
    boolean disabled = false;
    boolean visible = true;

    public CTextBox(CLayout parent, int x, int y, int width, int height, String id) {
        this(parent, x, y, width, height, id, "");
    }

    public CTextBox(CLayout parent, int x, int y, int width, int height, String id, String mask) {
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
        this.id = id;
        this.parent = parent;
        this.text = "";
        this.mask = mask;
        parent.addComponent(this);
    }

    @Override public String getId(){
        return id;
    }

    @Override public void invoke() {
        text = parent.getInput("Value for " + id + " :");
    }

    @Override public void highlight(){
        render(true);
    }

    @Override public String getType() {
        return getClass().toString().replace("class ", "");
    }
    
    @Override public boolean visible(){
        return visible;
    }

    @Override public void render(){
        render(false);
    }
    public String getAndClearText(){
        String text = this.text;
        this.text = "";
        return text;
    }
    
    public String getText(){
        return text;
    }

    @Override public void hide(){
        visible = false;
    }

    @Override public void show(){
        visible = true;
    }
    
     public void render(boolean bright) {
        resetColor();
        
        bgColor(Color.WHITE);
        fill(x, y, w, h);
        
        if(bright){
            bgColor(Color.BRIGHT_GREEN);
        }else{
            bgColor(Color.BLACK);
        }
        
        if(disabled){
            bgColor(Color.BRIGHT_BLACK);
        }
        fill(x - 1, y, 1, h);
        resetColor();
        fgColor(Color.BLACK);
        bgColor(Color.WHITE);
        if("".equals(mask)) {
            text(text.length() > w ? text.substring(0, w) : text, x, y);
        }else{
            String masked = "";
            for(int i = 0; i < text.length(); i++){
                masked += mask;
            }
            text(masked.length() > w ? masked.substring(0, w) : masked, x, y);
        }
    }
}

package Utils;

public class LabeledRect extends ConsoleUtils {
    public int x;
    int y;
    public int w;
    public int h;
    int captionX;
    int captionY;
    String caption;
    int fgColor;
    int bgColor;
    int highlightColor;
    
    public LabeledRect(int x, int y, String caption, int fgColor, int bgColor, int highlightColor){
        this.x = x;
        this.y = y;
        this.caption = caption;
        this.w = caption.length() + 2;
        this.h = 1;
        this.captionX = w/2 - caption.length() / 2 + x;
        this.captionY = y;
        this.fgColor = fgColor;
        this.bgColor = bgColor;
        this.highlightColor = highlightColor;
    }

    public void setWidth(int size){
        this.w = size + 2;
        this.captionX = w/2 - caption.length()/2 + x;
    }

    public void setX(int x){
        this.x = x;
        this.captionX = w/2 - caption.length() / 2 + x;
    }

    public void setY(int x){
        this.x = x;
        this.captionX = w/2 - caption.length() / 2 + x;
    }
       
    public void setFgColor(int color){
        fgColor = color;
    }
    
    public void setBgColor(int color){
        bgColor = color;
    }
    
    public void highLight(){
        resetColor();
        bgColor(highlightColor);
        fill(x, y, w, h);
        resetColor();
        bgColor(highlightColor);
        fgColor(fgColor);
        text(caption, captionX, captionY);
        resetColor();
    }

    

    public void render(){
        resetColor();
        bgColor(bgColor);
        fill(x, y, w, h);
        resetColor();
        bgColor(bgColor);
        fgColor(fgColor);
        text(caption, captionX, captionY);
        resetColor();
    }
}

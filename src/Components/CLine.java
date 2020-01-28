/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

/**
 *
 * @author Asus
 */
import Utils.Color;
import static Utils.ConsoleUtils.*;

public class CLine extends Component{
    int x;
    int y;
    String orientation;
    String id;
    int length;
    CLayout parent;
    boolean visible = true;
    public CLine(CLayout parent, int x, int y, int length, String orientation, String id){
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.id = id;
        this.length = length;
        parent.addComponent(this);
    }
    
    @Override public String getType() {
        return getClass().toString().replace("class ", "");
    }

    @Override public String getId(){
        return id;
    }

    @Override public void invoke() {

    }

    @Override public void highlight() {

    }

    @Override
    public void render() {
        resetColor();
        fgColor(Color.BLACK);
        bgColor(Color.BRIGHT_WHITE);
        if(visible){
            if(orientation == "vertical"){
                vline(x, y, length, 179);
            }else if(orientation == "horizontal"){
                hline(x, y, length, 196);
            }else{
                throw new Error("Orientation is not valid!");
            }
        }
    }
    
    @Override public void hide() {
        visible = false;
    }
    
    @Override public void show() {
        visible = true;
    }
    
    @Override boolean visible() {
        return visible;
    }

}

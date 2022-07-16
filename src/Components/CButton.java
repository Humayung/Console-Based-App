package Components;
import Utils.Color;
import Utils.LabeledRect;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CButton extends Component {
    String caption;
    String id;
    LabeledRect labeledRect;
    CLayout parent;
    boolean visible = true;
    public CButton(CLayout parent, int x, int y, String caption, String id) {
        labeledRect = new LabeledRect(x, y, caption, Color.BLACK, Color.WHITE, Color.BRIGHT_GREEN);
        labeledRect.setWidth(8);
        this.caption = caption;
        this.id = id;
        this.parent = parent;
        parent.addComponent(this);
    }

    @Override public void highlight(){
        labeledRect.highLight();
    }
    
    @Override public String getType() {
        return getClass().toString().replace("class ", "");
    }

    
    @Override public void render() {
        if(visible) {
            labeledRect.render();
        }
    }

    @Override public String getId() {
        return id;
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

    @Override public void invoke() {
        try {
            try {
                parent.getClass().getDeclaredMethod(id + "_touched").invoke(parent);
            } catch (NoSuchMethodException | SecurityException ex) {
                Logger.getLogger(CButton.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(CButton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

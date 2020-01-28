package Components;

public abstract class Component {
    abstract public void render();
    abstract public String getId();
    abstract public void invoke();
    abstract public void highlight();
    abstract public String getType();
    abstract public void hide();
    abstract public void show();
    abstract boolean visible();
}

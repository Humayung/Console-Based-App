package Components;

import Utils.Color;
import Utils.ConsoleUtils;
import org.fusesource.jansi.AnsiConsole;

import java.util.ArrayList;
import java.util.Scanner;

import static Utils.ConsoleUtils.*;

public class CLayout {
    static final String SWITCH_NEXT = "e";
    static final String SWITCH_PREV = "q";
    static final String TOUCH = "w";
    static boolean shutdownCatchStatus = false;
    static Scanner scan = new Scanner(System.in);
    int focusedIndex = 0;
    protected ArrayList<Component> components;
    ArrayList<Component> switchableComponents;
    Component focusedComponent;

    public CLayout() {
        if(!CLayout.shutdownCatchStatus){
            CLayout.runShutdownCatcher();
            CLayout.shutdownCatchStatus = true;
        }
        components = new ArrayList<>();
        switchableComponents = new ArrayList<>();
        AnsiConsole.systemInstall();
        createLayout();
        form_load();
    }
    
    private static void runShutdownCatcher(){
        Runtime.getRuntime().addShutdownHook(new Thread(ConsoleUtils::resetColor));
    }
    
    public void form_load(){
        
    }

    protected void createLayout() {

    }

    public void render() {
        for (Component component : components) {
            component.render();
        }
    }

    public void addComponent(Component component) {
        if (checkComponent(component.getId())) {
            throw new Error(String.format("Sudah ada component dengan id: %s", component.getId()));
        }
        if (!component.getType().equals("Components.CFrame") && !component.getType().equals("Components.CLabel") && !component.getType().equals("Components.CListView") && !component.getType().equals("Components.CLine")) {
            switchableComponents.add(component);
        }
        components.add(component);
    }

    private boolean checkComponent(String id) {
        for (Component component : components) {
            if (component.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Object getComponentById(String id) {
        for (Component component : components) {
            if (component.getId().equals(id)) {
                return component;
            }
        }
        throw new Error(String.format("Tidak ada Component dengan id: %s", id));
    }
    
    public Object getComponentByType(String type) {
        for (Component component : components) {
            if (component.getType().equals(type)) {
                return component;
            }
        }
        throw new Error(String.format("Tidak ada Component dengan type: %s", type));
    }

    public void getCommand(boolean clear) {
        form_load();
        String command = "";
        resetColor();
        if(clear) {
            clearScreen();
        }
        render();
        focusedComponent = switchableComponents.get(0);
        focusedComponent.highlight();
        while (!command.equalsIgnoreCase("x")) {
            command = getInput("Masukkan Perintah (q:prev, e:next, w:touch) : ");
            switch (command.toLowerCase()) {
                case TOUCH -> {
                    focusedComponent.invoke();
                    focusedComponent.highlight();
                }
                case SWITCH_NEXT -> {
                    focusedComponent.render();
                    focusedIndex = (focusedIndex + 1) % switchableComponents.size();
                    focusedComponent = switchableComponents.get(focusedIndex);
                    while (!focusedComponent.visible()) {
                        focusedIndex = (focusedIndex + 1) % switchableComponents.size();
                        focusedComponent = switchableComponents.get(focusedIndex);
                    }
                    focusedComponent.highlight();
                }
                case SWITCH_PREV -> {
                    focusedComponent.render();
                    focusedIndex--;
                    if (focusedIndex < 0) {
                        focusedIndex = switchableComponents.size() - 1;
                    }
                    focusedComponent = switchableComponents.get(focusedIndex);
                    while (!focusedComponent.visible()) {
                        focusedIndex--;
                        if (focusedIndex == 0) {
                            focusedIndex = switchableComponents.size() - 1;
                        }
                        focusedComponent = switchableComponents.get(focusedIndex);
                    }
                    focusedComponent.highlight();
                }
            }
        }
        System.exit(0);
    }

    String getInput(String message) {
        String input;
        bgColor(Color.MAGENTA);
        fgColor(Color.BRIGHT_WHITE);
        CFrame parentFrame = (CFrame)getComponentByType("Components.CFrame");
        moveCursor(0, parentFrame.height + 1);
        clearLine();
        System.out.print(message);
        input = scan.nextLine();
        resetColor();
        return input;
    }


}

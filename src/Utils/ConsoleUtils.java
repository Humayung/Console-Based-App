package Utils;
public class ConsoleUtils {

    public static void moveCursor(int x, int y) {
        System.out.print(String.format("%c[%d;%df", '\033', y, x));
    }
    static void size(int width, int height){
        System.out.println("\033[8;50;100t");
    }
    static void setColor(int r, int g, int b, String mode) {
        if (mode == "fg") {
            System.out.print("\033[38;2;" + r + ';' + g + ';' + b + 'm');
        } else if (mode == "bg") {
            System.out.print("\033[48;2;" + r + ';' + g + ';' + b + 'm');
        }
    }

    public static void fgColor(int code){
        System.out.println("\033[" + (30 + code) + "m");
    }


    public static void bgColor(int code){
        System.out.println("\033[" + (40 + code) + "m");
    }

    public static void vline(int x, int y, int length, int segment) {
        line(x, y, length, segment, "VERTICAL");
    }

    public static void hline(int x, int y, int length, int segment) {
        line(x, y, length, segment, "HORIZONTAL");
    }

    public static void line(int x, int y, int length, int segment, String orientation) {
        String character = ExtendedAscii.getAscii(segment);
        for (int i = 0; i < length; i++) {
            if (orientation.equals("HORIZONTAL")) {
                text(character, x + i, y);
            } else if (orientation.equals("VERTICAL")) {
                text(character, x, y + i);
            }else throw new Error("Unknown Orientation!");
        }
    }

    public static void fill(int x, int y, int w, int h){
        for(int i = 0; i < w; i++){
            for(int j = 0; j < h; j++){
                text(" ", i + x, j + y);
            }
        }
    }
    public static void text(String text, int x, int y) {
        moveCursor(x + 1, y + 1);
        System.out.print(text);
    }

    public static void resetColor(){
        fgColor(Color.DEFAULT);
        bgColor(Color.DEFAULT);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void clearLine(){
        System.out.print("\033[K");
        System.out.flush();
    }

    public static void textBold() {
        System.out.print("\033[0;1m");
    }
    public static void boldOff() {
        System.out.print("\033[0;22m");
    }
}

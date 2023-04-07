package OOP.ec22792.A8;

import javax.swing.*;

public class Main extends JFrame {
    public static JFrame f= new JFrame();
    public static void main(String[] args) {
        GUIVisitor_ec22792 g= new GUIVisitor_ec22792(f);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);

    }
}
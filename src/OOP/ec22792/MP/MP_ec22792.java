package OOP.ec22792.MP;

import javax.swing.*;

public class MP_ec22792 extends JFrame {
    public static JFrame f= new JFrame();
    public static void main(String[] args) {
        House_ec22792 house= new House_ec22792(f);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);

    }
}
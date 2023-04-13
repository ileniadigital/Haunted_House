package OOP.ec22792.MP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Floor3 extends JFrame implements ActionListener {
    public static JFrame f= new JFrame();
    public JPanel p2;
    public JPanel p3;
    public static JLabel gold;
    public JLabel welcome;
    public JTextArea map;
    public JButton a;
    public JButton b;
    public JButton c;
    public JButton d;

    //Constructor
    public Floor3(){
        //Setting the frame size to a phone screen
        f.setVisible(true);
        f.setSize(new Dimension(720,1280));
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setLayout(new GridLayout(3,1,0,10));
        f.setTitle("Second floor of ec22792");

        //Welcome & map
        p2= new JPanel();
        p2.setPreferredSize(new Dimension(500,425));
        welcome= new JLabel("Second floor of House of ec22792!");
        welcome.setHorizontalAlignment(JLabel.CENTER);
        welcome.setVerticalAlignment(JLabel.CENTER);
        p2.add(welcome);

        map= new JTextArea();
        map.setText(
                """
                         - - - - - - - - - - - - - -
                        |   ec22772 |  ec22519    |
                         - - - - - - - - - - - - - - - - - -\s
                        |                                   |
                         - - - - - - - - - - - - - -        | - - - - -
                        |  ec22871  |    ec22486   |       | YOU |
                         - - - - - - - - - - - - - - - - - - - - - - - -\s""");
        p2.add(map);

        //Options
        p3= new JPanel();
        p3.setPreferredSize(new Dimension(500,200));
        JLabel options= new JLabel("Go to:");
        p3.add(options);
        //ec221025
        a= new JButton("ec22519");
        a.addActionListener(this);
        p3.add(a);
        //ec221013
        b= new JButton("ec22486");
        b.addActionListener(this);
        p3.add(b);
        //ec22777
        c= new JButton("ec22871");
        c.addActionListener(this);
        p3.add(c);
        //ec22558
        d= new JButton("ec22772");
        d.addActionListener(this);
        p3.add(d);

        f.add(p2, BorderLayout.NORTH);
        f.add(p3);
        f.pack();
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==a) {House_ec22792.dir = new Room_ec22519().visit(House_ec22792.v, House_ec22792.dir);}
        else if(e.getSource()==b){House_ec22792.dir=new Room_ec22486().visit(House_ec22792.v,House_ec22792.dir);}
        else if(e.getSource()==c){House_ec22792.dir=new Room_ec22871().visit(House_ec22792.v,House_ec22792.dir);}
        else if(e.getSource()==d) {House_ec22792.dir=new Room_ec22772().visit(House_ec22792.v,House_ec22792.dir);}

    }
}

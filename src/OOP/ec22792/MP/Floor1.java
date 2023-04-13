package OOP.ec22792.MP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Floor1 extends JFrame implements ActionListener {
    public static JFrame f= new JFrame();
    public JPanel p1;
    public JPanel p2;
    public JPanel p3;
    public static JLabel gold;
    public static JLabel itemsLabel;

    public static String itemsString="";
    public JLabel welcome;
    public JTextArea map;
    public JButton a;
    public JButton b;
    public JButton c;
    public JButton d;

    //Constructor
    public Floor1(){
        //Setting the frame size to a phone screen
        f.setSize(new Dimension(500,450));
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setLayout(new GridLayout(2,1,0,10));
        f.setTitle("Ground floor of ec22792");

        //Welcome & map
        p2= new JPanel();
        p2.setPreferredSize(new Dimension(500,200));
        welcome= new JLabel("Ground floor of House of ec22792!");
        welcome.setHorizontalAlignment(JLabel.CENTER);
        welcome.setVerticalAlignment(JLabel.CENTER);
        p2.add(welcome);

        map= new JTextArea();
        map.setText(
                """
                         - - - - - - - - - - - - - -
                        |   ec22562  |  ec22433    |
                         - - - - - - - - - - - - - - - - - -\s
                        |                                   |
                         - - - - - - - - - - - - - -        | - - - - -
                        |  ec22860   |    ec22792   |       | YOU |
                         - - - - - - - - - - - - - - - - - - - - - - - -\s""");
        p2.add(map);

        //Options
        p3= new JPanel();
        p3.setPreferredSize(new Dimension(500,200));
        JLabel options= new JLabel("Go to:");
        p3.add(options);
        //ec22433
        a= new JButton("ec22433");
        a.addActionListener(this);
        p3.add(a);
        b= new JButton("ec22792");
        b.addActionListener(this);
        p3.add(b);
        c= new JButton("ec22860");
        c.addActionListener(this);
        p3.add(c);
        d= new JButton("ec22562");
        d.addActionListener(this);
        p3.add(d);

        f.add(p2, BorderLayout.NORTH);
        f.add(p3);
        f.pack();
        f.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==a) {House_ec22792.dir = new Room_ec22433().visit(House_ec22792.v, House_ec22792.dir);}
        else if(e.getSource()==b){House_ec22792.dir=new Room_ec22792().visit(House_ec22792.v,House_ec22792.dir);}
        else if(e.getSource()==c){House_ec22792.dir=new Room_ec22860().visit(House_ec22792.v,House_ec22792.dir);}
        else if(e.getSource()==d) {House_ec22792.dir=new Room_ec22562().visit(House_ec22792.v,House_ec22792.dir);}

    }
}

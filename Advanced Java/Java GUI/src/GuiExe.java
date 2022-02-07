import javax.swing.*;
import java.awt.event.*;

public class GuiExe extends JFrame{
    //JFrame f;
    GuiExe(){
        JButton b = new JButton(new ImageIcon("C:\\Users/anish/Pictures/node.jpg"));
        final JTextField tf=new JTextField();
        tf.setBounds(50,50,150,20);
        b.setBounds(130,100,100, 40);

        add(b);//adding button on frame
        add(tf);
        b.addActionListener(e -> tf.setText("Welcome to Javatpoint."));

        setSize(400,500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
//646057694753
    public static void main(String[] args) {
        new GuiExe();
    }
}
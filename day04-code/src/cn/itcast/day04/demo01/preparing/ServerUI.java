package cn.itcast.day04.demo01.preparing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerUI extends JFrame  {
    private static JTextField tf;
    private static JTextArea ta;
    private static JButton b;
    private static JScrollPane jsp;


    public ServerUI() {

        JFrame f = new JFrame();
        f.setTitle("服务器");

        f.setSize(400, 300);
        f.setLocation(100, 200);
        f.setLayout(null);

        b = new JButton("发送");
        b.setBounds(300, 220, 70, 30);
        f.add(b);

        tf = new JTextField();
        tf.setBounds(10, 220, 290, 30);
        f.add(tf);

        ta = new JTextArea();
        ta.setEditable(false);
//        ta.setBounds(10, 10, 360, 200);
        jsp=new JScrollPane(ta);
        jsp.setBounds(10, 10, 360, 200);
        f.add(jsp);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);


    }

    public static JTextArea getServerTa() {
        return ta;
    }

    public static JButton getB() { return b; }

    public static JTextField getTf() { return tf; }

}


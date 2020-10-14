package cn.itcast.day04.demo01.preparing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientUI extends JFrame implements ActionListener {
    private static JTextField tf;
    private static JTextArea ta;
    private static JButton b;
    private static JScrollPane jsp;


    public ClientUI() {

        JFrame f = new JFrame();
        f.setTitle("客户端");

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

        b.addActionListener(this);

    }

    public static JTextArea getClientTa() {
        return ta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = tf.getText();

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm:ss a");

        ta.append("我说:  "+text + "\t" + "\t" + sdf.format(date) + "\n");
        try {
            DataOutputStream dos = new DataOutputStream(Client.getS().getOutputStream());
            dos.writeUTF(text);
            tf.setText("");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

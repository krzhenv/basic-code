package cn.itcast.day04.demo01.preparing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Server {
        private static int count=0;

    public static int getCount() {
        return count;
    }

    public static void main(String[] args) throws Exception {
        ServerUI serverUI = new ServerUI();
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务器启动，等待客户端连接");
        while(true) {
            Socket s = serverSocket.accept();
            count++;
            int n = getCount();
            ServerThread serverThread=new ServerThread(s);
            serverThread.start();
            ServerUI.getB().addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = ServerUI.getTf().getText();
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm:ss a");
                    ServerUI.getServerTa().append("服务器对客户"+n+"说:"+text + "\t" +"\t" + sdf.format(date) + "\n");
                    try {
                        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                        dos.writeUTF(text);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }finally {
                        ServerUI.getTf().setText(null);
                    }
                }
            });
        }
    }



}


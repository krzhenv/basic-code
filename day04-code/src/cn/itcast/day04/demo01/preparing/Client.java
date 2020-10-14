package cn.itcast.day04.demo01.preparing;


import java.io.*;
import java.net.Socket;


public class Client {
    private  static Socket s;

    public static Socket getS() {
        return s;
    }
    public static void main(String[] args)  {
        ClientUI clientUI = new ClientUI();
        try {
            s= new Socket("127.0.0.1", 8888);
            ClientThread clientThread=new ClientThread(s);
            clientThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

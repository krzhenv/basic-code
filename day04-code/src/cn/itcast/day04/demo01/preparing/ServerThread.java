package cn.itcast.day04.demo01.preparing;

//import cn.itcast.day04.demo01.DBHelper.DBHelper;
import cn.itcast.day04.demo01.util.JDBCUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ServerThread extends Thread {

    private Socket s = null;

    public ServerThread(Socket s) {
        this.s = s;
    }

    public void run() {
        int n = Server.getCount();
        InputStream is = null;
        DataInputStream dis = null;
        try {
            is = s.getInputStream();
            dis = new DataInputStream(is);
            while (true) {
                PreparedStatement stmt = null;
                Connection conn = null;
                try {
                    String text = dis.readUTF();
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm:ss a");
                    ServerUI.getServerTa().append("客户端" + n + "说:  " + text + "\t" + "\t" + sdf.format(date) + '\n');

                    conn = JDBCUtils.getConnection();
                    String sql = "insert into tb_record values(null,'客户端" + n + "','" + text + "','" + sdf.format(date) + "')";
                    stmt = conn.prepareStatement(sql);
                    int i = stmt.executeUpdate();
                    if (i > 0) {
                        System.out.println("添加成功");
                    } else {
                        System.out.println("添加失败");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
//                    if (stmt != null) {
//                        try {
//                            stmt.close();
//                        } catch (SQLException throwables) {
//                            throwables.printStackTrace();
//                        }
//                    }
//                    if (conn != null) {
//                        try {
//                            conn.close();
//                        } catch (SQLException throwables) {
//                            throwables.printStackTrace();
//                        }
//                    }
                    JDBCUtils.close(stmt,conn);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}



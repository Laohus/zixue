package com.xuexi.zixue.Controller.checkdata;

import java.sql.*;
import java.util.Map;


public class connsql {

    Connection con;

    public boolean checklogin(String inputusername, String inputpassword, Map<String, String> mapsource){
        String driver = mapsource.get("driver");
        String url = mapsource.get("url");
        String user = mapsource.get("user");
        String password = mapsource.get("userpassword");

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            Statement statement = ((Connection) con).createStatement();
            String sql =String.format("SELECT * FROM `user` WHERE NAME=\"%s\" AND PASSWORD=\"%s\";",inputusername,inputpassword);
            ResultSet rs = statement.executeQuery(sql);
            return rs.next();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;

        }
    }

    public static void main(String[] args) {
//        Boolean res = new connsql().checklogin("weiw","weiw123");
//        System.out.println(res);
    }
}

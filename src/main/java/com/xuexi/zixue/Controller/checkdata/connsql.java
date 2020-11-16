package com.xuexi.zixue.Controller.checkdata;

import java.sql.*;

public class connsql {
    Connection con;
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://cdb-lheasfxq.cd.tencentcdb.com:10076/study_from?serverTimezone=UTC";
    String user = "root";
    String password = "Weiwei11432";

    public boolean checklogin(String inputusername, String inputpassword){
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            Statement statement = ((Connection) con).createStatement();
            String sql =String.format("SELECT * FROM `user` WHERE NAME=\"%s\" AND PASSWORD=\"%s\";",inputusername,inputpassword);
            ResultSet rs = statement.executeQuery(sql);
            return rs.next();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ceasd");
            e.printStackTrace();
            System.out.println("ce");
            return false;

        }
    }

    public static void main(String[] args) {
//        Boolean res = new connsql().checklogin("weiw","weiw123");
//        System.out.println(res);
    }
}

package com.syntax;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Recap {

    public static void main(String[] args) {

        String url = "jdbc:mysql://3.239.253.255:3306/syntaxhrm_mysql";
        String username = "syntax_hrm";
        String password = "syntaxhrm123";

        try {

            //establish connect and return data in a form of ResulSet
            Connection cn = DriverManager.getConnection(url, username, password);

            Statement st = cn.createStatement();

            String query = "select * from person order by FirstName;";

            ResultSet rst = st.executeQuery(query);

            ResultSetMetaData rsmdata = rst.getMetaData();

            // extract data from resultset and store in  one of the java data structures
            List<Map<String, String>> listFromRset = new ArrayList<>();

            //while loop iterates over rows
            while (rst.next()) {

                Map<String, String> map = new LinkedHashMap<>();
                //iterate over columns
                for (int i = 1; i <=rsmdata.getColumnCount(); i++) {

                    String key = rsmdata.getColumnName(i);
                    String value = rst.getString(key);
                    map.put(key, value);
                }
                System.out.println(map);
                listFromRset.add(map);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

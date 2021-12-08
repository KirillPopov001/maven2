package com.app;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;

public class Memory extends Parser {

    private final String url = "jdbc:mysql://localhost:3306/products";
    private final String user = "root";
    Connection conn = DriverManager.getConnection(url, user, "1234");

    public Memory() throws SQLException {
        super();
    }
    

    public Connection getConnect() throws SQLException, ClassNotFoundException {
        conn = DriverManager.getConnection(url, user, "1234");
        return conn;
    }

    public void write(String id, String charCode, String value, String name) throws SQLException {
        String newData = null;
        newData = "data" + name.substring(0, 2);
        newData += name.substring(3, 5);
        newData += name.substring(6, 10);
        String insert = "INSERT INTO " + newData + " (id, charcode, value) VALUES (?, ?, ?) ";
        try {
            PreparedStatement statement = conn.prepareStatement(insert);
            statement.setString(1, id);
            statement.setString(2, charCode);
            statement.setString(3, value);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void create(String name) {
        String newData = null;
        newData = "data" + name.substring(0, 2);
        newData += name.substring(3, 5);
        newData += name.substring(6, 10);
        String creation = "CREATE TABLE " + newData + " (id VARCHAR(10), charcode VARCHAR(3), value VARCHAR(9))";
        try {
            PreparedStatement statement = conn.prepareStatement(creation);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Данные об этом дне уже есть в базе");
        }
    }

    public void fulling(String name) throws ParserConfigurationException, IOException, SQLException {
        Parser parse = new Parser(name);
        int len = parse.proverka().size();
        Memory mem = new Memory();
        for (int i = 0; i < len; i++){
            String id = parse.proverka().get(i).substring(0, 3);
            String code = parse.proverka().get(i).substring(4, 7);
            String value = parse.proverka().get(i).substring(8, 15);
            mem.write(id, code, value, name);
        }


    }

    public void getText(String data, String name) throws ParserConfigurationException, IOException, SAXException {
        String newData = null;
        newData = "data" + data.substring(0, 2);
        newData += data.substring(3, 5);
        newData += data.substring(6, 10);
        String get = "SELECT value FROM " + newData + " WHERE charcode = '" + name + "'";
        try {
            PreparedStatement statement = conn.prepareStatement(get);
            ResultSet rs = statement.executeQuery();
            rs.next();
            System.out.println(rs.getString(1) + " взято из бд");

        } catch (SQLException throwables) {
            Parser pars = new Parser(data, name);
            System.out.println(pars.getValue());
        }
    }

    public void read(String name) throws SQLException {
        String newName = name.substring(0, 2);
        newName += name.substring(3, 5);
        newName += name.substring(6, 10);
        String reading = "DESCRIBE data" + newName;
        try {
            PreparedStatement statement = conn.prepareStatement(reading);
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
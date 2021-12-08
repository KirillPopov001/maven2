package com.app;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, SQLException, ClassNotFoundException {
        Parser y = new Parser("13/10/2020");
        //System.out.println("updateDB 13/10/2020".substring(9, 19));
        //System.out.println(y.proverka().get(0));
        Memory memory = new Memory();
        //memory.create("17/11/2020");
        //memory.write("1", "USD", "77.3", "13/10/2020");
        //memory.read("19/11/2020");
        //memory.getText("15/11/2020", "USD");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Для выхода введите exit.");
        while (true) {
            System.out.println("Введите дату и котировку валюты через пробел. Если поле даты окажется пустым, то выведутся данные за последний день.");
            String time = scanner.nextLine();
            try{
            if (time.equals("exit")) {
                break;
            }
            if (time.startsWith("updateDB")) {
                String time2 = time.substring(9, 19);
                memory.create(time2);
                memory.fulling(time2);
            }

            if (time.length() == 14) {
                String time2 = time.substring(0, 10);
                String money = time.substring(11, 14);
                memory.getText(time2, money);
            }
            if(time.length() == 3) {
                Parser x = new Parser("", time.substring(0, 3));
                System.out.println(x.getValue());
            }else{
                System.out.println("Неверно введены данные");
            }
            //System.out.println("Введите код валюты");
            //String money = scanner.nextLine().toUpperCase(Locale.ROOT);

            //if (money.length() != 3) {
            //   System.out.println("Недопустимый код валюты");
            //   continue;
            //}
            //Parser x = new Parser(time.substring(0, 10), time.substring(11, 14));
            //System.out.println(x.getValue());


        }catch (StringIndexOutOfBoundsException e){
                if(time.length() == 3) {
                    Parser x = new Parser("", time.substring(0, 3));
                    System.out.println(x.getValue());
                }
            }
        }
    }
        }
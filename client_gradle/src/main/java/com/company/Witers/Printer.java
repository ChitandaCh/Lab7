package com.company.Witers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Printer {

    private static final PrintStream writer = System.out;
    private static  final Scanner scanner =  new Scanner(System.in);


    public static String ReadLine(){
        return scanner.nextLine();
    }

    public static void InvalidValue(){
        try {
            byte[] buffer = ("значение не верно" + "\r\n").getBytes(Charset.forName("windows-1251"));
            writer.write(buffer);
        }
        catch (IOException e){
            System.out.println("вывод прерван. Сообщение выведенно в стандартный поток\r\n");
        }
    }
    public static void Write(Object str){
        try {
            byte[] buffer = str.toString().getBytes(Charset.forName("windows-1251"));
            writer.write(buffer);
        }
        catch (IOException e){
            System.out.println("вывод прерван. Сообщение выведенно в стандартный поток\r\n");
        }
    }
    public static void WriteLine(Object str){
        try {
            byte[] buffer = (str + "\r\n").getBytes(Charset.forName("windows-1251"));
            writer.write(buffer);
        }
        catch (IOException e){
            System.out.println("вывод прерван. Сообщение выведенно в стандартный поток\r\n");
        }
    }
}

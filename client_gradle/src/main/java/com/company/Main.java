package com.company;

import com.company.Commands.Empty;
import com.company.Helpes.Parser;
import com.company.Manager.TicketManager;
import com.company.Models.Responce;
import com.company.Witers.Printer;
import com.company.Witers.Sender;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Main {
    public static ArrayList<String> comm_with_tickets = new ArrayList<>();
    public static TicketManager manager = new TicketManager();
    public static Sender sender;
    public static String login = "";
    public static String password = "";

    public static void main(String[] args) throws IOException {
        comm_with_tickets.add("add");
        comm_with_tickets.add("remove_lower");
        comm_with_tickets.add("update_by_id");

        String ip;
        int host;
        while (true){
            try {
                Printer.WriteLine("введите ip");
                ip = Printer.ReadLine();
                InetAddress.getByName(ip);
                Printer.WriteLine("введите host");
                host = Integer.parseInt(Printer.ReadLine());
                break;
            }
            catch (Exception e){
                Printer.WriteLine("неверно ввведены значения");
            }
        }
        sender = new Sender(InetAddress.getByName(ip), host);


        Printer.WriteLine("введите логин");
        Main.login = Printer.ReadLine();
        Printer.WriteLine("введите пароль");
        Main.password = Printer.ReadLine();
        try {
            while (true) {
                Empty empty = new Empty();
                String command = Printer.ReadLine();
                if (command.toLowerCase(Locale.ROOT).startsWith("exit")) {
                    System.exit(0);
                } else if (command.toLowerCase(Locale.ROOT).startsWith("relogin")) {
                    Printer.WriteLine("введите логин");
                    Main.login = Printer.ReadLine();
                    Printer.WriteLine("введите пароль");
                    Main.password = Printer.ReadLine();
                } else {
                    empty.args = new ArrayList<>(Arrays.asList(command.split(",")));
                    empty.setName(empty.args.get(0));
                    empty.args.remove(0);
                    if (command.toLowerCase(Locale.ROOT).equals("remove_all_by_venue")) {
                        empty.args.add(Parser.GetVenue(manager.GetVenue()));
                    } else {
                        for (String comm : comm_with_tickets) {
                            if (command.toLowerCase(Locale.ROOT).startsWith(comm.toLowerCase(Locale.ROOT))) {
                                empty.args.add(Parser.GetTicket(manager.get_ticket()));
                            }
                        }
                    }
                    empty.login = login;
                    empty.password = password;
                    sender.Send(empty);
                    Responce responce = sender.Recieve();
                    for (String str : responce.responces) {
                        Printer.WriteLine(str);
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

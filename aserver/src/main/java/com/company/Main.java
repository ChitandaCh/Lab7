package com.company;

import com.company.Helper.Logger;
import com.company.Helper.Parser;
import com.company.Managers.CommandManager;
import com.company.Managers.TicketManager;
import com.company.Models.Responce;
import com.company.Models.Ticket;
import com.company.Models.user;

import java.net.*;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static DatagramPacket recieve = new DatagramPacket(new byte[2048], 2048);
    public static DatagramPacket send;
    public static DatagramSocket server;
    public static String ip = "192.168.5.1";
    public static int port = 1112;
    public static DataBase dataBase;

    public static user admin = new user("admin", "admin" , 0);

    public static ArrayDeque<Ticket> tickets = new ArrayDeque<>();
    public static LocalDateTime start;
    public static Thread client = new Thread(Main::console);
    public static TicketManager manager = new TicketManager();
    public static Logger logger = new Logger();
    public static ForkJoinPool forkJoinPool = new ForkJoinPool();
    public static ExecutorService pool = Executors.newCachedThreadPool();

    public static void console(){
        try {
            while (true) {
                String command = com.company.Helper.Printer.ReadLine();
                boolean is = false;
                for (Command comm : CommandManager.getInstance().GetCommands()){
                    if(command.toLowerCase(Locale.ROOT).startsWith(comm.getName().toLowerCase(Locale.ROOT))){
                        comm.args = new ArrayList<>(Arrays.asList(command.split(",")));
                        comm.args.remove(0);
                        if(command.toLowerCase(Locale.ROOT).equals("add") || command.toLowerCase(Locale.ROOT).equals("remove_lower") || command.toLowerCase(Locale.ROOT).equals("update_by_id")){
                            comm.args.add(Parser.GetTicket(manager.get_ticket()));
                        }
                        else if(command.toLowerCase(Locale.ROOT).equals("remove_all_by_venue")){
                            comm.args.add(Parser.GetStringVenue(manager.GetVenue()));
                        }
                        is = true;
                        Responce responce = comm.Execute(admin);
                        for (String ResponceString : responce.responces){
                            com.company.Helper.Printer.WriteLine(ResponceString);
                        }
                    }
                }
                if(!is){
                    com.company.Helper.Printer.WriteLine("такой команды не существует");
                }
                com.company.Helper.Printer.WriteLine("введите команду");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void send(){
        try {
            logger.Info("принят клиент с ip: " + recieve.getAddress());
            Command command = Parser.GetCommand(recieve.getData());
            boolean is = false;
            Responce responce = new Responce();
            for (Command command1 : CommandManager.getInstance().GetCommands()) {
                if (command.getName().toLowerCase(Locale.ROOT).startsWith(command1.getName().toLowerCase(Locale.ROOT))) {
                    command1.args = command.args;
                    responce = command1.Execute(dataBase.SelectUser(command.login, command.password));
                    is = true;
                    command1.args.clear();
                }
            }
            if (!is) {
                responce.add("такой команды не существует");
            }
            responce.add("введите команду");
            logger.Info("сервер выполнил или отверг комнаду");


            byte[] response = Parser.GetResponce(responce);
            responce.clear();
            send = new DatagramPacket(response, response.length, recieve.getAddress(), port - 1);
            new Thread(() -> {
                try {
                    server.send(send);
                } catch (Exception e) {
                    logger.Error(e.getMessage());
                    e.printStackTrace();
                }
            }).start();

            logger.Info("отправлен результат");
            create();
        }
        catch(Exception e){
            logger.Error(e.getMessage());
        }
    }
    public static void create(){
        pool.execute(() -> {
            try {
                recieve = new DatagramPacket(new byte[4096], 4096);
                server.receive(recieve);
                forkJoinPool.execute(Main::send);
            } catch (Exception e) {
                logger.Error(e.getMessage());
            }
        });
    }

    public static void main(String[] args) throws UnknownHostException, SocketException {
        dataBase = new DataBase("jdbc:postgresql://localhost:5432/db", "postgres","postgres");


        start = LocalDateTime.now();
        logger.Info("старт сервера");


        logger.Info("добавлены команды");

        client.start();

        tickets = new ArrayDeque<>(dataBase.SelectTickets());

        server = new DatagramSocket(port, InetAddress.getByName(ip));
        logger.Info("создан сервер");


        create();
    }
}

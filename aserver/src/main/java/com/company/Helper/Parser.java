package com.company.Helper;

import com.company.Commands.Empty;
import com.company.Main;
import com.company.Models.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Scanner;

public class Parser {

    public static Ticket GetTicket(String ticket){
        String[] split = ticket.split(",");
        return new Ticket(split[0], new Coordinates(Integer.parseInt(split[2]), Long.parseLong(split[3])), Integer.parseInt(split[1]), Integer.parseInt(split[9]), TicketType.valueOf(split[4]), new Venue(split[5], Integer.parseInt(split[7]), VenueType.valueOf(split[6])));
    }

    public static Venue GetVenue(String Venue){
        String[] spl = Venue.split(",");
        return new Venue(spl[0], Integer.parseInt(spl[1]), VenueType.valueOf(spl[2].trim()));
    }

    public static String GetStringVenue(Venue venue){
        return venue.getName() + "," + venue.getCapacity() + "," + venue.getType();
    }
    public static void Save(String path) {
        try {
            OutputStream outputStream = new FileOutputStream(path);
            for (Ticket ticket : Main.tickets){
                byte[] buffer = GetTicket(ticket).getBytes(StandardCharsets.UTF_16);
                outputStream.write(buffer,0, buffer.length);
                if(!ticket.getId().equals(Main.tickets.getLast().getId())){
                    byte[] buffer1 = ("\r\n").getBytes(StandardCharsets.UTF_16);
                    outputStream.write(buffer1,0, buffer1.length);
                }
            }
            outputStream.close();
        }
        catch (FileNotFoundException e){
            Printer.WriteLine("файл не найден");
        } catch (IOException e) {
            Printer.WriteLine("ошибка работы с файлом");
        }

    }

    public static void Read(String path){
        try {
            Scanner scanner = new Scanner(new FileInputStream(path), StandardCharsets.UTF_16);
            while (scanner.hasNext()) {
                String[] values = scanner.next().split(",");
                try {
                    Ticket ticket = new Ticket();
                    ticket.setName(values[0]);
                    ticket.setPrice(Integer.parseInt(values[1].trim()));
                    ticket.setCoordinates(new Coordinates(Integer.parseInt(values[2].trim()), Long.parseLong(values[3].trim())));
                    ticket.setType(TicketType.valueOf(values[4].replace(" ", "")));
                    ticket.setVenue(new Venue(values[5], Integer.parseInt(values[7].trim()), VenueType.valueOf(values[6].trim())));
                    ticket.setCreationDate(ZonedDateTime.parse(values[8].trim()));
                    ticket.setDiscount(Integer.parseInt(values[9].trim()));
                    Main.tickets.add(ticket);
                }
                catch (Exception e){
                    Printer.WriteLine("значния  либо неверные, либо недопустимые");
                }
            }
        }
        catch (FileNotFoundException e){
            Printer.WriteLine("файл не найден");
        }
    }


    public static String GetTicket(Ticket ticket){
        return ticket.getName() + "," + ticket.getPrice() + "," + ticket.getCoordinates().getX() + "," +  ticket.getCoordinates().getY() + "," +
                ticket.getType() + "," + ticket.getVenue().getName() + "," + ticket.getVenue().getType() + "," + ticket.getVenue().getCapacity() + "," + ticket.getCreationDate().toString()
                + "," + ticket.getDiscount();
    }

    public static Empty GetCommand(byte[] buffer){
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(buffer));
            return (Empty) inputStream.readObject();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] GetResponce(Responce responce){
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(stream);
            outputStream.writeObject(responce);
            return stream.toByteArray();
        }
        catch (Exception e){
            Printer.WriteLine(e.getMessage());
            return null;
        }
    }
       
}

package com.company.Helpes;

import com.company.Command;
import com.company.Commands.Empty;
import com.company.Models.Ticket;
import com.company.Models.Venue;
import com.company.Models.Responce;
import com.company.Witers.Printer;

import java.io.*;

public class Parser {

    public static String GetRequest(Command command){
        StringBuilder builder = new StringBuilder(command.args.get(0)).append("\r\n");
        for (int i = 1; i < command.args.size(); i++){
            if(command.args.size() - 1 == i)
                builder.append(command.args.get(i));
            else
                builder.append(command.args.get(i)).append(",");
        }
        return builder.toString();
    }

    public static String GetTicket(Ticket ticket){
        return ticket.getName() + "," + ticket.getPrice() + "," + ticket.getCoordinates().getX() + "," +  ticket.getCoordinates().getY() + "," +
                ticket.getType() + "," + ticket.getVenue().getName() + "," + ticket.getVenue().getType() + "," + ticket.getVenue().getCapacity() + "," + ticket.getCreationDate().toString()
                + "," + ticket.getDiscount();
    }
    public static String GetVenue(Venue venue){
        return venue.getName() + "," + venue.getCapacity() + "," + venue.getType();
    }

    public static byte[] GetCommand(Empty empty){
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(stream);
            outputStream.writeObject(empty);
            return stream.toByteArray();
        }
        catch (Exception e){
            Printer.WriteLine(e.getMessage());
            return null;
        }
    }
    public static Responce GetResponce(byte[] buffer){
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(buffer));
            return (Responce) inputStream.readObject();
        }
        catch (Exception e){
            Printer.WriteLine(e.getMessage());
            return null;
        }
    }
    public static Command GetCommand(byte[] buffer){
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(buffer));
            return (Command) inputStream.readObject();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}

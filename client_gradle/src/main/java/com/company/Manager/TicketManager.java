package com.company.Manager;


import com.company.Main;
import com.company.Models.*;
import com.company.Witers.Printer;

public class TicketManager implements Validator {

    public static Long ids_ticket = (long)0;
    public static Long ids_Venue = (long)0;

    public static String getString(String name){
        String str = "";
        while (str.equals("")){
            Printer.WriteLine("Введите поле " + name);
            str = Printer.ReadLine();
        }
        return str;
    }
    public static int getInt(String name){
        int str;
        while (true){
            try {
                Printer.WriteLine("Введите поле " + name);
                str = Integer.parseInt(Printer.ReadLine());
                break;
            }
            catch (Exception ignored){

            }
        }
        return str;
    }
    public static long getLong(String name){
        long str;
        while (true){
            try {
                Printer.WriteLine("Введите поле " + name);
                str = Long.parseLong(Printer.ReadLine());
                break;
            }
            catch (Exception ignored){

            }
        }
        return str;
    }

    public Ticket get_ticket(){

        String name = TicketManager.getString("name");
        while (validationName(name)) {
            Printer.InvalidValue();
            name = TicketManager.getString("name");
        }

        int price = TicketManager.getInt("price");
        while (validationPrice(price)) {
            Printer.InvalidValue();
            price = TicketManager.getInt("price");
        }

        int discount = TicketManager.getInt("discount");
        while (validationDiscount(discount)) {
            Printer.InvalidValue();
            discount = TicketManager.getInt("discount");
        }

        TicketType type = null;
        while (true){
            try{
                Printer.WriteLine("Введите TicketType");
                for (TicketType ticketType: TicketType.values()) {
                    Printer.Write("\t\t\t\t" + ticketType);
                }
                Printer.WriteLine("");
                String next = Printer.ReadLine();
                if(next.equals("null") || next.equals("")){
                    break;
                }
                type = TicketType.valueOf(next);
                break;
            }
            catch (Exception ignored){

            }
        }
        Printer.WriteLine("Вводится coordinate");

        int x = TicketManager.getInt("X");
        while (validationX(x)) {
            Printer.InvalidValue();
            x = TicketManager.getInt("X");
        }

        long y = TicketManager.getLong("Y");
        while (validationY(y)) {
            Printer.InvalidValue();
            y = TicketManager.getLong("Y");
        }

        Printer.WriteLine("Вводится Venue");

        String name1 = TicketManager.getString("name");
        while (validationName(name1)) {
            Printer.InvalidValue();
            name1 = TicketManager.getString("name");
        }

        int capacity = TicketManager.getInt("capacity");
        while (validationCapacity(capacity)) {
            Printer.InvalidValue();
            capacity = TicketManager.getInt("capacity");
        }

        VenueType type1 = null;
        while (true){
            try{
                Printer.WriteLine("Введите VenueType");
                for (VenueType VenueType: VenueType.values()) {
                    Printer.Write("\t\t\t\t" + VenueType);
                }
                Printer.WriteLine("");
                String next = Printer.ReadLine();
                if(next.equals("null") || next.equals("")){
                    break;
                }
                type1 = VenueType.valueOf(next);
                break;
            }
            catch (Exception ignored){

            }
        }
        return new Ticket(name, new Coordinates(x,y), price, discount, type, new Venue(name1, capacity, type1));
    }

    public Venue GetVenue(){
        Printer.WriteLine("Вводится Venue");

        String name1 = TicketManager.getString("name");
        while (validationName(name1)) {
            Printer.InvalidValue();
            name1 = TicketManager.getString("name");
        }

        int capacity = TicketManager.getInt("capacity");
        while (validationCapacity(capacity)) {
            Printer.InvalidValue();
            capacity = TicketManager.getInt("capacity");
        }

        VenueType type1 = null;
        while (true){
            try{
                Printer.WriteLine("Введите VenueType");
                for (VenueType VenueType: VenueType.values()) {
                    Printer.Write("\t\t\t\t" + VenueType);
                }
                Printer.WriteLine("");
                String next = Printer.ReadLine();
                if(next.equals("null") || next.equals("")){
                    break;
                }
                type1 = VenueType.valueOf(next);
                break;
            }
            catch (Exception ignored){

            }
        }
        return new Venue(name1, capacity, type1);
    }
    public boolean validationY(long y) {
        return y > 292;
    }
    public boolean validationX(int x){
        return false;
    }
    public boolean validationName(String s){
        return s == null || s.equals("");
    }
    public boolean validationPrice(int x){
        return x <= 0;
    }
    public boolean validationDiscount(int x){
        return x <= 0 || x > 100;
    }
    public boolean validationCapacity(int x){
        return x <= 0;
    }
}

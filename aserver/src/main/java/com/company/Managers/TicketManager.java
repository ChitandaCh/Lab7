package com.company.Managers;


import com.company.Models.*;
import com.company.Validator;

public class TicketManager implements Validator {
    public static Long ids = (long)-1;
    public static Long ids_venue = (long)-1;

    public static Long getIds() {
        ids++;
        return ids;
    }

    public static Long getIds_venue() {
        ids_venue++;
        return ids_venue;
    }

    public static String getString(String name){
        String str = "";
        while (str.equals("")){
            com.company.Helper.Printer.WriteLine("Введите поле " + name);
            str = com.company.Helper.Printer.ReadLine();
        }
        return str;
    }
    public static int getInt(String name){
        int str;
        while (true){
            try {
                com.company.Helper.Printer.WriteLine("Введите поле " + name);
                str = Integer.parseInt(com.company.Helper.Printer.ReadLine());
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
                com.company.Helper.Printer.WriteLine("Введите поле " + name);
                str = Long.parseLong(com.company.Helper.Printer.ReadLine());
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
            com.company.Helper.Printer.InvalidValue();
            name = TicketManager.getString("name");
        }

        int price = TicketManager.getInt("price");
        while (validationPrice(price)) {
            com.company.Helper.Printer.InvalidValue();
            price = TicketManager.getInt("price");
        }

        int discount = TicketManager.getInt("discount");
        while (validationDiscount(discount)) {
            com.company.Helper.Printer.InvalidValue();
            discount = TicketManager.getInt("discount");
        }

        TicketType type = null;
        while (true){
            try{
                com.company.Helper.Printer.WriteLine("Введите TicketType");
                for (TicketType ticketType: TicketType.values()) {
                    com.company.Helper.Printer.Write("\t\t\t\t" + ticketType);
                }
                com.company.Helper.Printer.WriteLine("");
                String next = com.company.Helper.Printer.ReadLine();
                if(next.equals("null") || next.equals("")){
                    break;
                }
                type = TicketType.valueOf(next);
                break;
            }
            catch (Exception ignored){

            }
        }
        com.company.Helper.Printer.WriteLine("Вводится coordinate");

        int x = TicketManager.getInt("X");
        while (validationX(x)) {
            com.company.Helper.Printer.InvalidValue();
            x = TicketManager.getInt("X");
        }

        long y = TicketManager.getLong("Y");
        while (validationY(y)) {
            com.company.Helper.Printer.InvalidValue();
            y = TicketManager.getLong("Y");
        }

        com.company.Helper.Printer.WriteLine("Вводится Venue");

        String name1 = TicketManager.getString("name");
        while (validationName(name1)) {
            com.company.Helper.Printer.InvalidValue();
            name1 = TicketManager.getString("name");
        }

        int capacity = TicketManager.getInt("capacity");
        while (validationCapacity(capacity)) {
            com.company.Helper.Printer.InvalidValue();
            capacity = TicketManager.getInt("capacity");
        }

        VenueType type1 = null;
        while (true){
            try{
                com.company.Helper.Printer.WriteLine("Введите VenueType");
                for (VenueType VenueType: VenueType.values()) {
                    com.company.Helper.Printer.Write("\t\t\t\t" + VenueType);
                }
                com.company.Helper.Printer.WriteLine("");
                String next = com.company.Helper.Printer.ReadLine();
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
        com.company.Helper.Printer.WriteLine("Вводится Venue");

        String name1 = TicketManager.getString("name");
        while (validationName(name1)) {
            com.company.Helper.Printer.InvalidValue();
            name1 = TicketManager.getString("name");
        }

        int capacity = TicketManager.getInt("capacity");
        while (validationCapacity(capacity)) {
            com.company.Helper.Printer.InvalidValue();
            capacity = TicketManager.getInt("capacity");
        }

        VenueType type1 = null;
        while (true){
            try{
                com.company.Helper.Printer.WriteLine("Введите VenueType");
                for (VenueType VenueType: VenueType.values()) {
                    com.company.Helper.Printer.Write("\t\t\t\t" + VenueType);
                }
                com.company.Helper.Printer.WriteLine("");
                String next = com.company.Helper.Printer.ReadLine();
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

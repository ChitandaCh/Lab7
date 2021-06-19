package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Responce;
import com.company.Models.Ticket;
import com.company.Models.Venue;
import com.company.Helper.Parser;
import com.company.Models.user;

public class Remove_all_by_venue extends Command {
    @Override
    public Responce Execute(user user) throws Exception {
        Responce responce = new Responce();
        if(args.size() > 0) {
            Venue v = Parser.GetVenue(args.get(0));
            for (Ticket ticket : Main.tickets) {
                if (ticket.getVenue().equals(v) && ticket.getIdCreate() == user.getUserId()) {
                    Main.tickets.remove(ticket);
                    responce.add("Успешно");
                } else {
                    responce.add("Неудачно");
                }
            }
        }
        else{
            responce.add("Не все аргументы");
        }
        return responce;
    }

}

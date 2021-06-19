package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Responce;
import com.company.Models.Ticket;
import com.company.Models.user;


public class Remove_by_id extends Command {
    @Override
    public Responce Execute(user user)  {
        Responce responce = new Responce();
        if(args.size() > 0) {
            for(Ticket ticket : Main.tickets) {
                if (ticket.getId() == Long.parseLong(args.get(0)) && ticket.getIdCreate() == user.getUserId()) {
                    Main.tickets.remove(ticket);
                    responce.add("Успешно");
                } else {
                    responce.add("ID не найден");
                }
            }
        }
        else{
            responce.add("Не все аргументы");
        }
        return responce;
    }
}

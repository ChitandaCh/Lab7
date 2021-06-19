package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Responce;
import com.company.Models.Ticket;
import com.company.Models.user;

public class Show extends Command {
    @Override
    public Responce Execute(user user) {
        Responce responce = new Responce();
        if(Main.tickets.size() != 0) {
            for (Ticket ticket : Main.tickets) {
                responce.add(ticket.toString());
            }
        }
        else{
            responce.add("Ни одного элемента нет");
        }
        return responce;
    }
}

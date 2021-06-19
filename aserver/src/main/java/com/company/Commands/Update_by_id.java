package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Responce;
import com.company.Models.Ticket;
import com.company.Helper.Parser;
import com.company.Models.user;

import java.util.ArrayList;

public class Update_by_id extends Command {
    @Override
    public Responce Execute(user user)  {
        Responce responce = new Responce();
        if(args.size() > 1) {
            Long id = Long.parseLong(args.get(0));
            ArrayList<Ticket> tickets = new ArrayList<>(Main.tickets);

            Ticket ticket = Parser.GetTicket(args.get(1));
            boolean isb = false;

            for (int i = 0; i < tickets.size(); i++) {
                if (tickets.get(i).getId().equals(id) && ticket.getIdCreate() == user.getUserId())  {
                    tickets.set(i, ticket);
                }
            }

            Main.tickets.clear();
            Main.tickets.addAll(tickets);
            if (isb) {
                responce.add("Успешно");
            } else {
                responce.add("ID не найден");
            }
        }
        else{
            responce.add("Не все аргументы");
        }
        return responce;
    }
}

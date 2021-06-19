package com.company.Commands;

import com.company.Main;
import com.company.Helper.Parser;
import com.company.Models.Responce;
import com.company.Command;
import com.company.Models.Ticket;
import com.company.Models.user;

public class Add extends Command {

    @Override
    public Responce Execute(user user)  {
        Responce responce = new Responce();
        if(args.size() >= 1) {
            if(user != null) {
                Ticket ticket = Parser.GetTicket(args.get(0));
                ticket.setIdCreate(user.getUserId());
                Main.tickets.add(ticket);
                responce.add("Успешно");
            }
            else{
                responce.add("пользователь не существует");
            }
        }
        else {
            responce.add("Неудача");
        }
        return responce;
    }
}

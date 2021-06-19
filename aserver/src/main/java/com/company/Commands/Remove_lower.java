package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Responce;
import com.company.Models.Ticket;
import com.company.Helper.Parser;
import com.company.Models.user;

public class Remove_lower extends Command {
    @Override
    public Responce Execute(user user)  {
        Responce responce = new Responce();
        try {
            if(args.size() > 0) {
                Ticket ticket = Parser.GetTicket(args.get(0));
                for(Ticket ticket1 : Main.tickets) {
                    if (ticket1.equals(ticket) && ticket1.getIdCreate() == user.getUserId()) {
                        Main.tickets.remove(ticket1);
                        responce.add("Успешно");
                    } else {
                        responce.add("Неудачно");
                    }
                }
            }
            else{
                responce.add("Не все аргументы");
            }
        }
        catch (Exception e){
            responce.add("Ошибка команды");
        }
        return responce;
    }
}

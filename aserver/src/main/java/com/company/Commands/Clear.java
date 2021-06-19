package com.company.Commands;

import com.company.Main;
import com.company.Models.Responce;
import com.company.Command;
import com.company.Models.user;

public class Clear extends Command {
    @Override
    public Responce Execute(user user) throws Exception {
        Responce responce = new Responce();
        if(user != null) {
            if(user.getUserId() == 0)
                Main.tickets.clear();
            else{
                Main.tickets.removeIf(ticket -> ticket.getIdCreate() == user.getUserId());
            }
        }
        else{
            responce.add("такого пользователя не существует");
        }
        responce.add("Удача");
        return responce;
    }
}

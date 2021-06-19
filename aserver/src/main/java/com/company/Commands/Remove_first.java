package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Responce;
import com.company.Models.user;

public class Remove_first extends Command {
    @Override
    public Responce Execute(user user)  {
        Responce responce = new Responce();
        if(Main.tickets.size() != 0 && user.getUserId() == Main.tickets.getFirst().getIdCreate()) {
            Main.tickets.removeFirst();
        }
        else{
            responce.add("Ни одного элемента нет");
        }
        return responce;
    }
}

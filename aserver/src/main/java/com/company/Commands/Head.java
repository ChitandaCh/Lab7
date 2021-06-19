package com.company.Commands;


import com.company.Command;
import com.company.Main;
import com.company.Models.Responce;
import com.company.Models.user;

public class Head extends Command {
    @Override
    public Responce Execute(user user)  {
        Responce responce = new Responce();
        if(Main.tickets.size() != 0) {
            responce.add(Main.tickets.getFirst().toString());
        }
        else{
                responce.add("Ни одного элемента нет");
        }
        return responce;
    }
}

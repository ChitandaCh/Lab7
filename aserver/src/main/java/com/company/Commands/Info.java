package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Responce;
import com.company.Models.user;

public class Info extends Command {
    @Override
    public Responce Execute(user user)  {
        Responce responce = new Responce();
        responce.add("Начало старта: " + Main.start.toString() + "\r\nКол-во элементов: " + Main.tickets.size());
        return responce;
    }
}

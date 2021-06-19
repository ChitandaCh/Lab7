package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Responce;
import com.company.Models.Ticket;
import com.company.Models.user;

public class Save extends Command {
    @Override
    public Responce Execute(user user) throws Exception {
        Responce responce = new Responce();
        Main.dataBase.DeleteTickets();
        for (Ticket ticket : Main.tickets){
            Main.dataBase.InsertTicket(ticket);
        }
        responce.add("успех");
        return responce;
    }
}

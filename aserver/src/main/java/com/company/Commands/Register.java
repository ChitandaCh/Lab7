package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Responce;
import com.company.Models.user;

public class Register extends Command {
    @Override
    public Responce Execute(user user) throws Exception {
        Responce responce = new Responce();
        try {
            Main.dataBase.InsertUser(new user(args.get(0), args.get(1), 0));
        }
        catch (IndexOutOfBoundsException e){
            responce.add("не хватает аргументов");
        }
        return responce;
    }
}

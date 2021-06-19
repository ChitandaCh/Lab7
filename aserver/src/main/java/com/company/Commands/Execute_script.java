package com.company.Commands;

import com.company.Command;
import com.company.Managers.CommandManager;
import com.company.Models.Responce;
import com.company.Models.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;


public class Execute_script extends Command {
    @Override
    public Responce Execute(user user) throws Exception {
        Responce responce = new Responce();
        try{
            if(args.size() > 0) {
                Scanner scanner = new Scanner(new File(args.get(0)));

                while (scanner.hasNext()) {
                    String next = scanner.nextLine().trim();
                    for (Command Commands : CommandManager.getInstance().GetCommands()) {
                        if (next.startsWith(Commands.getName()) || next.startsWith(Commands.getName().toLowerCase(Locale.ROOT))) {
                            responce.responces.addAll(Commands.Execute(user).responces);
                        }
                    }
                }
            }
            else{
                responce.add("Не все аргументы");
            }
        }
        catch (FileNotFoundException e){
                responce.add("Файла нет или доступ к нему запрещен");
        }
        catch (Exception e){
                responce.add("Команда работает неверно");
        }
        return responce;
    }

}

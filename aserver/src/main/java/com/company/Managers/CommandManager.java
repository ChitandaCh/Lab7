package com.company.Managers;

import com.company.Command;
import com.company.Commands.*;

import java.util.ArrayList;

public class CommandManager {
    static ArrayList<Command> commands = new ArrayList<>();
    static CommandManager instance;

    public CommandManager() {
        commands.add(new Info());
        commands.add(new Save());
        commands.add(new Show());
        commands.add(new Add());
        commands.add(new Clear());
        commands.add(new Execute_script());
        commands.add(new Group_counting_by_coordinates());
        commands.add(new Head());
        commands.add(new Help());
        commands.add(new Print_field_descending_type());
        commands.add(new Remove_all_by_venue());
        commands.add(new Remove_by_id());
        commands.add(new Remove_first());
        commands.add(new Remove_lower());
        commands.add(new Register());
        commands.add(new Update_by_id());
    }

    public static CommandManager getInstance() {
        if(instance == null){
            instance = new CommandManager();
        }
        return instance;
    }

    public ArrayList<Command> GetCommands(){
        return commands;
    }
}

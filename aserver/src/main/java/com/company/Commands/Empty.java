package com.company.Commands;

import com.company.Command;
import com.company.Models.Responce;
import com.company.Models.user;

public class Empty extends Command {
    private static final long serialVersionUID = 0x0fff;
    @Override
    public Responce Execute(user user) throws Exception {
        return null;
    }

    @Override
    public String toString() {
        return "Empty{" +
                "\n\tname=" + getName() + ",\n" +
                "\targs=" + args +
                "\n}";
    }
}

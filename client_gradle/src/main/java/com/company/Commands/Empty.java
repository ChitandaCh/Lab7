package com.company.Commands;

import com.company.Command;

public class Empty extends Command {
    private static final long serialVersionUID = 0x0fff;
    @Override
    public void Execute() throws Exception {

    }

    @Override
    public String toString() {
        return "Empty{" +
                "\n\tname=" + getName() + ",\n" +
                "\targs=" + args +
                "\n}";
    }
}

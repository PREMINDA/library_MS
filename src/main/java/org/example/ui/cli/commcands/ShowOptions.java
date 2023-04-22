package org.example.ui.cli.commcands;

import org.example.ui.cli.interfaces.CLICommand;

import java.util.Map;

public class ShowOptions implements CLICommand {

    private final Map<Integer, CLICommand> commands;

    public ShowOptions(Map<Integer, CLICommand> commands) {
        this.commands = commands;
    }
    @Override
    public void run() {
        System.out.println("Please choose an option: ");
        for (Integer key : commands.keySet()) {
            System.out.print(key + ". ");
            System.out.println( commands.get(key).optionDes());
        }
        System.out.println("");
    }

    @Override
    public String optionDes() {
        return "Show Menu";
    }
}




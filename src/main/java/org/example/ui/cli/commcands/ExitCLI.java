package org.example.ui.cli.commcands;

import org.example.ui.cli.interfaces.CLICommand;

public class ExitCLI implements CLICommand {
    @Override
    public void run() {
        System.out.println("Exiting the library system. Goodbye!");
        System.exit(0);
    }

    @Override
    public String optionDes() {
        return "Exit the library system";
    }

}


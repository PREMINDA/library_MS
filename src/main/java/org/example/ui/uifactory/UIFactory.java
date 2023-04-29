package org.example.ui.uifactory;

import org.example.Repository.LibraryRepository;
import org.example.Services.LibraryService;
import org.example.commands.Commander;
import org.example.enums.UIType;
import org.example.ui.cli.LibraryCLI;
import org.example.ui.gui.LibraryGUI;
import org.example.ui.interfaces.UI;

public class UIFactory {
    private final LibraryService libraryService;
    private final Commander commander;

    public UIFactory(LibraryService libraryService,Commander commander) {
        this.libraryService = libraryService;
        this.commander = commander;
    }

    public UI getUI(UIType type) {
        if (type == UIType.GUI) {
            return new LibraryGUI(libraryService,commander);
        }
        return new LibraryCLI(libraryService,commander);
    }
}

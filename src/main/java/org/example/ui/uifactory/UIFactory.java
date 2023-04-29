package org.example.ui.uifactory;

import org.example.Repository.LibraryRepository;
import org.example.Services.LibraryService;
import org.example.enums.UIType;
import org.example.ui.cli.LibraryCLI;
import org.example.ui.gui.LibraryGUI;
import org.example.ui.interfaces.UI;

public class UIFactory {
    private final LibraryService libraryService;

    public UIFactory(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public UI getUI(UIType type) {
        if (type == UIType.GUI) {
            return new LibraryGUI(libraryService);
        }
        return new LibraryCLI(libraryService);
    }
}

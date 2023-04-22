package org.example.ui.uifactory;

import org.example.Repository.LibraryRepository;
import org.example.enums.UIType;
import org.example.ui.cli.LibraryCLI;
import org.example.ui.gui.LibraryGUI;
import org.example.ui.interfaces.UI;

public class UIFactory {
    private final LibraryRepository libraryRepository;

    public UIFactory(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public UI getUI(UIType type) {
        if (type == UIType.GUI) {
            return new LibraryGUI(libraryRepository);
        }
        return new LibraryCLI(libraryRepository);
    }
}

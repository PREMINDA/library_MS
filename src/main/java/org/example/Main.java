package org.example;

import org.example.Repository.LibraryRepository;
import org.example.Repository.LibraryRepositoryImpl;
import org.example.enums.UIType;
import org.example.ui.interfaces.UI;
import org.example.ui.cli.LibraryCLI;
import org.example.ui.uifactory.UIFactory;

public class Main {
    public static void main(String[] args) {
        LibraryRepository libraryRepository = new LibraryRepositoryImpl();
        UIFactory uiFactory = new UIFactory(libraryRepository);
        UI ui = uiFactory.getUI(UIType.GUI);
        ui.show();
    }
}
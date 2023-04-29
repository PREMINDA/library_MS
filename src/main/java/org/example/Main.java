package org.example;

import org.example.Repository.LibraryRepository;
import org.example.Repository.LibraryRepositoryImpl;
import org.example.Services.LibraryService;
import org.example.Services.LibraryServiceImpl;
import org.example.database.DataBase;
import org.example.enums.UIType;
import org.example.ui.interfaces.UI;
import org.example.ui.uifactory.UIFactory;

public class Main {
    public static void main(String[] args) {
        LibraryRepository libraryRepository = new LibraryRepositoryImpl(DataBase.getSessionFactory());
        LibraryService libraryService = new LibraryServiceImpl(libraryRepository);
        UIFactory uiFactory = new UIFactory(libraryService);
        UI ui = uiFactory.getUI(UIType.GUI);
        ui.show();
    }
}
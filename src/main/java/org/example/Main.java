package org.example;

import org.example.Repository.LibraryRepositoryImpl;
import org.example.database.DataBase;
import org.example.ui.UI;
import org.example.ui.gui.LibraryGUI;

public class Main {
    public static void main(String[] args) {
        UI ui = new LibraryGUI(new LibraryRepositoryImpl());
        ui.show();
    }
}
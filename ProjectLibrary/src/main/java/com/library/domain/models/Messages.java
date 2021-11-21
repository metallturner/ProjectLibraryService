package com.library.domain.models;

public enum Messages {
    CREATE("Для создания нажмите 1"),
    DELETE("Для удаления нажмите 2"),
    SEARCH("Для поиска по имени нажмите 3"),
    UPDATE("Для редактирования нажмите 4"),
    CONTENT("Для показа всех книг 5"),
    EXIT("Для выхода наберите Exit"),
    ID("Введите ID"),
    SHOW_CONTENT("Показать содержимое"),
    NAME("Введите название"),
    ISBN("Введите ISBN книги"),
    AUTHOR("Введите автора"),
    LOCATION("Введите локацию"),
    YEAR_PUB("Введите год публикации"),
    MONTH_PUB("Введите месяц публикации"),
    DAY_PUB("Введите день публикации"),
    YEAR_ADD("Введите год добавления в библиотеку"),
    MONTH_ADD("Введите месяц добавления в библиотеку"),
    DAY_ADD("Введите день добавления в библиотеку"),
    YEAR_MOD("Введите год изменения в библиотеке"),
    MONTH_MOD("Введите месяц изменения в библиотеке"),
    DAY_MOD("Введите день изменения в библиотеке"),
    BOOKS("BOOKS press 1"),
    DOCUMENTS("DOCUMENTS press 2"),
    PATENT_DOCUMENTS("PATENT DOCUMENTS press 3"),
    MAGAZINES("MAGAZINES press 4"),
    USERS("USERS press 5"),
    VARIANTS("--------------------------\n"+
            "Для создания нажмите 1\n"+
            "Для удаления нажмите 2\n"+
            "Для поиска по имени нажмите 3\n"+
            "Для редактирования нажмите 4\n"+
            "Для показа всех книг 5\n"+
            "Для выхода наберите Exit\n"+
            "--------------------------"),
    UPDATE_FOR_BOOKS("Какой параметр вы хотите изменить?:\n" +
            "1. Имя\n" +
            "2. ISBN\n" +
            "3. Автор\n" +
            "4. Локация\n" +
            "5. Дату публикации\n" +
            "6. Дату добавления в библиотеку\n" +
            "7. Дату изменения в библиотеке\n"+
            "Для выхода наберите exit");

    String message;

    Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;

    }
}

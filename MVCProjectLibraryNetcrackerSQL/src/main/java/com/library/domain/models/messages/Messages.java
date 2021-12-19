package com.library.domain.models.messages;

public enum Messages {
    CREATE("Для создания нажмите 1"),
    DELETE("Для удаления нажмите 2"),
    SEARCH("Для поиска по имени нажмите 3"),
    UPDATE("Для редактирования нажмите 4"),
    CONTENT("Для показа всех книг 5"),
    EXIT("Для выхода наберите Exit"),
    ID("Введите ID"),
    ID_AUTHOR("Введите ID автора"),
    ID_LOCATION("Введите ID локации"),
    NUMBER_DOC("Введите номер документа"),
    NAME("Введите название"),
    ISBN("Введите ISBN книги"),
    AUTHOR("Введите имя автора"),
    LOCATION("Введите локацию"),
    LOCATION_NAME("Введите имя(страну)"),
    LOCATION_CITY("Введите город"),
    LOCATION_STATE("Введите штат"),
    YEAR_PUB("Введите год публикации"),
    MONTH_PUB("Введите месяц публикации"),
    DAY_PUB("Введите день публикации"),
    YEAR_CREATE("Введите год создания"),
    MONTH_CREATE("Введите месяц создания"),
    DAY_CREATE("Введите день создания"),
    YEAR_ADD("Введите год добавления в библиотеку"),
    MONTH_ADD("Введите месяц добавления в библиотеку"),
    DAY_ADD("Введите день добавления в библиотеку"),
    YEAR_MOD("Введите год изменения в библиотеке"),
    MONTH_MOD("Введите месяц изменения в библиотеке"),
    DAY_MOD("Введите день изменения в библиотеке"),
    BOOKS("-BOOKS press 1"),
    FINISH_PROGRAM("-TO END THE PROGRAM, ENTER Exit"),
    DOCUMENTS("-DOCUMENTS press 2"),
    PATENT_DOCUMENTS("-PATENT DOCUMENTS press 3"),
    MAGAZINES("-MAGAZINES press 4"),
    USERS("-USERS press 5"),
    AUTHORS("-AUTHORS press 6"),
    LOCATIONS("-LOCATIONS press 7"),
    VARIANTS("--------------------------\n" +
            "Для создания нажмите 1\n" +
            "Для удаления нажмите 2\n" +
            "Для поиска по имени нажмите 3\n" +
            "Для редактирования нажмите 4\n" +
            "Для показа содержимого 5\n" +
            "Для выхода наберите Exit\n" +
            "--------------------------"),
    UPDATE_FOR_BOOKS("Какой параметр вы хотите изменить?:\n" +
            "1. Имя\n" +
            "2. ISBN\n" +
            "3. Автор\n" +
            "4. Локация\n" +
            "5. Дату публикации\n" +
            "6. Дату добавления в библиотеку\n" +
            "7. Дату изменения в библиотеке\n" +
            "Для выхода наберите exit"),
    UPDATE_FOR_DOCS("Какой параметр вы хотите изменить?:\n" +
            "1. Имя\n" +
            "2. Номер документа\n" +
            "3. Локация\n" +
            "4. Дату создания\n" +
            "5. Дату добавления в библиотеку\n" +
            "6. Дату изменения в библиотеке\n" +
            "Для выхода наберите exit"),
    UPDATE_FOR_AUTHORS("Какой параметр вы хотите изменить?:\n" +
            "1. Имя\n" +
            "Для выхода наберите exit"),
    UPDATE_FOR_LOCATIONS("Какой параметр вы хотите изменить?:\n" +
            "1. Имя(страну)\n" +
            "2. Город\n" +
            "3. Штат\n" +
            "Для выхода наберите exit"),
    UPDATE_FOR_MAGAZINES("Какой параметр вы хотите изменить?:\n" +
            "1. Имя\n" +
            "2. Локация\n" +
            "3. Дату публикации\n" +
            "4. Дату добавления в библиотеку\n" +
            "5. Дату изменения в библиотеке\n" +
            "Для выхода наберите exit"),
    UPDATE_FOR_PATENT_DOCS("Какой параметр вы хотите изменить?:\n" +
            "1. Имя\n" +
            "2. Номер документа\n" +
            "3. Автор\n" +
            "4. Локация\n" +
            "5. Дату добавления в библиотеку\n" +
            "6. Дату изменения в библиотеке\n" +
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

package com.library.ui.Gson;

import com.google.gson.*;
import com.library.domain.models.Book;
import com.library.domain.models.Literature;

import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;

public class SerializeBookName implements JsonSerializer<Book> {
    @Override
    public JsonElement serialize(Book book, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty("name", book.getName());
        jsonObject1.addProperty("isbn", book.getIsbn());
        jsonObject1.addProperty("author", book.getAuthor());
        jsonObject1.addProperty("location", book.getLocation());
//        jsonObject1.addProperty("dateOfPublication", book.getDateOfPublication().format(DateTimeFormatter.ISO_LOCAL_DATE));
//        jsonObject1.addProperty("dateAddedToTheLibrary", book.getDateAddedToTheLibrary().format(DateTimeFormatter.ISO_LOCAL_DATE));
//        jsonObject1.addProperty("dateOfModification", book.getDateOfModification().format(DateTimeFormatter.ISO_LOCAL_DATE));

        JsonObject jsonObject3 = new JsonObject();
        jsonObject3.add(book.getName(), jsonObject1);

    return jsonObject3;
    }
}

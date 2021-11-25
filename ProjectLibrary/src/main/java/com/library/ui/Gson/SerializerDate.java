package com.library.ui.Gson;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

/**
 * тут мы учим java gson как работать с датой..
 */
public class SerializerDate implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)  {
        String ldtString = jsonElement.getAsString();
        return LocalDate.parse(ldtString);
    }
    @Override
    public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {

        return new JsonPrimitive(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
    }
}

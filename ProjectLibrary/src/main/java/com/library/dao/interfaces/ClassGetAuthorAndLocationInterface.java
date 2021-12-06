package com.library.dao.interfaces;

import com.library.domain.models.Author;
import com.library.domain.models.Location;

public interface ClassGetAuthorAndLocationInterface {
    public Location getLocation(int id);
    public Author getAuthor(int id);
}

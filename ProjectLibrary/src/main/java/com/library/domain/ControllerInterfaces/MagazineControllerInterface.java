package com.library.domain.ControllerInterfaces;

import com.library.domain.models.Book;
import com.library.domain.models.Magazine;

public interface MagazineControllerInterface {
    public void createMagazine(Magazine magazine);
    public void searchMagazineName(String name);
    public void deleteMagazine(Magazine magazine);
    public void showContent();
    public void updateMagazine(Magazine magazine);

}

package com.library.dao.interfaces;


import com.library.domain.models.Magazine;

public interface MagazineDaoInterface {
    public void createMagazine(Magazine magazine);
    public void searchMagazineName(String name);
    public void deleteMagazine(Magazine magazine);
    public void showContent();
    public void updateMagazine(Magazine magazine);
}

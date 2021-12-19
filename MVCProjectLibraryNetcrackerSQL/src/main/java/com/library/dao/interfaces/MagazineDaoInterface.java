package com.library.dao.interfaces;


import com.library.domain.models.Magazine;

import java.util.List;

public interface MagazineDaoInterface {
    public void createMagazine(Magazine magazine);
    public Magazine getById(int id);
    public void deleteMagazine(Magazine magazine);
    public List<Magazine> showContent();
    public void updateMagazine(Magazine magazine);
}

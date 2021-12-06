package com.library.domain.Controllers;

import com.library.dao.MagazineDao;
import com.library.dao.interfaces.MagazineDaoInterface;
import com.library.domain.ControllerInterfaces.MagazineControllerInterface;
import com.library.domain.models.Magazine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MagazineController implements MagazineControllerInterface {

    MagazineDaoInterface magazineDaoInterface;
@Autowired
    public MagazineController(@Qualifier("magazineDao") MagazineDaoInterface magazineDaoInterface) {
        this.magazineDaoInterface = magazineDaoInterface;
    }

    @Override
    public void createMagazine(Magazine magazine) {
        magazineDaoInterface.createMagazine(magazine);
    }

    @Override
    public void searchMagazineName(String name) {
        magazineDaoInterface.searchMagazineName(name);
    }

    @Override
    public void deleteMagazine(Magazine magazine) {
        magazineDaoInterface.deleteMagazine(magazine);
    }

    @Override
    public void showContent() {
        magazineDaoInterface.showContent();
    }

    @Override
    public void updateMagazine(Magazine magazine) {
        magazineDaoInterface.updateMagazine(magazine);
    }
}


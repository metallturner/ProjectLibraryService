package com.library.ui.Start;

import com.library.ui.RunInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class RunSpring {
    RunInterface runInterfaceBook;
    RunInterface runInterfaceDoc;
    RunInterface runInterfacePatent;
    RunInterface runInterfaceMag;
@Autowired
    public RunSpring(@Qualifier("runBook")RunInterface runInterfaceBook,
                     @Qualifier("runDocument")RunInterface runInterfaceDoc,
                     @Qualifier("runPatentDocuments")RunInterface runInterfacePatent,
                     @Qualifier("runMagazine")RunInterface runInterfaceMag) {
        this.runInterfaceBook = runInterfaceBook;
        this.runInterfaceDoc = runInterfaceDoc;
        this.runInterfacePatent = runInterfacePatent;
        this.runInterfaceMag = runInterfaceMag;
    }

    void runBook() {
        runInterfaceBook.show();

    }

    void runDoc() {
        runInterfaceDoc.show();

    }

    void runPatent() {
        runInterfacePatent.show();

    }

    void runMag() {
        runInterfaceMag.show();

    }
}

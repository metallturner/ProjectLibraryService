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
    RunInterface runInterfaceAuthors;
    RunInterface runInterfaceLocation;


    @Autowired
    public RunSpring(@Qualifier("runBook") RunInterface runInterfaceBook,
                     @Qualifier("runDocument") RunInterface runInterfaceDoc,
                     @Qualifier("runAuthor") RunInterface runInterfaceAuthors,
                     @Qualifier("runPatentDocuments") RunInterface runInterfacePatent,
                     @Qualifier("runMagazine") RunInterface runInterfaceMag,
                     @Qualifier("runLocation") RunInterface runInterfaceLocation) {
        this.runInterfaceBook = runInterfaceBook;
        this.runInterfaceDoc = runInterfaceDoc;
        this.runInterfacePatent = runInterfacePatent;
        this.runInterfaceMag = runInterfaceMag;
        this.runInterfaceAuthors = runInterfaceAuthors;
        this.runInterfaceLocation = runInterfaceLocation;
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
    void runAuthors() {
        runInterfaceAuthors.show();

    }
    void runLocations() {
        runInterfaceLocation.show();

    }
}

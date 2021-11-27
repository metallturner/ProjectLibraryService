package com.library.ui;

import org.apache.log4j.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class);
    public static void main(String[] args) {

        Run run = new Run();
        log.info("Был выполнен запуск программы");
        run.run();

    }




}



package com.library.ui.Start;

import com.library.domain.models.messages.Messages;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Scanner sc = new Scanner(System.in);
        boolean b = true;
        while (b) {

            System.out.println(Messages.BOOKS);
            System.out.println(Messages.DOCUMENTS);
            System.out.println(Messages.PATENT_DOCUMENTS);
            System.out.println(Messages.MAGAZINES);
            System.out.println(Messages.USERS);
            System.out.println(Messages.AUTHORS);
            System.out.println(Messages.LOCATIONS);
            System.out.println(Messages.FINISH_PROGRAM);
            String s = sc.nextLine();
            switch (s) {
                case "1":
                context.getBean("runSpring", RunSpring.class).runBook();
                    break;
                case "2":
                    context.getBean("runSpring", RunSpring.class).runDoc();
                    break;
                case "3":
                    context.getBean("runSpring", RunSpring.class).runPatent();
                    break;
                case "4":
                    context.getBean("runSpring", RunSpring.class).runMag();
                    break;
                case "6":
                    context.getBean("runSpring", RunSpring.class).runAuthors();
                    break;
                case "7":
                    context.getBean("runSpring", RunSpring.class).runLocations();
                    break;
            }
            if (s.equalsIgnoreCase("exit")){
                return;
            }
        }
    }
 }

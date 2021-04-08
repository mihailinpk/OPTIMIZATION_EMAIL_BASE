package main;

import main.iostreams.EmailBaseIOStreams;
import main.tools.EmailBaseTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.*;
import java.util.*;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private final String EMAIL_FILE_PATH = "emails_sourse.dat";

    private ApplicationContext context;

    @Autowired
    public void context(ApplicationContext context) { this.context = context; }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {

        EmailBaseIOStreams ioStreams = context.getBean("emailBaseIOStreams", EmailBaseIOStreams.class);
        EmailBaseTools tools = context.getBean("emailBaseTools", EmailBaseTools.class);

        try   {
            Map<String, String> usersEmailMap = ioStreams.getMapParsingDataFromInputStream(new FileInputStream(EMAIL_FILE_PATH));
            Map<String, HashSet<String>> structuredUsersEmailMap = tools.getStructuredUsersEmailMap(usersEmailMap);
            ioStreams.writeMapToOutputStream(System.out, structuredUsersEmailMap);
        } catch (Exception ex)  {
            ex.printStackTrace();
        }

    }

}
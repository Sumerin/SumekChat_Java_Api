package com.Sumerin.SumekChat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SumekChatApplication {
    
    public static void main(String[] args) throws IOException
    {
        
        initializeFirebaseConnection("/home/sumek/Downloads/private_firebase.json");
        SpringApplication.run(SumekChatApplication.class, args);


    }
    
    private static void initializeFirebaseConnection(String path ) throws IOException
    {
        FileInputStream serviceAccount = new FileInputStream(path);
        
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                .setDatabaseUrl("https://sumektest.firebaseio.com/")
                .build();
        
        FirebaseApp.initializeApp(options);
    }
}

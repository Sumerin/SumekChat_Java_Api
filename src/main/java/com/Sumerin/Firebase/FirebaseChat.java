/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Sumerin.Firebase;

import com.google.firebase.database.*;
import com.google.firebase.tasks.OnSuccessListener;
import java.util.*;
import java.util.logging.Level;
import com.google.firebase.tasks.*;

/**
 *
 * @author sumek
 */
public class FirebaseChat {

    private DatabaseReference dbChatRoom;
    private List<Message> messagesOnServer;

    public FirebaseChat(int room)
    {
        StringBuilder pathBuilder = new StringBuilder("Chat/Rooms/");
        pathBuilder.append(room);
        messagesOnServer = Collections.synchronizedList(new ArrayList<>());

        dbChatRoom = FirebaseDatabase.getInstance().getReference(pathBuilder.toString());
        dbChatRoom.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds)
            {
                Map<Object, Message> msgs = (Map<Object, Message>) ds.getValue();
                messagesOnServer = new ArrayList<>(msgs.values());
            }

            @Override
            public void onCancelled(DatabaseError de)
            {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

    }

    public void addMessage(Message data) throws InterruptedException
    {
        Object lock = new Object();
        synchronized (lock)
          {
            dbChatRoom.push().setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void t)
                {
                    lock.notify();
                }
            });
            lock.wait(1000);

          }
    }

    public List<Message> getMessages()
    {
        return messagesOnServer;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Sumerin.Firebase;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import net.thegreshams.firebase4j.service.Firebase;

/**
 *
 * @author sumek
 */
public class FirebaseChat {

    private final String url = "https://sumektest.firebaseio.com/";
    private final String room;
    private Firebase db;
    private int msgIterator;

    public FirebaseChat(int room)
    {
        StringBuilder pathBuilder = new StringBuilder("Chat/Rooms/");
        pathBuilder.append(room);
        pathBuilder.append("/");

        this.room = pathBuilder.toString();
        this.msgIterator = 1;
        try
          {

            this.db = new Firebase(url);
          }
        catch (FirebaseException ex)
          {
            Logger.getLogger(FirebaseChat.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    public int addMessage(Map<String, Object> data)
    {
        try
          {
            StringBuilder path = new StringBuilder(room);
            path.append(msgIterator++);

            FirebaseResponse response = db.put(path.toString(), data);
            return response.getCode();
          }
        catch (JacksonUtilityException | FirebaseException | UnsupportedEncodingException ex)
          {
            Logger.getLogger(FirebaseChat.class.getName()).log(Level.SEVERE, null, ex);
          }
        return 400;
    }

    public List<Message> getMessages()
    {
        try
          {
            FirebaseResponse response = db.get(room);
            return JsonConverter.JsonStringToListMessage(response.getRawBody());
          }
        catch (FirebaseException | UnsupportedEncodingException | JacksonUtilityException ex)
          {
            Logger.getLogger(FirebaseChat.class.getName()).log(Level.SEVERE, null, ex);
          }
        return null;
    }
}

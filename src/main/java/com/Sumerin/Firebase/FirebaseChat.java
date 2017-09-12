/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Sumerin.Firebase;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
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
    private Firebase db;
    private int msgIterator;

    public FirebaseChat()
    {
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

    public int addMessage(String username, String message)
    {
        try
          {
            Map<String, Object> data = new LinkedHashMap<String, Object>();
            data.put("username", username);
            data.put("message", message);

            StringBuilder path = new StringBuilder("Chat/");
            path.append(msgIterator++);
            
            FirebaseResponse response = db.put(path.toString(), data);
          }
        catch (JacksonUtilityException ex)
          {
            Logger.getLogger(FirebaseChat.class.getName()).log(Level.SEVERE, null, ex);
          }
        catch (FirebaseException ex)
          {
            Logger.getLogger(FirebaseChat.class.getName()).log(Level.SEVERE, null, ex);
          }
        catch (UnsupportedEncodingException ex)
          {
            Logger.getLogger(FirebaseChat.class.getName()).log(Level.SEVERE, null, ex);
          }
        return 200;
    }
    
    
}

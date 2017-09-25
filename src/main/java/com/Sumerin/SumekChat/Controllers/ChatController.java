/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Sumerin.SumekChat.Controllers;

import com.Sumerin.Firebase.FirebaseChat;
import com.Sumerin.Firebase.Message;
import java.util.List;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author sumek
 */
@RestController
@RequestMapping("/Chat")
public class ChatController {
    
    private final FirebaseChat[] chat = new FirebaseChat[2];
    
    public ChatController()
    {
        chat[0] = new FirebaseChat(0);
        chat[1] = new FirebaseChat(1);
    }
    
    
    @GetMapping("/{id}")
    public List<Message> listMessage(@PathVariable int id)
    {
        return chat[id].getMessages();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> putMessage(@PathVariable int id, @RequestBody Message msg)
    {
     
        chat[id].addMessage(msg.toEnvelope());
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
}

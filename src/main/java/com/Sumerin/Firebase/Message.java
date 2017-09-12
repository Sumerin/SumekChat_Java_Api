/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Sumerin.Firebase;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;

/**
 *
 * @author sumek
 */
public class Message {

    @Getter
    final private String username;

    @Getter
    final private String text;

    public Message(String username, String text)
    {
        this.username = username;
        this.text = text;
    }

    public Message(Map<String, Object> map)
    {
        this.username = (String) map.get("username");
        this.text = (String) map.get("text");
    }

    public Map<String, Object> toEnvelope()
    {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("username", username);
        result.put("text", text);

        return result;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null)
          {
            return false;
          }
        if (other == this)
          {
            return true;
          }
        if (!(other instanceof Message))
          {
            return false;
          }
        Message comp = (Message) other;

        return (this.username.equals(comp.username)
                && this.text.equals(comp.text));
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.username);
        hash = 79 * hash + Objects.hashCode(this.text);
        return hash;
    }
}

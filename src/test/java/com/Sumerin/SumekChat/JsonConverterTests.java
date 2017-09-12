/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Sumerin.SumekChat;

import com.Sumerin.Firebase.JsonConverter;
import java.util.List;
import org.junit.Test;
import com.Sumerin.Firebase.Message;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import static org.junit.Assert.*;

/**
 *
 * @author sumek
 */
public class JsonConverterTests {
    
    @Test
    public void whenProperStringGiven_thenListOfMessagesGot()
    {
        try
          {
            //Arrange
            String responseBody = "["
                    + "{\"text\":\"Witaj!\","
                    + "\"username\":\"Serwer\"}"
                    + ","
                    + "{\"text\":\"dupa\","
                    + "\"username\":\"Adam\"}"
                    + "]";
            
            List<Message> properAnswer = new ArrayList<>();
            
            properAnswer.add(new Message("Serwer", "Witaj!"));
            properAnswer.add(new Message("Adam", "dupa"));

            //Act
            List<Message> answer = JsonConverter.JsonStringToListMessage(responseBody);

            //Assert
            assertTrue(answer.equals(properAnswer));
          }
        catch (JacksonUtilityException ex)
          {
            fail();
          }
        
    }
    
}

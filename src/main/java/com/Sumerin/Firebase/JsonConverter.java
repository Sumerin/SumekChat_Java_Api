/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Sumerin.Firebase;

import java.util.ArrayList;
import java.util.List;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.util.JacksonUtility;

/**
 *
 * @author sumek
 */
public class JsonConverter {

    private static final String pattern = "\\},\\{";

    public static List<Message> JsonStringToListMessage(String rawBody) throws JacksonUtilityException
    {
        List<String> jsonStrings = splitIntoRecords(rawBody);
        List<Message> result = new ArrayList<>(); // TODO: maybe LinkedList

        
        for (String jsonString : jsonStrings)
          {
            result.add(new Message(
                    JacksonUtility.GET_JSON_STRING_AS_MAP(
                            jsonString)));
          }

        return result;
    }

    private static List<String> splitIntoRecords(String body)
    {
        body = body.substring(1, body.length() - 2);
        String[] split = body.split(pattern);

        List<String> result = new ArrayList<>(); //TODO: maybe LinkedList

        for (int i = 0; i < split.length; i++)
          {
            StringBuilder workSpaceOfString = new StringBuilder();
            if (i != 0)
              {
                workSpaceOfString.append("{");
              }
            workSpaceOfString.append(split[i]);
            if (i != split.length)
              {
                workSpaceOfString.append("}");
              }

            result.add(workSpaceOfString.toString());
          }

        return result;
    }
}

package com.xhd.chitchat.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xhd.chitchat.entity.ResultMessage;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class MessageUtils  {

    public static String getMessage(Boolean isSystemMessage,String fromName,Object message,String avataraddress){

        try {
            ResultMessage resultMessage = new ResultMessage();
            resultMessage.setIsStstem(isSystemMessage);
            resultMessage.setMessage(message);
            resultMessage.setAvataraddress(avataraddress);
            if ( fromName !=null) {
                resultMessage.setFromName(fromName);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(resultMessage);
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
                return null;
    }
}

package com.xhd.chitchat.controller;

import com.xhd.chitchat.entity.Message;
import com.xhd.chitchat.util.GetHttpsessionConfig;

import com.xhd.chitchat.util.MessageUtils;
import com.xhd.offline.entity.Offline;
import com.xhd.offline.service.OfflineService;
import com.xhd.user.entity.User;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chatserver",configurator = GetHttpsessionConfig.class)
@Component
public class ChatEndpoint {

    /**
     * 存取聊天的用户 ChatEndpoint类 相当于一个用户（用户客户端）
     */
    public static Map<String,Object>  onlineUser= new ConcurrentHashMap<>();


    /**
     *  用于信息相互
     */
    private Session session;

    /**
     *   httpSession存本人的用户信息
     */
    private HttpSession httpSession;


    /**
     * 存储聊天记录集合
     */
    private static ArrayList<Offline> offlineschat =new ArrayList<>();

    /**
     * 往数据库储存的list长度
     */
    private static  final  Integer  LIST_SIZE=1;



    /**
     * 注入service对象
     */
    private static OfflineService myOff;
    @Autowired
    public void setMyServiceImpl(OfflineService myService){
        ChatEndpoint.myOff = myService;
    }

    /**
     * 建立ws链接执行
     * @param session
     * @param endpointConfig
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig){
        this.session=session;
        HttpSession httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        this.httpSession=httpSession;
        //获取登录用户信息
        User user = (User) httpSession.getAttribute("user");
        //当前ChatEndpoint 保存到Map集合中
        onlineUser.put(user.getName(),this);
        ChatEndpoint chatEndpoint = (ChatEndpoint) onlineUser.get(user.getName());
    }

    @OnMessage
    public void onMessage(String message , Session session){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Message m= objectMapper.readValue(message, Message.class);
            User user = (User) httpSession.getAttribute("user");
                String toName = m.getToName();
                String mess = m.getMessage();
                String avataraddress = m.getAvataraddress();

                String resultmessage = MessageUtils.getMessage(false, user.getName(), mess,avataraddress);
                    ChatEndpoint  chatEndpoint = (ChatEndpoint) onlineUser.get(toName);
                if (chatEndpoint!=null){
                chatEndpoint.session.getBasicRemote().sendText(resultmessage);
                }
               Offline offline = new Offline();
               offline.setSendName(user.getName());
               offline.setAcceptName(toName);
               offline.setContent(mess);
               offline.setSendTime(new Date());

                if (chatEndpoint==null){
                    offline.setOfflineMessage(1);
                    offlineschat.add(offline);
                    myOff.saveBatch(offlineschat);
                    offlineschat.clear();
                }else{
                    offlineschat.add(offline);
                }
              if (offlineschat.size()==LIST_SIZE){
                myOff.saveBatch(offlineschat);
                offlineschat.clear();
               }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     @OnClose
     public void onClose(Session session){
         User user = (User) httpSession.getAttribute("user");
         onlineUser.remove(user.getName());
         myOff.saveBatch(offlineschat);
         offlineschat.clear();
     }


}

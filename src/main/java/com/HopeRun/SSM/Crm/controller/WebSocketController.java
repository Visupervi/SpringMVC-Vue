package com.HopeRun.SSM.Crm.controller;

import org.apache.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: visupervi
 * @Date: 2020-07-01 14:31
 * @return:
 * @Description 创建一个websocket服务
 */
@ServerEndpoint("/apis/wsTest")
public class WebSocketController {
    private Logger logger = Logger.getLogger(WebSocketController.class);
    private static int onlineCount = 0;


    /**
     * concurrent包的线程安全Set，对应客户端的每一个
     */
    private static CopyOnWriteArraySet<WebSocketController> webSocketSet = new CopyOnWriteArraySet<WebSocketController>();

    /**
     * 通过session向客户端传输数据
     */

    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        logger.info("有新链接联入" + getOnlineCount());

    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("来自客户端的信息===>" + message);

        for (WebSocketController item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }

    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("发生错误");
        error.printStackTrace();
    }

    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    private void subOnlineCount() {
        WebSocketController.onlineCount--;
    }

    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private void addOnlineCount() {
        WebSocketController.onlineCount++;

    }

}

package com.ivan.webapp.sockets;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SocketHandler extends TextWebSocketHandler {
  private static final Logger logger = LogManager.getLogger(SocketHandler.class);

  List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
  private static Integer aa = 0;

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message)
      throws InterruptedException, IOException {

    for (WebSocketSession webSocketSession : sessions) {
      // Map value = new Gson().fromJson(message.getPayload(), Map.class);
      aa++;
      if(aa % 3 == 0){
        webSocketSession.close();
      }else{
        webSocketSession.sendMessage(new TextMessage("Hello " + aa.toString() + "" + " !"));
      }
    }
  }

  @Override
  public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
    logger.error("error occured at sender " + session, throwable);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    logger.info(String.format("Session %s closed because of %s", session.getId(), status.getReason()));
    sessions.remove(session);

  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    sessions.add(session);
    logger.info("Connected ... " + session.getId());

  }

}
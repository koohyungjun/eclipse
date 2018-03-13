package total.controller;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

/*
 * ws 통신을 제어할용도의 컨트롤러 구현
 * 	1. WebSocketHandler (I) 를 implements 걸어서 목적에 개조해서 사용.
 * 	2. 목적에 맞는 WebSocketHandler를 extends 걸어서 사용
 * 		- TextWebSocketHandler  / BinaryWebSocketHandler
 * 
 *  WebSocket Handler 의 매핑은, spring 설정파일에.
 */
// scan 으로 등록되는 빈은 클래스명으로 등록됨. 바꿀수 있음.
@Controller("wsController")
public class WSController extends TextWebSocketHandler {

	Set<WebSocketSession> wsSessions;

	@PostConstruct // init-method
	public void init() {
		wsSessions = new LinkedHashSet<>();
	}

	@Override // 클라측에서 웹소켓 연결되었을때
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("afterConnectionEstablished.." + session.getId());
		// System.out.println(session.getRemoteAddress().getHostName());
		// System.out.println(session.getRemoteAddress().getAddress().getHostAddress());
		wsSessions.add(session);
		Map<String, Object> msg = new HashMap<>();
		msg.put("cnt", wsSessions.size());
		msg.put("info", "connected " + session.getRemoteAddress().getAddress().getHostAddress());
		String json = new Gson().toJson(msg);
		System.out.println(json);
		for (WebSocketSession ws : wsSessions) {
			ws.sendMessage(new TextMessage(json));
		}

	}

	@Override // 클라측에서 메세지가 들어올때.
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("handleTextMessage.." + session + " / " + message);
	}

	@Override // 클라측과 연결이 해제될때.
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// System.out.println("afterConnectionClosed.." + session);
		wsSessions.remove(session);
		Map<String, Object> msg = new HashMap<>();
			msg.put("cnt", wsSessions.size());
			msg.put("info", "disconnected " + session.getRemoteAddress().getAddress().getHostAddress());
		for (WebSocketSession ws : wsSessions) {
			ws.sendMessage(new TextMessage(new Gson().toJson(msg)));
		}
	}
}
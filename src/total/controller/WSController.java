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
 * ws ����� �����ҿ뵵�� ��Ʈ�ѷ� ����
 * 	1. WebSocketHandler (I) �� implements �ɾ ������ �����ؼ� ���.
 * 	2. ������ �´� WebSocketHandler�� extends �ɾ ���
 * 		- TextWebSocketHandler  / BinaryWebSocketHandler
 * 
 *  WebSocket Handler �� ������, spring �������Ͽ�.
 */
// scan ���� ��ϵǴ� ���� Ŭ���������� ��ϵ�. �ٲܼ� ����.
@Controller("wsController")
public class WSController extends TextWebSocketHandler {

	Set<WebSocketSession> wsSessions;

	@PostConstruct // init-method
	public void init() {
		wsSessions = new LinkedHashSet<>();
	}

	@Override // Ŭ�������� ������ ����Ǿ�����
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

	@Override // Ŭ�������� �޼����� ���ö�.
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("handleTextMessage.." + session + " / " + message);
	}

	@Override // Ŭ������ ������ �����ɶ�.
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
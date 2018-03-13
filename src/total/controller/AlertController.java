package total.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller("alertController")
public class AlertController extends TextWebSocketHandler {

	// 웹소켓 커넥션이 열릴때, 세션을 키로 해서 묶어서 관리를 하려고 함.
	Map<String, List<WebSocketSession>> sessions;

	@PostConstruct
	public void init() {
		sessions = new HashMap<>();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		/*
		 * HttpSession 을 접근해서 얻어와야 함.. 그냥은 안되고.. HttpSessionHandshakeInterceptor 를
		 * 설정해두면, Spring이 이 메서드를 호출할때, 이 클라이언트가 사용중인 HttpSession의 setAttribute 되어 있는 값들을
		 * WebSocketSession에서 뽑아다 쓸수 있게 넣어줌. 그러면서 추가로. "HTTP.SESSION.ID" 라는 키로 사용중인
		 * session id도 넣어주고.
		 */

		System.out.println("AlertController.connectionEstablished");
		Map<String, Object> map = session.getAttributes();
		System.out.println(map);
		String key = (String)session.getAttributes().get("HTTP.SESSION.ID"); 
		if (!sessions.containsKey(key))
			sessions.put(key, new ArrayList<>());
		sessions.get(key).add(session);
		
		for(String k : sessions.keySet() ) {
			int size = sessions.get(k).size();
			System.out.println("→ "+ k +" ("+ size +")");
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String key = (String)session.getAttributes().get("HTTP.SESSION.ID"); 
		System.out.println(sessions + " / " + sessions.get(key));
		System.out.println(key);
		System.out.println(sessions);
		System.out.println(sessions.get(key));
		
		sessions.get(key).remove(session);
		if(sessions.get(key).isEmpty()) {
			sessions.remove(key);
		}
	}
}

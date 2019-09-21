package com.example.card.Service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/battle")
@Component
public class Battle {
    private static int playerNum = 0;
    private static Map<String, Session> playersSession = new ConcurrentHashMap<>();
    private static Map<String, String> playersPick = new ConcurrentHashMap<>();
    private static Map<String, String> opponent = new ConcurrentHashMap<>();

    @OnOpen
    public void connect(Session session) {
        playerNum += 1;
        System.out.println("playersNum: " + playerNum);
    }

    @OnClose
    public void disConnect(Session session) {
        playerNum -= 1;
        System.out.println("playersNum: " + playerNum);
    }

    @OnError
    public void connectError(Throwable error) {
        System.out.println("connect error");
        error.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        // 注册
        JSONObject params = JSONObject.fromObject(msg);
        String address = params.getString("address");
        String soldier = params.getString("soldier");
        register(address, soldier, session);

        System.out.println(playersSession);
        System.out.println(playersPick);

        if (playersSession.size() == 2) {
            //判断结果
            String opponentAddress = opponent.get(address);
            String opponentSoldier = playersPick.get(opponentAddress);
            int result = judge(soldier, opponentSoldier);
            if (result == 1) {
                // 己方获胜
                JSONObject jsonObject = new JSONObject()
                        .element("result", "win");
                try {
                    sendMsg(session, jsonObject.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Session opponentSession = playersSession.get(opponentAddress);
                JSONObject jsonObject1 = new JSONObject()
                        .element("result", "lose");
                try {
                    sendMsg(opponentSession, jsonObject1.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (result == -1) {
                // 对方获胜
                JSONObject jsonObject = new JSONObject()
                        .element("result", "lose");
                try {
                    sendMsg(session, jsonObject.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Session opponentSession = playersSession.get(opponentAddress);
                JSONObject jsonObject1 = new JSONObject()
                        .element("result", "win");
                try {
                    sendMsg(opponentSession, jsonObject1.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                JSONObject jsonObject = new JSONObject()
                        .element("result", "tie");
                Session opponentSession = playersSession.get(opponentAddress);
                try {
                    sendMsg(opponentSession, jsonObject.toString());
                    sendMsg(session, jsonObject.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void register(String address, String soldier, Session session) {
        assert (playersSession.size() <= 2);
        if (!playersSession.containsKey(address)) {
            playersSession.put(address, session);
        }
        else {
            playersSession.replace(address, session);
        }

        assert (playersPick.size() <= 2);
        if (!playersPick.containsKey(address)) {
            playersPick.put(address, soldier);
        }
        else {
            playersPick.replace(address, soldier);
        }

        assert (opponent.size() <= 2);
        if (opponent.size() == 0) {
            opponent.put(address, "0x0");
        }
        else {
            String opponentAddress = "0x0";
            for (String item: opponent.keySet()) {
                opponent.replace(item, address);
                opponentAddress = item;
            }
            opponent.put(address, opponentAddress);
        }
    }

    private int judge(String type1, String type2) {
        switch (type1) {
            case "infancy":
                if (type2.equals("shieldman")) {
                    return 1;
                } else if (type2.equals("archer") || type2.equals("cavalry")) {
                    return -1;
                } else {
                    return 0;
                }
            case "spearman":
                if (type2.equals("cavalry")) {
                    return 1;
                } else if (type2.equals("shieldman") || type2.equals("archer")) {
                    return -1;
                } else {
                    return 0;
                }
            case "shieldman":
                if (type2.equals("spearman") || type2.equals("archer")) {
                    return 1;
                } else if (type2.equals("infancy") || type2.equals("cavalry")) {
                    return -1;
                } else {
                    return 0;
                }
            case "archer":
                if (type2.equals("infancy") || type2.equals("spearman")) {
                    return 1;
                } else if (type2.equals("shieldman")) {
                    return -1;
                } else {
                    return 0;
                }
            case "cavalry":
                if (type2.equals("infancy") || type2.equals("shieldman")) {
                    return 1;
                } else if (type2.equals("spearman")) {
                    return -1;
                } else {
                    return 0;
                }
            default:
                return 0;
        }
    }

    private void sendMsg(Session session, String msg) throws Exception {
        session.getBasicRemote().sendText(msg);
    }
}

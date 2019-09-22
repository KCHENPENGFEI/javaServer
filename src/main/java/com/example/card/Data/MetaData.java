package com.example.card.Data;

import net.sf.json.JSONObject;
import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MetaData  {
    private static Map<String, JSONObject> metaData = new ConcurrentHashMap<>();

    static {
        List<String> soldiers = Arrays.asList("infancy", "spearman", "shieldman", "archer", "cavalry");
        List<JSONObject> data = new ArrayList<>();

        String infancyPath = "/Users/chenpengfei/Documents/OasisHackathon/cardGame/javaServer/src/main/resources/static/assets/infancy.png";
        String spearmanPath = "/Users/chenpengfei/Documents/OasisHackathon/cardGame/javaServer/src/main/resources/static/assets/spearman.png";
        String shieldmanPath = "/Users/chenpengfei/Documents/OasisHackathon/cardGame/javaServer/src/main/resources/static/assets/shieldman.png";
        String archerPath = "/Users/chenpengfei/Documents/OasisHackathon/cardGame/javaServer/src/main/resources/static/assets/archer.png";
        String cavalryPath = "/Users/chenpengfei/Documents/OasisHackathon/cardGame/javaServer/src/main/resources/static/assets/cavalry.png";
//        File file = ResourceUtils.getFile("classpath:static/assets/" + name + ".png");
        List<String> filePath = Arrays.asList(infancyPath, spearmanPath, shieldmanPath, archerPath, cavalryPath);
        List<String> encodeBase64 = new ArrayList<>();

        for (int i = 0; i < filePath.size(); ++i) {
            String imageFile = filePath.get(i);
            InputStream inputStream = null;
            byte[] imageData;
            String encode;
            BASE64Encoder encoder = new BASE64Encoder();
            try {
                // 读取图片字节数组
                inputStream = new FileInputStream(imageFile);
                imageData = new byte[inputStream.available()];
                inputStream.read(imageData);
                encode = encoder.encode(imageData);
                encodeBase64.add(encode);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException er) {
                    // TODO Auto-generated catch block
                    er.printStackTrace();
                }
            }
        }
//        File file = new File("D:/test.jpg");
//        FileInputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        byte[] bytes = new byte[0];
//        try {
//            bytes = new byte[inputStream.available()];
//            inputStream.read(bytes, 0, inputStream.available());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        JSONObject infancy = new JSONObject()
                .element("name", "infancy")
                .element("description", "步兵, 战力最低")
                .element("image", encodeBase64.get(0))
                .element("properties", new JSONObject()
                        .element("restraint", new String[]{"shieldman"})
                        .element("beRestraint", new String[]{"archer", "cavalry"}));
        JSONObject spearMan = new JSONObject()
                .element("name", "spearman")
                .element("description", "矛兵，具有比步兵更高的战力")
                .element("image", encodeBase64.get(1))
                .element("properties", new JSONObject()
                        .element("restraint", new String[]{"cavalry"})
                        .element("beRestraint", new String[]{"shieldman", "archer"}));
        JSONObject shieldMan = new JSONObject()
                .element("name", "shieldman")
                .element("description", "盾兵，具有较强的防御力")
                .element("image", encodeBase64.get(2))
                .element("properties", new JSONObject()
                        .element("restraint", new String[]{"spearman", "archer"})
                        .element("beRestraint", new String[]{"infancy", "cavalry"}));
        JSONObject archer = new JSONObject()
                .element("name", "archer")
                .element("description", "弓箭手，克制短程士兵")
                .element("image", encodeBase64.get(3))
                .element("properties", new JSONObject()
                        .element("restraint", new String[]{"infancy", "spearman"})
                        .element("beRestraint", new String[]{"shieldman"}));
        JSONObject cavalry = new JSONObject()
                .element("name", "cavalry")
                .element("description", "骑兵，最高战力，攻防均衡")
                .element("image", encodeBase64.get(4))
                .element("properties", new JSONObject()
                        .element("restraint", new String[]{"infancy", "shieldman"})
                        .element("beRestraint", new String[]{"spearman"}));
        data.add(infancy);
        data.add(spearMan);
        data.add(shieldMan);
        data.add(archer);
        data.add(cavalry);
        for (int i = 0; i < soldiers.size(); ++i) {
            metaData.put(soldiers.get(i), data.get(i));
        }
    }

    public static JSONObject getMateData(String name) {
        return metaData.get(name);
    }
}

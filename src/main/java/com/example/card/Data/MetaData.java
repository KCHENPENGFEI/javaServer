package com.example.card.Data;

import net.sf.json.JSONObject;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MetaData {
    private static Map<String, JSONObject> metaData = new ConcurrentHashMap<>();

    static  {
        List<String> soldiers = Arrays.asList("infancy", "spearman", "shieldman", "archer", "cavalry");
        List<JSONObject> data = new ArrayList<>();

        JSONObject infancy = new JSONObject()
                .element("name", "infancy")
                .element("description", "步兵, 战力最低")
                .element("image", "image")
                .element("properties", new JSONObject()
                        .element("restraint", new String[]{"shieldman"})
                        .element("beRestraint", new String[]{"archer", "cavalry"}));
        JSONObject spearMan = new JSONObject()
                .element("name", "spearman")
                .element("description", "矛兵，具有比步兵更高的战力")
                .element("image", "image")
                .element("properties", new JSONObject()
                        .element("restraint", new String[]{"cavalry"})
                        .element("beRestraint", new String[]{"shieldman", "archer"}));
        JSONObject shieldMan = new JSONObject()
                .element("name", "shieldman")
                .element("description", "盾兵，具有较强的防御力")
                .element("image", "image")
                .element("properties", new JSONObject()
                        .element("restraint", new String[]{"spearman", "archer"})
                        .element("beRestraint", new String[]{"infancy", "cavalry"}));
        JSONObject archer = new JSONObject()
                .element("name", "archer")
                .element("description", "弓箭手，克制短程士兵")
                .element("image", "image")
                .element("properties", new JSONObject()
                        .element("restraint", new String[]{"infancy", "spearman"})
                        .element("beRestraint", new String[]{"shieldman"}));
        JSONObject cavalry = new JSONObject()
                .element("name", "cavalry")
                .element("description", "骑兵，最高战力，攻防均衡")
                .element("image", "image")
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

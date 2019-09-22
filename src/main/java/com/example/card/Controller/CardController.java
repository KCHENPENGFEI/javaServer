package com.example.card.Controller;

import com.example.card.Data.MetaData;
import com.example.card.Data.ParseCryptoKitty;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.sf.json.JSONObject;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class CardController {

//    @ResponseBody
//    @RequestMapping(value = "/CARD/{category}", method = RequestMethod.GET)
//    public JSONObject getMetaData(@PathVariable String category) throws FileNotFoundException {
//        JSONObject result = MetaData.getMateData(category);
//        return result;
//    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public JSONObject getCryptoKittyMetaData(@RequestParam String symbol, @RequestParam String uri, @RequestParam String uuid) {
        String[] strList = uri.split("/");
        JSONObject result;
        System.out.println(symbol);
        System.out.println(uri);
        System.out.println(uuid);
        String category = strList[strList.length - 1];
        if (symbol.equals("CARD")) {
            result = MetaData.getMateData(category);
        }
        else {
            ParseCryptoKitty parseCryptoKitty = new ParseCryptoKitty(symbol, category);
            String soldier = parseCryptoKitty.getSoldier();
            result = MetaData.getMateData(soldier);
        }
        return result;
    }
}

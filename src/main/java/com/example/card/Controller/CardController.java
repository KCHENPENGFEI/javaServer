package com.example.card.Controller;

import com.example.card.Data.MetaData;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.sf.json.JSONObject;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class CardController {

    @ResponseBody
    @RequestMapping(value = "/CARD/{category}", method = RequestMethod.GET)
    public JSONObject getMetaData(@PathVariable String category) throws FileNotFoundException {
        JSONObject result = MetaData.getMateData(category);
        return result;
    }

    @ResponseBody@RequestMapping(value = "/", method = RequestMethod.GET)
    public void test(){

    }
}

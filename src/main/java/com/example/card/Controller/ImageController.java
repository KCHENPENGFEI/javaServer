package com.example.card.Controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping(value = "/image")
public class ImageController {
    @RequestMapping(value = "/get/{name}",produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public BufferedImage getImage(@PathVariable String name) throws IOException {
        File file = ResourceUtils.getFile("classpath:static/assets/" + name + ".png");
        return ImageIO.read(new FileInputStream(new File(file.getPath())));
    }


}

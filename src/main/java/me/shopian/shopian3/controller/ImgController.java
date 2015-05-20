package me.shopian.shopian3.controller;

import me.shopian.shopian3.dao.AdDaoImpl;
import me.shopian.shopian3.entity.Ad;
import net.coobird.thumbnailator.Thumbnails;
import net.lafox.img.ImgProc;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("img")
public class ImgController {
    @Qualifier("adDaoImpl")
    @Autowired
    private AdDaoImpl adDao;
    private Map<String, MediaType> types = new HashMap<>();
    private String defaultImage = "/resources/images/default-image.png";


    private final long EXPIRE = new DateTime().plusYears(1).getMillis();;
    @PostConstruct
    private void init() {
        types.put("png", MediaType.IMAGE_PNG);
        types.put("gif", MediaType.IMAGE_GIF);
        types.put("jpg", MediaType.IMAGE_JPEG);
    }

    @RequestMapping(value = "ad{id:\\d+}-w{w:\\d+}-v{ver:\\d+}.{ext:png|jpg|gif}", method = RequestMethod.GET)
    @ResponseBody

    public ResponseEntity<byte[]> imgNew(
            HttpServletRequest request,
            @PathVariable long id,
            @PathVariable long ver,
            @PathVariable int w,
            @PathVariable String ext
    ) throws IOException {
        long quality = 80;
        final Ad ad = adDao.get(id);
        System.out.println("ad = " + ad);
        final HttpHeaders headers = new HttpHeaders();
        headers.setExpires(EXPIRE);
        InputStream in;
        headers.setContentType(types.get(ext));
        if (ad != null && ad.getImg() != null && ad.getImg().length > 0) {
            headers.setETag("\"ad-" + id + "-w" + w + "-v" + ad.getImg().length + '"');
//            headers.setLastModified(ad.getImg().getLastModified().getTime());
            in = new ByteArrayInputStream(ad.getImg());
        } else {
            in = request.getSession().getServletContext().getResourceAsStream(defaultImage);
//            String realPath = request.getSession().getServletContext().getRealPath("/resources/images/logo-shopian.png");
//            System.out.println("realPath = " + realPath);
        }
        System.out.println("in = " + in);
//        BufferedImage res = ImgProc.crop(ImageIO.read(in), w, 9.0 * w / 16.0);
        BufferedImage res = ImgProc.width(ImageIO.read(in), w);
        if (res != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Thumbnails.of(res).outputQuality(quality / 100.0).scale(1).outputFormat(ext).toOutputStream(baos);
            return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.OK);
        }
        return null;
    }
}
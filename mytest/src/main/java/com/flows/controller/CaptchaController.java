package com.flows.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * 验证码控制器
 * @author Wanggg
 *
 */
@Controller
public class CaptchaController {
	
	private static final Logger logger = LoggerFactory.getLogger(CaptchaController.class);
	
	@Autowired
    private Producer captchaProducer;

    @RequestMapping(value = "captcha", method = RequestMethod.GET)
    public void kaptcha(HttpServletRequest req,HttpServletResponse rsp) throws Exception {
        HttpSession session = req.getSession();
        /*String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        logger.debug("before code :" + code);*/
        rsp.setDateHeader("Expires", 0);
        rsp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        rsp.addHeader("Cache-Control", "post-check=0, pre-check=0");
        rsp.setHeader("Pragma", "no-cache");
        rsp.setContentType("image/jpeg");

        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        logger.debug("new code :" + capText);
        BufferedImage image = captchaProducer.createImage(capText);
        ServletOutputStream out = rsp.getOutputStream();
        ImageIO.write(image, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

}

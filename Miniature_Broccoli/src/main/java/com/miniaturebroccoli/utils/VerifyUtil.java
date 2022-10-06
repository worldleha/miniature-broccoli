package com.miniaturebroccoli.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Random;

public class VerifyUtil {

    // 验证码字符集
    private static final char[] chars = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '是'
    };
    /**字符数量**/
    private static final int SIZE = 6;
    /** 干扰线数量*/
    private static final int LINES = 6;
    /** 宽度**/
    private static final int WIDTH = 200;
    /** 高度**/
    private static final int HEIGHT = 80;
    /** 字体大小**/
    private static final int FONT_SIZE = 30;

    /** 存储验证码信息*/
    private  static final HashMap<String, String> str = new HashMap<>();
    /**
     * 创建随机验证码及图片
     * Object[0]：验证码字符串；
     * Object[1]：验证码图片。
     */
    public static Object[] createVerification() {
        StringBuffer str = new StringBuffer();
        // 1.创建空白图片
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        // 2.获取图片画笔
        Graphics graphic = image.getGraphics();
        // 3.设置画笔颜色
        graphic.setColor(Color.LIGHT_GRAY);
        // 4.绘制矩形背景
        graphic.fillRect(0, 0, WIDTH, HEIGHT);
        // 5.画随机字符
        Random ran = new Random();
        for (int i = 0; i < SIZE; i++) {
            // 取随机字符索引
            int n = ran.nextInt(chars.length);
            // 设置随机颜色
            graphic.setColor(getRandomColor());
            // 设置字体大小
            graphic.setFont(new Font(
                    null, Font.BOLD + Font.ITALIC, FONT_SIZE));
            // 画字符
            graphic.drawString(
                    chars[n] + "", i * WIDTH / SIZE, HEIGHT * 2 / 3);
            // 记录字符
            str.append(chars[n]);
        }
        // 6.画干扰线
        for (int i = 0; i < LINES; i++) {
            // 设置随机颜色
            graphic.setColor(getRandomColor());
            // 随机画线
            graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT),
                    ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
        }
        // 7.返回验证码和图片
        return new Object[]{str.toString(), image};
    }

    /**
     * 获取随机验证码及图片
     * Object[0]：验证码字符串；
     * Object[1]：验证码图片。
     */
    public static HashMap<String, String> getVerification () {

        ByteArrayOutputStream outputStream = null;
        try {
            Object[] objects = VerifyUtil.createVerification();
            // 得到验证码图片
            BufferedImage image = (BufferedImage) objects[1];
            // 生成base64字符串
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", outputStream);
            /*
             * base64将输出流程的byte[] 进行转码
             * verification_code_image  ==验证码图片 base64字符串
             */
            String verification_code_image = Base64.getEncoder().encodeToString(outputStream.toByteArray());
            str.put("verification_code", String.valueOf(objects[0]));
            str.put("verification_code_image",verification_code_image);
        } catch (Exception e) {
            System.out.println("生成验证码错误");
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException ex) {
                System.out.println("关闭流错误");
            }

        }
            return str;
    }

    /**
     * 随机取色
     */
    public static Color getRandomColor() {
        Random ran = new Random();
        return new Color(ran.nextInt(256),
                ran.nextInt(256), ran.nextInt(256));
    }

    private static String getVerificationCode() {
        HashMap<String, String> verification = getVerification();
        System.out.println("验证码" + verification.get("verification_code"));
        System.out.println("验证码图像base64:" + "\n" + verification.get("verification_code_image"));
        return verification.get("verification_code_image");
    }

}

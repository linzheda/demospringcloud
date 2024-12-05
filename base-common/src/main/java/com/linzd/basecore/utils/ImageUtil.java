package com.linzd.basecore.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * 图片处理工具类
 * 
 * @author Alan Yang
 * @created 2016-4-26 下午4:37:59
 */
public class ImageUtil {

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 缩略图文件名前缀
     */
    private static final String DEFAULT_PREVFIX = "thumb_";

    /**
     * 是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
     */
    private static final Boolean DEFAULT_FORCE = false;
    
    /**
     * 将图片转为base64编码
     * 
     * @author Alan Yang
     * @created 2016-5-9 下午4:54:59
     * @param imgFile
     * @return
     * @throws IOException
     */
    public String convertImg2Base64(File imgFile) {
        
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            if(in!=null) {
                try {
                    // 关闭文件输入流
                    in.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                } finally {
                    
                }
            }
        }
        
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
    
    /**
     * 判断文件是否是图片文件
     * 
     * @author Alan Yang
     * @created 2016-5-9 下午5:11:52
     * @param file
     * @return
     * @throws IOException
     */
    public boolean isImg(File file) throws IOException {
        BufferedImage bufreader = ImageIO.read(file);
        int width = bufreader.getWidth();  
        int height = bufreader.getHeight();
        if(width==0 || height==0) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * 生成缩略图
     * 
     * @author Alan Yang
     * @created 2016-4-26 下午4:40:13
     * @param imgFile
     *            原图片路径
     * @param w
     *            缩略图宽
     * @param h
     *            缩略图高
     * @param prevfix
     *            生成缩略图的前缀
     * @param force
     *            是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
     */
    public String createThumbImg(File imgFile, String tImgPath, int w, int h, String prevfix, boolean force) {
        if (imgFile.exists()) {
            try {
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG,
                // JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames());
                String suffix = null;
                // 获取图片后缀
                if (imgFile.getName().indexOf(".") > -1) {
                    suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
                }// 类型和图片后缀全部小写，然后判断后缀是否合法
                if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0) {
                    log.error("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
                    return "";
                }
                log.debug("target image's size, width:{}, height:{}.", w, h);
                Image img = ImageIO.read(imgFile);
                if (!force) {
                    // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
                    int width = img.getWidth(null);
                    int height = img.getHeight(null);
                    if ((width * 1.0) / w < (height * 1.0) / h) {
                        if (width > w) {
                            h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w / (width * 1.0)));
                            log.debug("change image's height, width:{}, height:{}.", w, h);
                        }
                    } else {
                        if (height > h) {
                            w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h / (height * 1.0)));
                            log.debug("change image's width, width:{}, height:{}.", w, h);
                        }
                    }
                }
                BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
                g.dispose();
                // 将图片保存在原目录并加上前缀
                String path = tImgPath + File.separator + prevfix + imgFile.getName();
                ImageIO.write(bi, suffix, new File(path));
                return path;
            } catch (IOException e) {
                log.error("generate thumbnail image failed.", e);
            }
        } else {
            log.warn("the image is not exist.");
        }
        return "";
    }

    /**
     * 
     * @author Alan Yang
     * @created 2016-4-26 下午5:24:21
     * @param imagePath
     * @param tImgPath
     * @param w
     * @param h
     * @param prevfix
     * @param force
     */
    public void createThumbImg(String imagePath, String tImgPath, int w, int h, String prevfix, boolean force) {
        File imgFile = new File(imagePath);
        createThumbImg(imgFile, tImgPath, w, h, prevfix, force);
    }

    /**
     * 
     * @author Alan Yang
     * @created 2016-4-26 下午5:24:26
     * @param imagePath
     * @param tImgPath
     * @param w
     * @param h
     */
    public void createThumbImg(String imagePath, String tImgPath, int w, int h) {
        createThumbImg(imagePath, tImgPath, w, h, DEFAULT_PREVFIX, DEFAULT_FORCE);
    }

    /**
     * 
     * @author Alan Yang
     * @created 2016-4-26 下午5:24:31
     * @param imgPath
     * @param tImgPath
     * @param width
     */
    public void createThumbImg(String imgPath, String tImgPath, int width) {
        createThumbImg(imgPath, tImgPath, width, width, DEFAULT_PREVFIX, DEFAULT_FORCE);
    }



}
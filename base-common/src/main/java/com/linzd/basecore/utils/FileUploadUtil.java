package com.linzd.basecore.utils;


import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * 描述 文件上传工具类
 *
 * @author Lorenzo Lin
 * @created 2019年11月25日 20:00
 */
public class FileUploadUtil {
    /**
     * 文件上传路径
     */
    private String uploadPath;

    /**
     * 文件上传的根路径
     */
    private String relativePath;

    /**
     * 缩略图路径
     */
    private String tempPath;


    private List<String> imgType;

    /**
     * 缩略图文件夹
     */
    public static final String THUMB_DIR = "thumb";

    /**
     * 缩略图文件前缀
     */
    public static final String THUMB_PREVFIX = "thumb_";

    /**
     * 缩略图大小
     */
    private static final int THUMB_SIZE = 150;

    public FileUploadUtil() {
        List<String> imgList = new ArrayList<>();
        imgList.add(".jpg");
        imgList.add(".png");
        imgList.add(".jpeg");
        imgList.add(".tiff");
        imgList.add(".gif");
        imgList.add(".png");
        imgType = imgList;
    }

    /**
     * 描述  初始化 文件路径  如果文件夹没有则创建
     *
     * @author Lorenzo Lin
     * @params
     * @created 2019/11/26 11:07
     **/
    public void initPath() throws Exception {
        //获取根目录：
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) {
            path = new File("");
        }
        File upload = new File(path.getAbsolutePath(), "static" + File.separator + "upload" + File.separator);
        if (!upload.exists()) {
            upload.mkdirs();
        }
        StringBuffer uploadpath = new StringBuffer();
        uploadpath.append(path.getAbsolutePath() + File.separator + "static" + File.separator + "upload");
        uploadpath.append(getNowDateStr());
        uploadPath = uploadpath.toString();
        File uploadPath = new File(this.uploadPath);
        relativePath = File.separator + "static" + File.separator + "upload" + getNowDateStr();
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        String thumbDir = uploadPath + File.separator + THUMB_DIR;
        tempPath = thumbDir;
        File tempPath = new File(this.tempPath);
        if (!tempPath.exists()) {
            tempPath.mkdirs();
        }
    }

    /**
     * 获得现在的日期的 路径
     *
     * @return 返回 "/" +year+"/"+month+"/"+day+"/";
     */
    private static String getNowDateStr() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DATE);
        return File.separator + year + File.separator + month + File.separator + day + File.separator;
    }

    /**
     * 生成唯一的文件名
     *
     * @return
     */
    private static String getUniqueFileName() {
        String str = UUID.randomUUID().toString();
        return str.replace("-", "");
    }

    /**
     * 通过base64  上传
     *
     * @param imgStr   base64 的文件内容
     * @param filename 文件名
     * @param filetype 文件类型
     * @return
     * @throws IOException
     */
    public Map<String, String> uploadBase64(String imgStr, String filename, String filetype) throws Exception {
        //初始化上传路径
        initPath();
        //文件后缀名
        String type = null;
        if (StringUtils.isNotBlank(filetype)) {
            type = "." + filetype;
        } else {
            type = "." + imgStr.substring(imgStr.indexOf('/') + 1, imgStr.indexOf(';'));
        }
        type = type.toLowerCase();
        String picBody = imgStr.substring(imgStr.indexOf(',') + 1, imgStr.length());
        String filealias = Long.valueOf(System.currentTimeMillis()).toString() + type;
        BASE64Decoder decoder = new BASE64Decoder();
        File file = null;
        OutputStream out = null;
        try {
            byte[] b = decoder.decodeBuffer(picBody);
            // 处理数据
            for (int i = 0; i < b.length; i++) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            out = new FileOutputStream(uploadPath + filename + type);
            out.write(b);
            out.flush();
            file = new File(uploadPath + filename + type);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
        Long fileSize = file.length();
        // 重命名上传文件
        File reNameFile = new File(uploadPath + filealias);
        // 重命名上传的文件
        file.renameTo(reNameFile);
        //判断是否是图片 如果是则生成缩略图
        if (imgType.contains(type)) {
            //生成缩略图
            ImageUtil imageUtils = new ImageUtil();
            String thumbDir = uploadPath + THUMB_DIR;
            File fileDir = new File(thumbDir);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            // 生成缩略图
            imageUtils.createThumbImg(reNameFile, thumbDir, THUMB_SIZE, THUMB_SIZE, THUMB_PREVFIX, false);
        }
        //返回上传的信息
        Map<String, String> result = new HashMap();
        result.put("fileName", filename);
        result.put("fileAlias", filealias);
        result.put("fileSize", fileSize.toString());
        result.put("filePath", uploadPath);
        result.put("relativePath", relativePath);
        return result;
    }


    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile()) {
                return deleteFile(fileName);
            } else {
                return deleteDirectory(fileName);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } else if (files[i].isDirectory()) {
                // 删除子目录
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

}

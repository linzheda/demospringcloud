package com.linzd.mobile.controllers;

import com.linzd.mobile.annotation.PassToken;
import com.linzd.mobile.config.ServerConfig;
import com.linzd.mobile.entity.Attachment;
import com.linzd.mobile.service.AttachmentService;
import com.linzd.mobile.util.FileUploadUtils;
import com.linzd.mobile.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.Map;

/**
 * 描述 附件控制器
 *
 * @author Lorenzo Lin
 * @created 2019年11月25日 19:11
 */
@Api(value = "附件控制器",tags = "附件控制器")
@Controller
@RequestMapping("/attachmentCtr")
@PassToken
public class AttachmentController {
    @Resource(name = "attachmentServiceImpl")
    private AttachmentService attachmentservice;
    @Autowired
    private ServerConfig serverConfig;

    /**
     * @return 项目的url地址
     */
    private String getUrl() {
        return serverConfig.getUrl();
    }

    @ApiOperation(value = "上传文件通过base64")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileData", value = "文件数据", required = true, dataType = "String"),
            @ApiImplicitParam(name = "fileName", value = "文件名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "fileType", value = "文件类型", required = true, dataType = "String")
    })
    @RequestMapping(value = "/uploadFileByBase64", method = RequestMethod.POST)
    public @ResponseBody
    ResultUtil uploadFileByBase64(String fileData, String fileName, String fileType) {
        FileUploadUtils fup = new FileUploadUtils();
        Map<String, String> result = null;
        try {
            result = fup.uploadBase64(fileData, fileName, fileType);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("上传失败");
        }
        if (result != null) {
            Attachment attachment = new Attachment();
            attachment.setFilename(result.get("fileName"));
            attachment.setFilealias(result.get("fileAlias"));
            attachment.setFilepath(result.get("filePath"));
            attachment.setFilesize(Double.valueOf(result.get("fileSize")));
            String url = (getUrl() + result.get("relativePath") + result.get("fileAlias"));
            attachment.setFileurl(url.replaceAll("\\\\", "/").toString());
            attachment.setType(fileType);
            int saveResult = attachmentservice.save(attachment);
            if (saveResult > 0) {
                return ResultUtil.success(attachment);
            } else {
                return ResultUtil.error("上传失败");
            }
        } else {
            return ResultUtil.error("上传失败");
        }
    }

    @ApiOperation(value = "根据id获取文件流")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "附件id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "isThumb", value = "是否为缩略图", required = false, dataType = "boolean")
    })
    @RequestMapping(value = "/getFileById", method = RequestMethod.GET)
    public ResponseEntity<FileSystemResource> getFileById(Integer id, boolean isThumb) {
        Attachment attachment = attachmentservice.findById(id);
        File file = null;
        //判断是否为缩略图
        if (isThumb) {
            file = new File(attachment.getFilepath() + File.separator + "thumb" + File.separator + "thumb_" + attachment.getFilealias());
            if (!file.exists()) {
                file = new File(attachment.getFilepath() + attachment.getFilealias());
            }
        } else {
            file = new File(attachment.getFilepath() + attachment.getFilealias());
        }
        return returnFileFlowByFile(file);
    }

    @ApiOperation(value = "删除文件根据id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "附件id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "realDelete", value = "是否为缩略图", required = false, dataType = "boolean")
    })
    @RequestMapping(value = "/deleteAttachmentById", method = RequestMethod.POST)
    public @ResponseBody
    ResultUtil deleteAttachmentById(Integer id, boolean realDelete) {
        Attachment attachment = attachmentservice.findById(id);
        if (realDelete) {
            int result = attachmentservice.deleteFile(attachment);
        }
        int deleteResult=attachmentservice.deleteAttachmentById(id);

        return deleteResult>0?ResultUtil.success("删除成功"):ResultUtil.error("删除失败");
    }

    @ApiOperation(value = "根据ids获取附件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "附件ids", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/getAttachmentByIds", method = RequestMethod.POST)
    public @ResponseBody
    ResultUtil getAttachmentByIds(String ids){
        Map<String,Object> result=attachmentservice.getAttachmentByIds(ids);
        return ResultUtil.success(result);
    }

    /**
     * 通过file  返回文件流
     *
     * @param file
     * @return
     */
    private ResponseEntity<FileSystemResource> returnFileFlowByFile(File file) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream")).body(new FileSystemResource(file));
    }

}

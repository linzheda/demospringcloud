package com.linzd.attachment.attachment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linzd.attachment.attachment.entity.Attachment;
import com.linzd.attachment.attachment.mapper.AttachmentMapper;
import com.linzd.attachment.attachment.service.AttachmentService;
import com.linzd.basecore.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-01-15
 */
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements AttachmentService {
    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    public int saveAttach(Attachment attachment) {
        return attachmentMapper.insert(attachment);
    }

    @Override
    public Attachment findById(int id) {
        Attachment result= attachmentMapper.selectById(id);
        return result;
    }

    @Override
    public int deleteFile(Attachment attachment) {
        String thumbPath=attachment.getFilepath() + File.separator + "thumb" + File.separator + "thumb_" + attachment.getFilealias();
        String filePath=attachment.getFilepath() + attachment.getFilealias();
        int result=0;
        File thumbFile = new File(thumbPath);
        FileUploadUtil fup=new FileUploadUtil();
        if(!thumbFile.exists()){
            if(fup.delete(thumbPath)){
                result++;
            }
        }
        File  file = new File(filePath);
        if(!file.exists()){
            if(fup.delete(filePath)){
                result++;
            }
        }
        return result;
    }

    @Override
    public int deleteAttachmentById(int id) {
        return  attachmentMapper.deleteById(id);
    }

    @Override
    public Map<String, Object> getAttachmentByIds(String ids) {
        Map<String,Object> condition=new HashMap<String,Object>(1);
        List<Integer> idArray=new ArrayList<Integer>();
        for(String item:ids.split(",")){
            idArray.add(Integer.valueOf(item));
        }
        condition.put("ids",idArray);
        List<Attachment> list=attachmentMapper.selectBatchIds(idArray);
        List<Attachment> pics=new ArrayList<>();
        List<Attachment> files=new ArrayList<>();
        for(Attachment att:list){
            if(isPicType(att.getType())){
                pics.add(att);
            }else{
                files.add(att);
            }
        }
        Map<String,Object> result=new HashMap<String,Object>(2);
        result.put("pics",pics);
        result.put("files",files);
        return result;
    }


    /**
     * 判断类型 是否是图片类型和MP4  如果是 返回true
     * @param type
     * @return
     */
    private boolean isPicType(String type){
        List<String> imgList = new ArrayList<>();
        imgList.add("jpg");
        imgList.add("png");
        imgList.add("jpeg");
        imgList.add("tiff");
        imgList.add("gif");
        imgList.add("png");
        if(imgList.contains(type.toLowerCase())){
            return  true;
        }else{
            return false;
        }
    }
}

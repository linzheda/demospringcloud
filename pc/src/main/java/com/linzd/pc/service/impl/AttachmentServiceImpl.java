package com.linzd.pc.service.impl;

import com.linzd.pc.dao.BaseDao;
import com.linzd.pc.entity.Attachment;
import com.linzd.pc.service.AttachmentService;
import com.linzd.pc.util.FileUploadUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述 附件接口实现
 *
 * @author Lorenzo Lin
 * @created 2019年11月25日 19:27
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {
    private BaseDao baseDao;

    @Resource(name = "baseDaoImpl")
    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public int save(Attachment attachment) {
        int result =this.baseDao.add(attachment,"insert");
        return result;
    }

    @Override
    public Attachment findById(int id) {
        Attachment attachment=new Attachment();
        attachment.setId(id);
        Attachment result= (Attachment) this.baseDao.findOne(attachment,"selectByPrimaryKey");
        return result;
    }

    @Override
    public int deleteFile(Attachment attachment) {
        String thumbPath=attachment.getFilepath() + File.separator + "thumb" + File.separator + "thumb_" + attachment.getFilealias();
        String filePath=attachment.getFilepath() + attachment.getFilealias();
        int result=0;
        File thumbFile = new File(thumbPath);
        FileUploadUtils fup=new FileUploadUtils();
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
        Attachment attachment=new Attachment();
        attachment.setId(id);
        return this.baseDao.delete(attachment,"deleteByPrimaryKey");
    }

    @Override
    public Map<String, Object> getAttachmentByIds(String ids) {
        Map<String,Object> condition=new HashMap<String,Object>(1);
        List<Integer> idArray=new ArrayList<Integer>();
        for(String item:ids.split(",")){
            idArray.add(Integer.valueOf(item));
        }
        condition.put("ids",idArray);
        List<Attachment> list=this.baseDao.findAll( new Attachment(),condition,"getAttachmentByIds");
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

package com.linzd.mobile.service;

import com.linzd.mobile.entity.Attachment;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 描述 附件接口
 *
 * @author Lorenzo Lin
 * @created 2019年11月25日 19:27
 */
@Repository
public interface AttachmentService {
    /**
     * 保存附件
     * @param attachment
     * @return
     */
    int save(Attachment attachment);

    /**
     * 查找附件根据id
     * @param id
     * @return
     */
    Attachment findById(int id);

    /**
     * 删除文件
     * @param attachment
     * @return
     */
    int deleteFile(Attachment attachment);

    /**
     * 根据id 删除附件
     * @param id
     * @return
     */
    int deleteAttachmentById(int id);

    /**
     * 根据ids  返回文件  pics 图片  和 files 文件
     * @param ids
     * @return
     */
    Map<String, Object> getAttachmentByIds(String ids);
}

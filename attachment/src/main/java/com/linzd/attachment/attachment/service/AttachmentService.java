package com.linzd.attachment.attachment.service;

import com.linzd.attachment.attachment.entity.Attachment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linzd
 * @since 2020-01-15
 */
public interface AttachmentService extends IService<Attachment> {
    /**
     * 保存附件
     * @param attachment
     * @return
     */
    int saveAttach(Attachment attachment);

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

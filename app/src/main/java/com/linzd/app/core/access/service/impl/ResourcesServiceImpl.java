package com.linzd.app.core.access.service.impl;

import com.linzd.app.core.access.entity.Resources;
import com.linzd.app.core.access.mapper.ResourcesMapper;
import com.linzd.app.core.access.service.ResourcesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-09-24
 */
@Service
public class ResourcesServiceImpl extends ServiceImpl<ResourcesMapper, Resources> implements ResourcesService {

}

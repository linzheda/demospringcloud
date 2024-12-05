package com.linzd.backsystem.core.user.service.impl;

import com.linzd.backsystem.core.user.entity.Test;
import com.linzd.backsystem.core.user.mapper.TestMapper;
import com.linzd.backsystem.core.user.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-03-20
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

}

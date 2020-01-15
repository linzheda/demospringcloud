package com.linzd.test.test.service.impl;

import com.linzd.test.test.entity.Test;
import com.linzd.test.test.mapper.TestMapper;
import com.linzd.test.test.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linzd
 * @since 2020-01-15
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

}

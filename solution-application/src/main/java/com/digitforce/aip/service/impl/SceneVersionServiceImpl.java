package com.digitforce.aip.service.impl;

import com.digitforce.aip.service.ISceneVersionService;
import com.digitforce.aip.entity.SceneVersion;
import com.digitforce.aip.mapper.SceneVersionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 场景表 服务实现类
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-09
 */
@Service
public class SceneVersionServiceImpl extends ServiceImpl<SceneVersionMapper, SceneVersion> implements ISceneVersionService {

}

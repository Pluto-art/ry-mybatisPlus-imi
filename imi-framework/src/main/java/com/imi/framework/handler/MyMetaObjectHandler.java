package com.imi.framework.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.imi.common.core.domain.entity.SysUser;
import com.imi.common.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 公共字段填充
 * @author: lyp
 * @Date: 2021/12/7 09:46
 * @Description:
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 录入人id
     */
    private final String CREATE_BY = "createBy";

    /**
     * 录入时间
     */
    private final String CREATE_TIME = "createTime";

    /**
     * 更新者
     */
    private final String UPDATE_BY = "update_by";

    /**
     * 更新时间
     */
    private final String UPDATER_TIME = "updateTime";


    @Override
    public void insertFill(MetaObject metaObject) {

        SysUser sysUser = ShiroUtils.getSysUser();
        Object createBy = getFieldValByName(CREATE_BY, metaObject);
        if (createBy == null && sysUser != null) {
            setFieldValByName(CREATE_BY, sysUser.getUserId().toString(), metaObject);
            setFieldValByName(UPDATE_BY, sysUser.getUserId().toString(), metaObject);
        }
        Object createTime = getFieldValByName(CREATE_TIME, metaObject);
        if (createTime == null) {
            setFieldValByName(CREATE_TIME, new Date(), metaObject);
            setFieldValByName(UPDATER_TIME, new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        SysUser sysUser = ShiroUtils.getSysUser();
        Object updateBy = getFieldValByName(UPDATE_BY, metaObject);
        if (updateBy == null && sysUser != null) {
            setFieldValByName(UPDATE_BY, sysUser.getUserId().toString(), metaObject);
        }
        Object updateTime = getFieldValByName(UPDATER_TIME, metaObject);
        if (updateTime == null) {
            setFieldValByName(UPDATER_TIME, new Date(), metaObject);
        }
    }
}
package org.zetaframework.controller.extra

import cn.hutool.core.bean.BeanUtil
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.zetaframework.annotation.satoken.PreCheckPermission
import org.zetaframework.annotation.satoken.PreMode
import org.zetaframework.base.param.UpdateStateParam
import org.zetaframework.base.result.ApiResult
import org.zetaframework.controller.BaseController
import java.io.Serializable

/**
 * 修改状态 Controller
 *
 * @param <Entity> 实体
 * @param <Id>     主键字段类型
 * @param <State>  state字段的类型
 * @author gcc
 */
interface UpdateStateController<Entity, Id: Serializable, State: Serializable>: BaseController<Entity> {


    /**
     * 修改状态
     *
     * @param param UpdateStateParam<Id, State> 修改对象
     * @return ApiResult<Boolean>
     */
    @PreCheckPermission(value = ["{}:edit", "{}:update"], mode = PreMode.OR)
    @ApiOperationSupport(order = 51, author = "AutoGenerate")
    @ApiOperation(value = "修改状态")
    @PutMapping("/state")
    fun updateState(@RequestBody param: UpdateStateParam<Id, State>): ApiResult<Boolean> {
        val result = handlerUpdateState(param)
        if (result.defExec) {
            // updateDTO -> Entity
            val entity = BeanUtil.toBean(param, getEntityClass())
            result.setData(getBaseService().updateById(entity))
        }
        return result
    }


    /**
     * 自定义修改状态
     *
     * @param param UpdateStateParam<Id, State> 修改对象
     * @return ApiResult<Boolean>
     */
    fun handlerUpdateState(param: UpdateStateParam<Id, State>): ApiResult<Boolean> {
        return ApiResult.successDef()
    }

}

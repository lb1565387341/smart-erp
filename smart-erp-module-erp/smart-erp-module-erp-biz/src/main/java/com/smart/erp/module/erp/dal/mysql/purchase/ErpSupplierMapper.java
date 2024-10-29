package com.smart.erp.module.erp.dal.mysql.purchase;

import com.smart.erp.framework.common.pojo.PageResult;
import com.smart.erp.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.smart.erp.framework.mybatis.core.mapper.BaseMapperX;
import com.smart.erp.module.erp.controller.admin.purchase.vo.supplier.ErpSupplierPageReqVO;
import com.smart.erp.module.erp.dal.dataobject.purchase.ErpSupplierDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ERP 供应商 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ErpSupplierMapper extends BaseMapperX<ErpSupplierDO> {

    default PageResult<ErpSupplierDO> selectPage(ErpSupplierPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ErpSupplierDO>()
                .likeIfPresent(ErpSupplierDO::getName, reqVO.getName())
                .likeIfPresent(ErpSupplierDO::getMobile, reqVO.getMobile())
                .likeIfPresent(ErpSupplierDO::getTelephone, reqVO.getTelephone())
                .orderByDesc(ErpSupplierDO::getId));
    }

    default List<ErpSupplierDO> selectListByStatus(Integer status) {
        return selectList(ErpSupplierDO::getStatus, status);
    }

}
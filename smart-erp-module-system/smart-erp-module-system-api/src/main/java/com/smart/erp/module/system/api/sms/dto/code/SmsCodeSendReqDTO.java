package com.smart.erp.module.system.api.sms.dto.code;

import com.smart.erp.framework.common.validation.InEnum;
import com.smart.erp.framework.common.validation.Mobile;
import com.smart.erp.module.system.enums.sms.SmsSceneEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 短信验证码的发送 Request DTO
 *
 * @author 芋道源码
 */
@Data
public class SmsCodeSendReqDTO {

    /**
     * 手机号
     */
    @Mobile
    @NotEmpty(message = "手机号不能为空")
    private String mobile;
    /**
     * 发送场景
     */
    @NotNull(message = "发送场景不能为空")
    @InEnum(SmsSceneEnum.class)
    private Integer scene;
    /**
     * 发送 IP
     */
    @NotEmpty(message = "发送 IP 不能为空")
    private String createIp;

}

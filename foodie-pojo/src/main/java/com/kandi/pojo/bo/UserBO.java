package com.kandi.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "用户对象BO",description = "从客户端，由用户传入的数据封装在此entity中")
public class UserBO {
    @ApiModelProperty(value = "用户名",name = "username",example = "kandi",required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;
    @ApiModelProperty(value = "密码",name = "password",example = "123123",required = true)
    @NotBlank(message = "密码不能为空")
    @Length(min = 6,message = "密码长度不能少于6")
    private String password;
    @ApiModelProperty(value = "重新输入密码",name = "confirmPassword",example = "123123",required = true)
    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;
}

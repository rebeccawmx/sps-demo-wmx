package com.example.spsdemowmx.service.dto;

/**
 * @Description TODO
 * @Author wumengxuan
 * @Date 2019/1/25 17:32
 */

import lombok.Data;

@Data
public class PasswordChangeDTO {
    String currentPassword;
    String newPassword;
}

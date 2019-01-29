package com.example.spsdemowmx.domian;

import com.example.spsdemowmx.web.VM.LoginVM;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


/**
 * 将LoginVM转换成实体Entity
 * Mapstruct是一个很好用的插件用来处理java工程中实体间的转换，比如po转vo，domain转dto。
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User loginVmToUser(LoginVM loginVm);
}


package com.github.luterc.heimdallpetstore.module.sys.mapper;


import com.github.luterc.heimdallpetstore.base.mapper.BaseExtendMapper;
import com.github.luterc.heimdallpetstore.module.sys.dto.SysRoleDTO;
import com.github.luterc.heimdallpetstore.module.sys.entity.SysRoleEntity;
import com.github.luterc.heimdallpetstore.module.sys.vo.SysRoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 系统用户 对象映射
 *
 * @author Luter
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysRoleMapper extends BaseExtendMapper<SysRoleDTO, SysRoleEntity, SysRoleVO> {


}

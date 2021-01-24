
package com.github.luterc.heimdallpetstore.module.sys.mapper;


import com.github.luterc.heimdallpetstore.base.mapper.BaseExtendMapper;
import com.github.luterc.heimdallpetstore.module.sys.dto.SysResourceDTO;
import com.github.luterc.heimdallpetstore.module.sys.entity.SysResourceEntity;
import com.github.luterc.heimdallpetstore.module.sys.vo.SysResourceVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 系统用户 对象映射
 *
 * @author Luter
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysResourceMapper extends BaseExtendMapper<SysResourceDTO, SysResourceEntity, SysResourceVO> {


}

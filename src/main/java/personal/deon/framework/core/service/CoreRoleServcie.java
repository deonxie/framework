package personal.deon.framework.core.service;

import personal.deon.framework.core.entity.CoreRole;
import personal.deon.framework.core.repository.CoreRoleDao;
import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.GenericService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 */
@Service
@Transactional(readOnly = true)
public class CoreRoleServcie extends GenericService<CoreRole> {
    @Autowired
    private CoreRoleDao roleDao;

    public CoreRole findRoleByRoleName(String name) {
        return roleDao.findByRoleName(name);
    }

    @Transactional(readOnly = false)
    public String saveRole(CoreRole role) {
        if (StringUtils.isBlank(role.getId())) {
            CoreRole oldRole = findRoleByRoleName(role.getRoleName());
            if (null != oldRole) {
                return "角色名已经存在！";
            }
        }
        roleDao.save(role);
        return "添加角色成功！";
    }

	@Override
	public GenericDao<CoreRole> getGenericDao() {
		return roleDao;
	}

}

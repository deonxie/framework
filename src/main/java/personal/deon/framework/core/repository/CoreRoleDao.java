package personal.deon.framework.core.repository;

import personal.deon.framework.core.entity.CoreRole;

/**
 * Created by chenling on 14-3-24.
 */
public interface CoreRoleDao extends GenericDao<CoreRole>{
    CoreRole findByRoleName(String roleName);
}

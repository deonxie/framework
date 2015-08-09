package personal.deon.framework.core.service;

import cn.gd.thinkjoy.modules.security.utils.Digests;
import cn.gd.thinkjoy.modules.utils.Clock;
import cn.gd.thinkjoy.modules.utils.Encodes;
import personal.deon.framework.core.entity.CoreRole;
import personal.deon.framework.core.entity.CoreUser;
import personal.deon.framework.core.repository.CoreUserDao;
import personal.deon.framework.core.repository.GenericDao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 */
@Component
@Transactional(readOnly = true)
public class CoreUserService extends GenericService<CoreUser> {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;

    private Clock clock = Clock.DEFAULT;
    @Autowired
    private CoreUserDao userDao;


    public CoreUser findUserByAccount(String account) {
        return userDao.findByAccount(account);
    }

    @Transactional(readOnly = false)
    public String registerUser(CoreUser user, List<String> checkedRoleList) {
        if (StringUtils.isBlank(user.getId())) {
            user.setRegisterTime(clock.getCurrentDate());
            CoreUser oldUser = findUserByAccount(user.getAccount());
            if (null != oldUser) {
                return "用户名已经存在!";
            }
        }
        user.getRoles().clear();
        for (String roleId : checkedRoleList) {
            CoreRole role = new CoreRole();
            role.setId(roleId);
            user.getRoles().add(role);
        }
        if (StringUtils.isNotBlank(user.getPlainPwd())) {
            entryptPassword(user);
        }

        userDao.save(user);

        return "添加用户成功！";
    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    private void entryptPassword(CoreUser user) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPlainPwd().getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }

	@Override
	public GenericDao<CoreUser> getGenericDao() {
		return userDao;
	}
}

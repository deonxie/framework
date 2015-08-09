package personal.deon.framework.core.service;

import personal.deon.framework.core.entity.CoreRole;
import personal.deon.framework.core.entity.CoreUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 */
@Service
public class CoreLoginService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    
    @Autowired
	protected CoreUserService userSer;
    
	public UserInfo findUserInfo(String account){
		CoreUser user = userSer.findUserByAccount(account);
		if(user ==null)
			return null;
		UserInfo info = new UserInfo();
		info.setId(user.getId());
		info.setUsername(user.getUserName());
		info.setPassword(user.getPassword());
		info.setSalt(user.getSalt());
		for(CoreRole role: user.getRoles()){
			info.getRoles().add(role.getRoleName());
			info.getPermissions().addAll(role.getPermissionList());
		}
		return info;
	}
    
    public static class UserInfo implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private String id;
    	private String password;
    	private String salt;
    	private String username;
    	private List<String> roles = Lists.newArrayList();
    	private List<String> permissions = Lists.newArrayList();
		/**
		 * @return the account
		 */
		public String getId() {
			return id;
		}
		/**
		 * @param account the account to set
		 */
		public void setId(String id) {
			this.id = id;
		}
		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}
		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
		}
		/**
		 * @return the salt
		 */
		public String getSalt() {
			return salt;
		}
		/**
		 * @param salt the salt to set
		 */
		public void setSalt(String salt) {
			this.salt = salt;
		}
		/**
		 * @return the username
		 */
		public String getUsername() {
			return username;
		}
		/**
		 * @param username the username to set
		 */
		public void setUsername(String username) {
			this.username = username;
		}
		/**
		 * @return the roles
		 */
		public List<String> getRoles() {
			return roles;
		}
		/**
		 * @param roles the roles to set
		 */
		public void setRoles(List<String> roles) {
			this.roles = roles;
		}
		/**
		 * @return the permissions
		 */
		public List<String> getPermissions() {
			return permissions;
		}
		/**
		 * @param permissions the permissions to set
		 */
		public void setPermissions(List<String> permissions) {
			this.permissions = permissions;
		}
    	
    }
    
}

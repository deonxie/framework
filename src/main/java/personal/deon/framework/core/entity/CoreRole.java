package personal.deon.framework.core.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

@Entity
@Table(name="core_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CoreRole extends AbsEntity {
	private String roleName;
	private String description;
	private String permissions;
	public static final String split = ",";
	
	/**
	 * @return the roleName
	 */
	@Column(unique=true,updatable=false)
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the permissions
	 */
	@Column(columnDefinition="text")
	public String getPermissions() {
		return permissions;
	}
	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
	@Transient
    public List<String> getPermissionList() {
        if (StringUtils.isNotBlank(permissions)) {
        	return ImmutableList.copyOf(StringUtils.split(permissions, split));
        } else {
            return Lists.newArrayList();
        }
    }
}

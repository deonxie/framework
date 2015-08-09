/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package personal.deon.framework.core.repository;

import personal.deon.framework.core.entity.CoreUser;


public interface CoreUserDao extends GenericDao<CoreUser> {
    CoreUser findByAccount(String account);

}

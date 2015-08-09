package personal.deon.framework.weixin.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.GenericService;
import personal.deon.framework.weixin.entity.AutoReplyMsg;
import personal.deon.framework.weixin.repository.AutoReplyMsgDao;
import personal.deon.framework.weixin.util.SendWeixinMsg;

@Service
@Transactional(readOnly=true)
public class AutoReplyMsgService extends GenericService<AutoReplyMsg> {
	static List<AutoReplyMsg> dblist = Lists.newArrayList();
	
	@Autowired
	AutoReplyMsgDao dao;
	
	@PostConstruct
	public void init(){
		dblist = super.getAll();
		if(dblist == null)
			dblist = Lists.newArrayList();
	}
	
	@Override
	public GenericDao<AutoReplyMsg> getGenericDao(){
		return dao;
	}
	
	public void deailTextMsg(String touser,String msg){
		for(AutoReplyMsg auto : dblist){
			if(auto.checkMsg(msg))
				SendWeixinMsg.receive(auto);
		}
	}
}

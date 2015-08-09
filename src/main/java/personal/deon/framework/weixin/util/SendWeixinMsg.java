package personal.deon.framework.weixin.util;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import personal.deon.framework.weixin.entity.AutoReplyMsg;


public class SendWeixinMsg implements Runnable{
	static Logger logger = LoggerFactory.getLogger(SendWeixinMsg.class);
	static ExecutorService pool = Executors.newFixedThreadPool(5);
	AutoReplyMsg msg;
	
	public SendWeixinMsg(AutoReplyMsg msg) {
		this.msg = msg;
	}
	
	public static void receive(AutoReplyMsg msg){
		pool.execute(new SendWeixinMsg(msg));
	}
	
	@Override
	public void run() {
		if(msg != null){
			//text,image,voice,video,music,news
			String type = msg.getMsgtype().toUpperCase();
			switch(type){
			case "TEXT":
				WeixinMsgUtil.textMsg(msg.getTouser(), msg.getContent());
				break;
			case "IMAGE":
				WeixinMsgUtil.imageMsg(msg.getTouser(), msg.getMediaid().getMediaId());
				break;
			case "VOICE":
				WeixinMsgUtil.voiceMsg(msg.getTouser(), msg.getMediaid().getMediaId());
				break;
			case "VIDEO":
				WeixinMsgUtil.videoMsg(msg.getTouser(), msg.getTitle(), msg.getMediaid().getMediaId(),
						msg.getDescription(), msg.getMediaid().getMediaId());
				break;
			case "MUSIC":
				WeixinMsgUtil.musicMsg(msg.getTouser(), msg.getTitle(), msg.getMusicurl(), 
						msg.getHqmusicurl(), msg.getMediaid().getMediaId(), msg.getDescription());
				break;
			case "NEWS":
				List<WeixinMsgUtil.Article> articles = Lists.newArrayList();
				articles.add(new WeixinMsgUtil.Article(msg.getTitle(), msg.getPicurl(), msg.getUrl(), msg.getDescription()));
				WeixinMsgUtil.newsMsg(msg.getTouser(), articles);
				break;
			default :
				logger.info("消息类型不正确，不能处理！");
				break;
			}
			
		}
	}
	


}

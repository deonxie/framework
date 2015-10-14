package personal.deon.framework.weixin.entity;

import java.lang.reflect.Field;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.google.common.collect.Maps;

import personal.deon.framework.core.entity.AbsEntity;
/***
 * 生成微信预支付订单
 * @author jlusoft
 *
 */
@Entity
@Table(name="weixin_perorder")
public class WeixinPerOrder extends AbsEntity {
	/**终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB" 
	 * device_info	否**/
	private String device_info;
	/**随机字符串，不长于32位。推荐随机数生成算法
	 * nonce_str	是**/
	private String nonce_str;
	/***商品描,述Ipad白色	商品或支付单简要描述	
	 * body 是**/
	private String body;
	/**商品详情,Ipad mini  16G  白色	商品名称明细列表	
	 * detail	否 8192**/
	private String detail;
	/**附加数据,在查询API和支付通知中原样返回,该字段主要用于商户携带订单的自定义数据	
	 * attach	否 127**/
	private String attach;
	/**商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号	
	 * out_trade_no	是*/
	private String out_trade_no;
	/**货币类型CNY	符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型	
	 * fee_type	否**/
	private String fee_type;
	/**总金额 单位为分
	 * total_fee	是*/
	private Integer total_fee;
	/**终端IP,APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP	
	 * spbill_create_ip	是**/
	private String spbill_create_ip;
	/***交易起始时间,订单生成时间，格式为yyyyMMddHHmmss，
	 * 如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则	
	 * time_start	否**/
	private String time_start;
	/**交易结束时间,订单失效时间，格式为yyyyMMddHHmmss，
	 * 如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则注意：最短失效时间间隔必须大于5分钟	
	 * time_expire	否**/
	private String time_expire;
	/**商品标记,WXG	商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠	
	 * goods_tag	否**/
	private String goods_tag;
	/**通知地址 	接收微信支付异步通知回调地址
	 * notify_url 	是 	String(256) 	
	 */
	private String notify_url;
	/***交易类型	取值如下：JSAPI，NATIVE，APP，WAP,详细说明见参数规定	
	 * trade_type	是*/
	private String trade_type;
	/**商品ID,trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义	
	 * product_id	否**/
	private String product_id;
	/**指定支付方式,no_credit--指定不能使用信用卡支付	
	 * limit_pay	否**/
	private String limit_pay;
	/**用户标识,trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。
	 * 下单前需要调用【网页授权获取用户信息】接口获取到用户的Openid。
	 * 企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，
	 * 再调用【企业号userid转openid接口】进行转换	
	 * openid	否**/
	private String openid;
	/**==========================返回字段==========================**/
	/**返回状态码*/
	private String return_code;
	/**返回信息**/
	private String return_msg;
	/**业务结果**/
	private String result_code;
	/**预支付交易会话标识**/
	private String prepay_id;
	/**二维码链接,trade_type为NATIVE是有返回，可将该参数值生成二维码展示出来进行扫码支*/
	private String code_url;
	/**错误代码*/
	private String err_code;
	/**错误代码描述 	err_code_des*/
	private String err_code_des;
	/**==========================通知字段==========================**/
	/**付款银行**/
	private String bank_type;
	/***是否关注公众账号**/
	private String is_subscribe;
	/***现金支付金额**/
	private Integer cash_fee;
	/**现金支付货币类型**/
	private String cash_fee_type;
	/**代金券或立减优惠金额*/
	private Integer coupon_fee;
	/**代金券或立减优惠使用数量**/
	private Integer coupon_count;
	/**微信支付订单号**/
	private String transaction_id;
	/**支付完成时间**/
	private String time_end;
	/**
	 * @return {@link #device_info}
	 */
	public String getDevice_info() {
		return device_info;
	}
	/**
	 * {@link #device_info}
	 */
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	/**
	 * @return {@link #nonce_str}
	 */
	public String getNonce_str() {
		return nonce_str;
	}
	/**
	 * {@link #nonce_str}
	 */
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	/**
	 * @return {@link #body}
	 */
	public String getBody() {
		return body;
	}
	/**
	 * {@link #body}
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * @return {@link #detail}
	 */
	public String getDetail() {
		return detail;
	}
	/**
	 * {@link #detail}
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	/**
	 * @return {@link #attach}
	 */
	public String getAttach() {
		return attach;
	}
	/**
	 * {@link #attach}
	 */
	public void setAttach(String attach) {
		this.attach = attach;
	}
	/**
	 * @return {@link #out_trade_no}
	 */
	public String getOut_trade_no() {
		return out_trade_no;
	}
	/**
	 * {@link #out_trade_no}
	 */
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	/**
	 * @return {@link #fee_type}
	 */
	public String getFee_type() {
		return fee_type;
	}
	/**
	 * {@link #fee_type}
	 */
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	/**
	 * @return {@link #total_fee}
	 */
	public Integer getTotal_fee() {
		return total_fee;
	}
	/**
	 * {@link #total_fee}
	 */
	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}
	/**
	 * @return {@link #spbill_create_ip}
	 */
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	/**
	 * {@link #spbill_create_ip}
	 */
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	/**
	 * @return {@link #time_start}
	 */
	public String getTime_start() {
		return time_start;
	}
	/**
	 * {@link #time_start}
	 */
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	/**
	 * @return {@link #time_expire}
	 */
	public String getTime_expire() {
		return time_expire;
	}
	/**
	 * {@link #time_expire}
	 */
	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}
	/**
	 * @return {@link #goods_tag}
	 */
	public String getGoods_tag() {
		return goods_tag;
	}
	/**
	 * {@link #goods_tag}
	 */
	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}
	/**
	 * @return {@link #notify_url}
	 */
	public String getNotify_url() {
		return notify_url;
	}
	/**
	 * {@link #notify_url}
	 */
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	/**
	 * @return {@link #trade_type}
	 */
	public String getTrade_type() {
		return trade_type;
	}
	/**
	 * {@link #trade_type}
	 */
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	/**
	 * @return {@link #product_id}
	 */
	public String getProduct_id() {
		return product_id;
	}
	/**
	 * {@link #product_id}
	 */
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	/**
	 * @return {@link #limit_pay}
	 */
	public String getLimit_pay() {
		return limit_pay;
	}
	/**
	 * {@link #limit_pay}
	 */
	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}
	/**
	 * @return {@link #openid}
	 */
	public String getOpenid() {
		return openid;
	}
	/**
	 * {@link #openid}
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * @return {@link #result_code}
	 */
	public String getResult_code() {
		return result_code;
	}
	/**
	 * {@link #result_code}
	 */
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	/**
	 * @return {@link #prepay_id}
	 */
	public String getPrepay_id() {
		return prepay_id;
	}
	/**
	 * {@link #prepay_id}
	 */
	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
	/**
	 * @return {@link #code_url}
	 */
	public String getCode_url() {
		return code_url;
	}
	/**
	 * {@link #code_url}
	 */
	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}
	/**
	 * @return {@link #err_code}
	 */
	public String getErr_code() {
		return err_code;
	}
	/**
	 * {@link #err_code}
	 */
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	/**
	 * @return {@link #err_code_des}
	 */
	public String getErr_code_des() {
		return err_code_des;
	}
	/**
	 * {@link #err_code_des}
	 */
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	/**
	 * @return {@link #return_code}
	 */
	public String getReturn_code() {
		return return_code;
	}
	/**
	 * {@link #return_code}
	 */
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	/**
	 * @return {@link #return_msg}
	 */
	public String getReturn_msg() {
		return return_msg;
	}
	/**
	 * {@link #return_msg}
	 */
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	/**
	 * @return {@link #bank_type}
	 */
	public String getBank_type() {
		return bank_type;
	}
	/**
	 * {@link #bank_type}
	 */
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	/**
	 * @return {@link #is_subscribe}
	 */
	public String getIs_subscribe() {
		return is_subscribe;
	}
	/**
	 * {@link #is_subscribe}
	 */
	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}
	/**
	 * @return {@link #cash_fee}
	 */
	public Integer getCash_fee() {
		return cash_fee;
	}
	/**
	 * {@link #cash_fee}
	 */
	public void setCash_fee(Integer cash_fee) {
		this.cash_fee = cash_fee;
	}
	/**
	 * @return {@link #cash_fee_type}
	 */
	public String getCash_fee_type() {
		return cash_fee_type;
	}
	/**
	 * {@link #cash_fee_type}
	 */
	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}
	/**
	 * @return {@link #coupon_fee}
	 */
	public Integer getCoupon_fee() {
		return coupon_fee;
	}
	/**
	 * {@link #coupon_fee}
	 */
	public void setCoupon_fee(Integer coupon_fee) {
		this.coupon_fee = coupon_fee;
	}
	/**
	 * @return {@link #coupon_count}
	 */
	public Integer getCoupon_count() {
		return coupon_count;
	}
	/**
	 * {@link #coupon_count}
	 */
	public void setCoupon_count(Integer coupon_count) {
		this.coupon_count = coupon_count;
	}
	/**
	 * @return {@link #transaction_id}
	 */
	public String getTransaction_id() {
		return transaction_id;
	}
	/**
	 * {@link #transaction_id}
	 */
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	/**
	 * @return {@link #time_end}
	 */
	public String getTime_end() {
		return time_end;
	}
	/**
	 * {@link #time_end}
	 */
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	
	public Map<String,String> fieldValue(){
		Field[] fields = getClass().getDeclaredFields();
		Map<String,String> map = Maps.newHashMap();
		Object value = null;
		try {
			for(Field field : fields){
					value = field.get(this);
				if(value !=null){
					map.put(field.getName(), value.toString());
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public void fieldValue(Map<String,String> msg){
		Field[] fields = getClass().getDeclaredFields();
		try {
			for(Field field : fields){
				if(msg.containsKey(field.getName())){
					if("java.lang.Integer".equals(field.getType().getName()))
						field.set(this, Integer.parseInt(msg.get(field.getName())));
					else
						field.set(this, msg.get(field.getName()));
				}	
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}

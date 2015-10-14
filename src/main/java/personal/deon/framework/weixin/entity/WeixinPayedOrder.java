package personal.deon.framework.weixin.entity;

import java.lang.reflect.Field;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import personal.deon.framework.core.entity.AbsEntity;
/***
 * 生成微信预支付订单
 * @author jlusoft
 *
 */
@Entity
@Table(name="weixin_payedorder")
public class WeixinPayedOrder extends AbsEntity {
	/**微信分配的公众账号ID String(32)*/
	private String appid;
	/**微信支付分配的商户号 String(32)*/
	private String mch_id;
	/**微信支付分配的终端设备号 String(32)**/
	private String device_info;
	/**SUCCESS/FAIL String(16)**/
	private String result_code;
	/**错误返回的信息描述 String(32)*/
	private String err_code;
	/**错误返回的信息描述String(128)*/
	private String err_code_des;
	/**用户在商户appid下的唯一标识String(128)**/
	private String openid;
	/**用户是否关注公众账号，Y-关注，N-未关注String(1)**/
	private String is_subscribe;
	/**JSAPI、NATIVE、APPString(16)**/
	private String trade_type;
	/**银行类型，采用字符串类型的银行标识String(16)*/
	private String bank_type;
	/**订单总金额，单位为分*/
	private int total_fee;
	/**货币类型，符合ISO4217标准的三位字母代码默认人民币：CNY String(8)**/
	private String fee_type;
	/**现金支付金额订单现金支付金额*/
	private int cash_fee;
	/**货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY String(16)**/
	private String cash_fee_type;
	/**代金券或立减优惠金额**/
	private int coupon_fee;
	/**代金券或立减优惠使用数量**/
	private int coupon_count;
	/**代金券或立减优惠ID,$n为下标，从0开始编号 String(20)*/
	private String coupon_ids;
	/**单个代金券或立减优惠支付金额,$n为下标，从0开始编号**/
	private String coupon_fees;
	/**微信支付订单号String(32)*/
	private String transaction_id;
	/**商户系统的订单号 String(32)**/
	private String out_trade_no;
	/**商家数据包String(128)**/
	private String attach;
	/**支付完成时间，格式为yyyyMMddHHmmss String(14)**/
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
	/**
	 * @return {@link #appid}
	 */
	public String getAppid() {
		return appid;
	}
	/**
	 * {@link #appid}
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}
	/**
	 * @return {@link #mch_id}
	 */
	public String getMch_id() {
		return mch_id;
	}
	/**
	 * {@link #mch_id}
	 */
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	/**
	 * @return {@link #coupon_ids}
	 */
	@Column(columnDefinition="text")
	public String getCoupon_ids() {
		return coupon_ids;
	}
	/**
	 * {@link #coupon_ids}
	 */
	public void setCoupon_ids(String coupon_ids) {
		this.coupon_ids = coupon_ids;
	}
	/**
	 * @return {@link #coupon_fees}
	 */
	@Column(columnDefinition="text")
	public String getCoupon_fees() {
		return coupon_fees;
	}
	/**
	 * {@link #coupon_fees}
	 */
	public void setCoupon_fees(String coupon_fees) {
		this.coupon_fees = coupon_fees;
	}
	/**
	 * {@link #total_fee}
	 */
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	/**
	 * {@link #cash_fee}
	 */
	public void setCash_fee(int cash_fee) {
		this.cash_fee = cash_fee;
	}
	/**
	 * {@link #coupon_fee}
	 */
	public void setCoupon_fee(int coupon_fee) {
		this.coupon_fee = coupon_fee;
	}
	/**
	 * {@link #coupon_count}
	 */
	public void setCoupon_count(int coupon_count) {
		this.coupon_count = coupon_count;
	}
	
	public void fieldValue(Map<String,String> msg){
		Field[] fields = getClass().getDeclaredFields();
		try {
			for(Field field : fields){
				if(msg.containsKey(field.getName())){
					if("int".equals(field.getType().getName()))
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

package saptacims.service.common;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommonHelper {
	
	@Autowired
//	SgeStatusMapper sgeStatusMapper;
	
	/**
	 * 获取本地库中当前交易日
	 * @return
	 */
	public Date getCurrentTradeDate(){
//		SgeStatus sgeStatus = sgeStatusMapper.selectLastTradeDate();
//		if(null == sgeStatus){
//			return null;
//		}else{
//			String date = sgeStatus.getTradeDate();
//			return DateUtil.getDateFromShortString(date);
//		}
		return null;
	}
	
}

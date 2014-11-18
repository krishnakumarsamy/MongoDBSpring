package org.kk.mongodbspring.service.helper;

import org.apache.log4j.Logger;
import org.kk.mongodbspring.common.Constants;
import org.springframework.util.StringUtils;

import com.mongodb.WriteResult;

/**
 * Helper class for StudentService.
 * 
 * @author krishnakumar
 * 
 */
public class StudentServiceHelper {

	private static final Logger logger = Logger.getLogger(StudentServiceHelper.class);

	/**
	 * @param result
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getResult(final WriteResult result) {
		String status = Constants.EMPTY_STRING;
		logger.info("Number of Documents Affected :" + result.getN());
		if (StringUtils.isEmpty(result.getError())) {
			status = Constants.TXT_STATUS_SUCCESS;
		} else {
			status = result.getError();
		}
		return status;
	}
}

package net.ms.employeeservice.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;

public class ExceptionUtil {

	public static String extractSQLErrorCode(DataIntegrityViolationException ex) {
		Throwable rootCause = ex.getRootCause();

		if (rootCause != null && rootCause instanceof SQLIntegrityConstraintViolationException) {
			StringBuilder sb = new StringBuilder();
			SQLIntegrityConstraintViolationException sqlErr = (SQLIntegrityConstraintViolationException) rootCause;
			sb.append(String.valueOf(sqlErr.getErrorCode()).concat("; "));
			sb.append(sqlErr.getSQLState().concat("; "));
			sb.append(sqlErr.getMessage());
			
			return sb.toString();
		}

		return ex.getMessage();
	}

}

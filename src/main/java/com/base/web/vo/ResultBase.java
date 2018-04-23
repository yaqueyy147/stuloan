package com.base.web.vo;

import java.util.HashMap;
import java.util.Map;

public class ResultBase implements Result {
	private static final long serialVersionUID = 4165926587298446217L;
	private boolean success = false;
	private Map<String, String> resultmaps = new HashMap<String, String>();

	
	private String message = "";

	public boolean isSuccess() {
		return this.success;
	}

	public boolean isExecuted() {
		return false;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public Map<String, String> getResultmaps() {
		return resultmaps;
	}

	public void setResultmaps(Map<String, String> resultmaps) {
		this.resultmaps = resultmaps;
	}
	
	public String toString() {
		return "ResultBase [success=" + this.success + ", message=" + this.message + "]";
	}
}
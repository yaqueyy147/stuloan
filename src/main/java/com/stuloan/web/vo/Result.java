package com.stuloan.web.vo;

import java.io.Serializable;

public abstract interface Result extends Serializable {
	public abstract boolean isSuccess();

	public abstract boolean isExecuted();

	public abstract String getMessage();
}
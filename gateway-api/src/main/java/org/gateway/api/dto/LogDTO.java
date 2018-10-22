package org.gateway.api.dto;

import java.util.Date;

public class LogDTO {
	private String traceId;
	private String uri;
	private Date startTime;
	public String getTraceId() {
		return traceId;
	}
	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public LogDTO(String traceId, String uri, Date startTime){
		this.traceId = traceId;
		this.uri = uri;
		this.startTime = startTime;
	}
}

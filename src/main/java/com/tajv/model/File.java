package com.tajv.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class File {

	@JsonProperty("fileId")
	private int fileId;

	@JsonProperty("fileName")
	private String fileName;

	@JsonProperty("data")
	private byte[] data;

	@JsonProperty("clientId")
	private int clientId;

	@JsonProperty("heartRateData")
	private String heartRateData;

	public long getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getHeartRateData() {
		return heartRateData;
	}

	public void setHeartRateData(String heartRateData) {
		this.heartRateData = heartRateData;
	}

}

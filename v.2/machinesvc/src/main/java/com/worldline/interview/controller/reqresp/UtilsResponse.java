package com.worldline.interview.controller.reqresp;

public class UtilsResponse {
	double costPerbatch;
	int batchSize;
	String engineName;
	public double getCostPerbatch() {
		return costPerbatch;
	}
	public void setCostPerbatch(double costPerbatch) {
		this.costPerbatch = costPerbatch;
	}
	public int getBatchSize() {
		return batchSize;
	}
	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}
	public String getEngineName() {
		return engineName;
	}
	public void setEngineName(String engineName) {
		this.engineName = engineName;
	}

	
}

package com.worldline.interview.utils;

public enum FuelType {
    PETROL("PETROL",9,8,"DefaultEngine"),
    DIESEL("DIESEL",12,8,"DefaultEngine"),
    WOOD("WOOD",4.35,2,"StreamEngine"),
    COAL("COAL",5.65,2,"StreamEngine");
	
	String name;
    double costPerbatch;
	int batchSize;
	String engineName;
	
	FuelType(String name,double costPerbatch, int batchSize,String engineName) {
		this.name=name;
		this.batchSize=batchSize;
		this.costPerbatch=costPerbatch;
		this.engineName=engineName;
	}

	public String getName() {
		return name;
	}

	public double getCostPerbatch() {
		return costPerbatch;
	}

	public int getBatchSize() {
		return batchSize;
	}

	public String getEngineName() {
		return engineName;
	}
	
	public static FuelType findByName(String name){
	    for(FuelType v : values()){
	        if(v.getName().equals(name.toUpperCase())){
	            return v;
	        }
	    }
	    return null;
	}
}

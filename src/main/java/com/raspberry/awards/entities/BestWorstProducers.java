package com.raspberry.awards.entities;

import java.util.List;

public class BestWorstProducers {

	private List<ProducerAwards> min;
	private List<ProducerAwards> max;
	
	public BestWorstProducers() {}
	
	public List<ProducerAwards> getMin() {
		return min;
	}
	
	public void setMin(List<ProducerAwards> min) {
		this.min = min;
	}
	
	public List<ProducerAwards> getMax() {
		return max;
	}
	
	public void setMax(List<ProducerAwards> max) {
		this.max = max;
	}
}

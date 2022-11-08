package com.raspberry.awards.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface ProducerAwards {

	String getName();
	@JsonProperty("interval")
    Integer getRange();
    Integer getPreviousWin();
    Integer getFollowingWin();
}

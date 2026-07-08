package com.wdyapplications.prime_access.utils.okhttp.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class _ItemsDto {
	private String zoneInfra;

	public String getZoneInfra() {
		return zoneInfra;
	}

	public void setZoneInfra(String zoneInfra) {
		this.zoneInfra = zoneInfra;
	}
}

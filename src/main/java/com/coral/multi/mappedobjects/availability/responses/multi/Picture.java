package com.coral.multi.mappedobjects.availability.responses.multi;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("picture")
public class Picture {
	private String pictureType;
	private String pictureUrl;
	
	public String getPictureType() {
		return pictureType;
	}
	public void setPictureType(String pictureType) {
		this.pictureType = pictureType;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
}

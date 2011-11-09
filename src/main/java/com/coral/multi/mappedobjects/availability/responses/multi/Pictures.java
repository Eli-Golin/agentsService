package com.coral.multi.mappedobjects.availability.responses.multi;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("pictures")
public class Pictures {
	@XStreamImplicit(itemFieldName = "picture")
	private List<Picture> pictures;

	public Pictures(){
		pictures = new ArrayList<Picture>();
	}
	
	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
}

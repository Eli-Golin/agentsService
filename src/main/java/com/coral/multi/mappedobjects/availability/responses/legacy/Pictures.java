package com.coral.multi.mappedobjects.availability.responses.legacy;

import java.util.ArrayList;
import java.util.List;

public class Pictures {
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

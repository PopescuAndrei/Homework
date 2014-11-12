package com.example.homework.home;

import java.util.ArrayList;
import java.util.List;

import com.example.homework.base.BaseModel;
/**
 * Model for the list of links
 * @author Andrei
 *
 */
public class ModelList extends BaseModel {

	List<ModelLink> linkList;

	public ModelList() {
		this.linkList = new ArrayList<ModelLink>();
	}

	public List<ModelLink> getClassList() {
		return linkList;
	}

}

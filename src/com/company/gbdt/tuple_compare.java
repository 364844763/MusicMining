package com.company.gbdt;

import java.util.Comparator;


public class tuple_compare implements Comparator<Data.tuple> {
	
	private Integer index;
	
	public tuple_compare(Integer idx) {
		this.index = idx;
		// TODO Auto-generated constructor stub
	}

	public int compare(Data.tuple o1, Data.tuple o2) {
		if (o1.features.get(index) > o2.features.get(index)) {
			return 1;
		} else if(o1.features.get(index) < o2.features.get(index)) {
			return -1;
		} else {
			return 0;
		}
	}
	
}
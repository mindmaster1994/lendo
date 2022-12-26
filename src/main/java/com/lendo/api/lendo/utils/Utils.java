package com.lendo.api.lendo.utils;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.JpaSort;

public class Utils {
	public static Sort sortBy(String sortBy, String sortOrder) {
		return "desc".equals(sortOrder) ? JpaSort.unsafe(Direction.DESC, "(" + sortBy + ")")
				: JpaSort.unsafe(Direction.ASC, "(" + sortBy + ")");
	}
}

package org.servicea.query;

import lombok.Data;

@Data
public class PageRow {
	private int pageSize = 20;
    private int pageNum = 1;
}

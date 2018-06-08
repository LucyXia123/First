package com.tsinghuadtv.www.entity.report;

import java.util.HashMap;
import java.util.Map;

import com.tsinghuadtv.www.mybatis.typehandler.IdInterface;

public enum ReportStatus implements IdInterface {
	
	APPROVING	(1, "APPROVING", "审核中"),
	PASSED		(2, "PASSED", "审核通过"),
	NOT_PASS	(3, "NOT_PASS", "审核未通过"),
	DELETED		(4, "DELETED", "已删除"),
	;

    private static final Map<String, ReportStatus> code2ReportStatuses;

    private final int id;
    private final String code;
    private final String description;

    static {
        code2ReportStatuses = new HashMap<>();

        for (ReportStatus productType : ReportStatus.values()) {
            code2ReportStatuses.put(productType.getCode(), productType);
        }
    }

    public static ReportStatus fromCode(String code) {
        return code2ReportStatuses.get(code);
    }

    ReportStatus(int id, String code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

}

package com.tsinghuadtv.www.entity.report;

import java.util.HashMap;
import java.util.Map;

import com.tsinghuadtv.www.mybatis.typehandler.IdInterface;

public enum ReportType implements IdInterface {
	
	IMAGE_TEXT	(1, "IMAGE_TEXT", "图文报道"),
	AUDIO		(2, "AUDIO", "语音报道")
    ;

    private static final Map<String, ReportType> code2ReportTypes;

    private final int id;
    private final String code;
    private final String description;

    static {
        code2ReportTypes = new HashMap<>();

        for (ReportType productType : ReportType.values()) {
            code2ReportTypes.put(productType.getCode(), productType);
        }
    }

    public static ReportType fromCode(String code) {
        return code2ReportTypes.get(code);
    }
    
    public static ReportType fromId(Integer id) {
    	if (id == null) {
    		return null;
    	}
    	for (ReportType type : ReportType.values()) {
    		if (type.getId() == id) {
    			return type;
    		}
    	}
    	return null;
    }

    ReportType(int id, String code, String description) {
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

package com.tsinghuadtv.www.entity.task;

import java.util.HashMap;
import java.util.Map;

import com.tsinghuadtv.www.mybatis.typehandler.IdInterface;

public enum TaskType implements IdInterface {
	
	ASK_ANSWER	(1, "ASK_ANSWER", "问答"),
	TOPIC_REPORT(2, "TOPIC_REPORT", "主题报道"),
    ;

    private static final Map<String, TaskType> code2TaskTypes;

    private final int id;
    private final String code;
    private final String description;

    static {
        code2TaskTypes = new HashMap<>();

        for (TaskType productType : TaskType.values()) {
            code2TaskTypes.put(productType.getCode(), productType);
        }
    }

    public static TaskType fromCode(String code) {
        return code2TaskTypes.get(code);
    }
    
    public static TaskType fromId(Integer id) {
    	if (id == null) {
    		return null;
    	}
    	for (TaskType type : TaskType.values()) {
    		if (type.getId() == id) {
    			return type;
    		}
    	}
    	return null;
    }

    TaskType(int id, String code, String description) {
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

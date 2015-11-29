package com.example.piroacc.myapplication.model.dto.response;

import java.util.Date;

/**
 * Created by PiroACC on 2015-11-28.
 */
public class RodzicMDTOResponse {

    private Integer parentId;

    @Override
    public String toString() {
        return "RodzicMDTOResponse{" +
                "parentId=" + parentId +
                '}';
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}

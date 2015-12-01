package com.example.piroacc.myapplication.model.dto.response.child;

import java.io.Serializable;

/**
 * Created by PiroACC on 2015-12-01.
 */
public class PositionMDTOResponse implements Serializable {

    private String status;

    @Override
    public String toString() {
        return "PositionMDTOResponse{" +
                "status='" + status + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

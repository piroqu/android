package com.example.piroacc.myapplication.model.dto.response.child;

/**
 * Created by PiroACC on 2015-12-01.
 */
public class ConnectionMDTOResponse {
    private String status;

    @Override
    public String toString() {
        return "ConnectionMDTOResponse{" +
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

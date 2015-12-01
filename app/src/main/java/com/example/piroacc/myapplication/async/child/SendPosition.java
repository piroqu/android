package com.example.piroacc.myapplication.async.child;

import android.os.AsyncTask;
import android.util.Log;

import com.example.piroacc.myapplication.model.Position;
import com.example.piroacc.myapplication.model.dto.request.DzieckoMDTORequest;
import com.example.piroacc.myapplication.model.dto.response.DzieckoMDTOResponse;
import com.example.piroacc.myapplication.model.dto.response.child.PositionMDTOResponse;
import com.example.piroacc.myapplication.resources.Constant;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by PiroACC on 2015-12-01.
 */
public class SendPosition extends AsyncTask<Object, Void , Void> {

    private static final String SYNC_PATH = "praca/rest/child/send/position/";
    private static final String DEBUG_LOG = "ASYNC TASK" + SendPosition.class.getName();
    private Integer childId;
    private Position positionToSync;

    @Override
    protected Void doInBackground(Object... params) {
        Log.d(DEBUG_LOG, "RECIEVIED : " + params[0] + params[1]);
        childId= (Integer) params[0];
        positionToSync = (Position) params[1];
         postByRestTemplate();
        return null;
    }

    public void postByRestTemplate() {
        String url = Constant.HOST_ADDRES + SYNC_PATH + childId;
        Log.d(DEBUG_LOG, "post to addres : " + url);
        Log.d(DEBUG_LOG, "post data : " + positionToSync);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.postForObject(url, positionToSync, PositionMDTOResponse.class);
    }
}

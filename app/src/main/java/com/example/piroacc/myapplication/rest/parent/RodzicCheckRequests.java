package com.example.piroacc.myapplication.rest.parent;

import android.os.AsyncTask;
import android.util.Log;

import com.example.piroacc.myapplication.model.Pozycja;
import com.example.piroacc.myapplication.model.dto.request.DzieckoMDTORequest;
import com.example.piroacc.myapplication.model.dto.request.RodzicMDTORequest;
import com.example.piroacc.myapplication.model.dto.response.DzieckoMDTOResponse;
import com.example.piroacc.myapplication.model.dto.response.KolejkaRodzicMDTOResponse;
import com.example.piroacc.myapplication.resources.Constant;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by PiroACC on 2015-11-30.
 */
public class RodzicCheckRequests extends AsyncTask<RodzicMDTORequest, Void, List<KolejkaRodzicMDTOResponse>> {

    private static final String DEBUG_LOG = "CHECK REQUESTS ";
    private static final String CHECK_PATH = "praca/rest/parent/queue/check";

    public List<KolejkaRodzicMDTOResponse> postByRestTemplate(RodzicMDTORequest request) {
        String url = Constant.HOST_ADDRES + CHECK_PATH;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        ResponseEntity<KolejkaRodzicMDTOResponse[]> response = restTemplate.postForEntity(url, request, KolejkaRodzicMDTOResponse[].class);
        List<KolejkaRodzicMDTOResponse> responseAsList = Arrays.asList(response.getBody());
        for (KolejkaRodzicMDTOResponse tmp : responseAsList) {
            Log.d(DEBUG_LOG, "RESULT : " + tmp.toString());
        }
        Log.d(DEBUG_LOG, "RESPONSE SIZE : " + responseAsList.size());
        return responseAsList;
    }

    @Override
    protected List<KolejkaRodzicMDTOResponse> doInBackground(RodzicMDTORequest... params) {
        RodzicMDTORequest request = (RodzicMDTORequest) params[0];
        return postByRestTemplate(request);
    }
}

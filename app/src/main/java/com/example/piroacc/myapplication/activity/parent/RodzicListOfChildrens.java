package com.example.piroacc.myapplication.activity.parent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.piroacc.myapplication.R;
import com.example.piroacc.myapplication.model.Pozycja;
import com.example.piroacc.myapplication.async.parent.RodzicGetChildPositions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RodzicListOfChildrens extends AppCompatActivity {

    private Button btnShowChildrenLocalization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rodzic_list_of_childrens);
        findUIElements();
    }

    public void goToMapAndShowChildrenLocalization(View view){
        try {
            List<Pozycja> positions = new RodzicGetChildPositions().execute(51).get();
            for(Pozycja tmp : positions){
                tmp.setId(51);
                tmp.setFkDzieckoId(51);
            }
            ArrayList<Pozycja> positionsAsArray = new ArrayList<Pozycja>(positions);
            Intent i = new Intent(this, ParentMainActivity.class);
            i.putParcelableArrayListExtra("childenLocalizations",  positionsAsArray);
            startActivity(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private void findUIElements(){
        btnShowChildrenLocalization = (Button)findViewById(R.id.btnShowChildrenLocalization);
    }
}

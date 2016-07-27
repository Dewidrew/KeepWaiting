package com.augmentis.ayp.keepwalking;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;
import java.util.UUID;

public class WalkingActivity extends FragmentActivity {
    private Button save_btn;
    private EditText title_edit;
    protected static final String WALKING_ID = "walkingActivity.walkingId";
    protected static final String WALKING_POS = "walkingActivity.walkingPos";
    private int position;
    private boolean state;
    private UUID walkingId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walking);
        bind();
        if(getIntent().getExtras() != null) {
            setVariableFromIntent();
            state = false;
        }else{
            state = true;
        }



        //Set Listener
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WalkingLab tempList = WalkingLab.getInstance(view.getContext());
                if(state) {
                    tempList.addWalking(title_edit.getText().toString());
                }else{
                    tempList.changeTitle(title_edit.getText().toString(),walkingId);
                }
                Intent intent = new Intent(WalkingActivity.this,WalkingListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setVariableFromIntent() {
            walkingId = (UUID) getIntent().getSerializableExtra(WALKING_ID);
            position = (int) getIntent().getExtras().get(WALKING_POS);

            WalkingLab tempLab = WalkingLab.getInstance(this);
            Walking temp = tempLab.getWalkingById(walkingId);
            title_edit.setText(temp.getTitle());
    }

    private void bind() {
        save_btn = (Button)findViewById(R.id.save_btn);
        title_edit = (EditText)findViewById(R.id.walking_title);
    }

    public static Intent newIntent(Context activity, UUID id, int position){
        Intent intent = new Intent(activity,WalkingActivity.class);
        intent.putExtra(WALKING_ID,id);
        intent.putExtra(WALKING_POS,position);
        return intent;
    }
}

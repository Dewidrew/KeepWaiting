package com.augmentis.ayp.keepwalking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class WalkingListActivity extends FragmentActivity {
    private Button add_btn;
    private RecyclerView walkingRecycleView;
    protected static  WalkingAdapter adapter;

    private int walkingPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walking_list);
        bind();
        setting();
        updateUI();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Walking.UPLOAD_CODE){
            if(resultCode == Activity.RESULT_OK){
                walkingPos = (int)data.getExtras().get("position");
            }
        }
    }

    private void updateUI() {
        final WalkingLab walkLab = WalkingLab.getInstance(this);
        List<Walking> walkings = walkLab.getWalkingList();
//        for (Walking w:walkings) {
//            Log.d("augmentis :",w.toString());
//
//        }
        if(adapter == null){
            adapter = new WalkingAdapter(walkings,this);
            walkingRecycleView.setAdapter(adapter);
        }else{
//            adapter.notifyItemChanged(walkingPos);
            walkingRecycleView.setAdapter(adapter);
        }

    }

    private void setting() {
        walkingRecycleView.setLayoutManager(new LinearLayoutManager(this));


        //Set Listener
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WalkingListActivity.this, WalkingActivity.class);
                startActivityForResult(intent, Walking.REQUEST_CODE);
            }
        });
    }

    private void bind() {
        add_btn = (Button) findViewById(R.id.walking_add_button);
        walkingRecycleView = (RecyclerView) findViewById(R.id.walking_recycler_view);
    }

    private class WalkingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleTextView, dateTextView;
        private int position;
        private Walking walking;
        private Context activity;

        public WalkingHolder(View itemView, Context activity) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.list_item_walking_title_text_view);
            dateTextView = (TextView) itemView.findViewById(R.id.list_item_walking_date_text_view);
            this.activity = activity;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = WalkingActivity.newIntent(activity, walking.getId(), position);
            startActivityForResult(intent, Walking.UPLOAD_CODE);
        }

        public void bind(Walking walking, int position) {
            this.walking = walking;
            this.position = position;
            titleTextView.setText(walking.getTitle());
            dateTextView.setText(walking.getWalkingDate().toString());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public class WalkingAdapter extends RecyclerView.Adapter<WalkingHolder> {
        private List<Walking> walkings;
        private Context activity;

        public WalkingAdapter(List<Walking> walkings, Context activity) {
            this.walkings = walkings;
            this.activity = activity;
            Log.d("augmentis","Start Walking Adapter");
        }

        @Override
        public WalkingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(activity);
            View v = layoutInflater.inflate(R.layout.list_item_walking, parent, false);
            Log.d("augmentis","onCreateViewHolder");
            return new WalkingHolder(v, activity);
        }

        @Override
        public void onBindViewHolder(WalkingHolder holder, int position) {
            Walking walking = walkings.get(position);
            holder.bind(walking, position);
        }

        @Override
        public int getItemCount() {
            return walkings.size();
        }
    }
}

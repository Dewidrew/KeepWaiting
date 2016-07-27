package com.augmentis.ayp.keepwalking;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Hattapong on 7/27/2016.
 */
public class WalkingLab {
    List<Walking> walkingList;

    private static WalkingLab instance;

    public static WalkingLab getInstance(Context context){
        if(instance == null){
            instance = new WalkingLab();
        }
        return instance;
    }

    public WalkingLab() {
        if(walkingList == null){
            walkingList = new ArrayList<>();
        }
    }

    public List<Walking> getWalkingList() {
        return walkingList;
    }

    public void addWalking(String title){
        Walking temp = new Walking();
        temp.setTitle(title);
        walkingList.add(temp);
    }

    public void changeTitle(String title,  UUID id){
        for(int i = 0;i<walkingList.size();i++){
            if(walkingList.get(i).getId().equals(id)){
                walkingList.get(i).setTitle(title);
                walkingList.get(i).setWalkingDate(new Date());
            }
        }
    }
    public Walking getWalkingById(UUID uuid){
        for(Walking walk:walkingList){
            if(walk.getId().equals(uuid)){
                return walk;
            }
        }
        return null;
    }
}

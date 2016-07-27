package com.augmentis.ayp.keepwalking;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Hattapong on 7/27/2016.
 */
public class Walking {
    protected static final int REQUEST_CODE = 12345;
    protected static final int UPLOAD_CODE = 543;
    private UUID id;
    private String title;
    private Date walkingDate;


    public Walking() {
        id = UUID.randomUUID();
        walkingDate = new Date();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getWalkingDate() {
        return walkingDate;
    }

    public void setWalkingDate(Date walkingDate) {
        this.walkingDate = walkingDate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UUID=").append(id);
        builder.append(",Title=").append(title);
        builder.append(",Date=").append(walkingDate);
        return builder.toString();
    }
}

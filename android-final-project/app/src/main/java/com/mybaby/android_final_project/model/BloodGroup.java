package com.mybaby.android_final_project.model;

import java.io.Serializable;

/**
 * Created by SabrinaKay on 8/15/15.
 */

public class BloodGroup implements Serializable{
    private int id;
    private String groupFactor;

    public BloodGroup(int id, String groupFactor) {
        this.id = id;
        this.groupFactor = groupFactor;
    }

    public String getGroupFactor() {
        return groupFactor;
    }

    public void setGroupFactor(String groupFactor) {
        this.groupFactor = groupFactor;
    }

    public int getId() {
        return id;
    }
}

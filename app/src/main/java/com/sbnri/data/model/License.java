package com.sbnri.data.model;

import androidx.room.Entity;

@Entity(tableName = "license_table")
public class License {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

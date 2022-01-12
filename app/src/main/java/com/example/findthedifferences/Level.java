package com.example.findthedifferences;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "levels")
public class Level {

    public Level(){
        number = 1;
        value = 1;
    }

    public Level(int n, int v) {
        number = n;
        value = v;
    }

    public int getNumber() {
        return number;
    }

    public int getValue() {
        return value;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @PrimaryKey
    @ColumnInfo(name = "number")
    private int number;

    @ColumnInfo(name = "value")
    private int value;


}

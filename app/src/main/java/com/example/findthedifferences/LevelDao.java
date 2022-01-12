package com.example.findthedifferences;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface LevelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Level level);

    @Query("SELECT * FROM levels WHERE number =:number")
    LiveData<Level> getLevel(int number);
}

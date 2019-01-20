package com.rag.khavaranmessenger.samplecleanarchitecture.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.rag.khavaranmessenger.samplecleanarchitecture.data.model.MedicModelData;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface MedicDAO {
    @Insert
    long insert(MedicModelData medicModelData);

    @Query("DELETE FROM medic_table")
    void deleteAll();

    @Query("DELETE FROM medic_table where `rowId`=:id")
    int deleteMedic(int id);

    @Query("SELECT * from medic_table where `rowId`=:rowId ")
    Single<MedicModelData> getMedic(int rowId);

    @Query("SELECT * from medic_table")
    Single<List<MedicModelData>> getAllMedic();
}

package com.rag.khavaranmessenger.samplecleanarchitecture.domain.repository;


import com.rag.khavaranmessenger.samplecleanarchitecture.domain.model.MedicModelDomain;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface MedicRepository {

    Single<List<MedicModelDomain>> getAllMedic();

    Single<MedicModelDomain> getMedic(int id);

    //    MedicModelDomain getMedic(int id);
//
    Single<Boolean> insert(MedicModelDomain modelEntities);

    Single<Boolean> delete(int id);
}

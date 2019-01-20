package com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.base;

import com.rag.khavaranmessenger.samplecleanarchitecture.domain.model.MedicModelDomain;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface BaseUseCaseHandler<R> {

    Single<R> buildListUseCaseObservable();

    Single<R> buildMedicUseCaseObservable(int id);

    Single<R> buildSaveUseCaseObservable(MedicModelDomain medicModelDomain);

    Single<R> buildDeleteUseCaseObservable(int id);

}

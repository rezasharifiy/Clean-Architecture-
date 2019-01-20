package com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.saveusecase;

import com.rag.khavaranmessenger.samplecleanarchitecture.R;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.model.MedicModelDomain;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.repository.MedicRepository;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.base.BaseUseCase;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;

public class SaveUseCase extends BaseUseCase {

    private MedicRepository medicRepository;

    public SaveUseCase(MedicRepository medicRepository) {
        this.medicRepository = medicRepository;
    }

    @Override
    public void execute(SingleObserver observer, Object o) {
        SingleObserver<R> disObservable = this.buildSaveUseCaseObservable((MedicModelDomain)o)
                .subscribeWith(observer);
    }

    @Override
    public Single<Boolean> buildSaveUseCaseObservable(MedicModelDomain medicModelDomain) {
        return medicRepository.insert(medicModelDomain);
    }
}

package com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.deleteusecase;

import com.rag.khavaranmessenger.samplecleanarchitecture.R;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.repository.MedicRepository;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.base.BaseUseCase;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;

public class DeleteUseCase extends BaseUseCase {
    private MedicRepository medicRepository;

    public DeleteUseCase(MedicRepository medicRepository) {
        this.medicRepository = medicRepository;
    }


    @Override
    public void execute(SingleObserver observer, Object id) {
        SingleObserver<R> disObservable = this.buildDeleteUseCaseObservable((int)id)
                .subscribeWith(observer);
    }

    @Override
    public Single<Boolean> buildDeleteUseCaseObservable(int id) {
        return medicRepository.delete(id);
    }
}

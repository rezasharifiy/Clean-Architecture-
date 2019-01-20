package com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.getusecase;

import com.rag.khavaranmessenger.samplecleanarchitecture.R;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.repository.MedicRepository;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.base.BaseUseCase;

import io.reactivex.Single;
import io.reactivex.SingleObserver;

public class GetMedicUseCase extends BaseUseCase {

    private MedicRepository medicRepository;

    public GetMedicUseCase(MedicRepository medicRepository) {
        this.medicRepository = medicRepository;
    }


    @Override
    public void execute(SingleObserver observer, Object o) {
        SingleObserver<R> disObservable = this.buildListUseCaseObservable()
                .subscribeWith(observer);
    }

    @Override
    public Single buildListUseCaseObservable() {
        return medicRepository.getAllMedic();
    }


    @Override
    public Single buildMedicUseCaseObservable(int id) {
        return medicRepository.getMedic(id);
    }
}

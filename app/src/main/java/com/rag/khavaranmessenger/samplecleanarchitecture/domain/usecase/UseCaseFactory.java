package com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase;

import com.rag.khavaranmessenger.samplecleanarchitecture.domain.repository.MedicRepository;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.base.BaseUseCase;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.deleteusecase.DeleteUseCase;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.getusecase.GetMedicUseCase;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.saveusecase.SaveUseCase;

public class UseCaseFactory {


    private MedicRepository repository;


    public UseCaseFactory(MedicRepository repository) {
        this.repository = repository;

    }

    public BaseUseCase getUseCase() {
        return new GetMedicUseCase(repository);
    }

    public BaseUseCase saveUseCase() {
        return new SaveUseCase(repository);
    }

    public BaseUseCase deleteUseCase() {
        return new DeleteUseCase(repository);
    }


}

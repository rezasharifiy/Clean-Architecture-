package com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.base;

import com.rag.khavaranmessenger.samplecleanarchitecture.R;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.model.MedicModelDomain;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseUseCase <T>implements BaseUseCaseHandler<R> {

    private CompositeDisposable disposables;

    public BaseUseCase() {

        this.disposables = new CompositeDisposable();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    public abstract void execute(SingleObserver<R> observer, T t) ;

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }


    private void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    @Override
    public Single<R> buildListUseCaseObservable() {
        return null;
    }

    @Override
    public Single buildMedicUseCaseObservable(int id) {
        return null;
    }

    @Override
    public Single buildSaveUseCaseObservable(MedicModelDomain medicModelDomain) {
        return null;
    }

    @Override
    public Single buildDeleteUseCaseObservable(int id) {
        return null;
    }

}

package com.rag.khavaranmessenger.samplecleanarchitecture.app.peresenter.base;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public abstract class BasePresenter<T> implements SingleObserver<T> , BasePresenterHandler {


    public BasePresenter() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }
}

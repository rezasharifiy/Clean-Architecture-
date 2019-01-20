package com.rag.khavaranmessenger.samplecleanarchitecture.app.mainview.peresenter;

import com.rag.khavaranmessenger.samplecleanarchitecture.app.mainview.view.DeleteMedicListener;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.mapper.AppModelMapper;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.peresenter.base.BasePresenter;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.UseCaseFactory;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.deleteusecase.DeleteUseCase;

import java.lang.ref.WeakReference;

public class DeleteMedicPresenter extends BasePresenter {


    private WeakReference<DeleteMedicListener> listener;
    private UseCaseFactory useCaseFactory;
    private AppModelMapper appModelMapper;

    public DeleteMedicPresenter(UseCaseFactory useCaseFactory, AppModelMapper appModelMapper, DeleteMedicListener listener) {
        this.useCaseFactory = useCaseFactory;
        this.appModelMapper = appModelMapper;
        this.listener = new WeakReference<DeleteMedicListener>(listener);
    }

    @Override
    public void onViewReady() {
        if (listener.get() != null) {
            run();
        }
    }

    @Override
    public void run() {

    }

    public void delete(int id) {
        DeleteUseCase getUseCase = (DeleteUseCase) useCaseFactory.deleteUseCase();
        getUseCase.execute(this, id);
    }

    @Override
    public void onSuccess(Object o) {
        if (listener != null)
            listener.get().onDeleteMedic((Boolean) o);
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
    }
}

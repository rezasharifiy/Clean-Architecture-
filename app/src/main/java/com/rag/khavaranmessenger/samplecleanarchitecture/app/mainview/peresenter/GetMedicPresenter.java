package com.rag.khavaranmessenger.samplecleanarchitecture.app.mainview.peresenter;

import com.rag.khavaranmessenger.samplecleanarchitecture.app.mapper.AppModelMapper;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.peresenter.base.BasePresenter;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.mainview.view.LoadMedicListener;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.model.MedicModelDomain;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.UseCaseFactory;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.getusecase.GetMedicUseCase;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetMedicPresenter extends BasePresenter {


    private WeakReference<LoadMedicListener> view;
    private UseCaseFactory useCaseFactory;
    private AppModelMapper appModelMapper;

    public GetMedicPresenter(UseCaseFactory useCaseFactory, AppModelMapper appModelMapper, LoadMedicListener view) {
        this.useCaseFactory = useCaseFactory;
        this.appModelMapper = appModelMapper;
        this.view = new WeakReference<LoadMedicListener>(view);
    }

    @Override
    public void onViewReady() {
        if (view.get() != null) {
            run();
        }
    }

    @Override
    public void run() {
        GetMedicUseCase getUseCase = (GetMedicUseCase) useCaseFactory.getUseCase();
        getUseCase.execute(this,null);
    }

    @Override
    public void onSuccess(Object o) {
        if (view != null)
            view.get().showMedicList(appModelMapper.transformToApp((List<MedicModelDomain>) o));
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
    }
}

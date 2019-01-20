package com.rag.khavaranmessenger.samplecleanarchitecture.app.addmedic.peresenter;

import com.rag.khavaranmessenger.samplecleanarchitecture.app.addmedic.view.SaveMedicListener;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.mapper.AppModelMapper;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.model.MedicModelApp;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.peresenter.base.BasePresenter;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.UseCaseFactory;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.saveusecase.SaveUseCase;

import java.lang.ref.WeakReference;

public class SaveMedicPresenter extends BasePresenter {


    private WeakReference<SaveMedicListener> listener;
    private UseCaseFactory useCaseFactory;
    private AppModelMapper appModelMapper;
    private MedicModelApp medicModelApp;

    public SaveMedicPresenter(UseCaseFactory useCaseFactory, AppModelMapper appModelMapper, SaveMedicListener listener) {
        this.useCaseFactory = useCaseFactory;
        this.appModelMapper = appModelMapper;
        this.listener = new WeakReference<SaveMedicListener>(listener);
    }


    @Override
    public void onViewReady() {
        if (listener.get() != null) {
            run();
        }
    }

    @Override
    public void run() {
        SaveUseCase getUseCase = (SaveUseCase) useCaseFactory.saveUseCase();
        getUseCase.execute(this,appModelMapper.transformToEntities(medicModelApp));
    }

    @Override
    public void onSuccess(Object o) {
        if (listener != null)
            listener.get().onMedicSave((Boolean) o);
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
    }

    public MedicModelApp getMedicModelApp() {
        return medicModelApp;
    }

    public void setMedicModelApp(MedicModelApp medicModelApp) {
        this.medicModelApp = medicModelApp;
    }
}

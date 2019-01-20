package com.rag.khavaranmessenger.samplecleanarchitecture.app.peresenter;

import com.rag.khavaranmessenger.samplecleanarchitecture.app.addmedic.peresenter.SaveMedicPresenter;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.addmedic.view.SaveMedicListener;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.mainview.peresenter.DeleteMedicPresenter;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.mainview.peresenter.GetMedicPresenter;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.mainview.view.DeleteMedicListener;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.mainview.view.LoadMedicListener;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.mapper.AppModelMapper;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.UseCaseFactory;

public class PresenterFactory {
    private static PresenterFactory instance;

    public static PresenterFactory getInstance() {
        if (instance == null) {
            instance = new PresenterFactory();
        }
        return instance;
    }

    private PresenterFactory() {
    }


    public GetMedicPresenter GetMedicPresenter(UseCaseFactory useCaseFactory,
                                           AppModelMapper appModelMapper, LoadMedicListener loadMedicListener) {
        return new GetMedicPresenter(useCaseFactory, appModelMapper, loadMedicListener);
    }

    public SaveMedicPresenter saveMedicPresenter(UseCaseFactory useCaseFactory,
                                                 AppModelMapper appModelMapper, SaveMedicListener listener) {
        return new SaveMedicPresenter(useCaseFactory, appModelMapper, listener);
    }

    public DeleteMedicPresenter deleteMedicPresenter(UseCaseFactory useCaseFactory,
                                                     AppModelMapper appModelMapper, DeleteMedicListener listener) {
        return new DeleteMedicPresenter(useCaseFactory, appModelMapper, listener);
    }

}

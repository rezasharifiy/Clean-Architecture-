package com.rag.khavaranmessenger.samplecleanarchitecture.app.mainview.view;

import com.rag.khavaranmessenger.samplecleanarchitecture.app.model.MedicModelApp;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.view.viewhandler.BaseView;

import java.util.List;

public interface LoadMedicListener extends BaseView {

    void showMedicList(List<MedicModelApp> list);

}

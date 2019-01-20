package com.rag.khavaranmessenger.samplecleanarchitecture.app.addmedic.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rag.khavaranmessenger.samplecleanarchitecture.R;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.addmedic.peresenter.SaveMedicPresenter;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.mainview.view.MainFragment;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.mapper.AppModelMapper;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.model.MedicModelApp;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.peresenter.PresenterFactory;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.view.MainActivity;
import com.rag.khavaranmessenger.samplecleanarchitecture.data.database.DataRepository;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.UseCaseFactory;

public class AddMedicFragment extends Fragment implements View.OnClickListener, SaveMedicListener {
    private View view;
    private EditText medic_et;

    public static AddMedicFragment newInstance() {
        Bundle args = new Bundle();
        AddMedicFragment fragment = new AddMedicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            setupView(inflater, container);
        }
        return view;
    }

    private void setupView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        view = inflater.inflate(R.layout.add_medic_fragment, container, false);
        medic_et = view.findViewById(R.id.medic_et);
        Button save_bt = view.findViewById(R.id.save_bt);
        save_bt.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_bt:
                if (checkFields())
                    saveMedic(generateMedic());
                break;
        }
    }

    private boolean checkFields() {
        if (medic_et.getText().equals("")) {
            showToast(getString(R.string.error_medic_name));
            return false;
        }
        return true;
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private MedicModelApp generateMedic() {
        MedicModelApp modelApp = new MedicModelApp();
        modelApp.setName(medic_et.getText().toString());
        return modelApp;
    }

    private void saveMedic(MedicModelApp modelApp) {
        DataRepository dataRepository = DataRepository.getInstance(getContext());
        SaveMedicPresenter saveMedicPresenter = PresenterFactory.getInstance().
                saveMedicPresenter(new UseCaseFactory(dataRepository), new AppModelMapper(), this);
        saveMedicPresenter.setMedicModelApp(modelApp);
        saveMedicPresenter.onViewReady();
    }


    private void backToMain() {
        if (getActivity() != null)
            ((MainActivity) getActivity()).switchFragment(MainFragment.newInstance());
    }

    @Override
    public void onMedicSave(Boolean saved) {
        Toast.makeText(getContext(), R.string.saved, Toast.LENGTH_SHORT).show();
        backToMain();
    }
}

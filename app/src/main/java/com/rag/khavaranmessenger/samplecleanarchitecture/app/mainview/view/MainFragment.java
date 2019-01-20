package com.rag.khavaranmessenger.samplecleanarchitecture.app.mainview.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rag.khavaranmessenger.samplecleanarchitecture.R;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.adapter.MedicAdapter;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.addmedic.view.AddMedicFragment;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.mainview.peresenter.DeleteMedicPresenter;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.mainview.peresenter.GetMedicPresenter;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.mapper.AppModelMapper;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.model.MedicModelApp;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.peresenter.PresenterFactory;
import com.rag.khavaranmessenger.samplecleanarchitecture.app.view.MainActivity;
import com.rag.khavaranmessenger.samplecleanarchitecture.data.database.DataRepository;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.usecase.UseCaseFactory;

import java.util.List;

public class MainFragment extends Fragment implements View.OnClickListener, DeleteMedicListener,
        LoadMedicListener, MedicAdapter.AdapterClickListener {
    private MedicAdapter medicAdapter;
    private View view;
    private List<MedicModelApp> medicModelAppList;


    private FloatingActionButton addButton;
    private RecyclerView recyclerView;
    private List<MedicModelApp> medicAppList;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.mian_fragment, container, false);
            setupView();
            getMedicList();
        }
        return view;
    }


    private void getMedicList() {
        DataRepository dataRepository = DataRepository.getInstance(getContext());
        GetMedicPresenter presenter = (GetMedicPresenter) PresenterFactory.getInstance().GetMedicPresenter(
                new UseCaseFactory(dataRepository), new AppModelMapper(), this);
        presenter.onViewReady();
    }

    private void setupView() {
        addButton = view.findViewWithTag("add_fab");
        recyclerView = view.findViewWithTag("medic_rv");
        addButton.setOnClickListener(this);
    }

    private void setupRecyclerView(List<MedicModelApp> list) {
        if (list != null && list.size() > 0) {
            medicAdapter = new MedicAdapter(getContext(), list, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(medicAdapter);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_fb:
                if (getActivity() != null)
                    ((MainActivity) getActivity()).switchFragment(AddMedicFragment.newInstance());
                break;
        }
    }

    @Override
    public void onListItemClick(int pos) {

    }

    @Override
    public void onListItemLongClick(int pos) {
        showDialog(pos, medicModelAppList.get(pos).getRowId());
    }

    private void showDialog(final int pos, final int id) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setMessage(R.string.remove_message_alert);
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                removeItemFromDB(id);
                removeItemFromList(pos);
            }
        });
        alertDialog.show();
    }

    private void removeItemFromList(int pos) {
        medicModelAppList.remove(pos);
        medicAdapter.notifyItemRemoved(pos);
    }

    private void removeItemFromDB(int id) {
        DataRepository dataRepository = DataRepository.getInstance(getContext());
        DeleteMedicPresenter deleteMedicPresenter = PresenterFactory.getInstance().
                deleteMedicPresenter(new UseCaseFactory(dataRepository), new AppModelMapper(), this);
        deleteMedicPresenter.delete(id);

    }


    @Override
    public void loading(boolean isLoading) {

    }

    @Override
    public void showMedicList(List<MedicModelApp> list) {
        this.medicModelAppList = list;
        setupRecyclerView(list);
    }


    @Override
    public void onDeleteMedic(Boolean isSuccess) {
        if (isSuccess) {
            showToast(getString(R.string.deleted));

        } else {

        }
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}

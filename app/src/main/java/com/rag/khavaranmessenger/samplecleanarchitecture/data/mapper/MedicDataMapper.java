package com.rag.khavaranmessenger.samplecleanarchitecture.data.mapper;

import com.rag.khavaranmessenger.samplecleanarchitecture.data.model.MedicModelData;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.model.MedicModelDomain;

import java.util.ArrayList;
import java.util.List;

public class MedicDataMapper {




    public MedicModelData transformToData(MedicModelDomain medicModelEntities) {
        if (medicModelEntities == null) {
            throw new IllegalArgumentException("Cannot transformToEntities a null value");
        }
        final MedicModelData medicModelData = new MedicModelData();
        medicModelData.setDate(medicModelEntities.getDate());
        medicModelData.setDescription(medicModelEntities.getDescription());
        medicModelData.setMessage(medicModelEntities.getMessage());
        medicModelData.setName(medicModelEntities.getName());
        medicModelData.setStatus(medicModelEntities.getStatus());
        medicModelData.setIsActive(medicModelEntities.getIsActive());
        medicModelData.setPeriod(medicModelEntities.getPeriod());
        medicModelData.setRowId(medicModelEntities.getRowId());
        medicModelData.setTakeTime(medicModelEntities.getTakeTime());
        medicModelData.setTime(medicModelEntities.getTime());
        return medicModelData;

    }
    public MedicModelDomain transformToEntities(MedicModelData medicModelEntities) {
        if (medicModelEntities == null) {
            throw new IllegalArgumentException("Cannot transformToEntities a null value");
        }
        final MedicModelDomain medicModelApp = new MedicModelDomain();
        medicModelApp.setDate(medicModelEntities.getDate());
        medicModelApp.setDescription(medicModelEntities.getDescription());
        medicModelApp.setMessage(medicModelEntities.getMessage());
        medicModelApp.setName(medicModelEntities.getName());
        medicModelApp.setStatus(medicModelEntities.getStatus());
        medicModelApp.setIsActive(medicModelEntities.getIsActive());
        medicModelApp.setPeriod(medicModelEntities.getPeriod());
        medicModelApp.setRowId(medicModelEntities.getRowId());
        medicModelApp.setTakeTime(medicModelEntities.getTakeTime());
        medicModelApp.setTime(medicModelEntities.getTime());
        return medicModelApp;

    }

    public List<MedicModelDomain> transformToEntities(List<MedicModelData> modelDataList) {
        List<MedicModelDomain> medicModelEntities = new ArrayList<>();
        for (MedicModelData model : modelDataList) {
            medicModelEntities.add(transformToEntities(model));
        }
        return medicModelEntities;
    }
}

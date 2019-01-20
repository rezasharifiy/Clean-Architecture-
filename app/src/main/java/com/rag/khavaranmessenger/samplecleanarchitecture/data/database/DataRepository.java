package com.rag.khavaranmessenger.samplecleanarchitecture.data.database;

import android.content.Context;

import com.rag.khavaranmessenger.samplecleanarchitecture.data.mapper.MedicDataMapper;
import com.rag.khavaranmessenger.samplecleanarchitecture.data.model.MedicModelData;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.model.MedicModelDomain;
import com.rag.khavaranmessenger.samplecleanarchitecture.domain.repository.MedicRepository;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.functions.Function;

public class DataRepository implements MedicRepository {
    private MedicDAO medicDAO;
    private MedicDataMapper medicDataMapper;
    private static DataRepository instance = null;

    private DataRepository(Context context) {
        RoomDatabase db = RoomDatabase.getInstance(context);
        medicDAO = db.dao();
        medicDataMapper = new MedicDataMapper();
    }

    public static DataRepository getInstance(Context context) {
        if (instance == null) {
            instance = new DataRepository(context);
        }
        return instance;
    }


    @Override
    public Single<List<MedicModelDomain>> getAllMedic() {

        return medicDAO.getAllMedic().map(new Function<List<MedicModelData>, List<MedicModelDomain>>() {
            @Override
            public List<MedicModelDomain> apply(List<MedicModelData> medicModelData) throws Exception {
                return medicDataMapper.transformToEntities(medicModelData);
            }
        });
    }

    @Override
    public Single<MedicModelDomain> getMedic(int id) {
        return medicDAO.getMedic(id).map(new Function<MedicModelData, MedicModelDomain>() {
            @Override
            public MedicModelDomain apply(MedicModelData medicModelData) throws Exception {
                return medicDataMapper.transformToEntities(medicModelData);
            }
        });
    }

    @Override
    public Single<Boolean> insert(MedicModelDomain modelEntities) {
        long result = medicDAO.insert(medicDataMapper.transformToData(modelEntities));
        if (result > -1) {
            return new Single<Boolean>() {
                @Override
                protected void subscribeActual(SingleObserver<? super Boolean> observer) {
                    observer.onSuccess(true);
                }

            };
        } else {
            return new Single<Boolean>() {
                @Override
                protected void subscribeActual(SingleObserver<? super Boolean> observer) {
                    observer.onSuccess(false);

                }

            };
        }
    }

    @Override
    public Single<Boolean> delete(int id) {
        long result = medicDAO.deleteMedic(id);
        if (result > -1) {
            return new Single<Boolean>() {
                @Override
                protected void subscribeActual(SingleObserver<? super Boolean> observer) {
                    observer.onSuccess(true);
                }

            };
        } else {
            return new Single<Boolean>() {
                @Override
                protected void subscribeActual(SingleObserver<? super Boolean> observer) {
                    observer.onSuccess(false);
                }
            };
        }
    }
}

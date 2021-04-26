package com.bit.revalorando.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bit.revalorando.entities.Articulo;
import com.bit.revalorando.entities.Trueque;

import com.bit.revalorando.repositories.TruequeRepository;

import java.util.List;

public class TruequeViewModel extends AndroidViewModel {

    private TruequeRepository truequeRepository;
    private final LiveData<List<Trueque>> trueques;

    public TruequeViewModel(Application application){
        super(application);
        truequeRepository = new TruequeRepository(application);
        trueques = truequeRepository.getTrueques();
    }

    public LiveData<List<Trueque>> getTrueques(){
        return trueques;
    }

    public void insert(Trueque trueque){
        truequeRepository.insert(trueque);
    }

    public void update(Trueque trueque){
        truequeRepository.update(trueque);
    }

    public void delete(Trueque trueque){
        truequeRepository.delete(trueque);
    }
    public void deleteTruequeByArticuloId(int id){
        truequeRepository.deleteTruequeByArticuloId(id);
    }
}


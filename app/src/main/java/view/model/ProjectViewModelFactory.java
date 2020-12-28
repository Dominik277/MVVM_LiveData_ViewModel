package view.model;

import android.util.ArrayMap;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import view.model.di.ViewModelSubComponent;
import view.model.viewmodel.ProjectViewModel;
import view.model.viewmodel.ProjectsListViewModel;

public class ProjectViewModelFactory implements ViewModelProvider.Factory {

    private final ArrayMap<Class,Callable<? extends ViewModel>> creators;

    @Inject
    public ProjectViewModelFactory(ViewModelSubComponent viewModelSubComponent){
        creators = new ArrayMap<>();
        creators.put(ProjectViewModel.class,() -> viewModelSubComponent.projectListViewModel());
        creators.put(ProjectsListViewModel.class, () -> viewModelSubComponent.projectListViewModel());
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Callable<? extends ViewModel> creator = creators.get(modelClass);
    }
}

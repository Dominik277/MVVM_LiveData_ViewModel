package view.model.viewmodel;

import android.app.Application;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import view.model.service.model.Project;
import view.model.service.repository.ProjectRepository;

public class ProjectsListViewModel extends AndroidViewModel {

    private final LiveData<List<Project>> projectListObservable;

    public ProjectsListViewModel(@NonNull Application application) {
        super(application);

        projectListObservable = ProjectRepository.getInstance().getProjectList("Google");
    }

    public LiveData<List<Project>> getProjectListObservable(){
        return projectListObservable;
    }

}

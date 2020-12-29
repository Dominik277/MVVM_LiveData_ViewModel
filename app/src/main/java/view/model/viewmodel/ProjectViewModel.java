package view.model.viewmodel;

import android.app.Application;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import view.model.service.model.Project;
import view.model.service.repository.ProjectRepository;

public class ProjectViewModel extends AndroidViewModel {

    private static final String TAG = ProjectViewModel.class.getName();
    private static final MutableLiveData ABSENT = new MutableLiveData();

    {
        ABSENT.setValue(null);
    }

    private final LiveData<Project> projectObservable;
    private final MutableLiveData<String> projectId;

    public ObservableField<Project> projectObservableField = new ObservableField<>();

    @Inject
    public ProjectViewModel(@NonNull ProjectRepository projectRepository, @NonNull Application application) {
        super(application);

        this.projectId = new MutableLiveData<>();

        projectObservable = Transformations.switchMap(projectId, input -> {
            if (input.isEmpty()) {
                return ABSENT;
            }
            return projectRepository.getProjectDetails("Google", projectId.getValue());

        });

    }
}
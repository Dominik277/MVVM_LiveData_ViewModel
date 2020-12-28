package view.model.di;

import dagger.Subcomponent;
import view.model.viewmodel.ProjectViewModel;
import view.model.viewmodel.ProjectsListViewModel;

@Subcomponent
public interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder{
        ViewModelSubComponent build();
    }

    ProjectsListViewModel projectListViewModel();
    ProjectViewModel projectViewModel();

}

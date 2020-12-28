package view.model.view.ui;

import android.os.Bundle;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import view.model.service.model.Project;
import view.model.view.adapter.ProjectAdapter;
import view.model.viewmodel.ProjectsListViewModel;

public class ProjectListFragment extends ListFragment {

    private ProjectAdapter projectAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ProjectsListViewModel viewModel = ViewModelProvider
                .of(this)
                .get(ProjectsListViewModel.class);

        observeViewModel(viewModel);
    }

    private void observeViewModel(ProjectsListViewModel viewModel) {

        viewModel.getProjectListObservable().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(List<Project> projects) {
                if (projects != null){

                    projectAdapter.setProjectList(projects);
                }
            }
        });

    }
}

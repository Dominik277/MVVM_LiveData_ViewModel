package view.model.view.ui;

import android.os.Bundle;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import view.model.di.Injectable;
import view.model.viewmodel.ProjectViewModel;

public class ProjectFragment extends LifecycleFragment implements Injectable {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ProjectViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ProjectViewModel.class);

}

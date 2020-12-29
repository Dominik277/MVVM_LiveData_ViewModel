package view.model.di;

import javax.inject.Singleton;

import androidx.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import view.model.ProjectViewModelFactory;
import view.model.service.repository.GitHubService;

@Module(subcomponents = ViewModelSubComponent.class)
public class AppModule {

    @Singleton
    GitHubService provideGithubService(){
        return new Retrofit.Builder()
                .baseUrl(GitHubService.HTTPS_API_GITHUB_URL)
                .addConverterFactory(GsoConverterFactory.create())
                .build()
                .create(GitHubService.class);
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
        ViewModelSubComponent.Builder viewModelSubComponent){

            return new ProjectViewModelFactory(viewModelSubComponent.build());
    }

}

package sercandevops.com.veterineruygulamasi.RestApi;

public class BaseManager {

    protected RestApi getRestApi()
    {
        RestApiClient restApiClient = new RestApiClient(BaseURL.URL);
        return restApiClient.getmRestApi();
    }

}

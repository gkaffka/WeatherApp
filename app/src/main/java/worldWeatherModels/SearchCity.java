package worldWeatherModels;

import com.google.gson.annotations.SerializedName;

final public class SearchCity {
    @SerializedName("search_api")
    final private Results searchApi;

    public SearchCity(Results searchApi) {
        this.searchApi = searchApi;
    }

    public Results getSearchApi() {
        return searchApi;
    }
}

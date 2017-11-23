package dhirajnayak.com.personalizedweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import dhirajnayak.com.personalizedweather.Interface.IRetrofit;
import dhirajnayak.com.personalizedweather.Model.City;
import dhirajnayak.com.personalizedweather.Model.Current;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static dhirajnayak.com.personalizedweather.Constant.API_KEY;
import static dhirajnayak.com.personalizedweather.Constant.BASE_URL;

public class SearchActivity extends AppCompatActivity implements CityAdapter.ICityListener{
    RecyclerView recyclerView;
    CityAdapter adapter;
    LinearLayoutManager layoutManager;
    DividerItemDecoration dividerItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.searchMenu);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();
        searchView.setQueryHint("start searching...");
        fireSuggestions(searchView)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String text) throws Exception {
                        if (text.isEmpty()) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                })
                .distinctUntilChanged()
                .switchMap(new Function<String, ObservableSource<ArrayList<City>>>() {
                    @Override
                    public ObservableSource<ArrayList<City>> apply(String query) throws Exception {
                        return dataFromNetwork(query);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<City>>() {
                    @Override
                    public void accept(ArrayList<City> result) throws Exception {
                        setRecyclerView(result);
                    }
                });
        return super.onCreateOptionsMenu(menu);
    }

    public static Observable<String> fireSuggestions(SearchView searchView) {

        final PublishSubject<String> subject = PublishSubject.create();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                subject.onComplete();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                subject.onNext(newText);
                return true;
            }
        });
        return subject;
    }

    public Observable<ArrayList<City>> dataFromNetwork(String searchText){
        final ArrayList<City> cities = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IRetrofit service = retrofit.create(IRetrofit.class);
        Call<List<City>> call = service.getLocationData(API_KEY,searchText);
        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                if(response.body() != null){
                    cities.addAll(response.body());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {

            }
        });
        return Observable.just(cities);
    }

    public void setRecyclerView(ArrayList<City> cityArrayList){
        adapter=new CityAdapter(SearchActivity.this,cityArrayList,SearchActivity.this);
        recyclerView.setAdapter(adapter);
        layoutManager=new LinearLayoutManager(SearchActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapter.notifyDataSetChanged();

    }


    @Override
    public void setSelectedCity(City selectedCity) {
        Intent intent = new Intent(SearchActivity.this,CurrentWeatherActivity.class);
        intent.putExtra("selectedCity",selectedCity.getName());
        startActivity(intent);
    }
}

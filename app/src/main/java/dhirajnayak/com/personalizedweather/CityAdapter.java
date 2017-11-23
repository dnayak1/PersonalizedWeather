package dhirajnayak.com.personalizedweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dhirajnayak.com.personalizedweather.Model.City;

/**
 * Created by dhirajnayak on 11/7/17.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {
    ArrayList<City> cityArrayList=new ArrayList<>();
    Context mContext;
    private ICityListener cityListener;

    public CityAdapter(Context mContext, ArrayList<City> cityArrayList, ICityListener cityListener) {
        this.cityArrayList = cityArrayList;
        this.mContext = mContext;
        this.cityListener = cityListener;

    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        View view= layoutInflater.inflate(R.layout.city_layout,parent,false);
        CityAdapter.CityViewHolder idRecyclerViewHolder=new CityAdapter.CityViewHolder(view);
        return idRecyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        final City city=cityArrayList.get(position);

        holder.textViewCity.setText(city.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityListener.setSelectedCity(city);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityArrayList.size();
    }

    public static class CityViewHolder extends RecyclerView.ViewHolder{

        TextView textViewCity;

        public CityViewHolder(View itemView) {
            super(itemView);
            textViewCity= (TextView) itemView.findViewById(R.id.textViewCity);
        }
    }

    interface ICityListener
    {
        void setSelectedCity(City selectedCity);
    }

}

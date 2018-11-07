package com.projctwash.com.proyek2_carwash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.projctwash.com.proyek2_carwash.Adapter.RecyclerAdapterJenisMotor;
import com.projctwash.com.proyek2_carwash.Model.GetKendaraan;
import com.projctwash.com.proyek2_carwash.Model.Kendaraan;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JenisMotorActivity extends AppCompatActivity {


    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterJenisMotor mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis_motor);

        mRecyclerView = findViewById(R.id.rcycler_jenismotor);
        mLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        initialize();
    }

    public void initialize(){
        Call<GetKendaraan> gKen = mApiInterface.getKendaraan();

        gKen.enqueue(new Callback<GetKendaraan>() {
            @Override
            public void onResponse(Call<GetKendaraan> call, Response<GetKendaraan> response) {

                List<Kendaraan> mJMotor = response.body().getListDataKendaraan();
                Log.d("Retrofit Get", "Jumlah data Kontak: " +String.valueOf(mJMotor.size()));

                mAdapter = new RecyclerAdapterJenisMotor(mJMotor,getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetKendaraan> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}

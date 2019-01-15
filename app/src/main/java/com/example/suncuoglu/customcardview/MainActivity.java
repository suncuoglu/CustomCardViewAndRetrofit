package com.example.suncuoglu.customcardview;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.suncuoglu.customcardview.api.APIInterface;
import com.example.suncuoglu.customcardview.customview.CustomCardView;
import com.example.suncuoglu.customcardview.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    CustomCardView cardview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardview = findViewById(R.id.myCardView);

        cardview.setOnCustomListener(new CustomCardView.OnCustomListener() {
            @Override
            public void onButtonClicked() {


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://jsonplaceholder.typicode.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                APIInterface service = retrofit.create(APIInterface.class);

                service.getUser().enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> user) {
                        String url = "https://www.tarihnotlari.com/wp-content/uploads/2011/12/resim-sanat-akimlari-700x380.jpg";
                        cardview.setData(url,user.body());

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });

            }
        });
    }
}

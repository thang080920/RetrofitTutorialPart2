package com.example.retrofitapitutorial.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.retrofitapitutorial.R;
import com.example.retrofitapitutorial.adapter.NewsAdapter;
import com.example.retrofitapitutorial.interfaces.NewsOnClick;
import com.example.retrofitapitutorial.model.Item;

import java.util.List;

import com.example.retrofitapitutorial.network.APIManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListNewsActivity extends AppCompatActivity {
  private NewsAdapter adapter;
  private List<Item> listData;

  TextView tvDate, tvTitle, tvContent;
  ImageView ivCover;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_news);

    tvTitle = findViewById(R.id.tvTitle);
    tvContent = findViewById(R.id.tvContent);
    tvDate = findViewById(R.id.tvDate);
    ivCover = findViewById(R.id.ivCover);
    getData();

    adapter = new NewsAdapter(this, listData);

    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

    RecyclerView rvNews = findViewById(R.id.rvNews);

    rvNews.setLayoutManager(layoutManager);
    rvNews.setAdapter(adapter);

    adapter.setiOnClick(new NewsOnClick() {
      @Override
      public void onClickItem(int position) {
        Item model = listData.get(position);
        Intent intent = new Intent(ListNewsActivity.this, DetailActivity.class);
        intent.putExtra("URL", model.getContent().getUrl());
        startActivity(intent);
      }
    });
  }

  public void getData() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(APIManager.SERVER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    APIManager service = retrofit.create(APIManager.class);
    service.getItemData().enqueue(new Callback<Item>() {
      @Override
      public void onResponse(Call<Item> call, Response<Item> response) {
        if (response.body()== null) {
          return;
        }

        Item model = response.body();
        tvTitle.setText(model.getTitle());
        tvDate.setText(model.getDate());
        tvContent.setText(model.getContent().getDescription());
        Glide.with(ListNewsActivity.this).load(model.getImage()).into(ivCover);
      }

      @Override
      public void onFailure(Call<Item> call, Throwable t) {
        Log.d("MainActivity","onFailure: " + t );
      }
    });
  }
}
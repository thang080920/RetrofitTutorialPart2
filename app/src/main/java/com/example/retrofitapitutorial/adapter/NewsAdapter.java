package com.example.retrofitapitutorial.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitapitutorial.R;
import com.example.retrofitapitutorial.interfaces.NewsOnClick;
import com.example.retrofitapitutorial.model.Item;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter{
  private Activity activity;
  private List<Item> listData;
  private NewsOnClick iOnClick;

  public NewsAdapter(Activity activity, List<Item> listData) {
    this.activity = activity;
    this.listData = listData;
  }

  public void setiOnClick(NewsOnClick iOnClick) {
    this.iOnClick = iOnClick;
  }

  public void reloadData(List<Item> listData) {
    this.listData = listData;
    this.notifyDataSetChanged();
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return null;
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return listData.size();
  }

//  @NonNull
//  @Override
//  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//    LayoutInflater inflater = activity.getLayoutInflater();
//    View v = inflater.inflate(R.layout.item_news, parent, false);
//    NewsHolder holder = new NewsHolder(v);
//    return holder;
//  }
//
//  @Override
//  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//    NewsHolder newsHolder = (NewsHolder) holder;
//    Item item = listData.get(position);
//    newsHolder.tvTitle.setText(item.getTitle());
//    newsHolder.tvContent.setText(item.getContent().getDescription());
//    newsHolder.tvDate.setText(item.getDate());
//    newsHolder.ivCover.setImageResource(item.getImage());
//  }

  public class NewsHolder extends RecyclerView.ViewHolder {
    TextView tvDate, tvTitle, tvContent;
    ImageView ivCover;

    public NewsHolder(@NonNull View itemView) {
      super(itemView);

      tvDate = itemView.findViewById(R.id.tvDate);
      tvTitle = itemView.findViewById(R.id.tvTitle);
      tvContent = itemView.findViewById(R.id.tvContent);
      ivCover = itemView.findViewById(R.id.ivCover);

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          iOnClick.onClickItem(getAdapterPosition());
        }
      });
    }
  }
}

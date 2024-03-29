package net.azuharu.bottomnavigation.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import net.azuharu.bottomnavigation.R;
import net.azuharu.bottomnavigation.model.RetroPhoto;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<RetroPhoto> dataList;
    private Context context;

    public CustomAdapter(List<RetroPhoto> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.title.setText(dataList.get(position).getTitle());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getThumbnailUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

        final String name = dataList.get(position).getTitle();
        holder.title.setText(dataList.get(position).getTitle());
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        public final View view;

        private TextView title;
        private ImageView image;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            title = view.findViewById(R.id.title_image);
            image = view.findViewById(R.id.cover_image);
        }
    }

    /**
     * below to refresh data using swipe layout
     */

    // Clean all elements of the recycler
    public void clear() {
        dataList.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<RetroPhoto> list) {
        dataList.addAll(list);
        notifyDataSetChanged();
    }
}

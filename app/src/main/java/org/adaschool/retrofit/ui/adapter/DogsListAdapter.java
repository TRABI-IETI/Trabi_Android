package org.adaschool.retrofit.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.adaschool.retrofit.R;
import org.adaschool.retrofit.network.dto.DogBreed;
import org.adaschool.retrofit.network.dto.PlaceDto;

import java.util.ArrayList;
import java.util.List;

public class DogsListAdapter extends RecyclerView.Adapter<DogsListAdapter.DogViewHolder> {


    private List<PlaceDto> dogs = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(PlaceDto dogBreed);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dog_row, parent, false);
        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        PlaceDto dog = dogs.get(position);
        holder.breedName.setText(dog.getName());
        Glide.with(holder.itemView.getContext())
                .load(dog.getImagen())
                .into(holder.dogImage);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(dog);
                }
            }});
    }

    @Override
    public int getItemCount() {
        return dogs.size();
    }

    public void update(List<PlaceDto> dogs) {
        this.dogs = dogs;
        notifyDataSetChanged();
    }

    static class DogViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView breedName;
        ImageView dogImage;

        public DogViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            breedName = itemView.findViewById(R.id.breedName);
            dogImage = itemView.findViewById(R.id.dogImage);
        }

    }
}

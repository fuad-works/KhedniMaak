package com.pro.takemedrive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pro.takemedrive.Models.FullTrip;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolderIndex>{

    private Context context;
    private List<FullTrip> trips;
    private final OnItemClickListener listener;


    public TripAdapter(Context c, OnItemClickListener listener, List<FullTrip> items) {
        context = c;
        trips = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderIndex onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myViewInflater = LayoutInflater.from(context).inflate(R.layout.item_trip, parent, false);
        return new ViewHolderIndex(myViewInflater);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderIndex holder, int position) {
        FullTrip trip = trips.get(position);
        holder.tv_title.setText("من: " + trip.getStart_place() +" إلى: "+ trip.getEnd_place());
        holder.tv_car.setText(trip.getCar());
        holder.tv_user.setText(trip.getDriver());
        holder.tv_date.setText(trip.getThe_time() + " - " + trip.getThe_date());
        holder.tv_seats.setText(trip.getSeats() + "");
        holder.tv_regs.setText(trip.getRegs() + "");
        holder.bind(trips.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    public interface OnItemClickListener {
        void onItemClick(FullTrip item);
    }


    public class ViewHolderIndex extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_date;
        TextView tv_user;
        TextView tv_car;
        TextView tv_seats;
        TextView tv_regs;

        public ViewHolderIndex(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.ed_itm_title);
            tv_date = itemView.findViewById(R.id.ed_itm_date);
            tv_user = itemView.findViewById(R.id.ed_itm_user);
            tv_car = itemView.findViewById(R.id.ed_itme_car);
            tv_seats = itemView.findViewById(R.id.txt_all_count);
            tv_regs = itemView.findViewById(R.id.txt_reg_count);
        }

        public void bind(final FullTrip item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

}

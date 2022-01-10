package com.pro.takemedrive;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.pro.takemedrive.Models.FullTrip;
import com.pro.takemedrive.Models.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolderIndex>{

    private Context context;
    private List<User> users;
    private final UserAdapter.OnItemClickListener listener;


    public UserAdapter(Context c, UserAdapter.OnItemClickListener listener, List<User> items) {
        context = c;
        users = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolderIndex onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myViewInflater = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new UserAdapter.ViewHolderIndex(myViewInflater);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolderIndex holder, int position) {
        User user = users.get(position);
        holder.tv_title.setText(user.getUser_name());
        if(user.getUser_type() == 2)
            holder.tv_active.setText("مدير");
        else
            holder.tv_active.setText("مستخدم");

        String uri = "@drawable/ic_user_active";  // where myresource (without the extension) is the file
        if(user.getActive() != 1)
            uri = "@drawable/ic_user_block";

        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());

        Drawable res = context.getDrawable(imageResource);
        holder.iuser.setImageDrawable(res);

        holder.bind(users.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public interface OnItemClickListener {
        void onItemClick(User item);
    }


    public class ViewHolderIndex extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_active;
        ImageView iuser;

        public ViewHolderIndex(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.ed_itm_title);
            tv_active = itemView.findViewById(R.id.ed_itm_user);
            iuser = itemView.findViewById(R.id.img_user1);
        }

        public void bind(final User item, final UserAdapter.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

}

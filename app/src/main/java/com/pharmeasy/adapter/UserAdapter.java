package com.pharmeasy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pharmeasy.R;
import com.pharmeasy.model.UserData;
import com.pharmeasy.model.UserEntity;
import com.pharmeasy.ui.UserDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Rajeev Ranjan on 21/07/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserItem> implements Filterable {
    Context context;
    List<UserData> userDataList;
    List<UserData> userDataListFiltered;
    UserEntity userEntity;

    public UserAdapter(Context context, UserEntity userEntity) {
        this.context = context;
        this.userEntity = userEntity;
        userDataList = userEntity.getData();
        userDataListFiltered = userEntity.getData();
    }

    @Override
    public UserItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserAdapter.UserItem(itemView);


    }

    @Override
    public void onBindViewHolder(UserItem holder, int position) {

        if (holder instanceof UserItem) {
            final UserData userData = userDataListFiltered.get(position);
            holder.tvName.setText("" + userData.getFirst_name() + " " + userData.getLast_name());
            holder.tvAge.setText(" Age " + userData.getId() + " Years");
           /* Glide.with(context).load(userData.getAvatar())
                    .placeholder(R.drawable.loading)
                    .into(holder.ivUserPic);*/

            Glide.with(context).load(userData.getAvatar())
                    .thumbnail(Glide.with(context).load(R.drawable.loading))
                    .fitCenter()
                    .crossFade()
                    .into(holder.ivUserPic);
            holder.cvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, UserDetailActivity.class)
                            .putExtra("USER_DATA", userData));
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        if (userDataListFiltered != null)
            return userDataListFiltered.size();
        else
            return 0;
    }


    public class UserItem extends RecyclerView.ViewHolder {
        @BindView(R.id.ivUserPic)
        ImageView ivUserPic;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.cvItem)
        CardView cvItem;
        @BindView(R.id.tvAge)
        TextView tvAge;

        public UserItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    userDataListFiltered = userDataList;
                } else {
                    List<UserData> filteredList = new ArrayList<>();
                    for (UserData row : userDataList) {

                        if (row.getFirst_name().toLowerCase().contains(charString.toLowerCase())
                                || row.getLast_name().toLowerCase().contains(charString.toLowerCase())
                                || String.valueOf(row.getId()).contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    userDataListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = userDataListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                userDataListFiltered = (ArrayList<UserData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
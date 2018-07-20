package com.pharmeasy.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pharmeasy.R;
import com.pharmeasy.model.UserData;
import com.pharmeasy.model.UserEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Rajeev Ranjan on 21/07/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserItem> /*implements  Filterable*/ {
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
            final UserData userData = userEntity.getData().get(position);
            holder.tvName.setText("" + userData.getFirst_name() + " " + userData.getLast_name());
            Glide.with(context).load(userData.getAvatar())
                    .into(holder.ivUserPic);
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

        public UserItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
/*
    public void refreshAdapter(List<QuoteListEntity> list) {
        mQuoteList = list;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mQuoteListFiltered = mQuoteList;
                } else {
                    List<QuoteListEntity> filteredList = new ArrayList<>();
                    for (QuoteListEntity row : mQuoteList) {
                        BikeMasterEntity carMasterEntity = new BikeMasterEntity();
                        try {

                            carMasterEntity = new DBPersistanceController(mFrament.getActivity())
                                    .getBikeVarientDetails(
                                            "" + row.getMotorRequestEntity().getVehicle_id());

                        } catch (Exception e) {

                        }
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getMotorRequestEntity().getFirst_name().toLowerCase().contains(charString.toLowerCase())
                                || row.getMotorRequestEntity().getLast_name().toLowerCase().contains(charString.toLowerCase())
                                || String.valueOf(row.getMotorRequestEntity().getCrn()).contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                        if (carMasterEntity != null) {
                            if (carMasterEntity.getMake_Name().toLowerCase().contains(charString.toLowerCase())
                                    || carMasterEntity.getModel_Name().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row);
                            }
                        }
                    }

                    mQuoteListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mQuoteListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mQuoteListFiltered = (ArrayList<QuoteListEntity>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }*/
}
package edu.psu.swen888.contentproviderpractice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    private ArrayList<Contact> contactsList;

    public ContactAdapter(ArrayList<Contact> contactsList) {
        this.contactsList = contactsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView contactName;
        private TextView contactNumber;
        public MyViewHolder(final View view){
            super(view);
            contactName = view.findViewById(R.id.textview_contact_name);
            contactNumber = view.findViewById(R.id.textview_contact_phone);
        }
    }

    @NonNull
    @Override
    public ContactAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.MyViewHolder holder, int position) {
        holder.contactName.setText(contactsList.get(position).getContact());
        holder.contactNumber.setText(contactsList.get(position).getNumber());

    }

    @Override
    public int getItemCount() {
        if(contactsList != null) {
            return contactsList.size();
        }
        return 0;
    }

}

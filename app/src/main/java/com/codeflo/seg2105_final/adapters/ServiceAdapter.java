package com.codeflo.seg2105_final.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codeflo.seg2105_final.R;
import com.codeflo.seg2105_final.models.Service;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ServiceAdapter extends ArrayAdapter<Service> {

    private Context mContext;
    private ArrayList<Service> serviceList;
    private FirebaseFirestore database;

    ServiceAdapter(Context context, ArrayList<Service> serviceList){
        super(context, 0, serviceList);
        mContext = context;
        this.serviceList = serviceList;
        update();
    }


    void update(){
        database = FirebaseFirestore.getInstance();
        database.collection("Services").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot doc : task.getResult().getDocuments()){
                        serviceList.add(new Service(doc.getId(), (int) doc.get("rate"), null));
                    }
                }
            }
        });
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        if(listItem == null) listItem = inflater.inflate(R.layout.service_item, parent, false);

        Service current = serviceList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.name);
        name.setText(current.getName());

        TextView rate = (TextView) listItem.findViewById(R.id.rate);
        rate.setText(current.getRate());

        return listItem;
    }
}

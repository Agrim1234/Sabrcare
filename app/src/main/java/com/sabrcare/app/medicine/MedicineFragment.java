package com.sabrcare.app.medicine;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.Realm;
import io.realm.RealmResults;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionMenu;
import com.sabrcare.app.R;

import java.util.ArrayList;


public class MedicineFragment extends Fragment {

    public com.github.clans.fab.FloatingActionButton fabNewMed;
    public FloatingActionMenu materialDesignFAM;
    private Realm realm;
    MedicineAdapter medicineAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.medicine_rv);
        realm=Realm.getDefaultInstance();
        materialDesignFAM = view.findViewById(R.id.material_design_android_floating_action_menu);
        fabNewMed = view.findViewById(R.id.NewMedicineFAB);

        //TODO This data to be loaded from server.
        RealmResults<MedicineModel> medicineModels = realm.where(MedicineModel.class).findAll();
        ArrayList<MedicineModel> medicineModelArrayList = new ArrayList<>(medicineModels);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        medicineAdapter = new MedicineAdapter(medicineModelArrayList, getContext());
        recyclerView.setAdapter(medicineAdapter);

        fabNewMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newMed = new Intent(getActivity(), NewMedActivity.class);
                newMed.setAction("new");
                startActivity(newMed);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        medicineAdapter.notifyDataSetChanged();
    }
}

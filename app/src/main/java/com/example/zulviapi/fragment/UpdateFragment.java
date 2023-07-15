package com.example.zulviapi.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zulviapi.R;
import com.example.zulviapi.api.ApiClient;
import com.example.zulviapi.model.Pones;
import com.example.zulviapi.services.PoneService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateFragment extends Fragment {

    private EditText edtId;
    private EditText edtPhoneName;
    private EditText edtPrice;
    private PoneService poneService;
    private Button btnUpdate;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update,container,false);
        edtId = view.findViewById(R.id.edtUpdateId);
        edtPhoneName = view.findViewById(R.id.edtUpdateName);
        edtPrice = view.findViewById(R.id.edtUpdatePrice);
        poneService = ApiClient.getClient().create(PoneService.class);

        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePhone();
            }
        });

        return view;

    }

    private void updatePhone() {
        int phoneId = Integer.parseInt(edtId.getText().toString());
        String phoneName = edtPhoneName.getText().toString();
        int phonePrice = Integer.parseInt(edtPrice.getText().toString());

        Pones pones = new Pones();
        pones.setPhoneName(phoneName);
        pones.setPrice(phonePrice);
        Call<Pones> call = poneService.updatePonesById(phoneId,pones);
        call.enqueue(new Callback<Pones>() {
            @Override
            public void onResponse(Call<Pones> call, Response<Pones> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Berhasil Update Data", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Gagal Update Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pones> call, Throwable t) {
                Toast.makeText(getActivity(), "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
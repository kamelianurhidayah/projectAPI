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
import android.widget.TextView;

import com.example.zulviapi.R;
import com.example.zulviapi.api.ApiClient;
import com.example.zulviapi.model.Pones;
import com.example.zulviapi.services.PoneService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDataFragment extends Fragment {

    private TextView tvresult;
    private EditText edtID;
    private Button btnGet;

    public GetDataFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_get_data,container,false);
       tvresult = view.findViewById(R.id.tvGetData);
       edtID = view.findViewById(R.id.edtGetId);
       btnGet = view.findViewById(R.id.btnGetData);
       btnGet.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int poneId = Integer.parseInt(edtID.getText().toString());
                getData(poneId);
           }
       });

       return view;


    }

    private void getData(int id) {
        PoneService poneService = ApiClient.getClient().create(PoneService.class);
        Call<Pones> call = poneService.getPonesById(id);
        call.enqueue(new Callback<Pones>() {
            @Override
            public void onResponse(Call<Pones> call, Response<Pones> response) {
                if (response.isSuccessful()) {
                    Pones phone = response.body();
                    if (phone != null) {
                        StringBuilder result = new StringBuilder();
                        result.append("ID: ").append(phone.getId())
                                .append("\nPhone Name: ").append(phone.getPhoneName())
                                .append("\nPrice: ").append(phone.getPrice());
                        tvresult.setText(result.toString());
                    } else {
                        tvresult.setText("Phone not found");
                    }
                } else {
                    tvresult.setText("Failed to get phone. Error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Pones> call, Throwable t) {
                tvresult.setText("Failed to get phone. Error message: " + t.getMessage());
            }
        });

    }
}
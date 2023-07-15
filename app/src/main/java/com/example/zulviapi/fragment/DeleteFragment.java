package com.example.zulviapi.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.telephony.CellSignalStrength;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zulviapi.R;
import com.example.zulviapi.api.ApiClient;
import com.example.zulviapi.services.PoneService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DeleteFragment extends Fragment {

    private EditText edtId;
    private Button btnDelete;
    private PoneService poneService;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete,container,false);
        edtId = view.findViewById(R.id.edtDeleteId);
        poneService = ApiClient.getClient().create(PoneService.class);

        btnDelete = view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePhone();
            }
        });

        return view;
    }

    private void deletePhone() {
        int phoneId = Integer.parseInt(edtId.getText().toString());

        Call<Void> call = poneService.deletePones(phoneId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Berhasil Menghapus Data", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Gagal Menghapus Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getActivity(), "Error :  " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package com.example.zulviapi.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.zulviapi.fragment.DeleteFragment;
import com.example.zulviapi.fragment.GetDataFragment;
import com.example.zulviapi.fragment.PostFragment;
import com.example.zulviapi.fragment.UpdateFragment;

public class ViewAdapter extends FragmentStateAdapter {

    public ViewAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new GetDataFragment();
            case 1:
                return new PostFragment();
            case 2:
                return new UpdateFragment();
            case 3:
                return new DeleteFragment();
            default:
                return new GetDataFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}

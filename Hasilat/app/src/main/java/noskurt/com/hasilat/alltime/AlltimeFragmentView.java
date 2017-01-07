package noskurt.com.hasilat.alltime;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import noskurt.com.hasilat.R;

public class AlltimeFragmentView extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (container != null) container.removeAllViews();

        view = inflater.inflate(R.layout.alltime_layout, container, false);

        return view;

    }



}
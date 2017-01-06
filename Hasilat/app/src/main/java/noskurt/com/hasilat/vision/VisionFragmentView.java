package noskurt.com.hasilat.vision;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import noskurt.com.hasilat.R;

public class VisionFragmentView extends Fragment {

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container != null) container.removeAllViews();

        View view = inflater.inflate(R.layout.vision_layout, container, false);


        return view;
    }
}

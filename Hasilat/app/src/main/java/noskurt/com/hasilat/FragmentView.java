package noskurt.com.hasilat;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentView extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layout_id;

        try {
            layout_id = getArguments().getInt("idData");
        } catch (Exception e) {
            layout_id = R.layout.news_layout;
        }

        return inflater.inflate(layout_id, container, false);

    }
}
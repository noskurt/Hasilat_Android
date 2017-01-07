package noskurt.com.hasilat.alltime;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import noskurt.com.hasilat.R;

public class AlltimeMenu extends Fragment{

    private View view;

    private ListView listView;

    public static int index = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (container != null) container.removeAllViews();

        view = inflater.inflate(R.layout.alltime_menu, container, false);

        listView = (ListView) view.findViewById(R.id.alltimeListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                AlltimeSchema fragment = new AlltimeSchema();
                changeFragment(fragment);
            }
        });

        return view;

    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.alltimeFragment, fragment);
        fragmentTransaction.commit();
    }
}
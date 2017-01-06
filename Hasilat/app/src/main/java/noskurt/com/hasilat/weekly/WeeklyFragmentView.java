package noskurt.com.hasilat.weekly;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import noskurt.com.hasilat.R;

public class WeeklyFragmentView extends Fragment {

    private Spinner spinner;
    private TableLayout tableLayout;
    private ArrayList<String> rowList;
    private ArrayList<ArrayList<String>> list;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container != null) container.removeAllViews();

        view = inflater.inflate(R.layout.weekly_layout, container, false);

        tableLayout = (TableLayout) view.findViewById(R.id.table_layout);

        createList();

        for (int j = 0; j < list.size(); j++) {
            TableRow tableRow = new TableRow(view.getContext());

            for (int i = 0; i < rowList.size(); i++) {
                TextView textView = new TextView(view.getContext());

                textView.setText(rowList.get(i));
                if (j % 2 == 0) textView.setBackground(getResources().getDrawable(R.drawable.blue_cell_shape, null));
                else textView.setBackground(getResources().getDrawable(R.drawable.lightgrey_cell_shape, null));
                textView.setGravity(Gravity.CENTER);
                textView.setPadding(10, 10, 10, 10);

                tableRow.addView(textView);
            }
            tableLayout.addView(tableRow);
        }

        spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.years, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        return view;
    }

    private void createList() {
        rowList = new ArrayList<>();
        list = new ArrayList<>();

        rowList.add("1");
        rowList.add("Osman Pazarlama");
        rowList.add("Warner Bros.");
        rowList.add("19.02.2016");
        rowList.add("1.012.109");
        rowList.add("%51.0");
        rowList.add("1226");
        rowList.add("826");
        rowList.add("1.983.777");

        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);
        list.add(rowList);

    }
}
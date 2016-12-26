package noskurt.com.hasilat.weekly;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import noskurt.com.hasilat.R;

public class WeeklyFragmentView extends Fragment {

    private TableLayout tableLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container != null) container.removeAllViews();

        View view = inflater.inflate(R.layout.weekly_layout, container, false);

        tableLayout = (TableLayout) view.findViewById(R.id.table_layout);

        TableRow tableRow = new TableRow(view.getContext());

        TextView textView = new TextView(view.getContext());
        textView.setText("1");

        tableRow.addView(textView);
        tableLayout.addView(tableRow);

        return view;
    }
}

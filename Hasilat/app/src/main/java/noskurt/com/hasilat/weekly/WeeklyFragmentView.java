package noskurt.com.hasilat.weekly;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import noskurt.com.hasilat.R;
import noskurt.com.hasilat.news.RecyclerViewAdapter;
import noskurt.com.hasilat.ygznsl.HtmlTable;
import noskurt.com.hasilat.ygznsl.HtmlTableRow;
import noskurt.com.hasilat.ygznsl.Obtain;

public class WeeklyFragmentView extends Fragment {

    private Spinner spinner;
    private TableLayout tableLayout;
    private ProgressBar progressBar;

    private HtmlTable table;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (container != null) container.removeAllViews();

        view = inflater.inflate(R.layout.weekly_layout, container, false);

        tableLayout = (TableLayout) view.findViewById(R.id.table_layout);
        progressBar = (ProgressBar) view.findViewById(R.id.weeklyProgress);

        spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.years, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                for (int a = 1; a < tableLayout.getChildCount(); a++) {
                    View child = tableLayout.getChildAt(a);
                    if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
                }
                new DownloadTask().execute(adapterView.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        return view;
    }

    private class DownloadTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Integer doInBackground(String... strings) {
            Integer result = 0;

            try {
                String URL = String.format(getResources().getString(R.string.weekly_url), Integer.valueOf(strings[0]));
                table = Obtain.obtainTable(getResources().getString(R.string.weekly_table_selector), URL);
                result = 1;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            progressBar.setVisibility(View.INVISIBLE);

            if (result == 1) {
                for (int j = 0; j < table.size(); j++) {
                    TableRow tableRow = new TableRow(view.getContext());

                    for (int i = 0; i < table.get(j).size(); i++) {
                        TextView textView = new TextView(view.getContext());

                        textView.setText(table.get(j).get(i).text());
                        if (j % 2 == 0) textView.setBackground(getResources().getDrawable(R.drawable.blue_cell_shape, null));
                        else textView.setBackground(getResources().getDrawable(R.drawable.lightgrey_cell_shape, null));
                        textView.setGravity(Gravity.CENTER);
                        textView.setPadding(10, 10, 10, 10);

                        tableRow.addView(textView);
                    }
                    tableLayout.addView(tableRow);
                }
            } else {
                Toast.makeText(getContext(), "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
package noskurt.com.hasilat.alltime;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import noskurt.com.hasilat.R;
import noskurt.com.hasilat.ygznsl.HtmlTable;
import noskurt.com.hasilat.ygznsl.Obtain;

public class AlltimeSchema extends Fragment {

    private TableLayout tableLayout;
    private ProgressBar progressBar;
    private TextView textView;

    private HtmlTable table;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container != null) container.removeAllViews();

        view = inflater.inflate(R.layout.alltime_schema, container, false);

        tableLayout = (TableLayout) view.findViewById(R.id.alltimeTableLayout);
        progressBar = (ProgressBar) view.findViewById(R.id.alltimeProgress);
        textView = (TextView) view.findViewById(R.id.alltimeTitle);

        new DownloadTask().execute();
        textView.setText(getResources().getStringArray(R.array.alltime)[AlltimeMenu.index]);

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

            String URL = getResources().getString(R.string.all_time_all_movies_url);
            String selector = getResources().getString(R.string.all_time_all_movies_table_selector);

            try {
                switch (AlltimeMenu.index) {
                    case 0:
                        URL = getResources().getString(R.string.all_time_all_movies_url);
                        break;
                    case 1:
                        URL = getResources().getString(R.string.all_time_turkish_movies_url);
                        break;
                    case 2:
                        URL = getResources().getString(R.string.audience_record_genre_action_url);
                        break;
                    case 3:
                        URL = getResources().getString(R.string.audience_record_genre_romance_url);
                        break;
                    case 4:
                        URL = getResources().getString(R.string.audience_record_genre_scifi_url);
                        break;
                    case 5:
                        URL = getResources().getString(R.string.audience_record_genre_cartoon_url);
                        break;
                    case 6:
                        URL = getResources().getString(R.string.audience_record_genre_drama_url);
                        break;
                    case 7:
                        URL = getResources().getString(R.string.audience_record_genre_thriller_url);
                        break;
                    case 8:
                        URL = getResources().getString(R.string.audience_record_genre_comedy_url);
                        break;
                    case 9:
                        URL = getResources().getString(R.string.audience_record_genre_horror_url);
                        break;
                    case 10:
                        URL = getResources().getString(R.string.audience_record_genre_adventure_url);
                        break;
                    case 11:
                        URL = getResources().getString(R.string.audience_record_genre_romance_comedy_url);
                        break;
                }
                table = Obtain.obtainTable(selector, URL);
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
                        if (j % 2 == 0)
                            textView.setBackground(getResources().getDrawable(R.drawable.blue_cell_shape, null));
                        else
                            textView.setBackground(getResources().getDrawable(R.drawable.lightgrey_cell_shape, null));
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
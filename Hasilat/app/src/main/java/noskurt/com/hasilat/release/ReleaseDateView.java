package noskurt.com.hasilat.release;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import noskurt.com.hasilat.R;
import noskurt.com.hasilat.ygznsl.Month;
import noskurt.com.hasilat.ygznsl.Movie;
import noskurt.com.hasilat.ygznsl.ReleaseDate;
import noskurt.com.hasilat.ygznsl.ReleaseWeek;

public class ReleaseDateView extends Fragment {

    private Context context;
    private ListView lstMovies;
    private ProgressBar progressBar;
    private int year = 0, month = 0;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container != null) container.removeAllViews();
        context = container.getContext();

        final View view = inflater.inflate(R.layout.release_date_layout, container, false);

        final Spinner spinnerYear = (Spinner) view.findViewById(R.id.spinnerYear);
        final Spinner spinnerMonth = (Spinner) view.findViewById(R.id.spinnerMonth);
        lstMovies = (ListView) view.findViewById(R.id.lstMovies);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        final LinkedList<String> years = new LinkedList<>(Arrays.asList("Yıl seçiniz"));
        for (int i = Calendar.getInstance().get(Calendar.YEAR) + 2; i > 2004 ; i--) years.add("" + i);
        spinnerYear.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, years));

        final LinkedList<String> months = new LinkedList<>(Arrays.asList("Ay seçiniz"));
        for (Month month : Month.values()) months.add(month.getName());
        spinnerMonth.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, months));

        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                year = (i == 0) ? 0 : Integer.parseInt(years.get(i));
                reloadListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                month = i;
                reloadListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    private void reloadListView(){
        if (year == 0 || month == 0) {
            lstMovies.setAdapter(ReleaseDateListItemAdapter.empty(context));
        } else {
            final String url = String.format(context.getResources().getString(R.string.release_date_url), year, month);

            final ReleaseDateUpdate rdu = new ReleaseDateUpdate() {
                @Override
                public void update(ReleaseDate rd) {
                    final ReleaseDate rd2 = rd;
                    ReleaseDateView.this.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (rd2 == null){
                                lstMovies.setAdapter(ReleaseDateListItemAdapter.empty(context));
                            } else {
                                final LinkedList<Movie> movies = new LinkedList<>();
                                for (ReleaseWeek rw : rd2.getWeeks()){
                                    for (Movie m : rw.getMovies()){
                                        movies.add(m);
                                    }
                                }
                                lstMovies.setAdapter(new ReleaseDateListItemAdapter(context, 0, movies));
                            }
                        }
                    });
                }
            };

            final DownloadRelaseDateInfoTask task = new DownloadRelaseDateInfoTask(context, year, month, rdu);
            task.setProgressBar(progressBar);
            task.execute(url);
        }
    }

}

package noskurt.com.hasilat.release;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ProgressBar;
import java.io.IOException;
import noskurt.com.hasilat.ygznsl.ReleaseDate;

public class DownloadRelaseDateInfoTask extends AsyncTask<String, Void, ReleaseDate> {

    private final ReleaseDateUpdate update;
    private ProgressBar progressBar = null;
    private final Context context;
    private final int year, month;

    public DownloadRelaseDateInfoTask(Context context, int year, int month, ReleaseDateUpdate update){
        this.context = context;
        this.year = year;
        this.month = month;
        this.update = update;
    }

    @Override
    protected void onPreExecute() {
        if (progressBar != null) progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected ReleaseDate doInBackground(String... strings) {
        ReleaseDate rd = null;
        try {
            rd = new ReleaseDate(context, year, month);
        } catch (IOException ioEx){
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context);
            dlgAlert.setMessage(ioEx.getMessage());
            dlgAlert.setTitle("Hata");
            dlgAlert.setPositiveButton("Tamam", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
        return rd;
    }

    @Override
    protected void onPostExecute(ReleaseDate result) {
        update.update(result);
        if (progressBar != null) progressBar.setVisibility(View.GONE);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public ReleaseDateUpdate getUpdateMethod() {
        return update;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

}

package noskurt.com.hasilat.ygznsl.release;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import noskurt.com.hasilat.R;
import noskurt.com.hasilat.ygznsl.Movie;

public class ReleaseDateListItemAdapter extends ArrayAdapter<Movie> {

    private final Context context;
    private final List<Movie> movies;

    public ReleaseDateListItemAdapter(Context context, int resource, List<Movie> movies) {
        super(context, R.layout.release_date_list_item_layout, movies);
        this.context = context;
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        final View row = inflater.inflate(R.layout.release_date_list_item_layout, null);
        final Movie movie = movies.get(position);

        final TextView txtReleaseDate = (TextView) row.findViewById(R.id.txtReleaseDate);
        final TextView txtName = (TextView) row.findViewById(R.id.txtName);
        final TextView txtGenre = (TextView) row.findViewById(R.id.txtGenre);
        final ImageView imgMovie = (ImageView) row.findViewById(R.id.imgMovie);

        final SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy, EEEE", Locale.forLanguageTag("tr-TR"));
        txtReleaseDate.setText(sdf.format(movie.getReleaseDate().getTime()));
        txtName.setText(movie.getName());
        txtGenre.setText(movie.getGenre());
        Picasso.with(context).load(movie.getImgUrl()).error(R.drawable.placeholder).placeholder(R.drawable.placeholder).into(imgMovie);

        return row;
    }

    public static ReleaseDateListItemAdapter empty(Context c) {
        return new ReleaseDateListItemAdapter(c, 0, new LinkedList<Movie>());
    }
}
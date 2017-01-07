package noskurt.com.hasilat.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import noskurt.com.hasilat.R;

public class NewsOpen extends AppCompatActivity {

    private TextView header;
    private ImageView imageView;
    private TextView content;
    private FeedItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_click_layout);

        header = (TextView) findViewById(R.id.newsHeader);
        imageView = (ImageView) findViewById(R.id.newsImage);
        content = (TextView) findViewById(R.id.newsContent);

        Intent intent = getIntent();
        item = (FeedItem) intent.getSerializableExtra("DATA");

        header.setText(item.getTitle());
        content.setText(item.getContent());

        Picasso.with(this).load(item.getThumbnail())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(imageView);

    }

}

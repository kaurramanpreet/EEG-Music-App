package com.example.youtbe;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.example.youtbe.data.model.Post;
import com.example.youtbe.data.remote.APIService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class list_of_songs extends AppCompatActivity {

    public static final  String EXTRA_MOOD="com.example.lock_unlock.MainActivity2.EXTRA_MOOD";
    public static final  String EXTRA_SONG="com.example.lock_unlock.MainActivity2.EXTRA_SONG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_songs);
        final ListView list = findViewById(R.id.list);
        String age,lang,genre;
        final Intent intent=getIntent();
        age=intent.getStringExtra(choose.EXTRA_AGE);
        genre=intent.getStringExtra(choose.EXTRA_GENRE);
        lang=intent.getStringExtra(choose.EXTRA_LANG);


        if(isNetworkAvailable())
        {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.4:5000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            APIService jsonPlaceholderApi = retrofit.create(APIService.class);
            Call<List<Post>> call = jsonPlaceholderApi.getPost(age,lang,genre);
            call.enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    if (!response.isSuccessful()) {
                        System.out.println("data not received succ");
                        return;
                    }
                    System.out.println("data rec succ:");
                    final List<Post> posts = response.body();
                    List<Map<String,String>> data= new ArrayList<>();
                    for (Post song : posts) {
                        Map<String, String> datum = new HashMap<String, String>(2);
                        System.out.println(song.getArtist()+" title: "+song.getTitle());
                        datum.put("First Line", song.getTitle());
                        datum.put("Second Line",song.getArtist());
                        data.add(datum);
                    }
                    SimpleAdapter adapter = new SimpleAdapter(list_of_songs.this, data,
                            R.layout.two_line,
                            new String[] {"First Line", "Second Line" },
                            new int[] {R.id.text1, R.id.text2 });
                    list.setAdapter(adapter);

                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(getApplicationContext(),single_song.class);
                            intent.putExtra(EXTRA_SONG, posts.get(position).getSong());
                            intent.putExtra(EXTRA_MOOD,posts.get(position).getMood());
                            Toast.makeText(list_of_songs.this,posts.get(position).getSong(),Toast.LENGTH_LONG).show();
                            startActivity(intent);

                        }
                    });

                }
                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {
                    System.out.println("not connected ");
                }
            });
        }
        else
        {
            Toast.makeText(list_of_songs.this,"You are not connected to internet!",Toast.LENGTH_SHORT).show();
        }

    }
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
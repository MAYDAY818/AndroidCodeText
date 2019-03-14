package cn.edu.hebtu.software.jsondemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Book> bookList=new ArrayList<Book>();
    private int a=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.btn1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LoginTask loginTask=new LoginTask();
                //loginTask.execute();
               BookListTask bookListTask=new BookListTask();

               bookListTask.execute();
               Log.e("msg",a+"");


            }
        });
    }

    public class BookListTask extends AsyncTask{
        private List<Book> books = new ArrayList<>();
        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                a++;
                URL url = new URL("http://10.7.89.249:8080/demomysql/Demo01");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("contentType","UTF-8");
                InputStream is = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String res = reader.readLine();
                //解析Json格式
                JSONArray array = new JSONArray(res);
                for (int i = 0; i < array.length(); i++){
                    JSONObject object = array.getJSONObject(i);
                    Book book = new Book();
                    book.setId(object.getInt("id"));
                    book.setName(object.getString("name"));
                    book.setAuthor(object.getString("password"));
                    books.add(book);
                }
                Log.e("msg","111111111111111111111111111111111111");
                Log.e("msg",books.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}

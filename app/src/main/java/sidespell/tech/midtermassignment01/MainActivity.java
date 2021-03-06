package sidespell.tech.midtermassignment01;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {


    String[] countries = new String[] {
            "India",
            "Pakistan",
            "Sri Lanka",
            "China",
            "Bangladesh",
            "Nepal",
            "Afghanistan",
            "North Korea",
            "South Korea",
            "Japan"
    };

    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] flags = new int[]{
            R.drawable.india,
            R.drawable.pakistan,
            R.drawable.srilanka,
            R.drawable.china,
            R.drawable.bangladesh,
            R.drawable.nepal,
            R.drawable.afghanistan,
            R.drawable.nkorea,
            R.drawable.skorea,
            R.drawable.japan
    };

    // Array of strings to store currencies
    String[] currency = new String[]{
            "Indian Rupee",
            "Pakistani Rupee",
            "Sri Lankan Rupee",
            "Renminbi",
            "Bangladeshi Taka",
            "Nepalese Rupee",
            "Afghani",
            "North Korean Won",
            "South Korean Won",
            "Japanese Yen"
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<10;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", countries[i]);
            hm.put("flag", Integer.toString(flags[i]) );
            hm.put("cur", currency[i]);
            aList.add(hm);

    }
        String[] from = { "flag","txt"};

        int[] to = { R.id.flag,R.id.txt};

        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.autocomplete_layout, from, to);

        AutoCompleteTextView autoComplete = ( AutoCompleteTextView) findViewById(R.id.autocomplete);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {

                HashMap<String, String> hm = (HashMap<String, String>) arg0.getAdapter().getItem(position);

                TextView tvCurrency = (TextView) findViewById(R.id.tv_currency) ;

                tvCurrency.setText("Currency : " + hm.get("cur"));
            }
        };
        autoComplete.setOnItemClickListener(itemClickListener);

        autoComplete.setAdapter(adapter);

    }

    protected void onSaveInstanceState(Bundle outState) {
        TextView tvCurrency = (TextView) findViewById(R.id.tv_currency) ;
        outState.putString("currency", tvCurrency.getText().toString());
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        TextView tvCurrency = (TextView) findViewById(R.id.tv_currency) ;
        tvCurrency.setText(savedInstanceState.getString("currency"));
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}

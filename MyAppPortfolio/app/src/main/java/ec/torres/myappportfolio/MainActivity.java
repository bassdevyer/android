package ec.torres.myappportfolio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickButton(View view){
        Toast toast;
        int duration = Toast.LENGTH_SHORT;
        String text = "This button will launch my";
        if(view != null){
            switch (view.getId()){
                case R.id.btn_spotify_streamer:
                    text += " spotify streamer!";
                    break;
                case R.id.btn_scores_app:
                    text += " scores app!";
                    break;
                case R.id.btn_library_app:
                    text += " library app!";
                    break;
                case R.id.btn_build_it_bigger:
                    text += " build it bigger app!";
                    break;
                case R.id.btn_xyz_reader:
                    text += " xyz reader app!";
                    break;
                case R.id.btn_capstone_my_own_app:
                    text += " capstone app!";
                    break;
                default:
                    text = "";
                    break;
            }
            if(!text.isEmpty()){
                toast = Toast.makeText(this, text, duration);
                toast.show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

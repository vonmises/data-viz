package ml.dev2dev.droiddata;

/**
 * Created by benson on 11/6/15.
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by benson on 10/13/15.
 */
public class Myintro extends AppIntro {

    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(AppIntroFragment.newInstance("Droid Data", "Droid data is a data visualization tool that transforms quantitative" +
                        "data to maps",
                R.drawable.splash,
                Color.parseColor("#7CA34C")));


        setBarColor(Color.parseColor("#9BCC5F"));
        setSeparatorColor(Color.parseColor("#9BCC5F"));
        showSkipButton(false);

//        setVibrate(true);
//        setVibrateIntensity(30);
    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, Auth.class);
        startActivity(intent);
    }

    @Override
    public void onSkipPressed() {
//        Toast.makeText(getApplicationContext(),"You Skipped",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDonePressed() {
//        Toast.makeText(getApplicationContext(),"You Done",Toast.LENGTH_SHORT).show();


        Intent a =  new Intent(Myintro.this, Auth.class);
        startActivity(a);
        finish();

    }

}

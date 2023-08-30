package design.design;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    private final ArrayList<Profile> PROFILES = new ArrayList<Profile>() {{
        add(new Profile("@drawable/background", "Model winner of 2009 Pandora Art costume design week", "Neytiri", Online.JUST_NOW, 2789, 798, 1748, 50));
        add(new Profile("@drawable/background11", "Model winner of 2022 Pandora Art costume design week", "Jake", Online.LAST_WEEK, 1389, 898, 948, 30));
        add(new Profile("@drawable/background12", "Geralt The Witcher\nGame design week", "Geralt", Online.JUST_NOW, 2754, 341, 1234, 75));
        add(new Profile("@drawable/background7", "Harry Potter character\nPandora Art costume design week", "Germiona", Online.LAST_MONTH, 3698, 945, 2347, 23));
        add(new Profile("@drawable/background5", "Cosplay winner", "Dead Pool", Online.JUST_NOW, 7845, 352, 5674, 61));
        add(new Profile("@drawable/background8", "Fast fourth race winner", "Gal Gadot", Online.LAST_WEEK, 4567, 165, 3647, 87));
        add(new Profile("@drawable/background9", "Trailblazer Award of 2015 Outstanding Nomination", "Scarlett Johansson", Online.LAST_MONTH, 3240, 543, 2357, 43));
        add(new Profile("@drawable/background4", "Exemplary Performance Recognized Nominee", "Tom Hardy", Online.LAST_WEEK, 4987, 156, 4678, 97));
        add(new Profile("@drawable/background2", "Distinguished Honor Prominent Selection", "Chris Evans", Online.JUST_NOW, 3541, 765, 3120, 78));
        add(new Profile("@drawable/background6", "Exceptional Accomplishment Nominated Star", "Jennifer Lawrence", Online.LAST_MONTH, 9741, 498, 9364, 35));
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            redraw();
                        }
                    });
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    private int num = 0;

    public void redraw() {
        Profile profile = PROFILES.get(num);
        ImageView photo = findViewById(R.id.photo);
        ImageView avatar = findViewById(R.id.avatar);
        TextView description = findViewById(R.id.description);
        TextView name = findViewById(R.id.name);
        TextView online = findViewById(R.id.online);
        TextView views = findViewById(R.id.views);
        TextView comments = findViewById(R.id.comments);
        TextView likes = findViewById(R.id.likes);

        int id = getResources().getIdentifier(profile.getImagePath(), "drawable", getPackageName());
        photo.setImageResource(id);
        avatar.setImageResource(id);

        description.setText(profile.getDescription());
        name.setText(profile.getName());
        online.setText(profile.getLastOnline().getOnline());
        views.setText(String.valueOf(profile.getCountViews()));
        comments.setText(String.valueOf(profile.getCountComments()));
        likes.setText(String.valueOf(profile.getCountLikes()));

        num++;
        if (num == PROFILES.size())
            num = 0;
    }
}
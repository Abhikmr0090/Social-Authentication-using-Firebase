package com.example.abhikmr.socialauthentication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class DashBoardActivity extends AppCompatActivity {
    private Button fbLogout;
    private ImageView profile_pic;
    private TextView name, lastName, print_email;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAuth = FirebaseAuth.getInstance();

        profile_pic = findViewById(R.id.profile_pic);
        name = findViewById(R.id.print_first_name);
        print_email = findViewById(R.id.print_email);
        fbLogout = findViewById(R.id.fb_logout);


        final FirebaseUser mUser = mAuth.getCurrentUser();

        // checks if user has already Logged in into the App or not
        if(mUser != null)
        {
            String first_name = mUser.getDisplayName();
            String pic_url = mUser.getPhotoUrl().toString();
            String email = mUser.getEmail();

            Glide.with(this).load(pic_url).into(profile_pic);
            name.setText(first_name);
            print_email.setText(email);
        }

        // Facebook OnClick Listener to Log Out from Facebook
        fbLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                LoginManager.getInstance().logOut();
                LoginActivity.mProgressBar.setVisibility(View.INVISIBLE);
                finish();
            }
        });

    }
}



package com.example.hired;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Method;

/**
 * This class gets a logged in company user's data from the Realtime Database and
 * displays their information on their profile page.
 */
public class CompanyProfile extends AppCompatActivity {
    private Button edit;
    private DatabaseReference ref;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private String userID;
    private String videosURL;
    private WebView mWebView;
    private boolean mIsPaused;
    private Company company;

    /**
     * Sets the content view of the activity_company_profile.xml page. Initializes an instance of the Firebase Auth and initializes the
     * FirebaseUser, user, as the current logged in user from the instance of the Firebase. Adds a ValueEventListener on a FirebaseDatabase
     * reference, ref, to get a DataSnapshot of the database. From the DataSnapshot, a for each loop is run to get the Company object stored
     * in every node of the database. Sets all of the activity_company_profile.xml TextView components to display the stored data in each Company
     * object.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_profile);

        videosURL = new String();

        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        // start *advertising for employees
        ref = FirebaseDatabase.getInstance().getReference(userID);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snappy: snapshot.getChildren()){
                    company = snappy.getValue(Company.class);
                    Log.d("CompanyProfile", "In the updateCompanyTest method");

                    TextView companyNameHeader = findViewById(R.id.companyNameHeader);
                    TextView companyEmail = findViewById(R.id.companyEmailHeader);
                    TextView companyLocation = findViewById(R.id.companyLocationHeader);
                    TextView companyIndustry= findViewById(R.id.companyIndustryHeader);
                    TextView ageMin= findViewById(R.id.companyAge);
                    TextView expReq= findViewById(R.id.companyExp);
                    TextView cert= findViewById(R.id.companyCert);

                    companyNameHeader.setText(company.getName());
                    companyEmail.setText(company.getEmail());
                    companyLocation.setText(company.getLocation().toString());
                    companyIndustry.setText(company.getCompanyType());
                    ageMin.setText(company.getAgeMinimum());
                    expReq.setText(company.getExperienceReq());
                    cert.setText(company.getCertification());
                    Log.d("testing","company: " + company.getUrl());
                    Log.d("testing","company: " + company.getName());
                    videosURL = "youtu.be/" + company.getUrl();
                }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


       final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 3s = 3000ms
                mWebView = (WebView) findViewById(R.id.companyProfileVideos);

                final WebView webview = (WebView) findViewById(R.id.companyProfileVideos);
                webview.setWebViewClient(new WebViewClient() {

                    @Override
                    public void onReceivedError (WebView view,int errorCode, String description, String
                            failingUrl){
                        Toast.makeText(CompanyProfile.this, description, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError er){
                        handler.proceed(); // Ignore SSL certificate errors
                    }
                });

                Log.d("testing","url: " + videosURL);

                WebSettings websetting = webview.getSettings();
                websetting.setJavaScriptEnabled(true);
                websetting.setDomStorageEnabled(true);
                mIsPaused = true;
                resumeBrowser();
                webview.loadUrl(videosURL);

            }


        }, 3000);




        /**
         * An OnClickListener to start the CompanySurveyPage whenever the "Edit" button is clicked on the activity_company_profile page.
         */
        edit = (Button) findViewById(R.id.comp_survey_button);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CompanySurveyPage.class);
                startActivity(intent);
            }});
    }

    /**
     * Signs the logged in company user out of their account and starts the MainActivity.
     * @param v
     */
    public void signOut(View v){
        auth.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Pauses the screen
     */
    @Override
    protected void onPause()
    {
        pauseBrowser();
        super.onPause();
    }


    /**
     * Resumes the screen
     */
    @Override
    protected void onResume()
    {
        resumeBrowser();
        super.onResume();
    }

    /**
     * Hidden method to pause webpage
     */
    private void pauseBrowser()
    {
        if (!mIsPaused)
        {
            // pause flash and javascript etc
            callHiddenWebViewMethod(mWebView, "onPause");
            mWebView.pauseTimers();
            mIsPaused = true;
        }
    }

    /**
     * Hidden method to resume webpage
     */
    private void resumeBrowser()
    {
        if (mIsPaused)
        {
            //resume flash and javascript etc
            callHiddenWebViewMethod(mWebView, "onResume");
            mWebView.resumeTimers();
            mIsPaused = false;
        }
    }

    /**
     * Calls hidden method
     * @param wv webview
     * @param name name of method call
     */
    private void callHiddenWebViewMethod(final WebView wv, final String name)
    {
        try
        {
            final Method method = WebView.class.getMethod(name);
            method.invoke(mWebView);
        } catch (final Exception e)
        {}
    }
}

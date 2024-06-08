package es.ulpgc.eite.da.hello_bye.bye;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.hello_bye.R;


public class ByeActivity
    extends AppCompatActivity implements ByeContract.View {

    public static String TAG = "HelloBye.ByeActivity";

    ByeContract.Presenter presenter;

    Button sayByeButton, goHelloButton;
    TextView byeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bye);

        setTitle(R.string.bye_screen_title);

        sayByeButton = findViewById(R.id.sayByeButton);
        goHelloButton = findViewById(R.id.goHelloButton);
        byeMessage = findViewById(R.id.byeMessage);

        sayByeButton.setText(getSayByeButtonLabel());
        goHelloButton.setText(getGoHelloButtonLabel());

        sayByeButton.setOnClickListener(v -> presenter.sayByeButtonClicked());
        goHelloButton.setOnClickListener(v -> presenter.goHelloButtonClicked());

        // do the setup
        ByeScreen.configure(this);

        // do some work
        if (savedInstanceState == null) {
            presenter.onCreateCalled();

        } else {
            presenter.onRecreateCalled();

        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        // do some work
        presenter.onResumeCalled();
    }


    @Override
    protected void onPause() {
        super.onPause();

        // do some work
        presenter.onPauseCalled();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.onBackButtonPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // do some work
        presenter.onDestroyCalled();
    }

    /*
    @Override
    public void displayByeData(ByeViewModel viewModel) {
        Log.e(TAG, "displayByeData");

        // deal with the data
        byeMessage.setText(viewModel.byeMessage);

    }
    */

    @Override
    public void displayByeData(ByeState viewModel) {
        Log.e(TAG, "displayByeData");

        // deal with the data
        byeMessage.setText(viewModel.byeMessage);

    }

    @Override
    public void finishView() {
        finish();
    }

    private String getGoHelloButtonLabel() {
        return getResources().getString(R.string.go_hello_button_label);
    }

    private String getSayByeButtonLabel() {
        return getResources().getString(R.string.say_bye_button_label);
    }


    @Override
    public void injectPresenter(ByeContract.Presenter presenter) {
        this.presenter = presenter;
    }
}

package es.ulpgc.eite.da.hello_bye.hello;

import java.lang.ref.WeakReference;

public interface HelloContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayHelloData(HelloViewModel viewModel);

        void navigateToByeScreen();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResumeCalled();

        void sayHelloButtonClicked();

        void goByeButtonClicked();

        void onPauseCalled();

        void onRecreateCalled();

        void onCreateCalled();

        void onDestroyCalled();
    }

    interface Model {

        String getHelloMessage();
    }
}
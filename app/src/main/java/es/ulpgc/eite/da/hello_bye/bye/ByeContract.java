package es.ulpgc.eite.da.hello_bye.bye;

import java.lang.ref.WeakReference;


public interface ByeContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayByeData(ByeViewModel viewModel);
        //void displayByeData(ByeState viewModel);

        void finishView();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResumeCalled();

        void sayByeButtonClicked();

        void goHelloButtonClicked();

        void onPauseCalled();

        void onBackButtonPressed();

        void onCreateCalled();

        void onRecreateCalled();

        void onDestroyCalled();
    }

    interface Model {

        String getByeMessage();
    }

}
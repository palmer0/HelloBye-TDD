package es.ulpgc.eite.da.hello_bye.hello;


import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.hello_bye.R;
import es.ulpgc.eite.da.hello_bye.app.AppMediator;


public class HelloScreen {

    public static void configure(HelloContract.View view) {

        WeakReference<FragmentActivity> context =
            new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = AppMediator.getInstance();
        HelloContract.Presenter presenter = new HelloPresenter(mediator);

        String message = context.get().getString(R.string.hello_message);
        HelloContract.Model model = new HelloModel(message);

        presenter.injectView(new WeakReference<>(view));
        presenter.injectModel(model);
        view.injectPresenter(presenter);

    }

}

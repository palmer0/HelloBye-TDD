package es.ulpgc.eite.da.hello_bye.bye;


import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.hello_bye.R;
import es.ulpgc.eite.da.hello_bye.app.AppMediator;


public class ByeScreen {

    public static void configure(ByeContract.View view) {

        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = AppMediator.getInstance();
        ByeContract.Presenter presenter = new ByePresenter(mediator);

        String message = context.get().getString(R.string.bye_message);
        ByeContract.Model model = new ByeModel(message);

        presenter.injectView(new WeakReference<>(view));
        presenter.injectModel(model);
        view.injectPresenter(presenter);

    }

}

package ml.dev2dev.droiddata;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GenderInequalityFragment extends Fragment {

    public GenderInequalityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gender_inequality_fragment, container, false);
        return view;
    }
}

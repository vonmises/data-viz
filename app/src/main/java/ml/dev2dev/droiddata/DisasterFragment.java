package ml.dev2dev.droiddata;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class DisasterFragment extends Fragment {
    int color;

    public DisasterFragment() {
    }

    @SuppressLint("ValidFragment")
    public DisasterFragment(int color) {
        this.color = color;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.disaster_fragment, container, false);

        final LinearLayout frameLayout = (LinearLayout) view.findViewById(R.id.disaster_frag);
        frameLayout.setBackgroundColor(color);

        return view;
    }
}

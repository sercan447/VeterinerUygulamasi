package sercandevops.com.veterineruygulamasi.Utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;


import sercandevops.com.veterineruygulamasi.R;
public class ChangeFragments {

    private Context context;

    public ChangeFragments(Context context)
    {
        this.context = context;
    }


    public void change(Fragment fragment)
    {
        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFramelayout,fragment,"fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}

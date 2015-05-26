package coursera.examples.modernartui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private DialogFragment mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List tvs = new ArrayList();
        tvs.add((TextView) findViewById(R.id.tv1));
        tvs.add((TextView) findViewById(R.id.tv2));
        tvs.add((TextView) findViewById(R.id.tv3));
        tvs.add((TextView) findViewById(R.id.tv4));
        tvs.add((TextView) findViewById(R.id.tv5));


        SeekBar colorControl = (SeekBar) findViewById(R.id.seekBar);
        colorControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                for(Object object : tvs){
                    TextView textView = (TextView) object;
                    ColorDrawable cd = (ColorDrawable) textView.getBackground();
                    int colorCode = cd.getColor();
                    if(colorCode != -1)
                        textView.setBackgroundColor(colorCode + progress * 100);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.more_info) {
            mDialog = AlertDialogFragment.newInstance();

            // Show AlertDialogFragment
            mDialog.show(getFragmentManager(), "Alert");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public static class AlertDialogFragment extends DialogFragment{
        public static AlertDialogFragment newInstance() {
            return new AlertDialogFragment();
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            String about = getResources().getString(R.string.about);
            String learnMore = getResources().getString(R.string.learn_more);
            return new AlertDialog.Builder(getActivity())
                    .setMessage(about + "\n" + "\n" + learnMore)

                            // User cannot dismiss dialog by hitting back button
                    .setCancelable(false)

                            // Set up No Button
                    .setNegativeButton("Not Now",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.dismiss();
                                }
                            })

                            // Set up Yes Button
                    .setPositiveButton("Visit MOMA",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        final DialogInterface dialog, int id) {
                                    Intent intent = new Intent("android.intent.action.VIEW",
                                            Uri.parse("http://www.moma.org"));
                                    startActivity(intent);
                                }
                            }).create();
        }
    }
}

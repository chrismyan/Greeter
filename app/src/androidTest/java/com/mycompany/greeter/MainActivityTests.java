package com.mycompany.greeter;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by Chris on 2/28/2016.
 */
public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {
    public MainActivityTests() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }

    public void testGreet() {
        MainActivity activity = getActivity();

        final EditText nameEditText =
                (EditText) activity.findViewById(R.id.greet_edit_text);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nameEditText.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("Jake");

        Button greetButton =
                (Button) activity.findViewById(R.id.greet_button);

        TouchUtils.clickView(this, greetButton);

        TextView greetMessage =
                (TextView) activity.findViewById(R.id.message_text_view);

        String actualText = greetMessage.getText().toString();
        assertEquals("Hello, Jake!", actualText);
    }

    public void testReverseButtonDisabled() {
        MainActivity activity = getActivity();

        Button reverse_button = (Button) activity.findViewById(R.id.reverse_button);
        boolean button_enabled = reverse_button.isEnabled();
        assertEquals(false, button_enabled);
    }
}

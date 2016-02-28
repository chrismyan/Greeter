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
    Button greetButton, reverseButton;
    TextView greetMessage;
    MainActivity activity;

    public MainActivityTests() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }

    public void testGreet() {
        this.setUp();

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

        TouchUtils.clickView(this, this.greetButton);

        String actualText = this.greetMessage.getText().toString();
        assertEquals("Hello, Jake!", actualText);
    }

    public void testReverseButtonDisabled() {
        this.setUp();
        assertFalse(this.reverseButton.isEnabled());
    }

    public void testReverseButtonEnabled() {
        this.setUp();
        TouchUtils.clickView(this, this.greetButton);
        boolean button_enabled = reverseButton.isEnabled();
        assertTrue(button_enabled);
    }

    public void testReverseButtonPressed() {
        this.setUp();

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

        TouchUtils.clickView(this, this.greetButton);

        TouchUtils.clickView(this, this.reverseButton);

        String actualText = this.greetMessage.getText().toString();
        assertEquals("!ekaJ ,olleH", actualText);
    }

    public void setUp() {
        this.activity = getActivity();
        this.reverseButton =
                (Button) activity.findViewById(R.id.reverse_button);
        this.greetButton =
                (Button) activity.findViewById(R.id.greet_button);
        this.greetMessage =
                (TextView) activity.findViewById(R.id.message_text_view);
    }
}

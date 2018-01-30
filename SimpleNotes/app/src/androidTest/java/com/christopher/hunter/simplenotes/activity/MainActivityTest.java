package com.christopher.hunter.simplenotes.activity;

import android.app.Instrumentation.ActivityMonitor;
import android.support.design.widget.FloatingActionButton;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.christopher.hunter.simplenotes.R;
import com.christopher.hunter.simplenotes.ui.main.MainActivity;
import com.christopher.hunter.simplenotes.ui.note.add.NoteAddActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

// TODO Create tests for opening edit screen and deleting notes. Add note for these tests to initialize()

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity activity;

    @Before
    public void initialize() {
        activity = rule.getActivity();
    }

    /*
        Check that the recycler view that holds notes exists on the screen
     */
    @Test
    public void checkRecyclerViewIsPresent() throws Exception {
        View viewById = activity.findViewById(R.id.recycler_view);
        assertThat(viewById, notNullValue());
        assertThat(viewById, instanceOf(RecyclerView.class));
    }

    /*
        Check that the fab button for adding a note exists on the screen
     */
    @Test
    public void checkAddNoteButtonIsPresent() throws Exception {
        FloatingActionButton fab = (FloatingActionButton) activity.findViewById(R.id.fab);
        assertThat(fab, notNullValue());
        assertThat(fab, instanceOf(FloatingActionButton.class));
    }

    /*
        Check that the NoteAddActivity activity starts when the add note button is pressed
     */
    @Test
    public void testThatNoteAddActivityStartsWhenAddNoteButtonIsPressed() throws Exception {
        ActivityMonitor activityMonitor = getInstrumentation().addMonitor(NoteAddActivity.class.getName(), null, false);

        final FloatingActionButton fab = (FloatingActionButton) activity.findViewById(R.id.fab);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fab.performClick();
            }
        });

        NoteAddActivity noteAddActivity = (NoteAddActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 500);
        assertThat(noteAddActivity, notNullValue());
        noteAddActivity.finish();
    }
}
package nyc.c4q.huilin.celebritytrap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import nyc.c4q.huilin.celebritytrap.model.Celebrity;

/**
 * Created by huilin on 12/17/16.
 */

public class EditCelebrityDialogFragment extends DialogFragment implements TextView.OnEditorActionListener{

    private static Celebrity celebClicked;
    private EditText inputStageName;

    public EditCelebrityDialogFragment() {
    }

    public static EditCelebrityDialogFragment newInstance(String title, Celebrity celeb) {
        EditCelebrityDialogFragment dialogFragment = new EditCelebrityDialogFragment();
        celebClicked = celeb;
        Bundle args = new Bundle();
        args.putString("title", title);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_celeb, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputStageName = (EditText) view.findViewById(R.id.txt_new_name);

        String title = getArguments().getString("title", "Enter Stage Name");
        getDialog().setTitle(title);

        inputStageName.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        inputStageName.setOnEditorActionListener(this);

    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (EditorInfo.IME_ACTION_DONE == i) {
            // Return input text back to activity through the implemented listener
            EditCelebrityDialogListener listener = (EditCelebrityDialogListener) getActivity();
            listener.onFinishEditDialog(inputStageName.getText().toString(), celebClicked);
            // Close the dialog and return back to the parent activity
            dismiss();
            return true;
        }
        return false;

    }

}

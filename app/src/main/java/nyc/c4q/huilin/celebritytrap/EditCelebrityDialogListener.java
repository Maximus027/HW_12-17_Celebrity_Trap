package nyc.c4q.huilin.celebritytrap;

import nyc.c4q.huilin.celebritytrap.model.Celebrity;

/**
 * Created by huilin on 12/18/16.
 */
public interface EditCelebrityDialogListener {

    void onFinishEditDialog(String inputText, Celebrity celebClicked);
}

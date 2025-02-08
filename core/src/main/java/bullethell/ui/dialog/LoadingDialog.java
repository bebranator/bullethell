package bullethell.ui.dialog;

public class LoadingDialog extends CDialog {
    public LoadingDialog() {
        super("loading");
        shouldPause = true;
        hideTitle();
    }
}

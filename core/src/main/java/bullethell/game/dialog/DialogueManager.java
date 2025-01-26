package bullethell.game.dialog;

// used to set current dialogue
public class DialogueManager {
    private GameDialog current;

    public DialogueManager() {

    }

    // if already doing, stop current and start new
    public void setDialogue(GameDialog dialogue) {
        if(current != null) {
            stop();
        }
    }

    public void stop() {

    }

    public boolean hasDialogue() {
        return false;
    }
}

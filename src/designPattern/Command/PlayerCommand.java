package designPattern.Command;

public class PlayerCommand implements Command {
    private AutoPlayer autoPlayer;

    public PlayerCommand(AutoPlayer autoPlayer1){
        autoPlayer = autoPlayer1;
    }

    @Override
    public void execute() {
        autoPlayer.play();
    }
}

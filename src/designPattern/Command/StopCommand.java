package designPattern.Command;

public class StopCommand implements Command {
    private AutoPlayer autoPlayer;

    public StopCommand(AutoPlayer autoPlayer1){
        autoPlayer = autoPlayer1;
    }

    @Override
    public void execute() {
        autoPlayer.stop();
    }
}

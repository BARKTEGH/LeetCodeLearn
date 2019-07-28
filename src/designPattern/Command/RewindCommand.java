package designPattern.Command;



public class RewindCommand implements Command {
    private AutoPlayer myAudio;

    public RewindCommand(AutoPlayer audioPlayer){
        myAudio = audioPlayer;
    }
    @Override
    public void execute() {
        myAudio.rewind();
    }
}

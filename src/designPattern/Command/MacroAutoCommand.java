package designPattern.Command;

import java.util.ArrayList;
import java.util.List;

public class MacroAutoCommand implements MacroCommand {

    private List<Command> commandList = new ArrayList<Command>();
    @Override
    public void add(Command cmd) {
        commandList.add(cmd);
    }

    @Override
    public void remove(Command cmd) {
        commandList.remove(cmd);
    }

    @Override
    public void execute() {
        for(Command cmd:commandList){
            cmd.execute();
        }
    }
}

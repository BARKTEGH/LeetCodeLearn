package designPattern.State;


//操作实体

import designPattern.State.StateImpl.HasQuarterState;
import designPattern.State.StateImpl.NoQuarterState;
import designPattern.State.StateImpl.SoldOutState;
import designPattern.State.StateImpl.SoldState;

public class GumballMachine {
    private HasQuarterState hasQuarterState;
    private NoQuarterState noQuarterState;
    private SoldOutState soldOutState;
    private SoldState soldState;

    private State state;
    private int count = 0;

    public GumballMachine(int num){
        count = num;
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);

        if (num > 0) {
            state = noQuarterState;
        } else {
            state = soldOutState;
        }
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");
        if (count != 0) {
            count -= 1;
        }
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public int getCount() {
        return count;
    }



}

package mastermind;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Player {
    private String name = SeqUtil.toStr(SeqUtil.seq());
    private VDMSeq key = SeqUtil.seq();
    private Number numMoves = 0L;

    public Player() {
        cg_init_Player_1();
    }

    public Player(final String playerName) {
        cg_init_Player_2(playerName);
    }

    public Player(final String playerName, final VDMSeq playerKey) {
        cg_init_Player_3(playerName, Utils.copy(playerKey));
    }

    public void cg_init_Player_1() {
        name = "Default";

        return;
    }

    public void cg_init_Player_2(final String playerName) {
        name = playerName;

        return;
    }

    public void cg_init_Player_3(final String playerName, final VDMSeq playerKey) {
        name = playerName;
        key = Utils.copy(playerKey);

        return;
    }

    public String GetName() {
        return name;
    }

    public VDMSeq GetKey() {
        return Utils.copy(key);
    }

    public Number GetNumberOfMoves() {
        return numMoves;
    }

    public void SetKey(final VDMSeq newKey) {
        key = Utils.copy(newKey);
    }

    public void SumMoves(final Number moves) {
        numMoves = numMoves.longValue() + moves.longValue();
    }

    public String toString() {
        return "Player{" + "name := " + Utils.toString(name) + ", key := " +
        Utils.toString(key) + ", numMoves := " + Utils.toString(numMoves) +
        "}";
    }
}

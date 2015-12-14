package mastermind;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Game {
    private Number moves = 10L;
    private Player codeMaker;
    private Player codeBreaker;
    private VDMSeq makerResults = SeqUtil.seq();
    private VDMSeq breakerMoves = SeqUtil.seq();
    private Boolean finished = false;

    public Game(final Player codeMakerPlayer, final Player codeBreakerPlayer) {
        cg_init_Game_1(codeMakerPlayer, codeBreakerPlayer);
    }

    public Game() {
    }

    public void cg_init_Game_1(final Player codeMakerPlayer,
        final Player codeBreakerPlayer) {
        codeMaker = codeMakerPlayer;
        codeBreaker = codeBreakerPlayer;

        return;
    }

    public Number GetCurrentMoves() {
        return moves;
    }

    public VDMSeq GetFinalKey() {
        return codeMaker.GetKey();
    }

    public Player GetCodeMaker() {
        return codeMaker;
    }

    public Player GetCodeBreaker() {
        return codeBreaker;
    }

    public VDMSeq GetMakerResults() {
        return Utils.copy(makerResults);
    }

    public VDMSeq GetBreakerMoves() {
        return Utils.copy(breakerMoves);
    }

    public VDMSeq GetLastResult() {
        return Utils.copy(((VDMSeq) Utils.get(makerResults, makerResults.size())));
    }

    public Player GetWinnerPlayer() {
        if (Utils.equals(GetLastResult(), SeqUtil.seq(4L, 0L))) {
            return codeBreaker;
        } else {
            return codeMaker;
        }
    }

    public Boolean IsFinished() {
        return finished;
    }

    public void AddKey(final VDMSeq key) {
        codeBreaker.SetKey(Utils.copy(key));
        breakerMoves = SeqUtil.conc(Utils.copy(breakerMoves),
                SeqUtil.seq(Utils.copy(key)));
    }

    private void CheckMove() {
        Number e_corrects = 0L;
        Number e_exists = 0L;
        Number makerElemRep = 0L;
        Number breakerElemRep = 0L;
        Number sumMins = 0L;
        VDMSeq breakerKey = codeBreaker.GetKey();
        VDMSeq makerKey = codeMaker.GetKey();
        long toVar_3 = codeBreaker.GetKey().size();
        long byVar_3 = 1L;

        for (Long i = 1L; (byVar_3 < 0) ? (i >= toVar_3) : (i <= toVar_3);
                i += byVar_3) {
            if (Utils.equals(((Number) Utils.get(breakerKey, i)),
                        ((Number) Utils.get(makerKey, i)))) {
                e_corrects = e_corrects.longValue() + 1L;
            }
        }

        long toVar_4 = 6L;
        long byVar_4 = 1L;

        for (Long i = 1L; (byVar_4 < 0) ? (i >= toVar_4) : (i <= toVar_4);
                i += byVar_4) {
            makerElemRep = Utilities.Count(Utils.copy(makerKey), i);
            breakerElemRep = Utilities.Count(Utils.copy(breakerKey), i);
            sumMins = sumMins.longValue() +
                Utilities.Min(makerElemRep, breakerElemRep).longValue();
        }

        e_exists = sumMins.longValue() - e_corrects.longValue();

        Number atomicTmp_1 = moves.longValue() - 1L;
        VDMSeq atomicTmp_2 = SeqUtil.conc(Utils.copy(makerResults),
                SeqUtil.seq(SeqUtil.seq(e_corrects, e_exists)));

        Boolean atomicTmp_3 = Utils.equals(e_corrects, 4L);
        moves = atomicTmp_1;
        makerResults = Utils.copy(atomicTmp_2);
        finished = atomicTmp_3;
    }

    private void PrintBoard(final VDMSeq breakerKeys,
        final VDMSeq makerResponses) {
        Number currentMove = 10L - moves.longValue();
        IO.print("Board Game - Move: ");
        IO.println(currentMove);
        IO.println("MOVE                 RESULT [Corrects, Exists]");

        long toVar_5 = 1L;
        long byVar_5 = -1L;

        for (Long i = (long) makerResponses.size();
                (byVar_5 < 0) ? (i >= toVar_5) : (i <= toVar_5);
                i += byVar_5) {
            IO.print(Utils.copy(((VDMSeq) Utils.get(breakerKeys, i))));
            IO.print("         ");
            IO.println(Utils.copy(((VDMSeq) Utils.get(makerResponses, i))));
        }

        IO.print("Moves Remaing: ");
        IO.println(moves);
        IO.print("\n");
    }

    public void MakeMove(final VDMSeq key) {
        if (moves.longValue() > 0L) {
            if (!(finished)) {
                AddKey(Utils.copy(key));
                CheckMove();
                PrintBoard(Utils.copy(breakerMoves), Utils.copy(makerResults));

                if (finished) {
                    IO.print(codeBreaker.GetName());
                    IO.println(" won the game.");
                    IO.print("The key was ");
                    IO.print(codeBreaker.GetKey());
                    IO.print(" and the number of tries was ");
                    IO.print(makerResults.size());
                    IO.println(".");
                    IO.print("\n");
                } else {
                    Boolean andResult_9 = false;

                    if (Utils.equals(moves, 0L)) {
                        if (!(finished)) {
                            andResult_9 = true;
                        }
                    }

                    if (andResult_9) {
                        finished = true;
                        IO.print(codeMaker.GetName());
                        IO.println(" won the game.");
                        IO.print("The key was ");
                        IO.print(GetFinalKey());
                        IO.print(" and the number of moves was ");
                        IO.print(makerResults.size());
                        IO.println(".");
                        IO.print("\n");
                    }
                }
            } else {
                IO.println("The game is over.");
                IO.print("\n");
            }
        } else {
            IO.println("The game is over.");
            IO.print("\n");
        }
    }

    public void PlayRandomly() {
        if (Utils.equals(codeMaker.GetKey().size(), 0L)) {
            codeMaker.SetKey(Player.GenerateRandomlyKey());
        }

        Boolean whileCond_2 = true;

        while (whileCond_2) {
            whileCond_2 = !(finished);

            if (!(whileCond_2)) {
                break;
            }

            MakeMove(Player.GenerateRandomlyKey());
        }
    }

    public String toString() {
        return "Game{" + "moves := " + Utils.toString(moves) +
        ", codeMaker := " + Utils.toString(codeMaker) + ", codeBreaker := " +
        Utils.toString(codeBreaker) + ", makerResults := " +
        Utils.toString(makerResults) + ", breakerMoves := " +
        Utils.toString(breakerMoves) + ", finished := " +
        Utils.toString(finished) + "}";
    }
}

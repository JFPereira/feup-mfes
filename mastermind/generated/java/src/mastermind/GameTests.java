package mastermind;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class GameTests extends MyTestCase {
    public GameTests() {
    }

    public void testCreateKey() {
        VDMSeq key = SeqUtil.seq(1L, 2L, 3L, 4L);
        super.assertEqual(SeqUtil.seq(1L, 2L, 3L, 4L), Utils.copy(key));
        super.assertEqual(4L, key.size());

        for (Iterator iterator_8 = key.iterator(); iterator_8.hasNext();) {
            Number x = (Number) iterator_8.next();

            {
                Boolean andResult_11 = false;

                if (x.longValue() >= 1L) {
                    if (x.longValue() <= 6L) {
                        andResult_11 = true;
                    }
                }

                super.assertTrue(andResult_11);
            }
        }
    }

    public void testCreateRandomlyKey() {
        VDMSeq key = Player.GenerateRandomlyKey();
        super.assertEqual(4L, key.size());

        for (Iterator iterator_9 = key.iterator(); iterator_9.hasNext();) {
            Number x = (Number) iterator_9.next();

            {
                Boolean andResult_12 = false;

                if (x.longValue() >= 1L) {
                    if (x.longValue() <= 6L) {
                        andResult_12 = true;
                    }
                }

                super.assertTrue(andResult_12);
            }
        }
    }

    public void testCreateKeyWithMoreThanFourElems() {
        VDMSeq key = null;
        key = SeqUtil.seq(1L, 2L, 3L, 4L, 5L);
    }

    public void testPlayer() {
        Player player = new Player();
        player.SetKey(SeqUtil.seq(1L, 2L, 3L, 4L));
        super.assertEqual(SeqUtil.seq(1L, 2L, 3L, 4L), player.GetKey());
        super.assertEqual("Default", player.GetName());
    }

    public void testPlayerWithKey() {
        Player player = new Player("Cristo", SeqUtil.seq(1L, 2L, 3L, 4L));
        super.assertEqual(SeqUtil.seq(1L, 2L, 3L, 4L), player.GetKey());
    }

    public void testGameMove() {
        Player codeMaker = new Player("CodeMaker", SeqUtil.seq(6L, 6L, 5L, 2L));
        Player codeBreaker = new Player("CodeBreaker");
        Game g = new Game(codeMaker, codeBreaker);
        super.assertEqual(10L, g.GetCurrentMoves());
        g.MakeMove(SeqUtil.seq(1L, 1L, 2L, 2L));
        super.assertEqual(SeqUtil.seq(1L, 0L), g.GetLastResult());
    }

    public void testInvalidGameMove() {
        Player codeMaker = new Player("CodeMaker", SeqUtil.seq(6L, 6L, 5L, 2L));
        Player codeBreaker = new Player("CodeBreaker");
        Game g = new Game(codeMaker, codeBreaker);
        super.assertEqual(10L, g.GetCurrentMoves());
        g.MakeMove(SeqUtil.seq(1L, 1L, 7L, 2L));
    }

    public void testGameWinnerCodeBreaker() {
        Player codeMaker = new Player("CodeMaker", SeqUtil.seq(6L, 6L, 5L, 2L));
        Player codeBreaker = new Player("CodeBreaker");
        Game g = new Game(codeMaker, codeBreaker);
        super.assertEqual(10L, g.GetCurrentMoves());
        g.MakeMove(SeqUtil.seq(1L, 1L, 2L, 2L));
        super.assertEqual(SeqUtil.seq(1L, 0L), g.GetLastResult());
        g.MakeMove(SeqUtil.seq(3L, 3L, 4L, 4L));
        super.assertEqual(SeqUtil.seq(0L, 0L), g.GetLastResult());
        g.MakeMove(SeqUtil.seq(5L, 5L, 6L, 6L));
        super.assertEqual(SeqUtil.seq(0L, 3L), g.GetLastResult());
        g.MakeMove(SeqUtil.seq(5L, 6L, 5L, 2L));
        super.assertEqual(SeqUtil.seq(3L, 0L), g.GetLastResult());
        g.MakeMove(SeqUtil.seq(6L, 6L, 5L, 2L));
        super.assertEqual(SeqUtil.seq(4L, 0L), g.GetLastResult());
        super.assertEqual("CodeBreaker", g.GetWinnerPlayer().GetName());
        IO.print("Winner is: ");
        IO.println(g.GetWinnerPlayer().GetName());
    }

    public void testGameWinnerCodeMaker() {
        Player codeMaker = new Player("CodeMaker", SeqUtil.seq(1L, 2L, 4L, 1L));
        Player codeBreaker = new Player("CodeBreaker");
        Game g = new Game(codeMaker, codeBreaker);
        super.assertEqual(10L, g.GetCurrentMoves());
        g.MakeMove(SeqUtil.seq(1L, 1L, 2L, 2L));
        super.assertEqual(SeqUtil.seq(1L, 2L), g.GetLastResult());
        g.MakeMove(SeqUtil.seq(3L, 3L, 4L, 4L));
        super.assertEqual(SeqUtil.seq(1L, 0L), g.GetLastResult());
        g.MakeMove(SeqUtil.seq(5L, 5L, 6L, 6L));
        super.assertEqual(SeqUtil.seq(0L, 0L), g.GetLastResult());
        g.MakeMove(SeqUtil.seq(2L, 1L, 3L, 4L));
        super.assertEqual(SeqUtil.seq(0L, 3L), g.GetLastResult());
        g.MakeMove(SeqUtil.seq(1L, 1L, 1L, 1L));
        super.assertEqual(SeqUtil.seq(2L, 0L), g.GetLastResult());
        g.MakeMove(SeqUtil.seq(2L, 2L, 2L, 2L));
        super.assertEqual(SeqUtil.seq(1L, 0L), g.GetLastResult());
        g.MakeMove(SeqUtil.seq(1L, 1L, 3L, 4L));
        super.assertEqual(SeqUtil.seq(1L, 2L), g.GetLastResult());
        g.MakeMove(SeqUtil.seq(2L, 1L, 3L, 4L));
        super.assertEqual(SeqUtil.seq(0L, 3L), g.GetLastResult());
        g.MakeMove(SeqUtil.seq(1L, 1L, 2L, 4L));
        super.assertEqual(SeqUtil.seq(1L, 3L), g.GetLastResult());
        g.MakeMove(SeqUtil.seq(1L, 2L, 1L, 4L));
        super.assertEqual(SeqUtil.seq(2L, 2L), g.GetLastResult());
        super.assertEqual("CodeMaker", g.GetWinnerPlayer().GetName());
        IO.print("Winner is: ");
        IO.println(g.GetWinnerPlayer().GetName());
    }

    public void testRandomlyGame() {
        Player codeMaker = new Player("CodeMaker", SeqUtil.seq(1L, 2L, 4L, 1L));
        Player codeBreaker = new Player("CodeBreaker");
        Game g = new Game(codeMaker, codeBreaker);
        super.assertEqual(10L, g.GetCurrentMoves());
        g.PlayRandomly();
        super.assertEqual(true, g.IsFinished());
        IO.print("Winner is: ");
        IO.println(g.GetWinnerPlayer().GetName());
    }

    public void testChampionshipWithFourPlayers() {
        Player p1 = new Player("Tito");
        Player p2 = new Player("Cristina");
        Player p3 = new Player("Jacinto");
        Player p4 = new Player("Ana");
        VDMSeq games = null;
        Championship champ = new Championship(SeqUtil.seq(p1, p2, p3, p4));
        super.assertEqual(4L, champ.GetPlayers().size());

        long toVar_8 = champ.GetNumberOfRounds().longValue();
        long byVar_8 = 1L;

        for (Long i = 1L; (byVar_8 < 0) ? (i >= toVar_8) : (i <= toVar_8);
                i += byVar_8) {
            games = champ.CreateGames(champ.GetCurrentPlayersOnChampionship());

            if (Utils.equals(i, 1L)) {
                super.assertEqual(2L, games.size());
            } else if (Utils.equals(i, 2L)) {
                super.assertEqual(1L, games.size());
            }

            long toVar_6 = games.size();
            long byVar_6 = 1L;

            for (Long j = 1L; (byVar_6 < 0) ? (j >= toVar_6) : (j <= toVar_6);
                    j += byVar_6) {
                ((Game) Utils.get(games, j)).PlayRandomly();
            }

            champ.PickAllWinnerPlayers(Utils.copy(games));

            if (Utils.equals(i, 1L)) {
                super.assertEqual(2L,
                    champ.GetCurrentPlayersOnChampionship().size());
            } else if (Utils.equals(i, 2L)) {
                super.assertEqual(1L,
                    champ.GetCurrentPlayersOnChampionship().size());
            }

            long toVar_7 = champ.GetCurrentPlayersOnChampionship().size();
            long byVar_7 = 1L;

            for (Long j = 1L; (byVar_7 < 0) ? (j >= toVar_7) : (j <= toVar_7);
                    j += byVar_7) {
                IO.print("Winner of Game ");
                IO.print(j);
                IO.print(" is: ");
                IO.println(((Player) Utils.get(
                        champ.GetCurrentPlayersOnChampionship(), j)).GetName());
            }

            champ.AddGames(Utils.copy(games));
        }

        champ.PrintStats();
    }

    public void testAll() {
        testCreateKey();
        testCreateRandomlyKey();
        testPlayer();
        testPlayerWithKey();
        testGameMove();
        testGameWinnerCodeBreaker();
        testGameWinnerCodeMaker();
        testRandomlyGame();
        testChampionshipWithFourPlayers();
    }

    public String toString() {
        return "GameTests{}";
    }
}

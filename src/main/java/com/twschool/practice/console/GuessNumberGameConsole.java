package com.twschool.practice.console;

import com.twschool.practice.domain.*;

import java.util.Arrays;
import java.util.List;

public class GuessNumberGameConsole {
    
    private GuessNumberGame guessNumberGame;
    private ConsoleInputReader consoleInputReader;
    private ConsoleOutputWriter consoleOutputWriter;

    public GuessNumberGameConsole(AnswerGenerator answerGenerator,
                                  ConsoleInputReader consoleInputReader,
                                  ConsoleOutputWriter consoleOutputWriter) {
        guessNumberGame = new GuessNumberGame(answerGenerator);
        this.consoleInputReader = consoleInputReader;
        this.consoleOutputWriter = consoleOutputWriter;
    }

    public String guess(String userAnswerString) {
        List<String> userAnswerNumbers = Arrays.asList(userAnswerString.split(" "));
        Answer userAnswer = new Answer(userAnswerNumbers);
        if (!userAnswer.isValidFormat()) {
            return "this is error!,plese input again";
        }
        return guessNumberGame.guess(userAnswerString);
    }

    public void play() {
        while (guessNumberGame.getStatus() == GameStatus.CONTINUED) {
            consoleOutputWriter.output("input answer: ");
            String userAnswerString = consoleInputReader.getInput();
            String result = guess(userAnswerString);
            consoleOutputWriter.output("result: " + result);
        }

        if (guessNumberGame.getStatus() == GameStatus.SUCCEED) {
            consoleOutputWriter.output("SUCCEED");
        }
        if (guessNumberGame.getStatus() == GameStatus.FAILED) {
            consoleOutputWriter.output("FAILED");
        }
    }
}

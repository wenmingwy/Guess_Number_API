package com.twschool.practice.console;

import com.twschool.practice.domain.AnswerGenerator;
import com.twschool.practice.domain.RandomAnswerGenerator;

public class GuessNumberGameConsoleApp {
    
    public static void main(String[] args) {
        GuessNumberGameConsole guessNumberGameConsole = new GuessNumberGameConsole(new AnswerGenerator(), new ConsoleInputReader(), new ConsoleOutputWriter());
        guessNumberGameConsole.play();
    }
}

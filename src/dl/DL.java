package dl;

import java.io.File;

import lexer.Lexer;
import parser.LL1Parser;

public class DL {
    public static void main(String[] args) throws Exception {
        Lexer lexer = new Lexer(new File("prog.dl"));
        if (lexer.getReader() == null)
            return;
        LL1Parser parser = new LL1Parser(lexer);
        parser.parse();
        System.out.println("Finalizado!");
    }
}

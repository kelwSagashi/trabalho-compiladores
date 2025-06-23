package lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Hashtable;

import lexer.tag.Tag;
import lexer.token.Token;

public class Lexer {
    private static final char EOF_CHAR = (char) -1;
    public static Integer line = 1;
    private BufferedReader reader;
    private char peek;
    private Hashtable<String, Tag> keywords;

    public Lexer(File file) {
        try {
            this.reader = new BufferedReader(new FileReader(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.peek = ' ';

        keywords = new Hashtable<String, Tag>();
        
		// keywords.put("verdadeiro", Tag.TRUE);
		// keywords.put("falso", Tag.FALSE);
		// keywords.put("real", Tag.REAL);
		// keywords.put("booleano", Tag.BOOL);

        // keywords.put("programa", Tag.PROGRAM);
        // keywords.put("inicio", Tag.BEGIN);
        // keywords.put("fim", Tag.END);
        // keywords.put("inteiro", Tag.INT);
        // keywords.put("escreva", Tag.WRITE);
        // keywords.put("se", Tag.IF);
        // keywords.put("senao", Tag.ELSE);
        // keywords.put("ler", Tag.READ);
        // keywords.put("enquanto", Tag.WHILE);
    }

    public BufferedReader getReader() {
        return reader;
    }

    public static int line() {
        return line;
    }

    public char nextChar() {
        if (peek == '\n')
            line++;
        try {
            peek = (char) reader.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return peek;
    }

    private static boolean isWhiteSpace(int c) {
        switch (c) {
            case ' ':
            case '\t':
            case '\n':
            case '\r':
                return true;
            default:
                return false;
        }
    }

    private static boolean isIdStart(int c) {
		return ( Character.isAlphabetic(c) || c == '_' );
	}

	private static boolean isIdPart(int c) {
		return (isIdStart(c) || Character.isDigit(c));
	}

	public Token nextToken() {
		while (isWhiteSpace(peek)) nextChar();
		switch(peek) {
		// case '=':
		// 	nextChar();
		// 	return new Token(Tag.ASSIGN, "=");
		case '+':
			nextChar();
			return new Token(Tag.SUM, "+");
		case '-':
			nextChar();
			return new Token(Tag.SUB, "-");
		case '*':
			nextChar();
			return new Token(Tag.MUL,"*");
		case '/':
			nextChar();
			return new Token(Tag.DIV,"/");
		// case '|':
		// 	nextChar();
		// 	return new Token(Tag.OR, "|");
		case '<':
			nextChar();
			// if ( peek == '=' ) {
			// 	nextChar();
			// 	return new Token(Tag.LE, "<=");
			// }
			return new Token(Tag.LT, "<");
		case '>':
			nextChar();
			return new Token(Tag.GT, ">");
		// case ';':
		// 	nextChar();
		// 	return new Token(Tag.SEMI, ";");
		// case '.':
		// 	nextChar();
		// 	return new Token(Tag.DOT, ".");
		case '(':
			nextChar();
			return new Token(Tag.LPAREN, "(");
		case ')':
			nextChar();
			return new Token(Tag.RPAREN, ")");
		case EOF_CHAR:
			return new Token(Tag.EOF, "");
		default:
			if (Character.isDigit(peek)) {
				String num = "";
				do {
					num += peek;
					nextChar();
				} while( Character.isDigit(peek) );
				if ( peek != '.' ) 
					return new Token(Tag.LIT_INT, num);
				do {
					num += peek;
					nextChar();
				} while ( Character.isDigit(peek) );
				return new Token(Tag.LIT_REAL, num);
			} else if ( isIdStart(peek)  ) {
				String id = "";
				do {
					id += peek;
					nextChar();
				} while ( isIdPart(peek) );
				if ( keywords.containsKey(id) )
					return new Token(
							keywords.get(id), id);
				return new Token(Tag.ID, id);
			}
		}
		String unk = String.valueOf(peek);
		nextChar();
		return new Token(Tag.UNK, unk);
	}

    // private Token operandAssign(Tag tag, String lexeme, Tag other) {
    //     nextChar();
    //     if (peek == '=')
    //         return new Token(tag, lexeme + peek);
    //     return new Token(other, lexeme);
    // }

}

package parser;

import lexer.Lexer;
import lexer.token.Token;
import lexer.tag.Tag;

public class LL1Parser {
    private Lexer lexer;
    private Token lookahead;

    public LL1Parser(Lexer lex) {
        lexer = lex;
        move();
    }

    private Token move() {
        Token save = lookahead;
        lookahead = lexer.nextToken();
        System.out.println(lookahead);
        return save;
    }

    private void error(String s) {
        // throw new Error("near line " + Lexer.line() + ": " + s);
        System.err.println("Linha " + Lexer.line() + ": " + s);
        System.exit(0);
    }

    private Token match(Tag tag) {
        if (lookahead.tag() == tag)
            return move();
        error("Erro de sintaxe. Esperado '" + tag + "' mas encontrado '" + lookahead.tag() + "'");
        return null;
    }

    private Token match() {
        return move();
    }

    public void parse() {
        expr();
    }

    // === GRAMÁTICA ===
    // EXPR -> TERM EXPR'
    private void expr() {
        term();
        // EXPR' -> + TERM EXPR' | - TERM EXPR' | ε
        while (lookahead.tag() == Tag.SUM || lookahead.tag() == Tag.SUB) {
            move();
            term();
        }
    }

    // EXPR' -> + TERM EXPR' | - TERM EXPR' | ε
    @Deprecated
    private void exprPrime() {
        switch (lookahead.tag()) {
            case SUM:
                match(Tag.SUM);
                term();
                exprPrime();
                break;
            case SUB:
                match(Tag.SUB);
                term();
                exprPrime();
                break;
            default:
                break;
        }
    }

    // TERM -> UNARY TERM'
    private void term() {
        unary();
        // TERM' -> * UNARY TERM' | / UNARY TERM' | ε
        while (lookahead.tag() == Tag.MUL || lookahead.tag() == Tag.DIV) {
            move();
            unary();
        }
    }

    // TERM' -> * UNARY TERM' | / UNARY TERM' | ε
    @Deprecated
    private void termPrime() {
        switch (lookahead.tag()) {
            case MUL:
                match(Tag.MUL);
                unary();
                termPrime();
                break;
            case DIV:
                match(Tag.DIV);
                unary();
                termPrime();
                break;
            default:
                break;
        }
    }

    // UNARY -> + UNARY | - UNARY | PRIMARY
    private void unary() {
        while (lookahead.tag() == Tag.SUM || lookahead.tag() == Tag.SUB) {
            move();
        }
        primary();
    }

    // PRIMARY -> < id | > id | POST
    private void primary() {
        if (lookahead.tag() == Tag.GT || lookahead.tag() == Tag.LT) {
            move();
            match(Tag.ID);
        } else {
            post();
        }
    }

    // POST -> FACTOR POST'
    private void post() {
        factor();
        // POST' -> > | < | ε
        if (lookahead.tag() == Tag.GT) {
            match(Tag.GT);
        } else if (lookahead.tag() == Tag.LT) {
            match(Tag.LT);
        }
    }

    // POST' -> > | < | ε
    @Deprecated
    private void postPrime() {
        switch (lookahead.tag()) {
            case GT:
                match(Tag.GT);
                break;
            case LT:
                match(Tag.LT);
                break;
            default:
                break;
        }
    }

    // FACTOR -> (EXPR) | id | digit
    private void factor() {
        switch (lookahead.tag()) {
            case LPAREN:
                match(Tag.LPAREN);
                expr();
                match(Tag.RPAREN);
                break;
            case ID:
                match(Tag.ID);
                break;
            case LIT_INT:
                match(Tag.LIT_INT);
                break;
            case LIT_REAL:
                match(Tag.LIT_REAL);
                break;
            default:
                error("Esperado fator (id, número ou parênteses)" + lookahead);
                break;
        }
    }

    // private void id() {
    // if (Character.isLetter(lookahead)) {
    // match(lookahead);
    // } else {
    // error("Identificador esperado (a-z)");
    // }
    // }

    // private void digit() {
    // if (Character.isDigit(lookahead)) {
    // match(lookahead);
    // } else {
    // error("Dígito esperado (0-9)");
    // }
    // }
}
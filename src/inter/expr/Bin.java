package inter.expr;

import lexer.token.Token;

public class Bin extends Expr {
    public Bin(Token op, Expr e1, Expr e2) {
        super(op, null);
        expr1 = e1;
        expr2 = e2;
        addChild(e1);
        addChild(e2);
    }

    protected Expr expr1;
    protected Expr expr2;
}

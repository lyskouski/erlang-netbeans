/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.creativity.erlang.lexer;

import org.antlr.runtime.Token;
import org.antlr.v4.runtime.CharStream;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

public class InitLexer implements Lexer<ErlangTokenId> {

    private LexerRestartInfo<ErlangTokenId> info;

    private ErlangLexer erlangLexer;

    public InitLexer(LexerRestartInfo<ErlangTokenId> info) {
        this.info = info;

        AntlrCharStream charStream = new AntlrCharStream(info.input(), "ErlangEditor");
        erlangLexer = new ErlangLexer((CharStream) charStream);
    }

    public org.netbeans.api.lexer.Token<ErlangTokenId> nextToken() {
        org.antlr.v4.runtime.Token token = erlangLexer.nextToken();
        if (token.getType() != ErlangLexer.EOF) {
            ErlangTokenId tokenId = ErlangLanguageHierarchy.getToken(token.getType());
            return info.tokenFactory().createToken(tokenId);
        }
        return null;
    }

    public Object state() {
        return null;
    }

    public void release() {}
}


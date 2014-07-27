/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creativity.erlang.lexer;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import by.creativity.erlang.lexer.ErlangLexer;
import by.creativity.erlang.lexer.ErlangParser;
import org.netbeans.spi.lexer.LanguageHierarchy;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

public class ErlangLanguageHierarchy extends LanguageHierarchy<ErlangTokenId> {

    @Override
    protected Collection<ErlangTokenId> createTokenIds() {
        return Arrays.asList(tokens);
    }

    @Override
    protected Lexer<ErlangTokenId> createLexer(LexerRestartInfo<ErlangTokenId> lri) {
        return new by.creativity.erlang.lexer.InitLexer(lri);
    }

    @Override
    protected String mimeType() {
        return "text/x-erlang";
    }

    static synchronized ErlangTokenId getToken(int id) {
        return idToToken.get(id);
    }

    private static final Map<Integer, ErlangTokenId> idToToken = new HashMap<Integer, ErlangTokenId>();
    private static final ErlangTokenId[] tokens = new ErlangTokenId[]{
        token(ErlangLexer.T__62, "operator"), // bsr
        token(ErlangLexer.T__61, "operator"), // =<
        token(ErlangLexer.T__60, "separator"),// [
        token(ErlangLexer.T__59, "operator"), // *
        token(ErlangLexer.T__58, "operator"), // or
        token(ErlangLexer.T__57, "operator"), // --
        token(ErlangLexer.T__56, "operator"), // <
        token(ErlangLexer.T__55, "operator"), // <=
        token(ErlangLexer.T__54, "operator"), // <<

        //		T__53=10, T__52=11, T__51=12, T__50=13, T__49=14, T__48=15, T__47=16, 
        //		T__46=17, T__45=18, T__44=19, T__43=20, T__42=21, T__41=22, T__40=23, 
        //		T__39=24, T__38=25, T__37=26, T__36=27, T__35=28, T__34=29, T__33=30, 
        //		T__32=31, T__31=32, T__30=33, T__29=34, T__28=35, T__27=36, T__26=37, 
        //		T__25=38, T__24=39, T__23=40, T__22=41, T__21=42, T__20=43, T__19=44, 
        //		T__18=45, T__17=46, T__16=47, T__15=48, T__14=49, T__13=50, T__12=51, 
        //		T__11=52, T__10=53, T__9=54, T__8=55, T__7=56, T__6=57, T__5=58, T__4=59, 
        //		T__3=60, T__2=61, T__1=62, T__0=63, 
        //  
        //		"'andalso'", "'=:='", "'band'", "'}'", "'after'", "'case'", "'->'", "'bnot'", 
        //		"'xor'", "')'", "':-'", "'orelse'", "'::'", "'='", "'bxor'", "'fun'", 
        //		"'div'", "'bor'", "'begin'", "'rem'", "'when'", "'|'", "'!'", "'..'", 
        //		"']'", "'of'", "','", "'receive'", "'-'", "'not'", "':'", "'('", "'if'", 
        //		"'=/='", "'try'", "'{'", "'...'", "'and'", "'catch'", "'<-'", "'++'", 
        //		"'>>'", "'.'", "'+'", "'bsl'", "';'", "'||'", "'>'", "'/='", "'/'", "'=='", 
        //		"'#'", "'>='", "'end'",
        
        token(ErlangLexer.TokAtom, "character"),
        token(ErlangLexer.TokVar, "identifier"),
        token(ErlangLexer.TokFloat, "number"),
        token(ErlangLexer.TokInteger, "number"),
        token(ErlangLexer.TokChar, "string"),
        token(ErlangLexer.TokString, "string"),
        token(ErlangLexer.AttrName, "keyword"),
        token(ErlangLexer.Comment, "comment"),
        token(ErlangLexer.WS, "method"), // <<
    };

    static {
        for (ErlangTokenId token : tokens) {
            idToToken.put(token.ordinal(), token);
        }
    }

    private static ErlangTokenId token(int antlrToken, String category) {
        return new ErlangTokenId(ErlangParser.tokenNames[antlrToken], category, antlrToken);
    }
}

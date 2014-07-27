%%% Module 'sample' as a small description of Erlang
%%% @author Viachaslau Lyskouski <deus@creativity.by>
%%% @since 2014-07-16

%%% @module sample
-module(sample).

%%% @methods ping(Pid, List, String), echo(String)
-export([ping/3, echo/1]).

%% Response for browser
% @sample sample:ping(self(), [{request,'Hello World!'}]). flush().
%
% @param pid SessionID - User session ID
% @param list Env - Enviroment params
% @param string Input - POST values, not used in this function
% @return string
ping(SessionID, Env, _Input) ->
    mod_esi:deliver(SessionID,
        ["Content-Type: text/html\r\n\r\n" | format(Env)]).

%% Response for console
%
% @sample sample:echo();
% @return string
echo(Input) ->
    io:format("Response: ~w", [Input]).

%% Concatenate list
%
% @access private
% @note internal functon for list concatenation
% @return string
format([]) ->
    "";
format([{Key, Value} | Env]) ->
    [io_lib:format("<b>~p:</b> ~p<br />\~n", [Key, Value]) | format(Env)].
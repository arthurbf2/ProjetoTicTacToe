if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'main'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'main'.");
}var main = function (_, Kotlin) {
  'use strict';
  var throwCCE = Kotlin.throwCCE;
  var split = Kotlin.kotlin.text.split_o64adg$;
  var toBoxedChar = Kotlin.toBoxedChar;
  var equals = Kotlin.equals;
  var toString = Kotlin.toString;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var board;
  function jogar(vsJarvis) {
    var options = document.getElementById('botoes');
    if (options != null) {
      options.innerHTML = '';
    }var status = document.getElementById('status');
    if (status != null)
      status.innerHTML = 'Vez de jogador 1(X)';
    var tabuleiro = document.getElementById('tabuleiro');
    if (tabuleiro != null)
      tabuleiro.innerHTML = '\n' + '        <html>' + '\n' + '        <table>' + '\n' + '                <tr>' + '\n' + '                    <td><button class=' + '"' + 'bts' + '"' + ' id=' + '"' + 'b00' + '"' + ' onclick=' + '"' + 'main.botaoPressionado(id, ' + vsJarvis + ')' + '"' + ' value=' + '"' + '0' + '"' + '>00<\/button><\/td>' + '\n' + '                    <td><button class=' + '"' + 'bts' + '"' + ' id=' + '"' + 'b01' + '"' + ' onclick=' + '"' + 'main.botaoPressionado(id, ' + vsJarvis + ')' + '"' + ' value=' + '"' + '0' + '"' + '>01<\/button><\/td>' + '\n' + '                    <td><button class=' + '"' + 'bts' + '"' + ' id=' + '"' + 'b02' + '"' + ' onclick=' + '"' + 'main.botaoPressionado(id, ' + vsJarvis + ')' + '"' + ' value=' + '"' + '0' + '"' + '>02<\/button><\/td>' + '\n' + '                <\/tr>' + '\n' + '                <tr>' + '\n' + '                    <td><button class=' + '"' + 'bts' + '"' + ' id=' + '"' + 'b10' + '"' + ' onclick=' + '"' + 'main.botaoPressionado(id, ' + vsJarvis + ')' + '"' + ' value=' + '"' + '0' + '"' + '>10<\/button><\/td>' + '\n' + '                    <td><button class=' + '"' + 'bts' + '"' + ' id=' + '"' + 'b11' + '"' + ' onclick=' + '"' + 'main.botaoPressionado(id, ' + vsJarvis + ')' + '"' + ' value=' + '"' + '0' + '"' + '>11<\/button><\/td>' + '\n' + '                    <td><button class=' + '"' + 'bts' + '"' + ' id=' + '"' + 'b12' + '"' + ' onclick=' + '"' + 'main.botaoPressionado(id, ' + vsJarvis + ')' + '"' + ' value=' + '"' + '0' + '"' + '>12<\/button><\/td>' + '\n' + '                <\/tr>' + '\n' + '                <tr>' + '\n' + '                    <td><button class=' + '"' + 'bts' + '"' + ' id=' + '"' + 'b20' + '"' + ' onclick=' + '"' + 'main.botaoPressionado(id, ' + vsJarvis + ')' + '"' + ' value=' + '"' + '0' + '"' + '>20<\/button><\/td>' + '\n' + '                    <td><button class=' + '"' + 'bts' + '"' + ' id=' + '"' + 'b21' + '"' + ' onclick=' + '"' + 'main.botaoPressionado(id, ' + vsJarvis + ')' + '"' + ' value=' + '"' + '0' + '"' + '>21<\/button><\/td>' + '\n' + '                    <td><button class=' + '"' + 'bts' + '"' + ' id=' + '"' + 'b22' + '"' + ' onclick=' + '"' + 'main.botaoPressionado(id, ' + vsJarvis + ')' + '"' + ' value=' + '"' + '0' + '"' + '>22<\/button><\/td>' + '\n' + '                <\/tr>' + '\n' + '            <\/table>' + '\n' + '        <\/html> ' + '\n' + '        ';
  }
  var testeglobal;
  function botaoPressionado(id, vsJarvis) {
    var tmp$;
    var status = document.getElementById('status');
    if (status != null)
      if (testeglobal === 1)
        if (vsJarvis) {
          jarvis();
          status.innerHTML = 'Vez de Jarvis (O)';
        } else {
          status.innerHTML = 'Vez de jogador 2(O)';
        }
       else
        status.innerHTML = 'Vez de jogador 1(X)';
    var botao = Kotlin.isType(tmp$ = document.getElementById(id), HTMLButtonElement) ? tmp$ : throwCCE();
    if (botao.disabled === false)
      if (testeglobal === 1) {
        botao.innerHTML = 'X';
        board[(id.charCodeAt(1) | 0) - 48 | 0][(id.charCodeAt(2) | 0) - 48 | 0] = 1;
        testeglobal = testeglobal - 1 | 0;
        botao.disabled = true;
      } else {
        botao.innerHTML = 'O';
        board[(id.charCodeAt(1) | 0) - 48 | 0][(id.charCodeAt(2) | 0) - 48 | 0] = -1;
        testeglobal = testeglobal + 1 | 0;
        botao.disabled = true;
      }
    if (fimDeJogo()) {
      desabilitaBts(0);
      var restart = document.getElementById('botaoRestart');
      if (restart != null)
        restart.innerHTML = '\n' + '                <button onclick=' + '"' + 'main.resetaBoard(0, 0, ' + vsJarvis + ')' + '"' + '>Jogar novamente<\/button>' + '\n' + '                <button onclick=' + '"' + 'resetaBoardGOTOMENU(0, 0)' + '"' + '>Voltar ao menu<\/button>' + '\n' + '            ';
    }}
  function resetaBoardGOTOMENU(linha, col) {
    if (col <= 2 && linha <= 2) {
      board[linha][col] = 0;
      resetaBoardGOTOMENU(linha + 1 | 0, col);
    } else if (col <= 2) {
      resetaBoardGOTOMENU(0, col + 1 | 0);
    }}
  function resetaBoard(linha, col, vsJarvis) {
    if (col <= 2 && linha <= 2) {
      board[linha][col] = 0;
      resetaBoard(linha + 1 | 0, col, vsJarvis);
    } else if (col <= 2) {
      resetaBoard(0, col + 1 | 0, vsJarvis);
    }jogar(vsJarvis);
  }
  function fimDeJogo() {
    var status = document.getElementById('status');
    if (verifica()) {
      if (status != null) {
        var s = split(status.innerHTML, Kotlin.charArrayOf(40));
        if (equals(toBoxedChar(s.get_za3lpa$(1).charCodeAt(0)), toBoxedChar(88)))
          status.innerHTML = 'O jogador 2 venceu!';
        else
          status.innerHTML = 'O jogador 1 venceu!';
      }return true;
    } else {
      if (verificaVelha(0)) {
        if (status != null)
          status.innerHTML = 'Deu velha!';
        return true;
      }}
    return false;
  }
  function verifica() {
    if (verificaDiagonais())
      return true;
    if (verificaLinhas(0, 0, 0))
      return true;
    if (verificaColunas(0, 0, 0))
      return true;
    return false;
  }
  function verificaLinhas(linha, col, x) {
    if (x === 3 || x === -3)
      return true;
    if (col <= 2 && linha <= 2) {
      var r = board[linha][col];
      return verificaLinhas(linha, col + 1 | 0, r + x | 0);
    } else if (linha <= 2) {
      return verificaLinhas(linha + 1 | 0, 0, 0);
    }return false;
  }
  function verificaColunas(linha, col, x) {
    if (x === 3 || x === -3)
      return true;
    if (col <= 2 && linha <= 2) {
      var r = board[linha][col];
      return verificaColunas(linha + 1 | 0, col, r + x | 0);
    } else if (col <= 2) {
      return verificaColunas(0, col + 1 | 0, 0);
    }return false;
  }
  function verificaDiagonais() {
    var d1 = board[0][0] + board[1][1] + board[2][2] | 0;
    var d2 = board[0][2] + board[1][1] + board[2][0] | 0;
    if (d1 === 3 || d1 === -3 || d2 === 3 || d2 === -3)
      return true;
    return false;
  }
  function verificaVelha(i) {
    if (i < 9) {
      var doc = document.getElementsByClassName('bts').item(i);
      if (doc != null)
        if (!equals(doc.innerHTML, 'X') && !equals(doc.innerHTML, 'O'))
          return false;
      return verificaVelha(i + 1 | 0);
    }return true;
  }
  function desabilitaBts(i) {
    var tmp$;
    if (i < 9) {
      var doc = Kotlin.isType(tmp$ = document.getElementsByClassName('bts').item(i), HTMLButtonElement) ? tmp$ : throwCCE();
      if (!doc.disabled)
        doc.disabled = true;
      desabilitaBts(i + 1 | 0);
    }}
  function jarvis() {
    var tmp$, tmp$_0;
    var possiblePositions = ArrayList_init();
    var bestMoveID = '';
    var score = 0;
    tmp$ = possiblePositions.iterator();
    while (tmp$.hasNext()) {
      var i = tmp$.next();
      if (jarvisBoardAnalysis(i) > score)
        bestMoveID = i;
    }
    bestMoveID = 'b' + bestMoveID;
    var move = Kotlin.isType(tmp$_0 = document.getElementById(bestMoveID), HTMLButtonElement) ? tmp$_0 : throwCCE();
    if (move != null)
      move.click();
  }
  function verificaEspacos(linha, col, list) {
    if (col <= 2 && linha <= 2) {
      if (board[linha][col] === 0) {
        list.add_11rb$('' + toString(linha) + toString(col));
      }return verificaEspacos(linha + 1 | 0, col, list);
    } else if (col <= 2) {
      return verificaEspacos(0, col + 1 | 0, list);
    } else
      return list;
  }
  function jarvisBoardAnalysis(id) {
    var linha = (id.charCodeAt(0) | 0) - 48 | 0;
    var col = (id.charCodeAt(1) | 0) - 48 | 0;
    var score = 0;
    if (linha === 1 && col === 1) {
      score = score + 2 | 0;
      if (positionHasEnemyAtDiagonal(linha, col))
        score = score - 2 | 0;
    } else if (linha === col || (linha === 0 && col === 2) || (linha === 2 && col === 0)) {
      score = score + 1 | 0;
      if (positionHasEnemyAtDiagonal(linha, col))
        score = score - 2 | 0;
    } else {
      if (positionHasEnemyAtLine(linha, 0) || positionHasEnemyAtColumn(0, col)) {
        score = score - 2 | 0;
      }}
    return score;
  }
  function positionHasEnemyAtLine(linha, col) {
    if (board[linha][col] === 1)
      return true;
    else if (col <= 2)
      return positionHasEnemyAtLine(linha, col + 1 | 0);
    return false;
  }
  function positionHasEnemyAtColumn(linha, col) {
    if (board[linha][col] === 1)
      return true;
    else if (linha <= 2)
      return positionHasEnemyAtColumn(linha + 1 | 0, col);
    return false;
  }
  function positionHasEnemyAtDiagonal(linha, col) {
    if (linha === 1 || col === 1) {
      if (board[0][0] === 1 || board[2][2] === 1 || board[0][2] === 1 || board[2][0] === 1)
        return true;
      else
        return false;
    } else if (linha === col) {
      if (board[0][0] === 1 || board[1][1] === 1 || board[2][2] === 1)
        return true;
      else
        return false;
    } else {
      if (board[0][2] === 1 || board[1][1] === 1 || board[2][0] === 1)
        return true;
      else
        return false;
    }
  }
  Object.defineProperty(_, 'board', {
    get: function () {
      return board;
    }
  });
  _.jogar = jogar;
  Object.defineProperty(_, 'testeglobal', {
    get: function () {
      return testeglobal;
    },
    set: function (value) {
      testeglobal = value;
    }
  });
  _.botaoPressionado = botaoPressionado;
  _.resetaBoardGOTOMENU = resetaBoardGOTOMENU;
  _.resetaBoard = resetaBoard;
  _.fimDeJogo = fimDeJogo;
  _.verifica = verifica;
  _.verificaLinhas = verificaLinhas;
  _.verificaColunas = verificaColunas;
  _.verificaDiagonais = verificaDiagonais;
  _.verificaVelha = verificaVelha;
  _.desabilitaBts = desabilitaBts;
  _.jarvis = jarvis;
  _.verificaEspacos = verificaEspacos;
  _.jarvisBoardAnalysis = jarvisBoardAnalysis;
  _.positionHasEnemyAtLine = positionHasEnemyAtLine;
  _.positionHasEnemyAtColumn = positionHasEnemyAtColumn;
  _.positionHasEnemyAtDiagonal = positionHasEnemyAtDiagonal;
  board = [[0, 0, 0], [0, 0, 0], [0, 0, 0]];
  testeglobal = 1;
  Kotlin.defineModule('main', _);
  return _;
}(typeof main === 'undefined' ? {} : main, kotlin);

if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'main'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'main'.");
}var main = function (_, Kotlin) {
  'use strict';
  var throwCCE = Kotlin.throwCCE;
  var contains = Kotlin.kotlin.text.contains_li3zpu$;
  var equals = Kotlin.equals;
  var toString = Kotlin.toString;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var board;
  function jogar(vsJarvis) {
    testeglobal = 1;
    var options = document.getElementById('botoes');
    if (options != null) {
      options.innerHTML = '';
    }var status = document.getElementById('status');
    if (status != null)
      status.innerHTML = 'Vez de jogador 1(X)';
    var tabuleiro = document.getElementById('tabuleiro');
    if (tabuleiro != null) {
      tabuleiro.innerHTML = '\n' + '        <html>' + '\n' + '        <table>' + '\n' + '                <tr>' + '\n' + '                    <td><button class=' + '"' + 'bts' + '"' + ' id=' + '"' + 'b00' + '"' + ' onclick=' + '"' + 'main.botaoPressionado(id, ' + vsJarvis + ')' + '"' + ' value=' + '"' + '0' + '"' + '>.<\/button><\/td>' + '\n' + '                    <td><button class=' + '"' + 'bts' + '"' + ' id=' + '"' + 'b01' + '"' + ' onclick=' + '"' + 'main.botaoPressionado(id, ' + vsJarvis + ')' + '"' + ' value=' + '"' + '0' + '"' + '>.<\/button><\/td>' + '\n' + '                    <td><button class=' + '"' + 'bts' + '"' + ' id=' + '"' + 'b02' + '"' + ' onclick=' + '"' + 'main.botaoPressionado(id, ' + vsJarvis + ')' + '"' + ' value=' + '"' + '0' + '"' + '>.<\/button><\/td>' + '\n' + '                <\/tr>' + '\n' + '                <tr>' + '\n' + '                    <td><button class=' + '"' + 'bts' + '"' + ' id=' + '"' + 'b10' + '"' + ' onclick=' + '"' + 'main.botaoPressionado(id, ' + vsJarvis + ')' + '"' + ' value=' + '"' + '0' + '"' + '>.<\/button><\/td>' + '\n' + '                    <td><button class=' + '"' + 'bts' + '"' + ' id=' + '"' + 'b11' + '"' + ' onclick=' + '"' + 'main.botaoPressionado(id, ' + vsJarvis + ')' + '"' + ' value=' + '"' + '0' + '"' + '>.<\/button><\/td>' + '\n' + '                    <td><button class=' + '"' + 'bts' + '"' + ' id=' + '"' + 'b12' + '"' + ' onclick=' + '"' + 'main.botaoPressionado(id, ' + vsJarvis + ')' + '"' + ' value=' + '"' + '0' + '"' + '>.<\/button><\/td>' + '\n' + '                <\/tr>' + '\n' + '                <tr>' + '\n' + '                    <td><button class=' + '"' + 'bts' + '"' + ' id=' + '"' + 'b20' + '"' + ' onclick=' + '"' + 'main.botaoPressionado(id, ' + vsJarvis + ')' + '"' + ' value=' + '"' + '0' + '"' + '>.<\/button><\/td>' + '\n' + '                    <td><button class=' + '"' + 'bts' + '"' + ' id=' + '"' + 'b21' + '"' + ' onclick=' + '"' + 'main.botaoPressionado(id, ' + vsJarvis + ')' + '"' + ' value=' + '"' + '0' + '"' + '>.<\/button><\/td>' + '\n' + '                    <td><button class=' + '"' + 'bts' + '"' + ' id=' + '"' + 'b22' + '"' + ' onclick=' + '"' + 'main.botaoPressionado(id, ' + vsJarvis + ')' + '"' + ' value=' + '"' + '0' + '"' + '>.<\/button><\/td>' + '\n' + '                <\/tr>' + '\n' + '            <\/table>' + '\n' + '        <\/html> ' + '\n' + '        ';
    }}
  var testeglobal;
  function botaoPressionado(id, vsJarvis) {
    var tmp$;
    var status = document.getElementById('status');
    var botao = Kotlin.isType(tmp$ = document.getElementById(id), HTMLButtonElement) ? tmp$ : throwCCE();
    var linha = (id.charCodeAt(1) | 0) - 48 | 0;
    var coluna = (id.charCodeAt(2) | 0) - 48 | 0;
    if (botao.disabled === false)
      if (testeglobal === 1) {
        botao.innerHTML = 'X';
        board[linha][coluna] = 1;
        testeglobal = 0;
        botao.disabled = true;
        if (status != null)
          status.innerHTML = 'Vez de jogador 2(O)';
        if (vsJarvis && !fimDeJogo()) {
          jarvis();
        }} else {
        testeglobal = 1;
        botao.innerHTML = 'O';
        board[linha][coluna] = -1;
        botao.disabled = true;
        if (status != null)
          status.innerHTML = 'Vez de jogador 1(X)';
      }
    if (fimDeJogo()) {
      desabilitaBts(0);
      if (status != null) {
        if (contains(status.innerHTML, '!') === false)
          vencedor(linha, coluna, vsJarvis);
      }var restart = document.getElementById('botaoRestart');
      if (restart != null)
        restart.innerHTML = '\n' + '                <button onclick=' + '"' + 'main.resetaBoard(0, 0, ' + vsJarvis + ')' + '"' + '>Jogar novamente<\/button>' + '\n' + '                <button onclick=' + '"' + 'main.resetaBoardGOTOMENU(0, 0)' + '"' + '>Voltar ao menu<\/button>' + '\n' + '            ';
    }}
  var glob;
  function vencedor(linha, coluna, vsJarvis) {
    var status = document.getElementById('status');
    if (status != null) {
      if (verifica()) {
        if (board[linha][coluna] === 1) {
          status.innerHTML = 'O jogador 1 venceu!';
        } else {
          if (!vsJarvis)
            status.innerHTML = 'O jogador 2 venceu!';
          else
            status.innerHTML = 'Jarvis ganhou!!!!!!';
        }
      } else if (verificaVelha(0))
        status.innerHTML = 'Deu Velha!';
    }}
  function resetaBoardGOTOMENU(linha, col) {
    if (col <= 2 && linha <= 2) {
      board[linha][col] = 0;
      resetaBoardGOTOMENU(linha + 1 | 0, col);
    } else if (col <= 2) {
      resetaBoardGOTOMENU(0, col + 1 | 0);
    }reset();
    glob = true;
  }
  function reset() {
    var options = document.getElementById('botoes');
    var tabuleiro = document.getElementById('tabuleiro');
    var status = document.getElementById('status');
    var restart = document.getElementById('botaoRestart');
    if (options != null) {
      options.innerHTML = '\n        <button class="botao" onclick="main.jogar(false); document.getElementById(\'player\').play()">Jogar contra um amigo<\/button>\n        <button class="botao" onclick="main.jogar(true); document.getElementById(\'player\').play()">Jogar contra Jarvis<\/button>\n        ';
    }if (tabuleiro != null)
      tabuleiro.innerHTML = ' ';
    if (status != null)
      status.innerHTML = ' ';
    if (restart != null)
      restart.innerHTML = ' ';
  }
  function resetaBoard(linha, col, vsJarvis) {
    if (col <= 2 && linha <= 2) {
      board[linha][col] = 0;
      resetaBoard(linha + 1 | 0, col, vsJarvis);
    } else if (col <= 2) {
      resetaBoard(0, col + 1 | 0, vsJarvis);
    }jogar(vsJarvis);
    glob = true;
  }
  function fimDeJogo() {
    return verifica() || verificaVelha(0);
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
    var score = 0;
    possiblePositions = verificaEspacos(0, 0, possiblePositions);
    if (possiblePositions.size > 0) {
      var bestMoveID = possiblePositions.get_za3lpa$(0);
      tmp$ = possiblePositions.iterator();
      while (tmp$.hasNext()) {
        var i = tmp$.next();
        if (jarvisBoardAnalysis(i) >= score)
          bestMoveID = i;
      }
      bestMoveID = 'b' + bestMoveID;
      var move = Kotlin.isType(tmp$_0 = document.getElementsByClassName('bts').item(auxiliarCasting(bestMoveID)), HTMLButtonElement) ? tmp$_0 : throwCCE();
      move.click();
    }}
  function auxiliarCasting(s) {
    for (var i = 0; i <= 9; i++) {
      var doc = document.getElementsByClassName('bts').item(i);
      if (doc != null)
        if (equals(doc.id, s))
          return i;
    }
    return 0;
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
    var teste = positionIsVital(linha, col);
    println('Posi\xE7\xE3o ' + linha + ' ' + col + ' \xE9 vital? ' + teste);
    if (linha === 1 && col === 1) {
      score = score + 2 | 0;
      if (positionHasEnemyAtDiagonal(linha, col))
        score = score - 2 | 0;
      if (positionIsVital(linha, col))
        score = score + 4 | 0;
    } else if (linha === col || (linha === 0 && col === 2) || (linha === 2 && col === 0)) {
      score = score + 1 | 0;
      if (positionIsVital(linha, col))
        score = score + 4 | 0;
      if (positionHasEnemyAtDiagonal(linha, col))
        score = score - 2 | 0;
    } else {
      if (positionHasEnemyAtLine(linha, 0) || positionHasEnemyAtColumn(0, col)) {
        if (positionIsVital(linha, col))
          score = score + 4 | 0;
        score = score - 2 | 0;
      }}
    return score;
  }
  function positionIsVital(linha, col) {
    var r1 = contabilizaLinha(linha, 0, 0);
    var r2 = contabilizaColuna(0, col, 0);
    if (r1 === 2 || r2 === -2) {
      return true;
    }if (r2 === 2 || r2 === -2)
      return true;
    return false;
  }
  function contabilizaLinha(linha, col, res) {
    if (col < 2) {
      var x = board[linha][col];
      return contabilizaLinha(linha, col + 1 | 0, x + res | 0);
    }return res;
  }
  function contabilizaColuna(linha, col, res) {
    if (linha < 2) {
      var x = board[linha][col];
      return contabilizaColuna(linha + 1 | 0, col, x + res | 0);
    }return res;
  }
  function positionHasEnemyAtLine(linha, col) {
    if (board[linha][col] === 1)
      return true;
    else if (col < 2)
      return positionHasEnemyAtLine(linha, col + 1 | 0);
    return false;
  }
  function positionHasEnemyAtColumn(linha, col) {
    if (board[linha][col] === 1)
      return true;
    else if (linha < 2)
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
  Object.defineProperty(_, 'glob', {
    get: function () {
      return glob;
    },
    set: function (value) {
      glob = value;
    }
  });
  _.vencedor = vencedor;
  _.resetaBoardGOTOMENU = resetaBoardGOTOMENU;
  _.reset = reset;
  _.resetaBoard = resetaBoard;
  _.fimDeJogo = fimDeJogo;
  _.verifica = verifica;
  _.verificaLinhas = verificaLinhas;
  _.verificaColunas = verificaColunas;
  _.verificaDiagonais = verificaDiagonais;
  _.verificaVelha = verificaVelha;
  _.desabilitaBts = desabilitaBts;
  _.jarvis = jarvis;
  _.auxiliarCasting = auxiliarCasting;
  _.verificaEspacos = verificaEspacos;
  _.jarvisBoardAnalysis = jarvisBoardAnalysis;
  _.positionsIsVital = positionIsVital;
  _.contabilizaLinha = contabilizaLinha;
  _.contabilizaColuna = contabilizaColuna;
  _.positionHasEnemyAtLine = positionHasEnemyAtLine;
  _.positionHasEnemyAtColumn = positionHasEnemyAtColumn;
  _.positionHasEnemyAtDiagonal = positionHasEnemyAtDiagonal;
  board = [[0, 0, 0], [0, 0, 0], [0, 0, 0]];
  testeglobal = 1;
  glob = true;
  Kotlin.defineModule('main', _);
  return _;
}(typeof main === 'undefined' ? {} : main, kotlin);

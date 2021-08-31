if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'main'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'main'.");
}var main = function (_, Kotlin) {
  'use strict';
  var throwCCE = Kotlin.throwCCE;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var equals = Kotlin.equals;
  var print = Kotlin.kotlin.io.print_s8jyv4$;
  var board;
  function jogar() {
    var tabuleiro = document.getElementById('tabuleiro');
    if (tabuleiro != null)
      tabuleiro.innerHTML = '\n        <html>\n        <table>\n                <tr>\n                    <td><button class="bts" id="b00" onclick="main.botaoPressionado(id)" value="0">00<\/button><\/td>\n                    <td><button class="bts" id="b01" onclick="main.botaoPressionado(id)" value="0">01<\/button><\/td>\n                    <td><button class="bts" id="b02" onclick="main.botaoPressionado(id)" value="0">02<\/button><\/td>\n                <\/tr>\n                <tr>\n                    <td><button class="bts" id="b10" onclick="main.botaoPressionado(id)" value="0">10<\/button><\/td>\n                    <td><button class="bts" id="b11" onclick="main.botaoPressionado(id)" value="0">11<\/button><\/td>\n                    <td><button class="bts" id="b12" onclick="main.botaoPressionado(id)" value="0">12<\/button><\/td>\n                <\/tr>\n                <tr>\n                    <td><button class="bts" id="b20" onclick="main.botaoPressionado(id)" value="0">20<\/button><\/td>\n                    <td><button class="bts" id="b21" onclick="main.botaoPressionado(id)" value="0">21<\/button><\/td>\n                    <td><button class="bts" id="b22" onclick="main.botaoPressionado(id)" value="0">22<\/button><\/td>\n                <\/tr>\n            <\/table>\n        <\/html> \n        ';
  }
  var testeglobal;
  function botaoPressionado(id) {
    var tmp$;
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
    var el = document.getElementById('tabuleiro');
    if (verifica()) {
      if (el != null)
        el.innerHTML = 'cabou';
    } else {
      println('OLA');
      if (verificaVelha())
        if (el != null)
          el.innerHTML = 'cabou';
    }
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
  function verificaVelha() {
    for (var i = 0; i <= 9; i++) {
      var doc = document.getElementsByClassName('bts').item(i);
      if (doc != null)
        if (!equals(doc.innerHTML, 'X') && !equals(doc.innerHTML, 'O')) {
          return false;
        }}
    print('DEU VELHA');
    return true;
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
  _.verifica = verifica;
  _.verificaLinhas = verificaLinhas;
  _.verificaColunas = verificaColunas;
  _.verificaDiagonais = verificaDiagonais;
  _.verificaVelha = verificaVelha;
  board = [[0, 0, 0], [0, 0, 0], [0, 0, 0]];
  testeglobal = 1;
  Kotlin.defineModule('main', _);
  return _;
}(typeof main === 'undefined' ? {} : main, kotlin);

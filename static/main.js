if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'main'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'main'.");
}var main = function (_, Kotlin) {
  'use strict';
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  function jogar() {
    println('funfando');
    var tabuleiroArray = [new Int32Array([0, 0, 0]), new Int32Array([0, 0, 0]), new Int32Array([0, 0, 0])];
    var tabuleiro = document.getElementById('tabuleiro');
    var status = document.getElementById('status');
    if (tabuleiro != null)
      tabuleiro.innerHTML = '\n        <html>\n        <table>\n                <tr>\n                    <td><button value="0"><\/button><\/td>\n                    <td><button value="0"><\/button><\/td>\n                    <td><button value="0"><\/button><\/td>\n                <\/tr>\n                <tr>\n                    <td><button value="0"><\/button><\/td>\n                    <td><button value="0"><\/button><\/td>\n                    <td><button value="0"><\/button><\/td>\n                <\/tr>\n                <tr>\n                    <td><button value="0"><\/button><\/td>\n                    <td><button value="0"><\/button><\/td>\n                    <td><button value="0"><\/button><\/td>\n                <\/tr>\n            <\/table>\n        <\/html> \n        ';
  }
  function printa() {
  }
  _.jogar = jogar;
  _.printa = printa;
  Kotlin.defineModule('main', _);
  return _;
}(typeof main === 'undefined' ? {} : main, kotlin);

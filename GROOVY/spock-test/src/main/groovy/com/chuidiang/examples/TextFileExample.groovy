package com.chuidiang.examples

// Abir un fichero para escribir en el.
def file = new File('theFile.txt')

file.write "Hello world!\n"
file << "Another line\n"
file << "eooooo!!\n"
file << '''En 
un
lugar de 
la Mancha ...'''

// Lectura de golpe
println "Leyendo todo de golpe"
def lines = file.readLines()
lines.each {  println it }
println ""

// Lectura poco a poco
println "Leyendo de uno en uno"
file.eachLine { line -> println line }
println ""

// Borrado
file.deleteOnExit()
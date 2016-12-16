package com.chuidiang.examples

def file = new File("byteFile.dat")

file.setBytes([1,2,3,4] as byte[])
file << ([3,3,3,3] as byte[])
file.append([4,4] as byte[])

// como Array
def bytes = file.bytes
println bytes

// De uno en uno
file.eachByte { b -> println b}

// Borrado
file.deleteOnExit()
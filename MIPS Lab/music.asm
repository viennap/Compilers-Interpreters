# This program plays the iconic melody from Für Elise by Beethoven.  
# @author	Vienna Parnell
# @version	05.10.2022

.data 
.text 
.globl main

main:
li $v0 33 # Loads MIDI out 
syscall

li $a2 0 # Piano
li $a3 100 # sets the tone's volume to 100 MIDI velocity
syscall

li $a0 76 # E
li $a1 250 # 300 ms
syscall
li $a0 75 # D#
li $a1 250 # 300 ms
syscall
li $a0 76 # E
li $a1 250 # 300 ms
syscall
li $a0 75 # D#
li $a1 250 # 300 ms
syscall
li $a0 76 # E
li $a1 250 # 300 ms
syscall
li $a0 71 # B
li $a1 250 # 300 ms
syscall
li $a0 74 # D
li $a1 250 # 300 ms
syscall
li $a0 72 # C
li $a1 250 # 300 ms
syscall
li $a0 69 # A
li $a1 900 # 300 ms
syscall

li $a0 60 # C
li $a1 250 # 300 ms
syscall
li $a0 64 # E
li $a1 250 # 300 ms
syscall
li $a0 69 # A
li $a1 250 # 300 ms
syscall
li $a0 71 # B
li $a1 850 # 300 ms
syscall

li $a0 64 # E
li $a1 250 # 300 ms
syscall
li $a0 69 # A
li $a1 250 # 300 ms
syscall
li $a0 71 # B
li $a1 250 # 300 ms
syscall
li $a0 72 # C
li $a1 900 # 300 ms
syscall

li $a0 64 # E
li $a1 250 # 300 ms
syscall

li $a0 76 # E
li $a1 250 # 300 ms
syscall
li $a0 75 # D#
li $a1 250 # 300 ms
syscall
li $a0 76 # E
li $a1 250 # 300 ms
syscall
li $a0 75 # D#
li $a1 250 # 300 ms
syscall
li $a0 76 # E
li $a1 250 # 300 ms
syscall
li $a0 71 # B
li $a1 250 # 300 ms
syscall
li $a0 74 # D
li $a1 250 # 300 ms
syscall
li $a0 72 # C
li $a1 250 # 300 ms
syscall
li $a0 69 # A
li $a1 900 # 300 ms
syscall

li $a0 60 # C
li $a1 250 # 300 ms
syscall
li $a0 64 # E
li $a1 250 # 300 ms
syscall
li $a0 69 # A
li $a1 250 # 300 ms
syscall
li $a0 71 # B
li $a1 850 # 300 ms
syscall


li $a0 64 # E
li $a1 250 # 300 ms
syscall
li $a0 72 # C
li $a1 250 # 300 ms
syscall
li $a0 71 # B
li $a1 259 # 300 ms
syscall
li $a0 69 # A
li $a1 850 # 300 ms
syscall

li $v0, 10  # normal termination 
syscall # system call

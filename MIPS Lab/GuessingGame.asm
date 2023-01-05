# This program simulates a guessing game in which the user guesses a randomly generated number 
# within the range of 0, inclusive to 100, exclusive. 
# @author	Vienna Parnell
# @version 	5.8.2022

.data
	askGuess: 	.asciiz 	"Guess a number. \n"
	tooLow:		.asciiz		"Guess is too low. Again..." 
	tooHigh:	.asciiz		"Guess is too high. Again..."
	correct:	.asciiz		"Guess is correct. \n" 
.text
.globl main

main:
	li $a1, 101 
	li $v0, 42 # Generate number in range of 0 to 100, inclusive. 
	syscall	
	
	# Moves randomly generated number into $k0 register. 
	move $k0, $a0
loop:
 	# Prints askGuess prompt. 
    	la $a0, askGuess
    	li $v0, 4
    	syscall
    	
    	# Reads in inputted guessed number. 
	li $v0, 5
	syscall
	
	move $t0, $v0
    	
    	beq $t0, $k0, exit
    	bgt $t0, $k0, high
    	j low
   
high:
	# Prints too high.
	la $a0, tooHigh
	li $v0, 4
	syscall	
	j loop
	
low:
	# Prints too low. 
	la $a0, tooLow
	li $v0, 4
	syscall
	j loop

exit:
	# Prints  ending. 
	la $a0, correct
	li $v0, 4
	syscall
	
	# Terminates program. 
	li $v0, 10
	syscall
